package ru.portfolio;

import java.util.*;

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
    static void getMinByNelderMead(ArrayList<Point> list){   //worst = 0, good = 1, best = 2
        double epsilon = 0.0001d;
        PointComparator pComp = new PointComparator();
        list.sort(pComp);

        //hashmap consists of 3 points: best, worst and good. The best has the lowest Z value,
        // the worst has max Z value, and good has max Z value after the worst.
        HashMap<String,Point> map = new HashMap<>();
        map.put("worst",list.get(2));
        map.put("good",list.get(1));
        map.put("best",list.get(0));


//        System.out.println(map.get("worst"));
//        System.out.println(getZ(map.get("worst")));
//        System.out.println(map.get("best"));
//        System.out.println(getZ(map.get("best")));

        Point tempr;
        Point middle;
        Point stretchedOrcontracted;

        //System.out.println(Point.getArea(map.get("worst"),map.get("good"),map.get("best")));

        while(Point.getArea(map.get("worst"),map.get("good"),map.get("best")) > epsilon){
            middle = Point.getMiddlePoint(map.get("best"), map.get("good"));
            //System.out.println(middle + " : " + getZ(middle));

            tempr = Point.getMirroredPoint(middle,map.get("worst"));
            //System.out.println("tempr = " + tempr);

            if(getZ(tempr) <= getZ(map.get("best"))){
                stretchedOrcontracted = Point.getChangedP(tempr,middle,Way.STRETCH);
                if(getZ(stretchedOrcontracted) <= getZ(map.get("best")))
                    map.replace("worst",stretchedOrcontracted);
                else
                    map.replace("worst",tempr);
            } else if (getZ(tempr) <= getZ(map.get("good")))
                map.replace("worst",tempr);

            //compare worst point with mirrored one
            else{
                if(getZ(tempr) <= getZ(map.get("worst")))
                    stretchedOrcontracted = Point.getMiddlePoint(tempr,middle);
                else
                    stretchedOrcontracted = Point.getMiddlePoint(middle,map.get("worst"));
                //compare the point obtained by contraction with the worst one
                if(getZ(stretchedOrcontracted) <= getZ(map.get("worst")))
                    map.replace("worst",stretchedOrcontracted);
                else{
                    Point.shrinkage(map.get("best"),map.get("worst"), map.get("good"));
                }
            }

            list.clear();
            list.addAll(map.values());
            list.sort(pComp);
            map.replace("worst",list.get(2));
            map.replace("good",list.get(1));
            map.replace("best",list.get(0));
//            System.out.println(map.get("worst"));
//            System.out.println(map.get("good"));
//            System.out.println(map.get("best"));
//            System.out.println(" ");
//            System.out.println("end of line");
//            System.out.println(" ");
        }
        System.out.println("***Nelder-Mead's method***");
        System.out.println("Local min in: " + map.get("best").getX() + "  " + map.get("best").getY());
        System.out.println("Amount of iterations later");
//        System.out.println(getZ(map.get("best")));
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
        getMinByNelderMead(new ArrayList<>(Arrays.asList(point1,point2,point3)));
    }
}
class Point{
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private final double x;
    private final double y;

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public static Point getMiddlePoint(Point p1, Point p2){
        return new Point((p1.getX() + p2.getX())/2,
                (p1.getY() + p2.getY())/2);
    }

    public static Point getMirroredPoint(Point middlePoint, Point currentPoint){
        if(middlePoint.getX() < currentPoint.getX() && middlePoint.getY() < currentPoint.getY())
                return new Point(middlePoint.getX() - Math.abs(currentPoint.getX() - middlePoint.getX()),
                        middlePoint.getY() - Math.abs(currentPoint.getY() - middlePoint.getY()));
        else if(middlePoint.getX() < currentPoint.getX() && middlePoint.getY() >= currentPoint.getY())
            return new Point(middlePoint.getX() - Math.abs(currentPoint.getX() - middlePoint.getX()),
                    middlePoint.getY() + Math.abs(currentPoint.getY() - middlePoint.getY()));
        else if(middlePoint.getX() >= currentPoint.getX() && middlePoint.getY() < currentPoint.getY())
            return new Point(middlePoint.getX() + Math.abs(currentPoint.getX() - middlePoint.getX()),
                    middlePoint.getY() - Math.abs(currentPoint.getY() - middlePoint.getY()));
        else
            return new Point(middlePoint.getX() + Math.abs(currentPoint.getX() - middlePoint.getX()),
                    middlePoint.getY() + Math.abs(currentPoint.getY() - middlePoint.getY()));
        }
    public static Point getChangedP(Point mirroredP, Point middleP, Way way) {
        if(way == Way.STRETCH)
            return Point.getMirroredPoint(mirroredP,middleP);
        else
            return Point.getMirroredPoint(middleP,mirroredP);
    }
    public static void shrinkage(Point best, Point...p){
        for (Point point: p) {
            point = Point.getMiddlePoint(best,point);
        }
    }

    public static double getArea(Point p1, Point p2, Point p3){
        double a = Math.pow(Math.pow(p1.getX() - p2.getX(),2) + Math.pow(p1.getY() - p2.getY(),2),0.5d);
        double b = Math.pow(Math.pow(p2.getX() - p3.getX(),2) + Math.pow(p2.getY() - p3.getY(),2),0.5d);
        double c = Math.pow(Math.pow(p1.getX() - p3.getX(),2) + Math.pow(p1.getY() - p3.getY(),2),0.5d);
        double halfPerimeter = (a + b + c)/2;
        return Math.pow(halfPerimeter*(halfPerimeter - a)*(halfPerimeter - b)*(halfPerimeter - c), 0.5d);
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
