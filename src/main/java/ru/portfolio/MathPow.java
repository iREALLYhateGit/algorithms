package ru.portfolio;

import java.util.Scanner;

public class MathPow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int base = scanner.nextInt(), k = scanner.nextInt();  // основание  и степень
        int chislo = 1; // доп множитель
        if(k == 0)
            base = 1;
        while( k > 0){
            if(k % 2 == 0){
                base *= base;
                k /= 2;
            }
            else if (k == 1){
                break;
            }
            else{
                chislo *= base;
                k --;
                if(chislo == base*base){
                    k++;
                    chislo = 1;
                    System.out.println("q");
                }
            }
        }
        System.out.println(base*chislo);
    }
}
