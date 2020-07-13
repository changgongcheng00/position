package com.trafigura.equity.position.service.impl;

import com.trafigura.equity.position.dao.PositionDao;
import com.trafigura.equity.position.dao.TransactionsDao;
import com.trafigura.equity.position.dto.TradeAddDto;
import com.trafigura.equity.position.dto.TransactionsDto;
import com.trafigura.equity.position.entity.Position;
import com.trafigura.equity.position.entity.Transactions;
import com.trafigura.equity.position.service.TransactionsServices;
import com.trafigura.equity.position.util.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName TransactionsServicesImpl
 * @Description TODO
 * @Author cheng
 * @Date 2020/7/12 23:10
 **/
@Service
public class TransactionsServicesImpl implements TransactionsServices {

    Logger logger = LoggerFactory.getLogger(TransactionsServicesImpl.class);

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;

    private static final String TRANSACTION_KEY = "transactions";

    @Autowired
    private TransactionsDao transactionsDao;
    @Autowired
    private PositionDao positionDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public List<TransactionsDto> list() {
        List<Transactions> transactionsList = transactionsDao.list();
        List<TransactionsDto> tdList = new ArrayList<>();
        for (Transactions transactions : transactionsList) {
            TransactionsDto td = new TransactionsDto();
            BeanUtils.copyProperties(transactions,td);
            tdList.add(td);
        }
        return tdList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseData transactionsAdd(TradeAddDto tradeAddDto) {
        if(StringUtils.isEmpty(tradeAddDto.getSecurityCode()) || StringUtils.isEmpty(
            tradeAddDto.getOperation()) || StringUtils.isEmpty(tradeAddDto.getDeal())){
            return ResponseData.error(500,"参数异常:请输入正确的SecurityCode[REL/ITC/INF] " +
                "OperationCode[INSERT/UPDATE/CANCEL] deal[Buy/Sell]");
        }

        try {
            Transactions transactions = new Transactions();
            BeanUtils.copyProperties(tradeAddDto,transactions);
            //tradeId 字段生产
            if("INSERT".equalsIgnoreCase(tradeAddDto.getOperation())){
                int tradeid = transactionsDao.selectLatestTradeid("");
                transactions.setTradeId(tradeid+1);
            }else{
                int tradeid = transactionsDao.selectLatestTradeid(tradeAddDto.getSecurityCode());
                transactions.setTradeId(tradeid);
            }
            //version 字段生产
            int version = transactionsDao.selectLatestVersion(tradeAddDto.getSecurityCode());
            Jedis jedis = new Jedis(host,port);
            jedis.auth(password);
            if(jedis.hexists(tradeAddDto.getSecurityCode(),tradeAddDto.getOperation())){
                transactions.setVersion(version);
            }else{
                jedis.hset(tradeAddDto.getSecurityCode(),tradeAddDto.getOperation(),"1");
                transactions.setVersion(version+1);
            }
            //positions data build
            transactionsDao.insert(transactions);
        } catch (Exception e) {
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("error TransactionsServicesImpl - transactionsAdd : "+e.getMessage());
            return ResponseData.error(500,"系统错误:新增transactions异常");
        }
        return ResponseData.success();
    }

    @Override
    public ResponseData dealEquity(){
        try {
            List<Transactions> list = transactionsDao.list();
            Map<String, List<Transactions>> collect = list.stream().collect(Collectors.groupingBy(Transactions::getSecurityCode));
            //分组插入，cancel指定事务回滚
            for (Map.Entry<String, List<Transactions>> stringListEntry : collect.entrySet()) {
                List<Transactions> trList = stringListEntry.getValue();
                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                        for (Transactions transactions : trList) {
                            switch (transactions.getOperation()){
                                case "INSERT":
                                    //取出 运算 放入
                                    Position position = positionDao.getBySecurityCode(transactions.getSecurityCode());
                                    int quantity = 0;
                                    if(position != null && position.getQuantity() != null){
                                        quantity = position.getQuantity();
                                    }else{
                                        position = new Position();
                                        position.setSecurityCode(transactions.getSecurityCode());
                                        position.setQuantity(quantity);
                                    }
                                    if("Buy".equalsIgnoreCase(transactions.getDeal())){
                                        position.setQuantity(quantity+transactions.getQuantity());
                                    }else{
                                        position.setQuantity(quantity-transactions.getQuantity());
                                    }
                                    positionDao.saveOrUpdate(position);
                                    break;
                                case "UPDATE":
                                    //放入
                                    Position p = new Position();
                                    p.setSecurityCode(transactions.getSecurityCode());
                                    if("Buy".equalsIgnoreCase(transactions.getDeal())){
                                        p.setQuantity(transactions.getQuantity());
                                    }else{
                                        p.setQuantity(0-transactions.getQuantity());
                                    }
                                    positionDao.saveOrUpdate(p);
                                    break;
                                case "CANCEL":
                                    //回滚
                                    transactionStatus.setRollbackOnly();
                                    break;

                            }
                        }
                        //事务阶段提交
                        transactionStatus.flush();
                    }

                });
            }
        } catch (TransactionException e) {
            logger.error("service error TransactionsServicesImpl - dealEquity :"+e.getMessage());
            return ResponseData.error(500,"业务异常");
        }
        return ResponseData.success();
    }

}
