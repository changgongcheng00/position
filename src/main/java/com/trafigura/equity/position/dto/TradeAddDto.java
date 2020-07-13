package com.trafigura.equity.position.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * transactions
 * @author 
 */
@ApiModel("TradeAddDto info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeAddDto implements Serializable {

    @ApiModelProperty("securityCode")
    private String securityCode;
    @ApiModelProperty("quantity")
    private Integer quantity;
    @ApiModelProperty("operation")
    private String operation;
    @ApiModelProperty("deal")
    private String deal;

    private static final long serialVersionUID = 1L;
}