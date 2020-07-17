package com.hincky.routesys.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hincky.routesys.dao.VehicleDao;
import com.hincky.routesys.pojo.dto.Page;
import com.hincky.routesys.pojo.entity.Vehicle;
import com.hincky.routesys.service.VehicleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Resource
    VehicleDao vehicleDao;

    @Override
    public List<Vehicle> getData(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Vehicle> vehicleList  = vehicleDao.findAll();
        return vehicleList;
    }

}
