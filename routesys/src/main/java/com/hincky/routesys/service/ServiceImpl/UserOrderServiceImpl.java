package com.hincky.routesys.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.hincky.routesys.dao.ShopcarDao;
import com.hincky.routesys.dao.UserDao;
import com.hincky.routesys.dao.UserOrderDao;
import com.hincky.routesys.pojo.entity.Shopcar;
import com.hincky.routesys.pojo.entity.User;
import com.hincky.routesys.pojo.entity.UserOrder;
import com.hincky.routesys.pojo.query.Point;
import com.hincky.routesys.pojo.query.Pot;
import com.hincky.routesys.pojo.vo.MapUtil;
import com.hincky.routesys.service.ShopcarService;
import com.hincky.routesys.service.UserOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UserOrderServiceImpl implements UserOrderService {
    @Resource
    UserDao userDao;
    @Resource
    ShopcarDao shopcarDao;
    @Resource
    ShopcarService shopcarService;
    @Resource
    UserOrderDao userOrderDao;

    /**
     * 往数据库添加用户订单
     * @param mapUtil
     * @param userId
     * @return
     */
    @Override
    public boolean insertUserOrder(MapUtil mapUtil, int userId) {
        double weight = shopcarService.getTotalWeight(userId);

        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(userId); //userId
        userOrder.setAllCost(shopcarService.getTotalPrice(userId));//allCost

        Date date = new Date(); //createTime
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        userOrder.setCreateTime(sdf.format(date));

        User user = userDao.selectByPrimaryKey(userId);
        userOrder.setReceiverPhone(user.getPhone());    //receiverPhone
        userOrder.setReceiverName(user.getRealName());  //receiverName
        userOrder.setOrderWeight(weight);//receiverAddress
        userOrder.setTimeWindow1(mapUtil.getTimeWindow1());//时间窗1
        userOrder.setTimeWindow2(mapUtil.getTimeWindow2());//时间窗2
        userOrder.setStatus("待配送");                  //status
        userOrder.setEarliestAccTime(mapUtil.getTimeWindow1().substring(0,8));//最早时间
        userOrder.setLatestAccTime(mapUtil.getTimeWindow2().substring(11,19));//最迟时间

//        String lat = String.format(mapUtil.getLat(), 9);
//        NumberFormat
        String point = mapUtil.getLng()+","+mapUtil.getLat();//合并经纬度
        userOrder.setPoint(point);//point
        userOrder.setDestination(mapUtil.getAddress());//用来记录坐标的收货具体地址

        //details
        List<Shopcar> shopcarList  = shopcarDao.findAllByUserId(userId);//查询出所有当前用户的商品
        String[] str = new String[shopcarList.size()];//创建数组用于保存商品信息
        for (int i = 0; i < shopcarList.size(); i++){  //逐一将每个商品信息作为一项赋值给数组
            str[i] = String.valueOf(shopcarList.get(i));
        }
        ArrayList<String> arrayList= new ArrayList<>(Arrays.asList(str));//将数组转化为string类型的list
        String shopcarDetail = String.valueOf(arrayList);
        userOrder.setDetails(shopcarDetail);
//        System.out.println(userOrder);

        if(userOrderDao.insert(userOrder)==1){
            System.out.println("---------成功添加一条记录---------");
            return true;
        }else {
        return false;}
    }

    @Override
    public List<UserOrder> getDataByUserId(Integer page, Integer limit,int userId) {
        PageHelper.startPage(page, limit);
        List<UserOrder> userOrderList = userOrderDao.findAllByUserId(userId);
        return userOrderList;

    }

    @Override
    public List<UserOrder> getData(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<UserOrder> userOrderList = userOrderDao.findAll();
        return userOrderList;
    }


    @Override
    public Point changeIntoPoint(String pointStr) {
        Point point = new Point();
        double lng = Double.valueOf(pointStr.substring(0,10));//经度
        double lat = Double.valueOf(pointStr.substring(11,20));//纬度
        point.setLng(lng);
        point.setLat(lat);
        return point;
    }

    @Override
    public Pot changeIntoPot(String pointStr) {
        Pot point = new Pot();
        double lng = Double.valueOf(pointStr.substring(0,10));//经度
        double lat = Double.valueOf(pointStr.substring(11,20));//纬度
        point.setLng(lng);
        point.setLat(lat);
        return point;
    }


}
