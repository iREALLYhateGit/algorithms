package ru.portfolio;

import java.io.IOException;
import java.util.Scanner;

public class First {
    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(reader.readLine()), m = Integer.parseInt(reader.readLine());
//        int price2 = Integer.parseInt(reader.readLine()), price5 = Integer.parseInt(reader.readLine());
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        int price2 = scanner.nextInt(), price5 = scanner.nextInt();
        int kolvo;
        int price;
        int itog = 0;
        int k = 0;
        if(4*price2 <= price5){
            price = price2*4;
        }
        else{
            price = price5;
        }
        kolvo = (m - n) / 4;
        itog = kolvo * price;
        int ost = (m -n) - kolvo*4;
        if(n >= m)
            System.out.println(0);
        else{
            if((m - n) % 4 != 0){
                for(int i = 1; i <=3; i++){
                    if(i*price2 < price5 && i >= ost){
                        itog += i*price2;
                        k++;
                        break;
                    }
                }
            }
            if(k == 0 && (m - n) %4!=0)
                itog += price;
        }
        System.out.println(itog);
    }
}
