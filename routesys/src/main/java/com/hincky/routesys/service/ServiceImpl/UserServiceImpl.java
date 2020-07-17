package com.hincky.routesys.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hincky.routesys.dao.UserDao;
import com.hincky.routesys.pojo.entity.User;
import com.hincky.routesys.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;
    @Resource
    UserService userService;

    @Override
    public boolean login(User user) {
        User dbUser = userDao.selectByPrimaryKey(user.getUserId());
        if(dbUser == null){
            return false;
        }else {
            if (user.getPassword().equals(dbUser.getPassword()))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateLoginTime(int userId) {
        User loginUser = userDao.selectByPrimaryKey(userId);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        loginUser.setLastLoginTime(sdf.format(date));
        userDao.updateByPrimaryKey(loginUser);
    }

    /**
     * 实现分页
     * @param pageNum    当前页数
     * @param pageSize       显示条数
     * @return
     */

    @Override
    public PageInfo<User> page(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(userDao.findAll());
    }

    @Override
    public List<User> getData(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<User> userList  = userDao.findAll();
        return userList;
    }


}
