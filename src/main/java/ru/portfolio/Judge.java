package ru.portfolio;

public class Judge {
    public static void main(String[] args) {
        int [] [] pop = {
                {2,1,4,3,5,6},
                {2,1,3,4,5,6},
                {3,2,4,1,6,5},
                {3,2,5,1,4,6}
        };
//        int [] [] mass = {
//                {2,2,2,2},
//                {1,1,1,1},
//                {3,2,3,3},
//                {4,4,4,4},
//                {5,5,5,5},
//                {6,6,6,6}
//        };
        int [] [] mass = {
                {2,2,3,3},
                {1,1,2,2},
                {4,3,4,5},
                {3,4,1,1},
                {5,5,6,4},
                {6,6,5,6}
        };
        int m =4, n = 6;
        double d;
        double ff [] = new double[6];
        for (int i = 0; i < 6; i ++){
            d = 0;
            for (int j = 0; j < 4; j++) {
                d += mass[i][j] - 0.5d*(n+1);
            }
            ff[i] = Math.pow(d,2);
        }
        d = 0;
        for (double i: ff) {
            d += i;
        }
        double w = 12*d/Math.pow(m,2)/(Math.pow(n,3) - n);
        System.out.println("Коэффициент конкордации = согласованность оценок:  " + w);
        System.out.println("Норма примерно 0.7 - 0.8");
    }
}