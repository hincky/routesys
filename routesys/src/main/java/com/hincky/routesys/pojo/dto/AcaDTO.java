package com.hincky.routesys.pojo.dto;

import lombok.Data;

@Data
public class AcaDTO {
    private int pointNum; //坐标数量0
    private int antsNum;  //蚂蚁数量0
    private int MAX_GEN;  //运行代数0
    private float[][] pheromoneMatrix; //信息素矩阵
    private double[][] distanceMatrix; //距离矩阵
    private double bestLength; //最佳长度
    private int[] bestTour; //最佳路径

    private float alpha;  //信息素的重要程度因子，值越大，在转移中的起的作用越大
    private float beta;   //启发函数因子，蚂蚁根据这个因子大小改变概率大小转移到距离短的坐标
    private float rho;    //信息素挥发因子，0~1



    public AcaDTO (int pointNum,int antsNum,int MAX_GEN,float alpha,float beta,float rho){
        this.pointNum = pointNum;
        this.antsNum = antsNum;
        this.MAX_GEN = MAX_GEN;
        this.alpha = alpha;
        this.beta = beta;
        this.rho = rho;

    }

}
