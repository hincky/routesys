package com.hincky.routesys.service;

import com.hincky.routesys.pojo.dto.AcaDTO;
import com.hincky.routesys.pojo.entity.Vehicle;
import com.hincky.routesys.pojo.query.Point;
import com.hincky.routesys.pojo.query.Pot;

import java.util.ArrayList;
import java.util.HashMap;

public interface RouteService {

    void initial(int pointNum);

    void stringFomat(String str);                  //double类型的一个格式转换方法

    double Rad(double dd);                         //经纬度转为弧度

    double getDistance(Point point1,Point point2); //在给定的两个经纬度坐标后，计算两个坐标的距离

    double[][] generateDistanceMatrix(HashMap<Integer,Point> pointsMap);//通过坐标集合生成距离矩阵

    double[][] generateDistanceMatrix(ArrayList<Point> pointList);//通过坐标集合生成距离矩阵

    double[][] generateDriveTimeMatrix(double[][] distanceMatrix,double speed);

    HashMap<Integer,Point> initialPoints();         //根据用户订单总数来初始化坐标集合

    ArrayList<Point> initialPointsWithArrayList();         //根据用户订单总数来初始化坐标集合

    ArrayList<Pot> initialPotByArrayList();

    double[] initDistance(ArrayList<Point> pointList);

    int judgeVehicleNum(ArrayList<Point> pointList);

    String minFormat(double time);

    int judgeWeight(ArrayList<Point> tourList, Vehicle vehicle);

    void judgeTime(ArrayList<Point> tourList,double speed);

    void getTourDistanceByBestTour(ArrayList<Point> tourList,int[] bestTour);

    void getTourTimeByBestTour(ArrayList<Point> tourList,int[] bestTour,double ss);

    void caculateTourTime(ArrayList<Point> tourList,int[] bestTour,double ss);

    Point[] sortPointsByTimeWindow1(ArrayList<Point> pointArrayList);

    double caculateTotalLengthByPointList(ArrayList<Point> tourList);

    double caculateTotalLengthByDistanceMatrix(double[][] distanceMatrix);

    double caculateTotalTime(ArrayList<Point> tourList,double ss);

    void ACA(AcaDTO acaDTO);

    ArrayList<Integer> ACOByArrayList(ArrayList<Point> pointArrayList,AcaDTO acaDTO);

    void GAByArrayList();
}
