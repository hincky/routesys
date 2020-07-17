package com.hincky.routesys.pojo.query;

import lombok.Data;

/**
 * 该类是用于记录经纬度的，不能删除,用于百度地图，只有经纬度，简化操作
 */
@Data
public class Pot {
//    private  int num;
    private double lng;//经度
    private double lat;//纬度

}
