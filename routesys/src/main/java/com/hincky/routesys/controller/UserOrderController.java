package com.hincky.routesys.controller;

import com.github.pagehelper.PageInfo;
import com.hincky.routesys.dao.ShopcarDao;
import com.hincky.routesys.dao.UserOrderDao;
import com.hincky.routesys.pojo.entity.UserOrder;
import com.hincky.routesys.pojo.vo.MapUtil;
import com.hincky.routesys.service.UserOrderService;
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
public class UserOrderController {
    @Resource
    UserOrderDao userOrderDao;
    @Resource
    UserOrderService userOrderService;
    @Resource
    ShopcarDao shopcarDao;

    @RequestMapping(value = "/addToUserOrder",method = RequestMethod.POST)
    public String addUserOrder(@RequestBody MapUtil mapUtil, HttpServletRequest request){
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(session.getAttribute("userId").toString());
        userOrderService.insertUserOrder(mapUtil,userId);
        return "redirect: /shopCar";
    }


    @ResponseBody //自动返回json格式的数据
    @RequestMapping(value="/getUserOrderPage",method=RequestMethod.GET)
    public Map<String, Object> getUserOrderPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                @RequestParam(value = "limit",defaultValue = "5") Integer limit,
                                                HttpServletRequest request
    ){//page、limit参数都是layui的table组件默认自动传递的，我们只需接收即可
        Map<String, Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(session.getAttribute("userId").toString());
        List<UserOrder> list = userOrderService.getDataByUserId(page, limit,userId);//执行分页查询的方法
        int count = userOrderDao.findAllByUserId(userId).size();
        PageInfo<UserOrder> pageInfo = new PageInfo<>(list);
        System.out.println("pageInfo的内容为：" + count);
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", count);
        map.put("data", pageInfo.getList());//最最最关键的代码，layui的table会自动获取并显示该数据集
        return map;
    }

}
