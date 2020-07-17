package com.hincky.routesys.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.hincky.routesys.dao.DeliverDao;
import com.hincky.routesys.dao.GoodsDao;
import com.hincky.routesys.dao.UserOrderDao;
import com.hincky.routesys.pojo.dto.AcaDTO;
import com.hincky.routesys.pojo.entity.Goods;
import com.hincky.routesys.pojo.entity.UserOrder;
import com.hincky.routesys.pojo.entity.Vehicle;
import com.hincky.routesys.pojo.query.Point;
import com.hincky.routesys.pojo.query.Pot;
import com.hincky.routesys.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class DeliverController {
    @Resource
    DeliverService deliverService;
    @Resource
    DeliverDao deliverDao;
    @Resource
    UserOrderDao userOrderDao;
    @Resource
    UserOrderService userOrderService;
    @Resource
    GoodsService goodsService;
    @Resource
    GoodsDao goodsDao;
    @Resource
    RouteService routeService;
    @Resource
    VehicleService vehicleService;


    @RequestMapping("/deliver/index")
    public String DeliverLogin(){
        return "/deliver/index";
    }

    @RequestMapping("/routeTestDemo")
    public String routeTestDemo(){ return "/deliver/routeTestDemo"; }

//    @ResponseBody //自动返回json格式的数据

//    public Map<String,Object> getPointsList(){
//         ArrayList<Point> pointsList = routeService.initialPointsWithArrayList();
//         Map<String,Object> map = new HashMap<>();
//         map.put("pointsList",pointsList);
//         return map;
//    }
    @RequestMapping(value="/getPointsList")
    public String getPointsList(HttpSession session){
        ArrayList<Point> list  = routeService.initialPointsWithArrayList();
        System.out.println(list);
        session.setAttribute("pointsList",list);
        return "pointsList";
    }

    @RequestMapping("/allMembersOrders")
    public String allMembersOrdersPage(){
        return "/deliver/all-members-orders";
    }


    @RequestMapping("/getAllCar")
    public String getAllCar(){
        return "/deliver/carsPage";
    }



    @RequestMapping("/deliverSelfManage")
    public String deliverSelfManage(){


        return "/deliver/deliverSelfManage";
    }

    @RequestMapping(value = "/sentDistance",method = RequestMethod.POST)
    public String sentDistance(@RequestBody String distance){
        System.out.println(distance);
//        model.addAttribute("distance",distance);
        return "redirect:/deliverSelfManage";
    }

    @RequestMapping("/routePage")//简单结果显示
    public String routePage(Model model){
        ArrayList<Pot> pList  = routeService.initialPotByArrayList();
        model.addAttribute("pointsList",pList);

        ArrayList<String> Lng = new ArrayList<>();
        ArrayList<String> Lat = new ArrayList<>();
        for (Pot pot : pList) {
            Lng.add(String.valueOf(pot.getLng()));
        }
        for (Pot pot : pList) {
            Lat.add(String.valueOf(pot.getLat()));
        }
        model.addAttribute("Lng",Lng);
        model.addAttribute("Lat",Lat);
        return "/deliver/routePage";
    }


    @RequestMapping("/routeResultPage")//优化结果显示
    public String routeResultPage(Model model){
        ArrayList<Pot> pList  = routeService.initialPotByArrayList();
        model.addAttribute("pointsList",pList);

        ArrayList<String> Lng = new ArrayList<>();
        ArrayList<String> Lat = new ArrayList<>();
        for (Pot pot : pList) {
            Lng.add(String.valueOf(pot.getLng()));
        }
        for (Pot pot : pList) {
            Lat.add(String.valueOf(pot.getLat()));
        }
        model.addAttribute("Lng",Lng);
        model.addAttribute("Lat",Lat);

        ArrayList<Point> list = routeService.initialPointsWithArrayList();
        int pointNum = list.size();
        AcaDTO acaDTO = new AcaDTO(pointNum,50,500,1.f,6.f,0.5f);
        ArrayList<Integer> potList = routeService.ACOByArrayList(list,acaDTO);
//        System.out.println(potList);
        model.addAttribute("bestTour",potList);

        return "/deliver/routeResultPage";
    }


    @ResponseBody //自动返回json格式的数据
    @RequestMapping(value="/getAllUserOrdersPage",method= RequestMethod.GET)
    public Map<String, Object> getAllUserOrdersPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                @RequestParam(value = "limit",defaultValue = "5") Integer limit
    ){//page、limit参数都是layui的table组件默认自动传递的，我们只需接收即可
        Map<String, Object> map = new HashMap<>();
        List<UserOrder> list = userOrderService.getData(page, limit);
        int count = userOrderDao.findAll().size();
        PageInfo<UserOrder> pageInfo = new PageInfo<>(list);
        System.out.println("pageInfo的内容为：" + count);
        map.put("msg", "操作成功");
        map.put("code", 0);
        map.put("count", count);
        map.put("data", pageInfo.getList());//最关键的代码，layui的table会自动获取并显示该数据集
        return map;
    }

    @ResponseBody //自动返回json格式的数据
    @RequestMapping(value="/getAllCars",method= RequestMethod.GET)
    public Map<String, Object> getAllCar(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                @RequestParam(value = "limit",defaultValue = "5") Integer limit
    ){//page、limit参数都是layui的table组件默认自动传递的，我们只需接收即可
        Map<String, Object> map = new HashMap<>();
        List<Vehicle> list = vehicleService.getData(page, limit);
        int count = list.size();
        PageInfo<Vehicle> pageInfo = new PageInfo<>(list);
        System.out.println("pageInfo的内容为：" + count);
        map.put("msg", "操作成功");
        map.put("code", 0);
        map.put("count", count);
        map.put("data", pageInfo.getList());//最关键的代码，layui的table会自动获取并显示该数据集
        return map;
    }





}
