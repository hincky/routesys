package com.hincky.routesys.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.hincky.routesys.dao.GoodsDao;
import com.hincky.routesys.pojo.entity.Goods;
import com.hincky.routesys.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    GoodsDao goodsDao;

    @Override
    public List<Goods> getData(Integer page, Integer limit) {

        PageHelper.startPage(page, limit);
        List<Goods> vehicleList  = goodsDao.findAll();
        return vehicleList;
    }

}
