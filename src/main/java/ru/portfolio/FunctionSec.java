package ru.portfolio;

public class FunctionSec {
    static int iter;
    public static double getZ(double x, double y){
        return Math.pow(Math.pow(x,2) + y - 11,2) + Math.pow(x + Math.pow(y,2) - 7,2);
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
    /*public static double partial_derivative_x(double x, double y){
        return 2*(Math.pow(x,2) + y - 11)*2*x + 2*(x + Math.pow(y,2) - 7);
    }public static double partial_derivative_y(double x, double y){
        return 2*(Math.pow(x,2) + y - 11) + 2*(x + Math.pow(y,2) - 7)*2*y;
    }*/
    public static void main(String[] args) {
        coordinate_descent(0,0,0.01, 0.0001);
    }
}
