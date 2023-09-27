package ru.portfolio;
import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int arr[] = new int[]{10,45,23,324,234,234,23,23,567,12,34, 989, 1, 47};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i])
                max = arr[i];
        }
        //or we may use streams
        //int max = Arrays.stream(arr).max().getAsInt();

        for (int s = 1; max / s > 0; s *= 10)
            countingSortForRadix(arr, s);

          //checking the results
//        for(int i : arr){
//            System.out.println(i);
//        }
    }
    // for 1-digit numbers
    static void countingSortForRadix(int[] arr, int s) {
        // amount of digits from 0 to 9
        int[] countingArray = new int[10];
        for (int j : arr) countingArray[(j / s) % 10]++;

        // sum of digits smaller than this + this
        for (int i = 1; i < 10; i++)
            countingArray[i] += countingArray[i - 1];

        int[] outputArray = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--)
            // find amount of initial numbers smaller than this one, then put it under index = index - 1
            outputArray[--countingArray[(arr[i] / s) % 10]] = arr[i]; //pay attention: --countingarray not count...y--

        for (int i = 0; i < arr.length; i++)
            arr[i] = outputArray[i];
    }
}
