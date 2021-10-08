package com.route.utils;



public class DistanceUtils {

    private static final double EARTH_RADIUS = 6378.137;// 地球半径,单位千米

    private static double rad(double d) {
        //角度转换成弧度
        return d * Math.PI / 180.0;
    }

    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);//纬度
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;//两点纬度之差
        double b = rad(lng1) - rad(lng2);//经度之差

        //计算两点之间距离的公式
        double s = 2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        //弧长乘地球半径（半径）
        s = s * EARTH_RADIUS;
        //精确距离的数值（单位千米）
        s = Math.round(s * 10000) / 10000;
        return s;

    }
}
