package com.hincky.routesys.service;

import com.hincky.routesys.pojo.entity.Vehicle;

import java.util.List;

public interface VehicleService {


    List<Vehicle> getData(Integer page, Integer limit);

}
