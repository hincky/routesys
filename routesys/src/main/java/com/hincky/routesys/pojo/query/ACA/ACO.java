package com.hincky.routesys.pojo.query.ACA;

import com.hincky.routesys.pojo.entity.Vehicle;
import lombok.Data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@Data
public class ACO {//蚁群类

    private Ant[] ants; //蚂蚁
    private int antNum; //蚂蚁数量
    private int pointNum; //坐标数量
    private int MAX_GEN; //运行代数
    private float[][] pheromone; //信息素矩阵
    private double[][] distance; //距离矩阵

    private double bestLength; //最佳长度
    private int[] bestTour; //最佳路径

    //三个参数
    private float alpha;        //信息素的重要程度因子，值越大，在转移中的起的作用越大
    private float beta;         //启发函数因子，蚂蚁根据这个因子大小改变概率大小转移到距离短的坐标
    private float rho;          //信息素挥发因子，0~1

    private Vehicle[] vehicles; //可用车辆
    private double[][] time;    //两个坐标之间的行驶时间，每个坐标加上相同的服务时间



    /**
     * 初始化ACO算法类
     *
     * @param filename 数据文件名，该文件存储所有城市节点坐标数据
     * @throws IOException
     */

    private void init(String filename) throws IOException {
        //读取数据
        int[] x;
        int[] y;
        String strbuff;
        BufferedReader data = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        distance = new double[pointNum][pointNum];
        x = new int[pointNum];
        y = new int[pointNum];
        for (int i = 0; i < pointNum; i++) {
            strbuff = data.readLine();
            String[] strcol = strbuff.split("");
            x[i] = Integer.valueOf(strcol[1]);
            y[i] = Integer.valueOf(strcol[2]);
        }
        //计算距离矩阵 ，针对具体问题，距离计算方法也不一样，此处用的是att48作为案例，它有48个城市，距离计算方法为伪欧氏距离，最优值为10628
        for (int i = 0; i < pointNum - 1; i++) {
            distance[i][i] = 0;  //对角线为0
            for (int j = i + 1; j < pointNum; j++) {
                double rij = Math.sqrt(((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j])) / 10.0);
                int tij = (int) Math.round(rij);
                if (tij < rij) {
                    distance[i][j] = tij + 1;
                    distance[j][i] = distance[i][j];
                } else {
                    distance[i][j] = tij;
                    distance[j][i] = distance[i][j];
                }
            }
        }
        distance[pointNum - 1][pointNum - 1] = 0;
        //初始化信息素矩阵
        pheromone = new float[pointNum][pointNum];
        for (int i = 0; i < pointNum; i++) {
            for (int j = 0; j < pointNum; j++) {
                pheromone[i][j] = 0.1f;  //初始化为0.1
            }
        }
        bestLength = Integer.MAX_VALUE;
        bestTour = new int[pointNum + 1];
        //随机放置蚂蚁
        for (int i = 0; i < antNum; i++) {
            ants[i] = new Ant(pointNum);
            ants[i].initAnts(distance, alpha, beta);
        }
    }

    public void solve() {
        for (int g = 0; g < MAX_GEN; g++) {
            for (int i = 0; i < antNum; i++) {
                for (int j = 1; j < pointNum; j++) {
                    ants[i].selectNextPoint(pheromone);
                }
                ants[i].getTaboo().add(ants[i].getFirstPoint());
                if (ants[i].getTourLength() < bestLength) {
                    bestLength = ants[i].getTourLength();
                    for (int k = 0; k < pointNum + 1; k++) {
                        bestTour[k] = ants[i].getTaboo().get(k);
                    }
                }
                for (int j = 0; j < pointNum; j++) {
                    ants[i].getDelta()[ants[i].getTaboo().get(j)][ants[i].getTaboo().get(j + 1)]
                            = (float) (1. / ants[i].getTourLength());

                    ants[i].getDelta()[ants[i].getTaboo().get(j + 1)][ants[i].getTaboo().get(j)]
                            = (float) (1. / ants[i].getTourLength());
                }
            }
            //更新信息素
            updatePheromone();
            //重新初始化蚂蚁
            for (int i = 0; i < antNum; i++) {
                ants[i].initAnts(distance, alpha, beta);
            }
//        }
            //打印最佳结果
            printOptimal();
        }
    }

    //更新信息素
    private void updatePheromone() {
//        //信息素挥发
        for (int i = 0; i < pointNum; i++)
            for (int j = 0; j < pointNum; j++)
                pheromone[i][j] = pheromone[i][j] * (1 - rho);
//        //信息素更新
        for (int i = 0; i < pointNum; i++) {
            for (int j = 0; j < pointNum; j++) {
                for (int k = 0; k < antNum; k++) {
                    pheromone[i][j] += ants[k].getDelta()[i][j];
                }
            }
        }
    }

    private void printOptimal() {//added
        System.out.println("The optimal length is: " + bestLength);//最佳长度
        System.out.println("The optimal tour is: ");//最佳路径
        for (int i = 0; i < pointNum + 1; i++) {
            System.out.println(bestTour[i]);
        }
    }
}
