package com.hincky.routesys.controller;

import com.hincky.routesys.dao.ShopcarDao;
import com.hincky.routesys.pojo.entity.Shopcar;
import com.hincky.routesys.service.ShopcarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
public class IndexController {

    @RequestMapping("loginPage")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/")
    public String setPage(){
        return "login";
    }

    @RequestMapping("/welcome")
    public String welcomePage(){
        return "welcome";
    }

    @RequestMapping("/registerPage")
    public String registerPage(){
        return "register";
    }

    @RequestMapping("/goodsAddPage")
    public String goodAddPage(){
        return "/goods/good-add";
    }

    @RequestMapping("/goodsList")
    public String goodsList(){
        return "/goods/good-list";
    }

    @RequestMapping("/mapTestPage")
    public String mapTest(){return "/test";}

    @Resource
    ShopcarDao shopcarDao;
    @Resource
    ShopcarService shopcarService;

    @RequestMapping("/test")
    public void test(){
        List<Shopcar> shopcarList  = shopcarDao.findAllByUserId(1001);//userId=1001   查询出所有当前用户的商品

//        String[] str = new String[shopcarList.size()];//创建数组用于保存商品信息

//        //逐一将每个商品信息作为一项赋值给数组
//        for (int i = 0; i < shopcarList.size(); i++){ str[i] = String.valueOf(shopcarList.get(i)); }

//        ArrayList<String> arrayList= new ArrayList<>(Arrays.asList(str));//将数组转化为string类型的list
        System.out.println(shopcarService.getTotalPrice(1001));
    }

}
