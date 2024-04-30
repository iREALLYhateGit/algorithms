package ru.portfolio;

import java.io.IOException;
import java.util.Scanner;

public class Backpack {
    static int kolvo;

    static int maxWeight;
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        maxWeight = scanner.nextInt();    //capacity of knapsack
        kolvo = scanner.nextInt();     //number of products

        int weight [] = new int[kolvo];   // array of weights
        int price [] = new int[kolvo];    //array of prices
        for(int i = 0; i < kolvo; i++){
            weight[i] = scanner.nextInt();
            price[i] = scanner.nextInt();
        }

        int itog = findKnapSack(weight,price);
        System.out.println(itog);
    }
    /*  row = product
        column = 1 ... maxWeight

        array column starts with 0 till maxWeight - 1, so column in array is always reduced by 1

        The thing is that specified cell equals max:: between the upper cell value and
                current product weight + the rest occupied space product

    */

    private static int findKnapSack(int [] weight, int [] price){

        int table [][] = new int[kolvo][maxWeight];

        for(int i = 0; i < kolvo; i ++) {
            for (int k = 1;  k <= maxWeight; k++) {
                    table[i][k-1] = Integer.max(i > 0 ? table[i - 1][k - 1]: 0, //if row = 0 then 0 else the upper cell
                            weight[i] <= k ?  // the second number, if the product can be placed than ^lower^ else 0
                                    price[i] + // if there is an extra space and such cell exists than take it from the upper row else 0
                                            (i > 0 && k - weight[i] - 1 >= 0? table[i - 1][k - weight[i] - 1] : 0)
                            : 0);
                System.out.print(table[i][k-1] + " ");
            }
            System.out.println();
        }
        return table[kolvo - 1][maxWeight - 1];
    }
}
