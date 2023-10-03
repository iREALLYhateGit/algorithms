package ru.portfolio;

public class Shennon {
    static double countLog(double x)
    {
        return x*(Math.log(x) / Math.log(2));
    }
    public static void main(String[] args) {
        double res = 0;
        double [] input = {0.145, 0.095, 0.074, 0.064, 0.064, 0.056, 0.056, 0.047,
                0.041, 0.039, 0.036, 0.029, 0.026, 0.026, 0.024, 0.021,
                0.019, 0.016, 0.015, 0.015, 0.015, 0.014, 0.013, 0.01,
                0.009, 0.008, 0.007, 0.006, 0.004, 0.003, 0.003, 0.002 };
        for (double v : input) {
            res += countLog(v);
        }
        System.out.println(-res);
    }
}
