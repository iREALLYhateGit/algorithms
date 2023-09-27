package ru.portfolio;

import java.util.Arrays;
import java.util.Random;

public class FisherYates_Shuffle {
    public static void main(String[] args) {
        int [] arr = {1,2,3};
        fisherYatesShuffle(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void fisherYatesShuffle(int[] arr) {
        Random rand = new Random();
        int temp;

        for (int i = arr.length - 1; i >= 1; i--) {
            int j = rand.nextInt(i + 1);

            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
