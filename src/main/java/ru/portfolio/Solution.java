package ru.portfolio;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Solution {
    public static void nextPermutation(int[] nums) {
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) throws Throwable {
        Cat cat = new Cat();
        //cat.Pi = 13;
        Field [] fields = cat.getClass().getDeclaredFields();
        System.out.println(Arrays.toString(fields));
        Field specialField = null;
        for(Field field : fields){
            //if(field.canAccess(cat))
            if(field.getName().equals("str")){
                field.setAccessible(true);
                specialField = field;
                field.set(cat,"pop");}
        }
        specialField.setAccessible(true);
        specialField.getName();
        System.out.println(specialField.get(cat));
        //System.out.println(cat.str);
    }
}
class Cat{
    private final int a = 13;
    private static boolean joj = false;

    private final String str = "dsad";
    public double Pi;

}