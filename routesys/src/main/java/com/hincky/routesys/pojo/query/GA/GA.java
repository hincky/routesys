package com.hincky.routesys.pojo.query.GA;

import lombok.Data;

import java.util.Random;

/**
 * 基因：元素
 * 染色体：可行解（一个可行解由多个元素构成）
 * 适应度函数：约束条件（在N次迭代中作为选择优良种群的条件）
 * 适应度：判断条件的优良差的标准
 * 每一条染色体的适应度：染色体i被选择的概率 = 染色体i的适应度 / 所有染色体的适应度之和
 * 交叉：选择来自父母各一方的染色体，按照轮盘赌算法进行基因选择，并交叉成为新的染色体
 */

@Data
public class GA {//遗传算法

    private int pointNum;   //等于用户订单数
    private float[][] E;

    private int point;      //起始点
    private int scale;      // 种群规模
    private int cityNum;    // 城市数量，染色体长度
    private int MAX_GEN;    // 运行代数
    private int[][] distance; // 距离矩阵
    private int bestT;      // 最佳出现代数
    private int bestLength; // 最佳长度
    private int[] bestTour; // 最佳路径

    // 初始种群，父代种群，行数表示种群规模，一行代表一个个体，即染色体，列表示染色体基因片段
    private int[][] oldPopulation;
    private int[][] newPopulation;// 新的种群，子代种群
    private int[] fitness;  // 种群适应度，表示种群中各个个体的适应度

    private float[] Pi;     // 种群中各个个体的累计概率
    private float Pc;       // 交叉概率
    private float Pm;       // 变异概率
    private int t;          // 当前代数

    private Random random;

    public GA(){

    }

    public GA(int s, int n, int g, float c, float m) {
        scale = s;          // 种群规模
        cityNum = n;        // 城市数量，染色体长度
        MAX_GEN = g;        // 运行代数
        Pc = c;             // 交叉概率
        Pm = m;             // 变异概率
    }

}
