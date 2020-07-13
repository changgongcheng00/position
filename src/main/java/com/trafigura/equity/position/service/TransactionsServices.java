package com.trafigura.equity.position.service;

import com.trafigura.equity.position.dto.TradeAddDto;
import com.trafigura.equity.position.dto.TransactionsDto;
import com.trafigura.equity.position.util.ResponseData;

import java.util.List;

public interface TransactionsServices {

    List<TransactionsDto> list();

    ResponseData transactionsAdd(TradeAddDto tradeAddDto);

    ResponseData dealEquity();
}
