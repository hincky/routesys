package com.hincky.routesys.controller;

import com.hincky.routesys.dao.UserOrderDao;
import com.hincky.routesys.pojo.dto.AcaDTO;
import com.hincky.routesys.pojo.dto.UserOrderDTO;
import com.hincky.routesys.pojo.entity.UserOrder;
import com.hincky.routesys.pojo.query.Point;
import com.hincky.routesys.service.RouteService;
import com.hincky.routesys.service.UserOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class RouteController {
    @Resource
    UserOrderDao userOrderDao;
    @Resource
    UserOrderService userOrderService;
    @Resource
    RouteService routeService;


    /**
     * 一个用于测试的控制器
     * @return
     */
    @RequestMapping("/routetest")
    public String testDemo(){//纬度补0 测试
        ArrayList<Point> list = routeService.initialPointsWithArrayList();
        int pointNum = list.size();
        AcaDTO acaDTO = new AcaDTO(pointNum,50,500,1.f,6.f,0.5f);
        ArrayList<Integer> potList = routeService.ACOByArrayList(list,acaDTO);
        System.out.println(potList);
//        System.out.println(resultList);

//        routeService.GAByArrayList();
//        double[][] doubles = routeService.generateDistanceMatrix(routeService.initialPoints());
//        double[][] timeMatirx = routeService.generateDriveTimeMatrix(doubles,16.7);
//        System.out.println(Arrays.deepToString(timeMatirx));

//        ArrayList<Point> list = routeService.initialPointsWithArrayList();
//        double[] dis = routeService.initDistance(list);
//        for(double dd : dis){
//            System.out.println(dd+"m");
//        }
//
//        double sum = 0;
//        for (int i=0;i<dis.length;i++){
////            sum += dis
//            sum += dis[i];
//        }
//        System.out.println("总距离为："+sum);
//        routeService.judgeTime(list,60);

//        int[] bb = new int[]{8,7,6,5,4,3,2,1};
//        int sub = 0;
//        for (int i=0;i<bb.length-1;i++){
//            for (int j=0;j<bb.length-1-i;j++) {
//                if (bb[j] > bb[j + 1]) {
//                    sub = bb[j];
//                    bb[j] = bb[j + 1];
//                    bb[j + 1] = sub;
//                }
//            }
//        }
//
//        for (int i=0;i<bb.length;i++){
//            System.out.println(bb[i]);
//        }

        return "/";
    }


}
