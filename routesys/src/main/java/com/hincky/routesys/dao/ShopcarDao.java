package com.hincky.routesys.dao;

import com.hincky.routesys.pojo.entity.Shopcar;

import java.util.List;

public interface ShopcarDao {
    int deleteByPrimaryKey(Integer shopCarId);

    int insert(Shopcar record);

    int insertSelective(Shopcar record);

    Shopcar selectByPrimaryKey(Integer shopCarId);

    int updateByPrimaryKeySelective(Shopcar record);

    int updateByPrimaryKey(Shopcar record);

    List<Shopcar> findAll();

    List<Shopcar> findAllByUserId(Integer userId);
}