package ru.portfolio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Second {
    static Logger logger = Logger.getLogger(Second.class.getName());
    public static void main(String[] args) throws IOException {
        logger.info("Start");
        System.out.println("starting///////");
        int a = 2, b = 3;
        int c = a + b;
        try{
            int l = c/0;
        }catch (Exception ex){
            logger.log(Level.SEVERE,"Divide: ", ex);
        }
        logger.info("closed");
        logger.fine("adasd");
    }
}
