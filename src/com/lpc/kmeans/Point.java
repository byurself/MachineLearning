package com.lpc.kmeans;

/**
 * @author byu_rself
 * @date 2022/5/15 21:04
 */
public class Point {
    // 点的坐标
    private Double x;
    private Double y;

    // 所在类ID
    private int clusterID = -1;

    public Point(Double x, Double y) {

        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return getClusterID() + " " + this.x + " " + this.y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public int getClusterID() {
        return clusterID;
    }

    public void setClusterID(int clusterID) {
        this.clusterID = clusterID;
    }
}
