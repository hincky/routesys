package com.hincky.routesys.service.ServiceImpl;

import com.hincky.routesys.pojo.dto.AcaDTO;
import com.hincky.routesys.pojo.query.ACA.Ant;
import com.hincky.routesys.pojo.query.Point;
import com.hincky.routesys.service.ACAService;
import com.hincky.routesys.service.RouteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class ACAServiceImpl implements ACAService {
    @Resource
    RouteService routeService;

//    @Override
//    public void ACA(AcaDTO acaDTO) {
//        HashMap<Integer, Point> pointHashMap = routeService.initialPoints();//return HashMap<Integer,Point>
//        int pointNum = pointHashMap.size();
//        double[][] distanceMatrix = routeService.generateDistanceMatrix(pointHashMap);//初始化距离矩阵
//        //初始化信息素矩阵
//        float[][] pheromone = new float[pointNum][pointNum];
//        for (int i = 0; i < pointNum; i++) {
//            for (int j = 0; j < pointNum; j++) {
//                pheromone[i][j] = 0.1f;  //初始化为0.1
//            }
//        }
//        double bestLength = Double.MAX_VALUE;
//        int[] bestTour = new int[pointNum + 1];
//
//
//        //随机放置蚂蚁
//        int antNum = acaDTO.getAntsNum();
//        float alpha = acaDTO.getAlpha();
//        float beta = acaDTO.getBeta();
//        Ant[] ants = new Ant[antNum];
//        for (int i = 0; i < antNum; i++) {
//            ants[i] = new Ant(pointNum);
//            ants[i].initAnts(distanceMatrix, alpha, beta);
//        }
//
//        int MAX_GEN = acaDTO.getMAX_GEN();
//        for (int g = 0; g < MAX_GEN; g++) {
//            for (int i = 0; i < antNum; i++) {
//                for (int j = 1; j < pointNum; j++) {
//                    ants[i].selectNextPoint(pheromone);
//                }
//                ants[i].getTaboo().add(ants[i].getFirstPoint());
//                if (ants[i].getTourLength() < bestLength) {
//                    bestLength = ants[i].getTourLength();
//                    for (int k = 0; k < pointNum + 1; k++) {
//                        bestTour[k] = ants[i].getTaboo().get(k);
//                    }
//                }
//                for (int j = 0; j < pointNum; j++) {
//                    ants[i].getDelta()[ants[i].getTaboo().get(j)][ants[i].getTaboo().get(j + 1)]
//                            = (float) (1. / ants[i].getTourLength());
//
//                    ants[i].getDelta()[ants[i].getTaboo().get(j + 1)][ants[i].getTaboo().get(j)]
//                            = (float) (1. / ants[i].getTourLength());
//                }
//            }
//            //更新信息素
//            float rho = acaDTO.getRho();
//            for (int i = 0; i < pointNum; i++)
//                for (int j = 0; j < pointNum; j++)
//                    pheromone[i][j] = pheromone[i][j] * (1 - rho);
//            //信息素更新
//            for (int i = 0; i < pointNum; i++) {
//                for (int j = 0; j < pointNum; j++) {
//                    for (int k = 0; k < antNum; k++) {
//                        pheromone[i][j] += ants[k].getDelta()[i][j];
//                    }
//                }
//            }
//            //重新初始化蚂蚁
//            for (int i = 0; i < antNum; i++) {
//                ants[i].initAnts(distanceMatrix, alpha, beta);
//            }
//        }
//        //打印最佳结果
//        System.out.println("The optimal length is: " + bestLength);//最佳长度
//        System.out.println("The optimal tour is: ");//最佳路径
//        for (int i = 0; i < pointNum + 1; i++) {
//            System.out.print(bestTour[i]+"->");
//        }
//    }



//
//    @Override
//    public void updatePheromone(int pointNum,int antNum,Ant[] ants,float[][] pheromone,float rho) {
//        //信息素挥发
//        for (int i = 0; i < pointNum; i++)
//            for (int j = 0; j < pointNum; j++)
//                pheromone[i][j] = pheromone[i][j] * (1 - rho);
//        //信息素更新
//        for (int i = 0; i < pointNum; i++) {
//            for (int j = 0; j < pointNum; j++) {
//                for (int k = 0; k < antNum; k++) {
//                    pheromone[i][j] += ants[k].getDelta()[i][j];
//                }
//            }
//        }
//    }
//
//    @Override
//    public void printOptimal(int pointNum,double bestLength, int[] bestTour) {
//        System.out.println("The optimal length is: " + bestLength);//最佳长度
//        System.out.println("The optimal tour is: ");//最佳路径
//        for (int i = 0; i < pointNum + 1; i++) {
//            System.out.println(bestTour[i]);
//        }
//    }


}
