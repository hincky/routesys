package com.hincky.routesys.service.ServiceImpl;

import com.hincky.routesys.dao.AdminDao;
import com.hincky.routesys.pojo.entity.Admin;
import com.hincky.routesys.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminDao adminDao;

    @Override
    public boolean login(Admin admin) {
        Admin dbAdmin = adminDao.selectByPrimaryKey(admin.getAdminId());
        if(dbAdmin == null){
            return false;
        }else {
            if (admin.getPassword().equals(dbAdmin.getPassword()))
            {
                return true;
            }
        }
        return false;
    }
}
