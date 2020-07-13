package com.trafigura.equity.position.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * transactions
 * @author 
 */
@ApiModel("position info")
@Data
public class TransactionsDto implements Serializable {

    @ApiModelProperty("securityCode")
    private String securityCode;
    @ApiModelProperty("quantity")
    private Integer quantity;

    private static final long serialVersionUID = 1L;
}