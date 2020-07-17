package com.hincky.routesys.dao;

import com.hincky.routesys.pojo.entity.Admin;

public interface AdminDao {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}