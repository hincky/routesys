package com.hincky.routesys.pojo.dto;

import lombok.Data;

@Data
public class UserOrderDTO {
    private int OrderId;
    private double orderWeight;
    private String timeWindow1;
    private String timeWindow2;
    private String point;
    private String destination;
}
