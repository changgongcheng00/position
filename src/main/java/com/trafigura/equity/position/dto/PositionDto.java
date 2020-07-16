package com.trafigura.equity.position.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName PositionDto
 * @Description TODO
 * @Author cheng
 * @Date 2020/7/16 17:34
 **/
@Data
@ApiModel("PositionDto info")
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto implements Serializable {

    @ApiModelProperty("securityCode")
    private String securityCode;

    @ApiModelProperty("quantity")
    private Integer quantity;
}
