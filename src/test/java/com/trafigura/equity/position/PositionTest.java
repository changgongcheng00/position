package com.trafigura.equity.position;

import com.trafigura.equity.position.controller.PositionController;
import com.trafigura.equity.position.controller.TransactionsController;
import com.trafigura.equity.position.dto.PositionDto;
import com.trafigura.equity.position.dto.TradeAddDto;
import com.trafigura.equity.position.service.TransactionsServices;
import com.trafigura.equity.position.service.impl.TransactionsServicesImpl;
import com.trafigura.equity.position.util.ResponseData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.lang.System.out;

/**
 * @ClassName PositionTest
 * @Description TODO 补充数据测试
 * @Author cheng
 * @Date 2020/7/13 16:00
 **/
@SpringBootTest
public class PositionTest {

    @Autowired
    private TransactionsController transactionsController;
    @Autowired
    private PositionController positionController;

//    @BeforeEach
    @Test
    public void makeData(){
        TradeAddDto tradeAddDto = null;
        tradeAddDto = new TradeAddDto("REL", 50, "INSERT", "Buy");
        transactionsController.transactionsAdd(tradeAddDto);
        tradeAddDto = new TradeAddDto("ITC", 40, "INSERT", "Sell");
        transactionsController.transactionsAdd(tradeAddDto);
        tradeAddDto = new TradeAddDto("INF", 70, "INSERT", "Buy");
        transactionsController.transactionsAdd(tradeAddDto);
        tradeAddDto = new TradeAddDto("REL", 60, "UPDATE", "Buy");
        transactionsController.transactionsAdd(tradeAddDto);
        tradeAddDto = new TradeAddDto("ITC", 30, "CANCEL", "Buy");
        transactionsController.transactionsAdd(tradeAddDto);
        tradeAddDto = new TradeAddDto("INF", 20, "INSERT", "Sell");
        transactionsController.transactionsAdd(tradeAddDto);
    }

    @Test
    public void positionTest(){
        transactionsController.dealEquity();
    }

    @Test
    public void positionListTest(){
        ResponseData<PositionDto> positionList = positionController.getPositionList(1, 10);
        positionList.getResult().getData().stream().forEach(p->out.println(p));
    }

}
