package com.hincky.routesys.dao;

import com.hincky.routesys.pojo.dto.Page;
import com.hincky.routesys.pojo.entity.Vehicle;

import java.util.List;

public interface VehicleDao {
    int deleteByPrimaryKey(Integer vehicleId);

    int insert(Vehicle record);

    int insertSelective(Vehicle record);

    Vehicle selectByPrimaryKey(Integer vehicleId);

    int updateByPrimaryKeySelective(Vehicle record);

    int updateByPrimaryKey(Vehicle record);

    List<Vehicle> findAll();

}