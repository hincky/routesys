package com.hincky.routesys.controller;

import com.github.pagehelper.PageInfo;
import com.hincky.routesys.dao.VehicleDao;
import com.hincky.routesys.pojo.entity.Vehicle;
import com.hincky.routesys.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class VehicleController {
    @Resource
    VehicleDao vehicleDao;
    @Resource
    VehicleService vehicleService;

    @RequestMapping("/vehicle/vehicle-list")
    public String VehicleList(Model model){
        List<Vehicle> vehicles = vehicleDao.findAll();
        model.addAttribute("vehicles", vehicles);
        return "/vehicle/vehicle-list";
    }

    @RequestMapping("/vehicle/vehicle-list1")
    public String editPage(Model model){
        List<Vehicle> vehicles = vehicleDao.findAll();
        model.addAttribute("vehicles", vehicles);
        return "/vehicle/vehicle-list1";
    }

    @ResponseBody //自动返回json格式的数据
    @RequestMapping(value="/getVehiclePage",method=RequestMethod.GET)
    public Map<String, Object> getPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "limit",defaultValue = "5") Integer limit){//page、limit参数都是layui的table组件默认自动传递的，我们只需接收即可
        Map<String, Object> map = new HashMap<>();
        List<Vehicle> list = vehicleService.getData(page, limit);//执行分页查询的方法
        int count = vehicleDao.findAll().size();
        PageInfo<Vehicle> pageInfo = new PageInfo<>(list);
        System.out.println("pageInfo的内容为：" + count);
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", count);
        map.put("data", pageInfo.getList());//最最最关键的代码，layui的table会自动获取并显示该数据集
        return map;
    }

}
