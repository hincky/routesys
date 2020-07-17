package com.hincky.routesys.pojo.dto;

import lombok.Data;

@Data
public class GaDTO {
    private int pointNum; //坐标数量0
    private int antsNum;  //蚂蚁数量0
    private int MAX_GEN;  //运行代数0
    private float[][] pheromoneMatrix; //信息素矩阵
    private double[][] distanceMatrix; //距离矩阵
    private double bestLength; //最佳长度
    private int[] bestTour; //最佳路径
}
