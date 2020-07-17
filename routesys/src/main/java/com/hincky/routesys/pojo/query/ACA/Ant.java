package com.hincky.routesys.pojo.query.ACA;

import lombok.Data;

import java.util.Random;
import java.util.Vector;

@Data

public class Ant implements Cloneable{          //蚂蚁类

    private Vector<Integer> taboo;              //禁忌表,表示已经走过的坐标
    private Vector<Integer> allowedPoints;      //未走过的坐标
    private float[][] delta;      //信息素      //记录所经过的路径信息数变化矩阵

    private double[][] distance;                //距离矩阵
    private float alpha;//信息素的重要程度因子，值越大，在转移中的起的作用越大
    private float beta; //启发函数因子，蚂蚁根据这个因子大小改变概率大小转移到距离短的坐标
    private double tourLength;                  //路径长度
    private int pointNum;                       //坐标数量private int firstPoint; //起始坐标
    private int firstPoint;                     //起始坐标点
    private int currentPoint;                   //当前坐标

    public Ant(){
        pointNum = 30;
        tourLength = 0;
    }

    /**
     * 蚂蚁数量
     * @param number
     */
    public Ant(int number){
        pointNum = number;
        tourLength = 0;
    }

    /**
     * 初始化蚂蚁，随机选择起始位置
     * @param distance
     * @param a
     * @param b
     */
    public void initAnts(double[][] distance, float a, float b){
        alpha = a;beta = b;
        allowedPoints = new Vector<>();
        taboo = new Vector<>();
        this.distance = distance;
        //新建该蚂蚁自己的信息素矩阵(坐标数*坐标数)，所以记录的是每条路径的信息素浓度
        delta = new float[pointNum][pointNum];
        for (int i=0;i<pointNum;i++){
            Integer integer = i;
            allowedPoints.add(integer);//将所有坐标设置为可走
            for (int j=0;j<pointNum;j++){
                delta[i][j] = 0.f;     //所有路径起始信息素变化矩阵均为零
            }
        }
        firstPoint = 0;                //将固定起始点设置为下标为0的坐标
        for (Integer i : allowedPoints){
            if(i == firstPoint){
                allowedPoints.remove(i);//并将下标12从可走的坐标数组移除
                break;
            }
        }
        taboo.add(firstPoint);//将下标的坐标添加到禁忌数组之中
        currentPoint = firstPoint;
    }

    public void selectNextPoint(float[][] pheromone){
        float[] p = new float[pointNum];
        float sum = 0.0f;
//        计算分母部分
        for (Integer i : allowedPoints){//此时起始下标的坐标已经不在
            sum += Math.pow(pheromone[currentPoint][i], alpha)//底数的n次方
                    * Math.pow(1.0/distance[currentPoint][i], beta);
        }
//        计算概率矩阵
        for (int i =0;i<pointNum;i++){
            boolean flag = false;
            for (Integer j : allowedPoints){
                if (i == j) {
                  p[i] = (float) (Math.pow(pheromone[currentPoint][i], alpha)
                          * Math.pow(1.0/distance[currentPoint][i], beta))/sum;
                  flag = true;
                  break;
                }
            }
            if (!flag){
                p[i] = 0.f;
            }
        }
        //轮盘赌选择下一个城市
        Random random = new Random(System.currentTimeMillis());
        float sleectP = random.nextFloat();
        int selectPoint = 0;
        float sum1 = 0.f;
        for (int i = 0; i < pointNum; i++) {
        sum1 += p[i];
        if (sum1 >= sleectP) {
           selectPoint = i;
           break;
         }
        }
        //从允许选择的城市中去除select city
        for (Integer i:allowedPoints) {
          if (i == selectPoint) {
            allowedPoints.remove(i);
            break;
          }
        }
        //在禁忌表中添加select city
        taboo.add(selectPoint);
        //将当前城市改为选择的城市
        currentPoint = selectPoint;
        }


    /**
     * 计算路径长度
     * @return 路径长度
     */
    private int calculateTourLength(){
        int len = 0;
        for (int i = 0; i < pointNum; i++) {
            len += distance[this.taboo.get(i)][this.taboo.get(i + 1)];
        }
        return len;
    }
    }

