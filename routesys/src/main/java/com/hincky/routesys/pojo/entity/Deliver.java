package com.hincky.routesys.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * deliver
 * @author 
 */
@Data
public class Deliver implements Serializable {
    private Integer deliverId;

    private String password;

    private String deliverName;

    private String phone;

    private static final long serialVersionUID = 1L;

}