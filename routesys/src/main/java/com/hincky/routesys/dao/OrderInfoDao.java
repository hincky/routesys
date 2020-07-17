package com.hincky.routesys.dao;

import com.hincky.routesys.pojo.entity.OrderInfo;

public interface OrderInfoDao {
    int deleteByPrimaryKey(Integer orderInfoId);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer orderInfoId);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);
}