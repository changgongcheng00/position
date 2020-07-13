package com.trafigura.equity.position.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * transactions
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transactions implements Serializable {
    private Integer transactionId;

    private Integer tradeId;

    private Integer version;

    private String securityCode;

    private Integer quantity;

    /**
     * insert/update/cancel
     */
    private String operation;

    /**
     * buy/sell
     */
    private String deal;

    private static final long serialVersionUID = 1L;
}