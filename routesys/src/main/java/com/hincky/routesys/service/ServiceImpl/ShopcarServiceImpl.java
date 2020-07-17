package com.hincky.routesys.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.hincky.routesys.dao.ShopcarDao;
import com.hincky.routesys.pojo.entity.Goods;
import com.hincky.routesys.pojo.entity.Shopcar;
import com.hincky.routesys.service.ShopcarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShopcarServiceImpl implements ShopcarService {
    @Resource
    ShopcarDao shopcarDao;

    @Override
    public boolean insertShopCarInfo(Goods goods,int userId) {
        Shopcar shopcar = new Shopcar();
        System.out.println(goods);                                  //
        shopcar.setGoodsName(goods.getGoodsName());                 //
        shopcar.setPrice(goods.getPrice());                         //
        shopcar.setCount(1);                                        //
        shopcar.setTotal(shopcar.getPrice()*shopcar.getCount());    //
        shopcar.setUserId(userId);                                  //
        shopcar.setGoodsId(goods.getGoodsId());                     //
        shopcar.setWeight(goods.getWeight());                       //

        System.out.println(shopcar);

        System.out.println();
        if (shopcarDao.insert(shopcar)!=0){
            return true;
        }else {
            return false;
        }
//        return true;
    }

    @Override
    public boolean insertShopCar(Goods goods) {
//
        Shopcar shopcar = new Shopcar();
        System.out.println(goods);
        shopcar.setGoodsName(goods.getGoodsName());
        shopcar.setPrice(goods.getPrice());
        shopcar.setCount(1);
        shopcar.setTotal(shopcar.getPrice()*shopcar.getCount());
        shopcar.setWeight(goods.getWeight());
        shopcar.setGoodsId(goods.getGoodsId());

        if(shopcarDao.insert(shopcar)==1){
        System.out.println(shopcar);
        return true;
        }
        return false;
    }

    @Override
    public boolean exitShopCarInfo(Goods goods) {
        goods.getGoodsName();
        return false;
    }

    @Override
    public float getTotalPrice(int userId) {
        List<Shopcar> shopcarList = shopcarDao.findAllByUserId(userId);
        float result = 0;
        for (int i=0;i<shopcarList.size();i++){
            result += shopcarList.get(i).getCount() * shopcarList.get(i).getPrice();
        }
        return result;
    }

    @Override
    public float getTotalWeight(int userId) {
        List<Shopcar> shopcarList = shopcarDao.findAllByUserId(userId);
        float result = 0;
        for (int i=0;i<shopcarList.size();i++){
            result += shopcarList.get(i).getCount() * shopcarList.get(i).getWeight();//
        }
        return result;
    }

    @Override
    public boolean updateCountByShopCarId(int shopcarid, int count) {
        System.out.println(shopcarid+','+count);
        return true;
    }

    @Override
    public List<Shopcar> getData(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Shopcar> shopcarList  = shopcarDao.findAll();
        return shopcarList;
    }

    @Override
    public List<Shopcar> getDataByUserId(Integer page, Integer limit, int userId) {
        PageHelper.startPage(page, limit);
        List<Shopcar> shopcarList  = shopcarDao.findAllByUserId(userId);
        return shopcarList;
    }

}
