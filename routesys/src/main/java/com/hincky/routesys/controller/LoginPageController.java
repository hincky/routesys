package com.hincky.routesys.controller;

import com.hincky.routesys.dao.AdminDao;
import com.hincky.routesys.dao.DeliverDao;
import com.hincky.routesys.dao.UserDao;
import com.hincky.routesys.pojo.entity.Admin;
import com.hincky.routesys.pojo.entity.Deliver;
import com.hincky.routesys.pojo.entity.User;
import com.hincky.routesys.service.AdminService;
import com.hincky.routesys.service.DeliverService;
import com.hincky.routesys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Controller
public class LoginPageController {
    @Resource
    UserService userService;
    @Resource
    UserDao userDao;
    @Resource
    DeliverService deliverService;
    @Resource
    DeliverDao deliverDao;
    @Resource
    AdminService adminService;
    @Resource
    AdminDao adminDao;

    @RequestMapping("/home")
    public String login(Integer username, String password, String identity,
                        Model model, HttpSession session){//model 是用来存放消息用的

        boolean loginResult;
        String realName;
        //根据identity的不同值登录不同的身份
        if (identity.equals("user")){//用户
            User user = new User();
            user.setUserId(username);
            user.setPassword(password);
            loginResult = userService.login(user);
            if (loginResult){
                realName = userDao.selectByPrimaryKey(username).getRealName();
                session.setAttribute("realName",realName);
                session.setAttribute("userId",username);
                userService.updateLoginTime(username);
//                model.addAttribute(user);
                return "/member/index";
            }else {
                model.addAttribute("登录失败，请确认重登！");
                return "redirect:/";
            }
        }else if (identity.equals("deliver")){//配送员
            Deliver deliver = new Deliver();
            deliver.setDeliverId(username);
            deliver.setPassword(password);
            loginResult = deliverService.login(deliver);
            if (loginResult){
                realName = deliverDao.selectByPrimaryKey(username).getDeliverName();
                session.setAttribute("realName",realName);
//                model.addAttribute(deliver);
                return "/deliver/index";
            }else {
                model.addAttribute("登录失败，请确认重登！");
                return "redirect:/";
            }
        }else if(identity.equals("admin")){//管理员
            Admin admin = new Admin();
            admin.setAdminId(username);
            admin.setPassword(password);
            loginResult = adminService.login(admin);
            if (loginResult){
                realName = adminDao.selectByPrimaryKey(username).getAdminName();
                session.setAttribute("realName",realName);
                session.setAttribute("time",new Date());
//                model.addAttribute(admin);
                return "/admin/index";
            }else {
                model.addAttribute("登录失败，请确认重登！");
                return "redirect:/";
            }
        }
        return "redirect:/";
    }

}
