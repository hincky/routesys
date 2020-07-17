package com.hincky.routesys.service.ServiceImpl;

import com.hincky.routesys.dao.DeliverDao;
import com.hincky.routesys.pojo.entity.Deliver;
import com.hincky.routesys.service.DeliverService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DeliverServiceImpl implements DeliverService {
    @Resource
    DeliverDao deliverDao;

    @Override
    public boolean login(Deliver deliver) {
        Deliver dbDeliver = deliverDao.selectByPrimaryKey(deliver.getDeliverId());
        if(dbDeliver == null){
            return false;
        }else {
            if (deliver.getPassword().equals(dbDeliver.getPassword()))
            {
                return true;
            }
        }
        return false;
    }
}
