package com.hincky.routesys.dao;

import com.hincky.routesys.pojo.dto.UserOrderDTO;
import com.hincky.routesys.pojo.entity.UserOrder;

import java.util.List;

public interface UserOrderDao {

    int deleteByPrimaryKey(Integer orderId);

    int insert(UserOrder record);

    int insertSelective(UserOrder record);

    UserOrder selectByPrimaryKey(Integer orderId);

    UserOrder selectByUserIdAndPoint(int userId, String point);

    int updateByPrimaryKeySelective(UserOrder record);

    int updateByPrimaryKeyWithBLOBs(UserOrder record);

    int updateByPrimaryKey(UserOrder record);

    List<UserOrder> findAllByUserId(int userId);

    List<UserOrder> findAll();

    List<UserOrderDTO> getDTO();
}