package com.hincky.routesys.service;

import com.github.pagehelper.PageInfo;
import com.hincky.routesys.pojo.entity.User;

import java.util.List;

public interface UserService {

    boolean login(User user);

    void updateLoginTime(int userId);

    PageInfo<User> page(int pageNum, int pageSize);

    List<User> getData(Integer page, Integer limit);


}
