package ru.portfolio.math.stat;

import java.util.*;

public class MathStatAlgo2 {
    static int iter = 1;
    public static double getZ(double x, double y){
       return Math.pow(Math.pow(x,2) + y - 11,2) + Math.pow(x + Math.pow(y,2) - 7,2);
//        return Math.pow(x,3) + 0.2*Math.pow(y,3) - 3*Math.pow(x,2)*y + 12*y;
    }
    public static double getZ(Point point){
        return Math.pow(Math.pow(point.x(), 2) + point.y() - 11,2) + Math.pow(point.x() + Math.pow(point.y(), 2) - 7,2);
//        return Math.pow(point.x(), 3) + 0.2*Math.pow(point.y(), 3) - 3*Math.pow(point.x(), 2)*point.y() + 12*point.y();
    }
    public static void coordinate_descent(double x, double y, double step, double eps){
        System.out.println("***Метод спуска с постоянным шагом***" + "\n" + "x = " + x +
                "; y = " + y + "; step = " + step + "; epsilon = " + eps);
        double prev_x, prev_y;
        double x1,x2,y1,y2;
        for (int i = 0; i < 1000; i++){ // max_iter = 1000
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
            if(iter < 5)
                System.out.println("iter = " + iter + "; x = " + x +
                        "; y = " + y + "; step = " + step + "; epsilon = " + eps);
            iter ++;
        }
        System.out.println("Local min in:" +
                "  " + x + "  " + y + "  || Amount of iterations: " + --iter + "\n");
        iter = 1;
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
    static void getMinByNelderMead(ArrayList<Point> list, double epsilon){   //worst = 0, good = 1, best = 2
        PointComparator pComp = new PointComparator();
        list.sort(pComp);

        //hashmap consists of 3 points: best, worst and good. The best has the lowest Z value,
        // the worst has max Z value, and good has max Z value after the worst.
        HashMap<String,Point> map = new HashMap<>();
        map.put("worst",list.get(2));
        map.put("good",list.get(1));
        map.put("best",list.get(0));
        System.out.println("***Nelder-Mead's method***" + "\n" + "list of starting points: " + map +
                " \nepsilon = " + epsilon + " - площадь треугольника");

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
                    System.out.println("Shrink");
                }
            }

            list.clear();
            list.addAll(map.values());
            list.sort(pComp);
            map.replace("worst",list.get(2));
            map.replace("good",list.get(1));
            map.replace("best",list.get(0));
            if (iter < 5)
                System.out.println("iter = " + iter + "; worst point = " + map.get("worst") +
                        "; good point = " + map.get("good") + "; best point = " + map.get("best"));
            iter++;
        }
        System.out.println("Local min in:  " + map.get("best").x() + "  " + map.get("best").y() +
                "  || Amount of iterations: " + iter + "\n");
        iter = 1;
    }
    public static void getGradientMin(Point point, double step, double epsilon){

        System.out.println("*****Gradient method*****");
        System.out.println("starting point: x = " + point.x() + " y = " + point.y()
                + "   step = " + step + "  epsilon = " + epsilon);

        Point gradP = new Point(partial_derivative_x(point.x(),
                point.y()),partial_derivative_y(point.x(), point.y()));
        while( Math.abs(getPiphagor(gradP)) >= epsilon && iter < 1000){
            point = Point.getGradP(point,gradP,step);
            gradP = new Point(partial_derivative_x(point.x(), point.y()),
                    partial_derivative_y(point.x(), point.y()));
            if(iter < 5){
                System.out.println("iter = " + iter + "; " + "x = " +
                        point.x() + ";  y = " + point.y());
            }
            iter++;
        }
        System.out.println("Local min in:  " + point.x() + "  " + point.y() +
                "  || Amount of iterations: " + iter + "\n");
        iter = 1;
    }

    public static double partial_derivative_x(double x, double y){
//        return 2*(Math.pow(x,2) + y - 11)*2*x + 2*(x + Math.pow(y,2) - 7);
        return 3*Math.pow(x,2) - 6*x;
    }public static double partial_derivative_y(double x, double y){
//        return 2*(Math.pow(x,2) + y - 11) + 2*(x + Math.pow(y,2) - 7)*2*y;
        return 0.6*Math.pow(y,2) - 3*Math.pow(x,2) + 12;
    }
    public static double getPiphagor(Point point){
        return Math.pow(point.x() * point.x() + point.y() * point.y(), 0.5d);
    }
    public static void main(String[] args) {
        System.out.println();
        System.out.println(" x и y - начальная точка" + "; step - шаг; " +
                "epsilon - когда стоит остановиться");
        //System.out.println("\n" + "Функция Химмельблау");
        //метод покоординатного спуска с шагом 0.01, пока точность не будет меньше 0.0001
        coordinate_descent(50,50,1, 0.01d);

        //создаём точки для треугольника метода Нелдера-Мида
        Point point1 = new Point(-100,100);
        Point point2 = new Point(-20,20);
        Point point3 = new Point(-30,50);
        System.out.println("Выбираем 3 точки - вершины треугольника, и площадь треугольника  - epsilon, \n" +
                "при которой стоит остановиться ");
        //метод Нелдера-Мида: цикл выполянется, пока площадь треугольника не станет меньше заданного epsilon.
        getMinByNelderMead(new ArrayList<>(Arrays.asList(point1,point2,point3)),0.01d);
        getGradientMin(new Point(30,30),0.01d,0.01d);
    }
}

