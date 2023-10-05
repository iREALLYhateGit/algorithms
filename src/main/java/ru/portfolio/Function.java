package ru.portfolio;

import java.util.Scanner;

enum Direction{
    MAX, MIN
}
public class Function {

    /* CONST_FI is a const value defined for Golden Section algorithm
     */
    private final double CONST_FI = (1 + Math.sqrt(5)) / 2;
    private double x;
    int iter;
    int Alliter;
    public static double getY(double x){
        return x*Math.pow(x - 1,2)*Math.pow(x-3,3);
    }

    public static double getPrimeY(double x){
        return 6*Math.pow(x,5) - 55 * Math.pow(x,4) + 184*Math.pow(x,3) - 270*Math.pow(x,2)+162*x - 27;
    }
    public static double getSecPrimeY(double x){
        return 30*Math.pow(x,4) - 220 * Math.pow(x,3) + 552*Math.pow(x,2) - 540*x+162;
    }
    public static double getThirdPrimeY(double x){
        return 120*Math.pow(x,3) - 660 * Math.pow(x,2) + 1104*x - 540;
    }

    /**
     * This method takes 3 parameters, then it checks every section of the step size
     * and try to find whether this section contains any extremum
     * @param start = left boundary for function
     * @param end = right boundary for function
     * @param step = step
     * If there is an extremum inside the function, it causes the execution of clarify method
     */
    public void scan(double start, double end, double step){
        double curr = start + step;
        double res;
        while(curr < end){
            if(getY(curr) <= getY(curr + step) && getY(curr) <= getY(curr - step)){
                System.out.println("Min is inside: [" + (curr - step)
                        + " :: " + (curr + step) + "]");
                res = clarify(curr - step, curr + step,step/100,Direction.MIN);
                System.out.println("precise value of Min ( x axis): " + res);
                System.out.println("Amount of iterations = " + iter + "\n");
                iter = 0;
            }
            else if(getY(curr) >= getY(curr + step) && getY(curr) >= getY(curr - step)){
                System.out.println("Max is inside: [" + (curr - step)
                        + " :: " + (curr + step) + "]" );
                res = clarify(curr - step, curr + step,step/100,Direction.MAX);
                System.out.println("precise value of Max (x axis): " + res);
                System.out.println("Amount of iterations = " + iter + "\n");
                iter = 0;
            }
            curr += step;
        }
    }

    /** Golden Section algorithm:
     * @param leftB = left boundary
     *              @param rightB = right boundary
     *                            @param step = step.
     * @param dir = to indicate whether we want to find the max or min on the segment
    Then this method computes 2 points, that is under one constraint
    ( left boundary <-> x2 = x1 <-> right boundary).

    In case we intend to find the min: if the value in x1 is higher -> take x1 as the next left boundary
    This lasts till we run up against the difference between boundaries lower than the defined step.
     @return extremum of the function inside the section
     **/
    private double clarify(double leftB, double rightB, double step, Direction dir){
        double x1, x2;
        while (Math.abs(rightB - leftB) >= step){
            iter++;
            Alliter++;
            x1 = rightB - (rightB - leftB) / CONST_FI;
            x2 = leftB + (rightB - leftB) / CONST_FI;
            switch (dir){
                case MIN:
                    if (getY(x1) >= getY(x2))
                        leftB = x1;
                    else
                        rightB = x2;
                    break;
                case MAX:
                    if (getY(x1) <= getY(x2))
                        leftB = x1;
                    else
                        rightB = x2;
                    break;
            }
        }
        return (leftB + rightB) / 2;
    }

    /**
     * This method id defined in order to find roots of function
     * @param leftB - left boundary of the segment
     * @param rightB - right boundary of the segment
     * @param epsilon - the step
     */

    public static void classicTon(double leftB, double rightB, double epsilon) {
        double curr = leftB;
        while(curr < rightB) {
            if(Math.abs(getY(curr)/getPrimeY(curr)) <= epsilon){
                System.out.println("New zero: " + curr);
                curr += 0.5;
            }
            else{
                curr += Math.abs(getY(curr)/getPrimeY(curr));
            }
        }
    }

    /**
     * Modified Newton's methods, used to find the minimum of function.
     * First of all, it compares 0 with multiplication of the first and third
     * derivatives of the predefined function.
     * If the multiplication beats, we use this side as a start pointer.
     * Otherwise, we would pick another side.
     * @param leftB left side
     * @param rightB right side
     * @param epsilon precise class
     */
    public static void newTon(double leftB, double rightB, double epsilon) {
        double last = 0;
        double curr;
//        System.out.println(getPrimeY(leftB) + "  " + getThirdPrimeY(leftB));
        if(getPrimeY(leftB)*getThirdPrimeY(leftB) > 0){
            last = leftB;
            curr = last + Math.abs(getPrimeY(last)/getSecPrimeY(last));
            while(Math.abs(curr - last) >= epsilon && last < rightB) {
                last = curr;
                curr = last + Math.abs(getPrimeY(last)/getSecPrimeY(last));
            }
        }
        else {
            last = rightB;
            curr = last + Math.abs(getPrimeY(last)/getSecPrimeY(last));
            while(Math.abs(curr - last) >= epsilon && last > leftB) {
                last = curr;
                curr = last - Math.abs(getPrimeY(last)/getSecPrimeY(last));
            }
        }
        System.out.println("local min in: " + last);
    }

    public static void main(String[] args) {
        Function f = new Function();
        f.scan(-5,5, 0.1);
        classicTon(2.5,3.5,0.01); // необходимо правильно задавать диапазон, иначе не сработает
        newTon(-5,5,0.01);
        //System.out.println(f.Alliter);
        //System.out.println("Do you want to draw the graphic?");
//        Scanner scanner = new Scanner(System.in);
//        if (scanner.next().equals("Yes"))
//            Graphic.drawGraphic();
    }
}

