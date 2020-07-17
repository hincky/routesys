package com.hincky.routesys.pojo.query.ACA;

import java.io.*;

public class ACODemo {
    //定义蚂蚁群
    AntDemo []ants;
    int antCount;//蚂蚁的数量
    int [][]distance;//表示城市间距离
    double [][] pheromone;//信息素矩阵
    int cityCount;//城市数量
    int[] bestTour;//求解的最佳路径
    int bestLength;//求的最优解的长度

    /** 初始化函数
     *@param filename tsp数据文件
     *@param antnum 系统用到蚂蚁的数量
     *@throws
     */
    public void init(String filename,int antnum) throws FileNotFoundException, IOException {
        antCount =antnum;
        ants=new AntDemo[antCount];
        //读取数据
        int[] x;
        int[] y;
        String strbuff;
        BufferedReader tspdata = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        strbuff = tspdata.readLine();
        cityCount = Integer.valueOf(strbuff);
        distance = new int[cityCount][cityCount];
        x = new int[cityCount];
        y = new int[cityCount];
        for (int citys = 0; citys < cityCount; citys++) {
            strbuff = tspdata.readLine();
            String[] strcol = strbuff.split(" ");
            x[citys] = Integer.valueOf(strcol[1]);
            y[citys] = Integer.valueOf(strcol[2]);
        }
        //计算距离矩阵
        for (int city1 = 0; city1 < cityCount - 1; city1++) {
            distance[city1][city1] = 0;
            for (int city2 = city1 + 1; city2 < cityCount; city2++) {
                distance[city1][city2] = (int) (Math.sqrt((x[city1] - x[city2]) * (x[city1] - x[city2])
                        + (y[city1] - y[city2]) * (y[city1] - y[city2])) + 0.5);
                distance[city2][city1] = distance[city1][city2];
            }
        }
        distance[cityCount - 1][cityCount - 1] = 0;
        //初始化信息素矩阵,均为0.1
        pheromone =new double[cityCount][cityCount];
        for(int i = 0; i< cityCount; i++)
        {
            for(int j = 0; j< cityCount; j++){
                pheromone[i][j]=0.1;
            }
        }


        bestLength =Integer.MAX_VALUE;
        bestTour =new int[cityCount +1];
        //随机放置蚂蚁
        for(int i = 0; i< antCount; i++){
            ants[i]=new AntDemo();
            ants[i].RandomSelectCity(cityCount);
        }
    }
    /**
     * ACO的运行过程
     * @param maxgen ACO的最多循环次数
     *
     */
    public void run(int maxgen){
        for(int runtimes=0;runtimes<maxgen;runtimes++){
            //每一只蚂蚁移动的过程
            for(int i = 0; i< antCount; i++){
                for(int j = 1; j< cityCount; j++){
                    ants[i].SelectNextCity(j, pheromone,distance);
                }
                //计算蚂蚁获得的路径长度
                ants[i].CalTourLength(distance);
                if(ants[i].tourlength< bestLength){
                    //保留最优路径
                    bestLength =ants[i].tourlength;
                    System.out.println("第"+runtimes+"代，发现新的解"+ bestLength);
                    for(int j = 0; j< cityCount +1; j++)
                        bestTour[j]=ants[i].tour[j];
                }
            }
            //更新信息素矩阵
            UpdatePheromone();
            //重新随机设置蚂蚁
            for(int i = 0; i< antCount; i++){
                ants[i].RandomSelectCity(cityCount);
            }
        }
    }

    /**
     * 更新信息素矩阵
     */
    private void UpdatePheromone(){
        double rou=0.5;
        //信息素挥发
        for(int i = 0; i< cityCount; i++)
            for(int j = 0; j< cityCount; j++)
                pheromone[i][j]= pheromone[i][j]*(1-rou);
        //信息素更新
        for(int i = 0; i< antCount; i++){
            for(int j = 0; j< cityCount; j++){
                pheromone[ants[i].tour[j]][ants[i].tour[j+1]]+=1.0/ants[i].tourlength;
            }
        }
    }

    /**
     * 输出程序运行结果
     */
    public void ReportResult(){
        System.out.println("最优路径长度是"+ bestLength);
    }
}
