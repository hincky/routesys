package com.hincky.routesys.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * vehicle
 * @author 
 */
@Data
public class Vehicle implements Serializable {

    private Integer vehicleId;

    private String type;

    private String oilType;

    private Integer maxMile;

    private Float maxLoad;

    private Integer maxSpeed;

    private String status;

    private static final long serialVersionUID = 1L;


}