package com.hincky.routesys.service;

import com.hincky.routesys.pojo.entity.Goods;
import com.hincky.routesys.pojo.entity.Shopcar;

import java.util.List;

public interface ShopcarService {

    List<Shopcar> getData(Integer page, Integer limit);

    List<Shopcar> getDataByUserId(Integer page, Integer limit,int userId);

    boolean insertShopCarInfo(Goods goods,int userId);

    boolean insertShopCar(Goods goods);

    boolean exitShopCarInfo(Goods goods);

    float getTotalPrice(int userId);

    float getTotalWeight(int userId);

    boolean updateCountByShopCarId(int shopcarid, int count);
}
