package com.hincky.routesys.controller;

import com.github.pagehelper.PageInfo;
import com.hincky.routesys.dao.UserDao;
import com.hincky.routesys.pojo.entity.User;
import com.hincky.routesys.service.UserService;
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
public class UserController {
    @Resource
    UserDao userDao;
    @Resource
    UserService userService;

    @RequestMapping("userHome")
    public String userHome(Model model){
        model.getClass().getName();
        return "index";
    }

    //显示用户列表的页面
    @RequestMapping("/member-list")
    public String memberListPage(){ return "/member/member-list";}

    @RequestMapping("/memberGoodsList")
    public String memberGoodsListPage(){
        return "/member/member-good-list";
    }

    @RequestMapping("/selfManage")
    public String selfManagePage(){return "/member/selfManagePage";}

    @RequestMapping("/shopCar")
    public String shopCarPage(){return "/member/shopCarPage";}

    @RequestMapping("/member-order-list")
    public String memberOrderListPage(){return "/member/member-order-list";}

    //查询所有用户的分页实现
    @ResponseBody //自动返回json格式的数据
    @RequestMapping(value="/getUserPage",method=RequestMethod.GET)
    public Map<String, Object> getPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "limit",defaultValue = "5") Integer limit){//page、limit参数都是layui的table组件默认自动传递的，我们只需接收即可
        Map<String, Object> map = new HashMap<>();
        List<User> list = userService.getData(page, limit);//执行分页查询的方法
        int count = userDao.findAll().size();
        PageInfo<User> pageInfo = new PageInfo<>(list);
        System.out.println("pageInfo的内容为：" + count);
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", count);
        map.put("data", pageInfo.getList());//最最最关键的代码，layui的table会自动获取并显示该数据集
        return map;
    }

    @RequestMapping("/register")
    public String register(User user){
        int result = userDao.insert(user);
        Map<String, Object> map = new HashMap<>();
        if (result == 1) {
            map.put("result","添加成功");
        }else{
            map.put("result","添加失败");
        }
        return "login";
    }

}
