package ru.portfolio;

import java.util.*;

public class Quicksort {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> lizt = new ArrayList<>();
        lizt.add(12);
        lizt.add(223);
        lizt.add(3);
        lizt.add(3);
        lizt.add(324);
        Date date = new Date();
        long a = (new Date()).getTime();
        Arrays.parallelSort(lizt.toArray(), new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return Integer.compare((Integer) o1, (Integer) o2);
            }
        });
        long b = (new Date()).getTime();
        Thread.sleep(1000);
        System.out.println("now " + (new Date()).getTime());
        System.out.println(a + "  start_time");
        System.out.println(b + "  start_time");
        System.out.println(b - a + "  secs");

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
