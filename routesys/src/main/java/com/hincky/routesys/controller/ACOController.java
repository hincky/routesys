package com.hincky.routesys.controller;

import com.hincky.routesys.pojo.dto.AcaDTO;
import com.hincky.routesys.service.ACAService;
import com.hincky.routesys.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class ACOController {
    @Resource
    ACAService acaService;
    @Resource
    RouteService routeService;

    /**
     * 一个用于测试的控制器
     * @return
     */
//    @RequestMapping("/routetest")
//    public String testDemo(){//纬度补0 测试
//        int pointNum = routeService.initialPoints().size();
//        AcaDTO acaDTO = new AcaDTO(pointNum,50,500,1.f,5.f,0.5f);
//        acaService.ACA(acaDTO);
////
//        return "/";
//    }
}
