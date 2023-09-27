package ru.portfolio;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Recursion {
    public static void main(String[] args) {

        // sizeOfList calls
        /*var arrayl = new ArrayList<Integer>();
        arrayl.add(12);
        arrayl.add(13);
        System.out.println(sizeOfList(arrayl, 0));*/


        // sizeOfList calls
        /*var arrayl = new ArrayList<Integer>();
        arrayl.add(12);
        arrayl.add(13);
        arrayl.add(10);
        System.out.println(maxInList(arrayl));*/


    }
    static Integer sizeOfList(List list, Integer m){
        if(!list.isEmpty()){
            list.remove(0);
            m = sizeOfList(list,m + 1);
        }
        return m;
    }
    static Integer maxInList(List list){
        if(list.size() >= 2){
            if((Integer) list.get(0) >= (Integer) list.get(1) ){
                list.set(0,list.get(1));
            }
            list.remove(0);
            maxInList(list);
        }
        return (Integer) list.get(0);
    }


}
