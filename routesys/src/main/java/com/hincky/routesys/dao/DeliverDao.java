package com.hincky.routesys.dao;

import com.hincky.routesys.pojo.entity.Deliver;

public interface DeliverDao {
    int deleteByPrimaryKey(Integer deliverId);

    int insert(Deliver record);

    int insertSelective(Deliver record);

    Deliver selectByPrimaryKey(Integer deliverId);

    int updateByPrimaryKeySelective(Deliver record);

    int updateByPrimaryKey(Deliver record);

    int countDeliver();
}