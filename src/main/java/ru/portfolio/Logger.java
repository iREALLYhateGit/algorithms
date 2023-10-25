package ru.portfolio;

import java.io.IOException;
import java.util.logging.Level;

public class Logger {
    static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Logger.class.getName());
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
