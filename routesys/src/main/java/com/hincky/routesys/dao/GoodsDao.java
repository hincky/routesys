package com.hincky.routesys.dao;

import com.hincky.routesys.pojo.entity.Goods;

import java.util.List;

public interface GoodsDao {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> findAll();
}