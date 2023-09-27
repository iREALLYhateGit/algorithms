package ru.portfolio;

import java.util.ArrayList;
import java.util.List;

public class Quicksort {
    public static void main(String[] args) {
        List<Integer> lizt = new ArrayList<>();
        lizt.add(12);
        lizt.add(223);
        lizt.add(3);
        lizt.add(3);
        lizt.add(324);

        System.out.println(quickSort(lizt));
    }
    public static List <Integer> quickSort(List <Integer> list){
        if(list.size() >=2 ){
            Integer middle = list.get(list.size()/2);
            list.remove(list.get(list.size()/2));
            List <Integer> lowlist = new ArrayList<>();
            List <Integer> highlist = new ArrayList<>();
            for (Integer integer : list) {
                if (integer <= middle) {
                    lowlist.add(integer);
                } else {
                    highlist.add(integer);
                }
            }
            //System.out.println(lowlist);
            list.clear();
            list.addAll(quickSort(lowlist));
            list.add(middle);
            list.addAll(quickSort(highlist));
        }
        return list;
    }
}
