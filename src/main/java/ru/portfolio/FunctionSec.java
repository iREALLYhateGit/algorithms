package ru.portfolio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class FunctionSec {
    static int iter;
    public static double getZ(double x, double y){
        return Math.pow(Math.pow(x,2) + y - 11,2) + Math.pow(x + Math.pow(y,2) - 7,2);
//        return Math.pow(x,3) + 0.2*Math.pow(y,3) - 3*Math.pow(x,2)*y + 12*y;
    }
    public static double getZ(Point point){
        return Math.pow(Math.pow(point.getX(), 2) + point.getY() - 11,2) + Math.pow(point.getX() + Math.pow(point.getY(), 2) - 7,2);
//        return Math.pow(x,3) + 0.2*Math.pow(y,3) - 3*Math.pow(x,2)*y + 12*y;
    }
    public static void coordinate_descent(double x, double y, double step, double eps){
        iter = 0;
        double prev_x, prev_y;
        double x1,x2,y1,y2;
        for (int i = 0; i < 1000; i++){ // max_iter = 1000
            iter ++;
            prev_x = x;  // keep in memory previous results
            prev_y = y;
            x1 = x + step;   // adding step
            x2 = x - step;
            x = getMin(x1,y,x2,y,true);
            x = getMin(prev_x,y,x,y,true);
            y1 = y + step;  // adding step for y
            y2 = y - step;
            y = getMin(x,y1,x,y2,false);
            y = getMin(x,prev_y,x,y,false);
            if(Math.abs(x - prev_x) < eps && Math.abs(y - prev_y) < eps)
                break;
        }
        System.out.println("***Метод спуска с постоянным шагом***" + "\n" + "Local min in:" +
                "  " + x + "  " + y + "  || Amount of iterations: " + iter);
    }
    public static double getMin(double x1,double y1,double x2, double y2, boolean isX){
        if(getZ(x1,y1) < getZ(x2,y2)){
            if(isX)
                return x1;
            else
                return y1;
        }
        else
            if(isX)
                return x2;
            else
                return y2;
    }
    static double getMinByNelderMead(ArrayList<Point> list){   //worst = 0, good = 1, best = 2
        final double epsilon = 0.001d;
        list.sort(new PointComparator());

        //hashmap consists of 3 points: best, worst and good
        HashMap<String,Point> map = new HashMap<>();
        map.put("worst",list.get(0));
        map.put("good",list.get(1));
        map.put("best",list.get(2));

        Point tempr;
        Point middle;
        while(getZ(list.get(0)) > epsilon){
            middle = Point.getMiddlePoint(map.get("best"), map.get("good"));
            tempr = Point.getMirroredPoint(middle,map.get("worst"));

        }
        return 0;
    }
    /*public static double partial_derivative_x(double x, double y){
        return 2*(Math.pow(x,2) + y - 11)*2*x + 2*(x + Math.pow(y,2) - 7);
    }public static double partial_derivative_y(double x, double y){
        return 2*(Math.pow(x,2) + y - 11) + 2*(x + Math.pow(y,2) - 7)*2*y;
    }*/
    public static void main(String[] args) {
        coordinate_descent(0,0,0.01, 0.0001);
        Point point1 = new Point(0,0);
        Point point2 = new Point(2,2);
        Point point3 = new Point(2,0);
        System.out.println(getMinByNelderMead(new ArrayList<>(Arrays.asList(point1,point2,point3))));
    }
}
class Point{
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private double x;
    private double y;

    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }

    public static Point getMiddlePoint(Point p1, Point p2){
        return new Point((p1.getX() + p2.getX())/2,
                (p1.getY() + p2.getY())/2);
    }

    // not valid, rewrite some info
    public static Point getMirroredPoint(Point middlePoint, Point currentPoint){
        if(middlePoint.getX() < currentPoint.getX() && middlePoint.getY() < currentPoint.getY())
                return new Point(currentPoint.getX()- middlePoint.getX(),
                        currentPoint.getY() - middlePoint.getY());
        else if(middlePoint.getX() < currentPoint.getX() && middlePoint.getY() >= currentPoint.getY())
            return new Point(middlePoint.getX()- currentPoint.getX(),
                    currentPoint.getY() - middlePoint.getY());
        else if(middlePoint.getX() >= currentPoint.getX() && middlePoint.getY() < currentPoint.getY())
            return new Point(middlePoint.getX()- currentPoint.getX(),
                    currentPoint.getY() - middlePoint.getY());
        else
            return new Point(middlePoint.getX()- currentPoint.getX(),
                    currentPoint.getY() - middlePoint.getY());
        }
    public static Point getChangedP(Point mirroredP, Point middleP, Way way) {
        if(way == Way.STRETCH)
            return Point.getMirroredPoint()
    }
    public static Point gett(Point p1, Point p2){
        return new Point((p1.getX() + p2.getX())/2,
                (p1.getY() + p2.getY())/2);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
enum Way{
    STRETCH, CONTRACTION
}
class PointComparator implements Comparator<Point> {

    @Override
    public int compare(Point point1, Point point2) {
        return Double.compare(FunctionSec.getZ(point1.getX(), point1.getY()), FunctionSec.getZ(point2.getX(), point2.getY()));
    }
}
class HashSort{
    public int compare(Point point1, Point point2) {
        return Double.compare(FunctionSec.getZ(point1.getX(), point1.getY()), FunctionSec.getZ(point2.getX(), point2.getY()));
    }
}
