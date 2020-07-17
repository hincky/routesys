package com.hincky.routesys.service.ServiceImpl;

import com.hincky.routesys.dao.UserOrderDao;
import com.hincky.routesys.pojo.dto.AcaDTO;
import com.hincky.routesys.pojo.dto.UserOrderDTO;
import com.hincky.routesys.pojo.entity.Vehicle;
import com.hincky.routesys.pojo.query.ACA.Ant;
import com.hincky.routesys.pojo.query.GA.GA;
import com.hincky.routesys.pojo.query.Point;
import com.hincky.routesys.pojo.query.Pot;
import com.hincky.routesys.service.RouteService;
import com.hincky.routesys.service.UserOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class RouteServiceImpl implements RouteService {
    @Resource
    UserOrderDao userOrderDao;
    @Resource
    UserOrderService userOrderService;

    @Override
    public void initial(int pointNum) {
        final double earthR = 6378137;//赤道半径
        final double earthB = 6356725;//极点半径

        GA ga = new GA();
        double[] x; x = new double[pointNum];
        double[] y; y = new double[pointNum];

        ga.setDistance(new int[pointNum][pointNum]);

        List<UserOrderDTO> userOrderDTOList = userOrderDao.getDTO();
        String[] pointStr = new String[userOrderDTOList.size()];//获取坐标数组

        for (int i = 0; i < userOrderDTOList.size(); i++){
            pointStr[i] = userOrderDTOList.get(i).getPoint();
            String lng = pointStr[i].substring(0,10);//经度
            String lat = pointStr[i].substring(11,20);//纬度
            x[i] = Double.valueOf(lat);//横坐标为纬度
            y[i] = Double.valueOf(lng);//纵坐标为经度
            System.out.println(pointStr[i]+"        "+x[i]+"    "+y[i]);
        }
        System.out.println("-----------------------------");
        // 计算距离矩阵
        // ，针对具体问题，距离计算方法也不一样，此处用的是att48作为案例，它有48个城市，距离计算方法为伪欧氏距离，最优值为10628

//        for (int i = 0; i < pointNum - 1; i++) {
//            int[][] distance = ga.getDistance();
//            distance[i][i] = 0; // 对角线为0
//            for (int j = i + 1; j < pointNum; j++) {
//                //两个坐标的距离
//                double rij = Math.sqrt(((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j])) / 10.0);
//                // 四舍五入，取整
//                int tij = (int) Math.round(rij);
//                System.out.println(tij);
//                if (tij < rij) {
//                    distance[i][j] = tij + 1;
//                    distance[j][i] = distance[i][j];
//                } else {
//                    distance[i][j] = tij;
//                    distance[j][i] = distance[i][j];
//                }
//            }
//        }

    }

    /**
     * double类型的一个格式转换方法
     * @param str
     */
    @Override
    public void stringFomat(String str) {
        double dou = Double.valueOf(str);
        String res =  new DecimalFormat("00.000000").format(dou);
        System.out.println(res);
    }

    /**
     * 经纬度转为弧度
     * @param dd
     * @return
     */
    @Override
    public double Rad(double dd){
        return dd * Math.PI / 180.0;
    }

    /**
     * 在给定的两个经纬度坐标后，计算两个坐标的距离
     * @param point1
     * @param point2
     * @return
     */
    @Override
    public double getDistance(Point point1, Point point2) {
        double a = Rad(point1.getLat()) - Rad(point2.getLat());
        double b = Rad(point1.getLng()) - Rad(point2.getLng());
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + Math.cos(  Rad(point1.getLat()) )
                * Math.cos(Rad(point2.getLat())) * Math.pow(Math.sin(b / 2), 2) ));
        double EarthRadius = 6378137;// 赤道半径(单位m)
        distance = distance * EarthRadius;
        DecimalFormat df = new DecimalFormat("#.00");
        double dis = Double.valueOf(df.format(distance));
        return dis;
    }

    /**
     * 根据用户订单总数来初始化坐标集合
     * @return HashMap<Integer,Point>
     */
    @Override
    public HashMap<Integer,Point> initialPoints(){
        List<UserOrderDTO> list = userOrderDao.getDTO();//获取所有用户订单数目，则知道有n个坐标点
        int pointsSize =  list.size();//坐标点的总个数
        HashMap<Integer,Point> pointsMap = new HashMap<>();
        Point point;
        for (int i=0;i<pointsSize;i++){
            point = userOrderService.changeIntoPoint(list.get(i).getPoint());
            point.setNum(i);        //给point逐个标上序号
            point.setTimeWindow1(list.get(i).getTimeWindow1());
            point.setTimeWindow2(list.get(i).getTimeWindow2());
            point.setWeight(list.get(i).getOrderWeight());
            pointsMap.put(i,point); //成功创建并初始化pointsMap,并可输出map结果
        }
        return pointsMap;
    }

    /**
     * 根据用户订单总数来初始化坐标集合
     * @return ArrayList<Point>
     */
    @Override
    public ArrayList<Point> initialPointsWithArrayList() {
        List<UserOrderDTO> list = userOrderDao.getDTO();//获取所有用户订单数目，则知道有n个坐标点
        int pointsSize =  list.size();//坐标点的总个数
        ArrayList<Point> pointsList = new ArrayList<>();
        Point point;
        for (int i=0;i<pointsSize;i++){
            point = userOrderService.changeIntoPoint(list.get(i).getPoint());
            point.setNum(i);        //给point逐个标上序号
            point.setTimeWindow1(list.get(i).getTimeWindow1());
            point.setTimeWindow2(list.get(i).getTimeWindow2());
            point.setWeight(list.get(i).getOrderWeight());
            pointsList.add(point); //成功创建并初始化pointsList,并可输出list结果
        }
        return pointsList;
    }

    @Override
    public ArrayList<Pot> initialPotByArrayList() {
        List<UserOrderDTO> list = userOrderDao.getDTO();//获取所有用户订单数目，则知道有n个坐标点
        int pointsSize =  list.size();//坐标点的总个数
        ArrayList<Pot> pots = new ArrayList<>();
        Pot point = new Pot();
        for (int i=0;i<pointsSize;i++){
//            point.setNum(i);        //给point逐个标上序号
            point = userOrderService.changeIntoPot(list.get(i).getPoint());
            pots.add(point);
        }
        return pots;
    }

    /**
     * 通过坐标集合生成距离矩阵
     * @param pointsMap
     * @return
     */
    @Override
    public double[][] generateDistanceMatrix(HashMap<Integer, Point> pointsMap) {
        int pointsNum = pointsMap.size();
        double[][] distanceMatrix = new double[pointsNum][pointsNum];//创建二维数组作为距离矩阵
        for (int i =0;i<pointsNum;i++){
            for (int j = 0;j<pointsNum;j++){
                if ((i==j)|(i>j)){//去除相同和位置互换重复的组合
                    distanceMatrix[i][j] = 0;//不作计算的位置赋值为0.0
                    continue;
                } else {
                    Point point1 = pointsMap.get(i);
                    Point point2 = pointsMap.get(j);
                    double distance = getDistance(point1,point2);
                    distanceMatrix[i][j] = distance;
                }
            }
        }

        for (int i =0;i<pointsNum;i++){
            for (int j = 0;j<pointsNum;j++){
                if (i==j)//去除相同和位置互换重复的组合
                    continue;
                else if(i>j){
                    distanceMatrix[i][j] = distanceMatrix[j][i];//关于对角线对称的值都相等了
                }

            }
        }
//        System.out.println("距离矩阵："+ Arrays.deepToString(distanceMatrix));
        return distanceMatrix;
    }

    /**
     * 通过坐标集合生成距离矩阵
     * @param pointList
     * @return
     */
    @Override
    public double[][] generateDistanceMatrix(ArrayList<Point> pointList) {
        int pointsNum = pointList.size();
        double[][] distanceMatrix = new double[pointsNum][pointsNum];//创建二维数组作为距离矩阵
        for (int i =0;i<pointsNum;i++){
            for (int j = 0;j<pointsNum;j++){
                if ((i==j)|(i>j)){//去除相同和位置互换重复的组合
                    distanceMatrix[i][j] = 0;//不作计算的位置赋值为0.0
                    continue;
                } else {
                    Point point1 = pointList.get(i);
                    Point point2 = pointList.get(j);
                    double distance = getDistance(point1,point2);
                    distanceMatrix[i][j] = distance;
                }
            }
        }
        for (int i =0;i<pointsNum;i++){
            for (int j = 0;j<pointsNum;j++){
                if (i==j)//去除相同和位置互换重复的组合
                    continue;
                else if(i>j){
                    distanceMatrix[i][j] = distanceMatrix[j][i];//关于对角线对称的值都相等了
                }

            }
        }
        return distanceMatrix;
    }

    /**
     * 通过传入坐标集，求每两个之间的距离
     * @param pointList
     * @return
     */
    public double[] initDistance(ArrayList<Point> pointList){
        double[] distance = new double[pointList.size()];
        for (int i=0;i<pointList.size()-1;i++){
            distance[i] += getDistance(pointList.get(i),pointList.get(i+1));
        }
        return distance;
    }

    /**
     * 根据距离矩阵和速度求出对应两点间的实行时间
     * @param distanceMatrix
     * @param ss
     * @return
     */
    @Override
    public double[][] generateDriveTimeMatrix(double[][] distanceMatrix,double ss){//速度单位为：km/h
        DecimalFormat df = new DecimalFormat("#.00");
        double speed = Double.valueOf(df.format((ss*10)/36));
        int pointsNum = distanceMatrix.length;//返回二维数组的行数，也即是坐标点的数目
        double[][] timeMatrix = new double[pointsNum][pointsNum];
        for (int i =0;i<pointsNum;i++){
            for (int j = 0;j<pointsNum;j++){
                if ((i==j)|(i>j)){//去除相同和位置互换重复的组合
                    timeMatrix[i][j] = 0;//不作计算的位置赋值为0.0
                    continue;
                } else {
                    double driveTime = distanceMatrix[i][j]/speed;
                    double dTime = Double.valueOf(df.format(driveTime));
                    timeMatrix[i][j] = dTime;
                }
            }
        }
        for (int i =0;i<pointsNum;i++){
            for (int j = 0;j<pointsNum;j++){
                if (i==j)//去除相同和位置互换重复的组合
                    continue;
                else if(i>j){
                    timeMatrix[i][j] = timeMatrix[j][i];//关于对角线对称的值都相等了
                }
            }
        }
        return timeMatrix;
    }

    /**
     * @Author Hincky
     * @Date 2020/5/17 1:37
     * @Description 根据时间窗选择上午的配送订单坐标点
     * @Param
     * @Return
     * @Since version-1.0
     */
    public ArrayList<Pot> morningMission() {
        List<UserOrderDTO> list = userOrderDao.getDTO();//获取所有用户订单数目，则知道有n个坐标点
        int pointsSize =  list.size();//坐标点的总个数
        ArrayList<Pot> pots = new ArrayList<>();
        Pot point = new Pot();
        for (int i=0;i<pointsSize;i++){
//            point.setNum(i);        //给point逐个标上序号
            point = userOrderService.changeIntoPot(list.get(i).getPoint());
            pots.add(point);
        }
        return pots;
    }

    /**
     * @Author Hincky
     * @Date 2020/5/17 1:38
     * @Description 根据时间窗选择下午的配送订单坐标点
     * @Param
     * @Return
     * @Since version-1.0
     */

    public ArrayList<Pot> afternoonMission() {
        List<UserOrderDTO> list = userOrderDao.getDTO();//获取所有用户订单数目，则知道有n个坐标点
        int pointsSize =  list.size();//坐标点的总个数

//        list.stream().filter(list.contains())

        ArrayList<Pot> pots = new ArrayList<>();
        Pot point = new Pot();
        for (int i=0;i<pointsSize;i++){
//            point.setNum(i);        //给point逐个标上序号
            point = userOrderService.changeIntoPot(list.get(i).getPoint());
            pots.add(point);
        }
        return pots;
    }


    /**
     * 根据相应条件求出运输的车辆数目
     * @param pointList
     * @return
     */
    //！
    public int judgeVehicleNum(ArrayList<Point> pointList){
        int pointNum = pointList.size();
        boolean departureOnMorning = true;
        if (!departureOnMorning){//在上午出发
            String[] afternoonTimeWindowList = new String[pointNum];
            for (int i=0;i<pointNum;i++){
                afternoonTimeWindowList[i] = pointList.get(i).getTimeWindow2();
            }
        }else {//在下午出发
            String[] morningTimeWindowList = new String[pointNum];
            for (int i = 0; i < pointNum; i++) {
                morningTimeWindowList[i] = pointList.get(i).getTimeWindow1();
            }
        }
        return 0;
    }

    /**
     * 判断该路径的所有点的订单重量是否超过当前车辆的限重
     * @param tourList
     */
    public int judgeWeight(ArrayList<Point> tourList, Vehicle vehicle){
        double sum = 0;
        int pointNum = tourList.size();
        for (int i=0;i<pointNum;i++){
            sum += tourList.get(i).getWeight();
        }
        if (sum<=vehicle.getMaxLoad())
            return 200;//符合则返回200
        return 400;//不符合则返回400
    }

    /**
     * 将秒转换为xxx min,xx s 的格式
     * @param time
     * @return
     */
    public String minFormat(double time){
        DecimalFormat df = new DecimalFormat("#");
        int min = Integer.valueOf(df.format(time/60));
        int s = Integer.valueOf(df.format(time%60));
//        int min = Integer.valueOf(String.valueOf(time/60));
//        int s = Integer.valueOf(String.valueOf(time%60));
        return min+"min, "+s+"s";
    }

    /**
     * 根据坐标集
     * @param tourList
     * @param ss
     */
    @Override
    public void judgeTime(ArrayList<Point> tourList,double ss) {
        DecimalFormat df = new DecimalFormat("#.00");
        double speed = Double.valueOf(df.format((ss*10)/36));
        double[] distanceArrays = initDistance(tourList);
        int timeNum = tourList.size()-1;
        double[] timeArrays = new double[timeNum];
        for(int i = 0;i<timeNum;i++){
            timeArrays[i] = distanceArrays[i]/speed;
//            System.out.println("路径"+i+"的时间为："+timeArrays[i]);
            System.out.println("路径"+i+"的时间为："+minFormat(timeArrays[i]));
        }
        double sum = 0;
        for (int i=0;i<timeNum;i++){
            sum += timeArrays[i];
        }
        System.out.println("路程加上服务的总时间："+sum+11*60*10+"  ，即"+minFormat(sum+11*60*10));//输出时加上每个点的10分钟服务时间
    }

    /**
     * 通过蚁群最后生成的最佳坐标路径来输出每一段坐标之间的距离
     * @param tourList
     * @param bestTour
     */
    public void getTourDistanceByBestTour(ArrayList<Point> tourList,int[] bestTour){
        double[][] distanceMatrix = generateDistanceMatrix(tourList);
        DecimalFormat df = new DecimalFormat("#.00");
        double[] dis = new double[distanceMatrix.length];
        for (int i=0;i<distanceMatrix.length;i++){
//            double[i] dis = distanceMatrix[bestTour[i]][bestTour[i+1]];

            double distance = Double.valueOf(df.format(distanceMatrix[bestTour[i]][bestTour[i+1]]));
            System.out.println("从坐标"+bestTour[i]+"到坐标"+bestTour[i+1]+"的距离为："
                    +distance);
        }
    }

    /**
     * 通过蚁群最后生成的最佳坐标路径来输出每一段坐标之间的距离和时间
     * @param tourList
     * @param bestTour
     * @param ss
     */
    public void getTourTimeByBestTour(ArrayList<Point> tourList,int[] bestTour,double ss) {
        double[][] distanceMatrix = generateDistanceMatrix(tourList);
        DecimalFormat df = new DecimalFormat("#.00");
        double speed = Double.valueOf(df.format((ss*10)/36));//转换速度单位，成为m/s
        DecimalFormat intf = new DecimalFormat("#");
        double distanceSum = 0;
        double timeSum = 0;
        for (int i=0;i<distanceMatrix.length;i++){
            double distance = Double.valueOf(df.format(distanceMatrix[bestTour[i]][bestTour[i+1]]));
            int time = Integer.valueOf(intf.format(distance/speed));
            System.out.println("从坐标"+bestTour[i]+"到坐标"+bestTour[i+1]+"的距离为："
                    +distance+"时间为："+minFormat(time));

            distanceSum += distance;
            timeSum += distance/speed+(bestTour.length-1)*60*10;

        }
        System.out.println("总距离为："+df.format(distanceSum/1000)+"km,"+"总用时为："+minFormat(timeSum));
    }

    public void caculateTourTime(ArrayList<Point> tourList,int[] bestTour,double ss) {
        DecimalFormat df = new DecimalFormat("#.00");
        double speed = Double.valueOf(df.format((ss*10)/36));//转换速度单位，成为m/s
        double[] distanceArrays = initDistance(tourList);
        int timeNum = tourList.size()-1;
        double[] timeArrays = new double[timeNum];
        for(int i = 0;i<timeNum;i++){
            timeArrays[i] = distanceArrays[i]/speed;
//            System.out.println("路径"+i+"的时间为："+timeArrays[i]);
            System.out.println("路径"+i+"的时间为："+minFormat(timeArrays[i]));
        }
        double sum = 0;
        for (int i=0;i<timeNum;i++){
            sum += timeArrays[i];
        }
        System.out.println("路程加上服务的总时间："+sum+11*60*10+"  ，即"+minFormat(sum+11*60*10));//输出时加上每个点的10分钟服务时间
    }

    /**
     * 根据时间窗进行先后排序
     * @param pointArrayList
     * @return
     */
    public Point[] sortPointsByTimeWindow1(ArrayList<Point> pointArrayList){
        Point[] points = new Point[pointArrayList.size()];
        String[] T1End = new String[pointArrayList.size()];
        String[] T1Start = new String[pointArrayList.size()];
        int[] T1EndHour = new int[pointArrayList.size()];
        int[] T1EndMin = new int[pointArrayList.size()];
        Point[] sortPoints = new Point[pointArrayList.size()];
        Point sub = new Point();
        for (int i=0;i<pointArrayList.size();i++){//先比较该时间窗的结束时间，再比较开始的时间
            T1End[i] = pointArrayList.get(i).inputWindowStringForEndTime(pointArrayList.get(i).getTimeWindow1());
            T1Start[i] = pointArrayList.get(i).inputWindowStringForStartTime(pointArrayList.get(i).getTimeWindow1());
            T1EndHour[i] = Integer.valueOf(T1End[i].substring(11,13));
            T1EndMin[i] = Integer.valueOf(T1End[i].substring(14,16));
        }
        for (int i=0;i<pointArrayList.size()-1;i++){
            if(T1EndHour[i+1]>=T1EndHour[i]){
                if(T1EndHour[i+1]>=T1EndHour[i])
                    sortPoints[i] = points[i];
            }else {
                sub = points[i+1];
                points[i+1] = points[i];
                points[i] = sub;
            }
        }
        return sortPoints;
    }

    /**
     * 根据路径距离矩阵得出该路径出车的总路程长度
     * @param tourList
     * @return
     */
    public double caculateTotalLengthByPointList(ArrayList<Point> tourList){
        double[][] distanceMatrix = generateDistanceMatrix(tourList);
        int pointNum = distanceMatrix.length;
        double totalLength = 0;
        for (int i=0;i<pointNum;i++){
            for (int j=0;j<pointNum;j++){
                if (i==j){
                    continue;
                }else{
                    totalLength += distanceMatrix[i][j];
                }
            }
        }
        DecimalFormat df = new DecimalFormat("#.00");
        totalLength = Double.valueOf(df.format(totalLength/1000));
        return totalLength;
    }

    public double caculateTotalLengthByDistanceMatrix(double[][] distanceMatrix){
        int pointNum = distanceMatrix.length;
        double totalLength = 0;
        for (int i=0;i<pointNum;i++){
            for (int j=0;j<pointNum;j++){
                if (i==j){
                    continue;
                }else{
                    totalLength += distanceMatrix[i][j];
                }
            }
        }
        return totalLength;
    }

    /**
     * 根据路径时间矩阵加上每个点服务时间得出每次出车的总时间
     * @param tourList
     * @return
     */
    public double caculateTotalTime(ArrayList<Point> tourList,double ss){//待改善，每个点的服务时间可能出错了
        DecimalFormat df = new DecimalFormat("#.00");
        double speed = Double.valueOf(df.format((ss*10)/36));
        double[][] timeMatrix = generateDriveTimeMatrix(generateDistanceMatrix(tourList),speed);
        int pointNum = timeMatrix.length;
        double timeSum = 0;
        for (int i=0;i<pointNum;i++){
            for (int j=0;j<pointNum;j++){
                if (i==j){
                    continue;
                }else{
                    timeSum += timeMatrix[i][j];//加上每一点的服务时间10分钟
                }
            }
        }
        return timeSum+60*10*pointNum;
    }

    public double caculateTourLength(int[] bestTour){
        double[][] distanceMatrix = generateDistanceMatrix(initialPointsWithArrayList());
        int pointNum = distanceMatrix.length;
        double bestLength = 0;
        for (int i=0;i<pointNum;i++){
            bestLength += distanceMatrix[bestTour[i]][bestTour[i+1]];
        }
        return bestLength;
    }

    public double caculateTourTime(int[] bestTour,double ss){
        DecimalFormat df = new DecimalFormat("#.00");
        double speed = Double.valueOf(df.format((ss*10)/36));
        double[][] distanceMatrix = generateDistanceMatrix(initialPointsWithArrayList());
        int pointNum = distanceMatrix.length;
        double tourTime = 0;
        for (int i=0;i<pointNum;i++){
            tourTime += distanceMatrix[bestTour[i]][bestTour[i+1]]/speed;
        }
        return tourTime;
    }

    /**
     * 蚁群算法实施，根据map的points集合
     * @param acaDTO
     */
    @Override
    public void ACA(AcaDTO acaDTO) {
        HashMap<Integer, Point> pointHashMap = initialPoints();//return HashMap<Integer,Point>
        int pointNum = pointHashMap.size();
        double[][] distanceMatrix = generateDistanceMatrix(pointHashMap);//初始化距离矩阵
        //初始化信息素矩阵
        float[][] pheromone = new float[pointNum][pointNum];
        for (int i = 0; i < pointNum; i++) {
            for (int j = 0; j < pointNum; j++) {
                pheromone[i][j] = 0.1f;  //初始化为0.1
            }
        }
        double bestLength = Double.MAX_VALUE;
        int[] bestTour = new int[pointNum + 1];

        //随机放置蚂蚁
        int antNum = acaDTO.getAntsNum();
        float alpha = acaDTO.getAlpha();
        float beta = acaDTO.getBeta();
        Ant[] ants = new Ant[antNum];
        for (int i = 0; i < antNum; i++) {
            ants[i] = new Ant(pointNum);
            ants[i].initAnts(distanceMatrix, alpha, beta);
        }

        int MAX_GEN = acaDTO.getMAX_GEN();
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
            float rho = acaDTO.getRho();
            for (int i = 0; i < pointNum; i++)
                for (int j = 0; j < pointNum; j++)
                    pheromone[i][j] = pheromone[i][j] * (1 - rho);
            //信息素更新
            for (int i = 0; i < pointNum; i++) {
                for (int j = 0; j < pointNum; j++) {
                    for (int k = 0; k < antNum; k++) {
                        pheromone[i][j] += ants[k].getDelta()[i][j];
                    }
                }
            }
            //重新初始化蚂蚁
            for (int i = 0; i < antNum; i++) {
                ants[i].initAnts(distanceMatrix, alpha, beta);
            }
        }
        //打印最佳结果
        System.out.println("The optimal length is: " + bestLength);//最佳长度
        System.out.println("The optimal tour is: ");//最佳路径
        for (int i = 0; i < pointNum + 1; i++) {
            if(i == pointNum)
                System.out.print(bestTour[i]);
            else
                System.out.print(bestTour[i]+"->");
        }
    }

    /**
     * 蚁群算法实施，根据list的points集合计算出最优路径，并返回最优路径依次的坐标点集合
     * @param acaDTO
     */
    @Override
    public ArrayList<Integer> ACOByArrayList(ArrayList<Point> pointArrayList,AcaDTO acaDTO) {//蚂蚁数，坐标数，循环次数，alpha,beta,rho
//        ArrayList<Point> pointArrayList = initialPointsWithArrayList();//return ArrayList<Point>
        int pointNum = pointArrayList.size();
        //初始化距离矩阵
        double[][] distanceMatrix = generateDistanceMatrix(pointArrayList);
        //初始化每条路径的信息素矩阵,全为0.1
        float[][] pheromone = new float[pointNum][pointNum];
        for (int i = 0; i < pointNum; i++) {
            for (int j = 0; j < pointNum; j++) {
                pheromone[i][j] = 0.1f;  //初始化为0.1
            }
        }
        double bestLength = Double.MAX_VALUE;//初始最佳长度为0
        //创建最佳路径，记录从配送中心到各个坐标并返回配送中心的顺序
        int[] bestTour = new int[pointNum + 1];//增加多一个返回点，因为首尾的点一样，所以数量加一
        ArrayList<Integer> potList = new ArrayList<>();

        //固定点放置蚂蚁
        int antNum = acaDTO.getAntsNum();//蚂蚁数量
        float alpha = acaDTO.getAlpha();//信息素的重要程度因子，值越大，在转移中的起的作用越大
        float beta = acaDTO.getBeta();//启发函数因子，蚂蚁根据这个因子大小改变概率大小转移到距离短的坐标
        Ant[] ants = new Ant[antNum];
        for (int i = 0; i < antNum; i++) {
            ants[i] = new Ant(pointNum);
            ants[i].initAnts(distanceMatrix, alpha, beta);//初始化蚁群
        }

        int MAX_GEN = acaDTO.getMAX_GEN();//获取循环代数，准备循环
//        System.out.println("起始信息素矩阵");
//        System.out.println(Arrays.deepToString(pheromone));//全为0.1，这是ants的信息素矩阵
        for (int g = 0; g < MAX_GEN; g++) {
            for (int i = 0; i < antNum; i++) {
                for (int j = 1; j < pointNum; j++) {   //因为起始点的序号为0，所以这里开始为1
                    ants[i].selectNextPoint(pheromone);//将蚁群ants的总信息素矩阵传给蚁群中的每一只蚂蚁
                }
                ants[i].getTaboo().add(ants[i].getFirstPoint());//将起始点加到当前的蚂蚁禁忌表中
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
            float rho = acaDTO.getRho();//信息素挥发因子，0~1
            for (int i = 0; i < pointNum; i++)
                for (int j = 0; j < pointNum; j++)
                    pheromone[i][j] = pheromone[i][j] * (1 - rho);
            //信息素更新
            for (int i = 0; i < pointNum; i++) {
                for (int j = 0; j < pointNum; j++) {
                    for (int k = 0; k < antNum; k++) {
                        pheromone[i][j] += ants[k].getDelta()[i][j];
                    }
                }
            }
            //重新初始化蚂蚁
            for (int i = 0; i < antNum; i++) {
                ants[i].initAnts(distanceMatrix, alpha, beta);
            }
        }
        //打印最佳结果
//        bestLength =

        System.out.println("The optimal tour is: ");//最佳路径
        for (int i = 0; i < pointNum + 1; i++) {
            if(i == pointNum){
                System.out.print(bestTour[i]);
                System.out.println(" ");
            }
            else
                System.out.print(bestTour[i]+"->");
        }

        for (int i=0;i<pointNum;i++){
            bestLength += distanceMatrix[bestTour[i]][bestTour[i+1]];
        }
        DecimalFormat df = new DecimalFormat("#.00");
        bestLength = Double.valueOf(df.format(bestLength/1000));
        System.out.println("The optimal length is: " + bestLength+"km");//最佳长度

        double tourTime = 0;
        for (int i=0;i<pointNum;i++){
            tourTime += distanceMatrix[bestTour[i]][bestTour[i+1]]/16.7;//speed=60km/h=16.7m/s
        }
        tourTime = Double.valueOf(df.format(tourTime));
        System.out.println("The optimal tourTime is: " + tourTime+"  s"+"即"+minFormat(tourTime));

//        judgeTime(pointArrayList,60);
        getTourTimeByBestTour(pointArrayList,bestTour,60);

        ArrayList<Point> resultList = new ArrayList<>();
        for (int i=0;i<bestTour.length;i++){
            resultList.add(i,pointArrayList.get(bestTour[i]));
        }


        for (int i=0 ;i<bestTour.length;i++){
            potList.add(bestTour[i]);
        }

        return potList;
    }

    /**
     * 遗传算法实施
     */
    @Override
    public void GAByArrayList() {
        ArrayList<Point> pointArrayList = initialPointsWithArrayList();//return ArrayList<Point>
        int pointNum = pointArrayList.size();
        //初始化距离矩阵
        double[][] distanceMatrix = generateDistanceMatrix(pointArrayList);

//        distanceMatrix[pointNum - 1][pointNum - 1] = 0;
//        double bestLength = Double.MAX_VALUE;
//        int[] bestTour = new int[pointNum + 1];
//        bestT = 0;
//        t = 0;

//        int[][] newPopulation = new int[scale][cityNum];
//        int[][] oldPopulation = new int[scale][cityNum];
//        int[] fitness = new int[scale];
//        float[] Pi = new float[scale];

//        Random random = new Random(System.currentTimeMillis());


    }

}
