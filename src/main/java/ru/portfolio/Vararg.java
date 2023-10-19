package ru.portfolio;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Vararg {
    static void vaTest(int...x){
        System.out.println(Arrays.toString(x));
    }
    static void vaTest(){
        System.out.println("Hello from nothing");
    }
    static void vaTest(int x, int ...y){
        System.out.println("Solo");
    }


    public static void main(String[] args) {
        vaTest();
        vaTest();
    }
}
