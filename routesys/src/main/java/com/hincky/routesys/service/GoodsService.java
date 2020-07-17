package com.hincky.routesys.service;

import com.hincky.routesys.pojo.entity.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> getData(Integer page, Integer limit);


}
