package com.hincky.routesys.pojo.query;

import lombok.Data;

/**
 * 该类是用于记录经纬度的，不能删除
 */
@Data
public class Point {
    private int num;   //序号
    private double lat;//纬度
    private double lng;//经度

    private String timeWindow1;
    private String timeWindow2;
    private double weight;

    public Point(){

    }

    public String inputWindowStringForStartTime(String timeWindow){
        return timeWindow.substring(0,8);
    }

    public String inputWindowStringForEndTime(String timeWindow){
        return timeWindow.substring(11,19);
    }

    public int inputWindowStringForTimeDelta(String timeWindow){
        int startHour = Integer.valueOf(timeWindow.substring(0,2));
        int endHour = Integer.valueOf(timeWindow.substring(11,13));

        int startMin = Integer.valueOf(timeWindow.substring(3,5));
        int endMin = Integer.valueOf(timeWindow.substring(14,16));

        int startSS = Integer.valueOf(timeWindow.substring(6,8));
        int endSS = Integer.valueOf(timeWindow.substring(17,19));

        int hourDiffer = endHour-startHour;
        int minDiffer = endMin-startMin;
        int sDiffer = endSS-startSS;
//            System.out.println(end+"-"+start+"  "+endHour+":"+endMin+":"+endSS+"-"+startHour+":"+startMin+":"+startSS);
//        System.out.println(end+"-"+start);
//        System.out.println(hourDiffer+":"+minDiffer+":"+sDiffer);
        int delta = hourDiffer*3600+minDiffer*60+sDiffer;
//        System.out.println("上午时间窗为："+delta+"秒");
        return delta;
    }
}
