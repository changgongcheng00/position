package com.trafigura.equity.position.dao;

import com.trafigura.equity.position.entity.Transactions;

import java.util.List;

public interface TransactionsDao {

    int insert(Transactions record);

    List<Transactions> list();

    Transactions selectByPrimaryKey(Integer transactionId);

    int selectLatestVersion(String securityCode);

    int selectLatestTradeid(String securityCode);

}