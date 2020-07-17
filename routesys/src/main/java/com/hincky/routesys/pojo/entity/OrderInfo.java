package com.hincky.routesys.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * order_info
 * @author 
 */
@Data
public class OrderInfo implements Serializable {
    private Integer orderInfoId;

    private String orderId;

    private Integer originGoodId;

    private BigDecimal price;

    private Integer count;

    private static final long serialVersionUID = 1L;

}