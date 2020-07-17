package com.hincky.routesys.service;

import com.hincky.routesys.pojo.entity.UserOrder;
import com.hincky.routesys.pojo.query.Point;
import com.hincky.routesys.pojo.query.Pot;
import com.hincky.routesys.pojo.vo.MapUtil;

import java.util.List;

public interface UserOrderService {

    boolean insertUserOrder(MapUtil mapUtil,int userId);

    List<UserOrder> getDataByUserId(Integer page, Integer limit,int userId);

    List<UserOrder> getData(Integer page, Integer limit);

    Point changeIntoPoint(String pointStr);

    Pot changeIntoPot(String potStr);

}
