package com.hincky.routesys.controller;

import com.github.pagehelper.PageInfo;
import com.hincky.routesys.dao.ShopcarDao;
import com.hincky.routesys.pojo.entity.Goods;
import com.hincky.routesys.pojo.entity.Shopcar;
import com.hincky.routesys.service.ShopcarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class ShopcarController {
    @Resource
    ShopcarDao shopcarDao;
    @Resource
    ShopcarService shopcarService;

    @ResponseBody //自动返回json格式的数据
    @RequestMapping(value="/getShopCarPage",method= RequestMethod.GET)
    public Map<String, Object> getPage(//page、limit参数都是layui的table组件默认自动传递的，我们只需接收即可
                                        HttpServletRequest request,
                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "limit",defaultValue = "5") Integer limit){
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(session.getAttribute("userId").toString());
        System.out.println("用户id为："+userId);
        List<Shopcar> list = shopcarService.getDataByUserId(page, limit,userId);//执行分页查询的方法
        int count = shopcarDao.findAllByUserId(userId).size();
        PageInfo<Shopcar> pageInfo = new PageInfo<>(list);
        System.out.println("购物车内容条数为：" + count);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", count);
        map.put("data", pageInfo.getList());//最最最关键的代码，layui的table会自动获取并显示该数据集
        return map;
    }


    @RequestMapping(value = "/addToShopCar",method = RequestMethod.POST)
    public String addToShopCar(@RequestBody Goods goods,HttpServletRequest request){
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(session.getAttribute("userId").toString());
        System.out.println("用户id为："+userId);
        if (shopcarService.insertShopCarInfo(goods,userId)){
            return "redirect: /getShopCarPage";
        }
        return "/getShopCarPage";
    }

    @RequestMapping(value = "/deleteShopCar",method = RequestMethod.POST)
    public String deleteShopCar(@RequestBody int shopcarid){
        shopcarDao.deleteByPrimaryKey(shopcarid);
        return "redirect: /shopCar";
//        return "/member/shopCarPage";
    }

    @RequestMapping(value = "/updateShopCar",method = RequestMethod.GET)
    public String updateShopCar(@RequestBody int shopcarid,@RequestBody int count){
        shopcarService.updateCountByShopCarId(shopcarid,count);
        return "/member/shopCarPage";
    }



}
