package com.hincky.routesys.dao;

import com.hincky.routesys.pojo.entity.User;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> findAll();
}