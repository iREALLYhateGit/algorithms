package ru.portfolio;

import java.io.*;
import java.nio.Buffer;
import java.util.*;
import java.util.stream.Stream;

public class Rocket {
    public static void main(String[] args) {
        System.out.println("ready");
        BufferedReader reader;
        int n;
        String [] input;
//        int [] mass;
        Integer time;
        HashMap<Integer, TreeMap<Integer, Character>> hashMap = new HashMap<>();
        TreeMap<Integer, Character> tr;
        ArrayList<Integer> numb = new ArrayList<>();
        try {
            //reader = new BufferedReader(new FileReader("input.txt"));
            reader = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++){
                input = reader.readLine().split(" ");

//                mass = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
                time = Integer.parseInt(input[0])*24*60 + Integer.parseInt(input[1])*60 + Integer.parseInt(input[2]);
                if(!hashMap.containsKey(Integer.parseInt(input[3]))) {
                    tr = new TreeMap<>(Comparator.comparingInt(f -> f));
                    hashMap.put(Integer.parseInt(input[3]),tr);
                    numb.add(Integer.parseInt(input[3]));
                }
                else{
                    tr = hashMap.get(Integer.parseInt(input[3]));
                }
                tr.put(time,input[4].charAt(0));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(hashMap);
        for(Integer inz : numb){
            tr = hashMap.get(inz);
            time = 0;
            for(Integer k : tr.keySet()){
                if(tr.get(k).equals('C') || tr.get(k).equals('S')){
                    time += k;
                }
            }
        }


    }


}