record Point(double x, double y) {
    public static Point getMiddlePoint(Point p1, Point p2) {
        return new Point((p1.x() + p2.x()) / 2,
                (p1.y() + p2.y()) / 2);
    }

    public static Point getMirroredPoint(Point middlePoint, Point currentPoint) {
        if (middlePoint.x() < currentPoint.x() && middlePoint.y() < currentPoint.y())
            return new Point(middlePoint.x() - Math.abs(currentPoint.x() - middlePoint.x()),
                    middlePoint.y() - Math.abs(currentPoint.y() - middlePoint.y()));
        else if (middlePoint.x() < currentPoint.x() && middlePoint.y() >= currentPoint.y())
            return new Point(middlePoint.x() - Math.abs(currentPoint.x() - middlePoint.x()),
                    middlePoint.y() + Math.abs(currentPoint.y() - middlePoint.y()));
        else if (middlePoint.x() >= currentPoint.x() && middlePoint.y() < currentPoint.y())
            return new Point(middlePoint.x() + Math.abs(currentPoint.x() - middlePoint.x()),
                    middlePoint.y() - Math.abs(currentPoint.y() - middlePoint.y()));
        else
            return new Point(middlePoint.x() + Math.abs(currentPoint.x() - middlePoint.x()),
                    middlePoint.y() + Math.abs(currentPoint.y() - middlePoint.y()));
    }

    public static Point getChangedP(Point mirroredP, Point middleP, Way way) {
        if (way == Way.STRETCH)
            return Point.getMirroredPoint(mirroredP, middleP);
        else
            return Point.getMirroredPoint(middleP, mirroredP);
    }

    public static void shrinkage(Point best, Point... p) {
        for (Point point : p) {
            point = Point.getMiddlePoint(best, point);
        }
    }

    public static Point getGradP(Point startPoint, Point gradPoint, double step){
        return new Point(startPoint.x - step*gradPoint.x,startPoint.y - step*gradPoint.y);
    }

    public static double getArea(Point p1, Point p2, Point p3) {
        double a = Math.pow(Math.pow(p1.x() - p2.x(), 2) + Math.pow(p1.y() - p2.y(), 2), 0.5d);
        double b = Math.pow(Math.pow(p2.x() - p3.x(), 2) + Math.pow(p2.y() - p3.y(), 2), 0.5d);
        double c = Math.pow(Math.pow(p1.x() - p3.x(), 2) + Math.pow(p1.y() - p3.y(), 2), 0.5d);
        double halfPerimeter = (a + b + c) / 2;
        return Math.pow(halfPerimeter * (halfPerimeter - a) * (halfPerimeter - b) * (halfPerimeter - c), 0.5d);
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
        return Double.compare(MathStatAlgo2.getZ(point1.x(), point1.y()), MathStatAlgo2.getZ(point2.x(), point2.y()));
    }
}
