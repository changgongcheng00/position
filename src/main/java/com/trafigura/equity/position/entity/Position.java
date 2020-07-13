package com.trafigura.equity.position.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * position
 * @author 
 */
@Data
public class Position implements Serializable {
    private Integer id;

    private String securityCode;

    private Integer quantity;

    private Integer tableSize;

    private static final long serialVersionUID = 1L;
}