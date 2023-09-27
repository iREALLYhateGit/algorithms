package ru.portfolio;

import java.util.Arrays;
import java.util.Scanner;

public class Knapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int kolvo = scanner.nextInt();
        int weight = scanner.nextInt();
        int [] costslist = new int[kolvo];
        int[] weightslist = new int[kolvo];
        for(int i =0; i < kolvo; i++){
            costslist[i] = scanner.nextInt();
        }
        for(int i =0; i < kolvo; i++){
            weightslist[i] = scanner.nextInt();
        }
        System.out.println(backpack(kolvo,weight, costslist, weightslist));
    }

    // возвращает максимальную стоимость, которую может унести воришка
    public static int backpack(int kolvo, int weight, int[] costslist, int[] weightslist){
        int [][] mass = new int [kolvo+1][weight + 1];
        boolean isFirstline = true;
        int diff;
        for(int outer = 0; outer < mass.length; outer++){
            if(isFirstline){
                //заполенение массива вручную
//                for(int line = 0; line < mass[0].length; line++){
//                    mass[0][line] = 0;
//                }
                Arrays.fill(mass[0],0);
                isFirstline = false;
            }
            else{
                mass[outer][0] = 0;
                for(int i = 1; i < mass[outer].length; i++){
                    diff = i - weightslist[outer - 1];
                    if(diff < 0){
                        mass[outer][i] = mass[outer - 1][i];
                    }
                    else{
                        mass[outer][i] = Math.max(mass[outer-1][i], costslist[outer-1] + mass[outer-1][diff]);
                    }
                }
            }
        }
        //для проверки массива
//        for(int[] m : mass){
//            System.out.println("");
//            for(int k : m){
//                System.out.print(k);
//            }
//        }
        return mass[kolvo][weight];
    }
}