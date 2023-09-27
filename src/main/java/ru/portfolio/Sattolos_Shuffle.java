package ru.portfolio;

import java.util.Arrays;
import java.util.Random;

public class Sattolos_Shuffle {
    public static void main(String[] args) {
        int [] arr = {1,2,3};
        sattoloAlgorithm(arr);
        System.out.println(Arrays.toString(arr));
    }
    static void sattoloAlgorithm(int[] arr) {
        int temp;
        int i = arr.length;
        Random rand = new Random();

        while (i > 1) {
            i--;
            int j = rand.nextInt(i);
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
