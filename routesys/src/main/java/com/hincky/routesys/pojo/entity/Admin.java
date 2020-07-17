package com.hincky.routesys.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * admin
 * @author 
 */
@Data
public class Admin implements Serializable {
    private Integer adminId;

    private String password;

    private String adminName;

    private static final long serialVersionUID = 1L;

}