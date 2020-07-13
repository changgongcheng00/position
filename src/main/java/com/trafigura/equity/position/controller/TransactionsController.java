package com.trafigura.equity.position.controller;

import com.trafigura.equity.position.dto.TradeAddDto;
import com.trafigura.equity.position.dto.TransactionsDto;
import com.trafigura.equity.position.service.TransactionsServices;
import com.trafigura.equity.position.util.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName TransactionsController
 * @Description TODO
 * @Author cheng
 * @Date 2020/7/12 23:11
 **/
@Api(tags = "TransactionsController")
@RestController
public class TransactionsController {

    Logger logger = LoggerFactory.getLogger(TransactionsController.class);

    @Autowired
    private TransactionsServices transactionsServices;

    @ApiOperation(value = "test db list")
    @GetMapping("/list")
    public List<TransactionsDto> list(){
        logger.info("[TransactionsController - list] test db connection");
        return transactionsServices.list();
    }

    @PostMapping("/transactionsAdd")
    public ResponseData transactionsAdd(TradeAddDto tradeAddDto){
        return transactionsServices.transactionsAdd(tradeAddDto);
    }

    @GetMapping("/dealEquity")
    public ResponseData dealEquity(){
        return transactionsServices.dealEquity();
    }
}
