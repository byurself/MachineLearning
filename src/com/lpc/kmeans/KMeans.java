package com.lpc.kmeans;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author byu_rself
 * @date 2022/5/15 21:02
 */
public class KMeans {
    // 用来聚类的点集
    public List<Point> points;

    // 将聚类结果保存到文件
    FileWriter out = null;

    // 格式化double类型的输出，保留两位小数
    DecimalFormat dFormat = new DecimalFormat("00.00");

    // 具体执行聚类的对象
    public KMeansCluster kMeansCluster;

    // 簇的数量，迭代次数
    public int numCluster = 5;
    public int numIterator = 200;

    // 点集的数量，生成指定数量的点集
    public int numPoints = 50;

    // 聚类结果保存路径
    public static final String FILEPATH = "src/com/lpc/kmeans/result.txt";

    public static void main(String[] args) {
        // 指定点集个数，簇的个数，迭代次数
        KMeans kmeans = new KMeans(100, 5, 200);

        // 初始化点集、KMeansCluster对象
        kmeans.init();

        // 使用KMeansCluster对象进行聚类
        kmeans.runKmeans();

        kmeans.printRes();
        kmeans.saveResToFile(FILEPATH);
    }

    public KMeans(int numPoints, int clusterNumber, int iteratorNumber) {
        this.numPoints = numPoints;
        this.numCluster = clusterNumber;
        this.numIterator = iteratorNumber;
    }

    private void init() {
        this.initPoints();
        kMeansCluster = new KMeansCluster(numCluster, numIterator, points);
    }

    private void runKmeans() {
        kMeansCluster.runKmeans();
    }

    // 初始化点集
    public void initPoints() {
        points = new ArrayList<>(numPoints);

        Point tmpPoint;

        for (int i = 0; i < numPoints; i++) {
            tmpPoint = new Point(Math.random() * 150, Math.random() * 100);
            points.add(tmpPoint);
        }
    }

    public void printRes() {

        System.out.println("==================Centers-I====================");
        for (Point center : kMeansCluster.centers) {
            System.out.println(center.toString());
        }

        System.out.println("==================Points====================");

        for (Point point : points) {
            System.out.println(point.toString());
        }
    }

    public void saveResToFile(String filePath) {
        try {
            out = new FileWriter(filePath);
            for (Point point : points) {
                out.write(String.valueOf(point.getClusterID()));
                out.write("  ");
                out.write(dFormat.format(point.getX()));
                out.write("  ");
                out.write(dFormat.format(point.getY()));
                out.write("\r\n");
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
