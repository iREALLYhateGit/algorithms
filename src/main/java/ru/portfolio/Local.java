package ru.portfolio;

public class Local {
//    public static void main(String[] args) {
//        double x [] = {4.25, 4.21, 4.23, 4.21, 4.25, 4.23, 4.26, 4.22, 4.21, 4.23, 4.31, 4.21, 4.25, 4.24, 4.26, 4.22};
//        System.out.println(x.length);
//        double sum = 0;
//        for(double d: x){
//            sum += d;
//        }
//        System.out.println(sum);
//        double avg = sum/ (double) x.length;
//        System.out.println(avg);
//        double s = 0;
//        for(double d: x){
//            s += Math.pow((d - avg), 2);
//        }
//        s /= 15d;
//        s = Math.pow(s, 0.5d);
//        System.out.println(s);
//        for (double d : x) {
//            System.out.println((avg - d)/ s + ":  for Xi = " + d);
//        }
//    }
public static void main(String[] args) {
    double x [] = {40.4, 41, 40.2, 40, 43.5, 42.7, 40.3, 40.4, 40.8};
    System.out.println(x.length);
    double sum = 0;
    for(double d: x){
        sum += d;
    }
    double avg = sum / (double) x.length;
    System.out.println(sum);
    System.out.println(avg);
    double s = 0;
    for(double d: x){
        s += Math.pow((d - avg), 2);
    }
    s /= 8;
    s = Math.pow(s, 0.5d);
    System.out.println(s + " = s");
    double snorm = s / Math.pow(x.length, 0.5d);
    System.out.println(snorm + " = snorm");
    double itog1 = avg  - 3.355*snorm;
    double itog2 = avg  + 3.355*snorm;
    System.out.println(itog1 + " avg " + itog2);
    System.out.println(itog2 - itog1);
}
}
