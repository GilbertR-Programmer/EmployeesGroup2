package com.sparta.teamtwo.logging;

import com.sparta.teamtwo.App;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerInitialiser {
    public static final Logger logger = Logger.getLogger(App.class.getName());
    public static void setUpLogFINEST() {

        logger.setUseParentHandlers(false);
        ConsoleHandler finestConsoleHandler = new ConsoleHandler();
        logger.addHandler(finestConsoleHandler);
        finestConsoleHandler.setFormatter(new CustomFormatter());
        logger.setLevel(Level.ALL);
        finestConsoleHandler.setLevel(Level.ALL);

        try {
            FileHandler fileHandler = new FileHandler("src/main/resources/log-file-finest.log",false); //log files should be in the gitignore
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new CustomFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void setUpLogINFO(){
        logger.setUseParentHandlers(false);
        ConsoleHandler infoConsoleHandler = new ConsoleHandler();
        logger.addHandler(infoConsoleHandler);
        infoConsoleHandler.setFormatter(new CustomFormatter());
        logger.setLevel(Level.ALL);
        infoConsoleHandler.setLevel(Level.INFO);

        try {
            FileHandler fileHandler = new FileHandler("src/main/resources/log-file-info.log",false); //log files should be in the gitignore
            fileHandler.setLevel(Level.INFO);
            fileHandler.setFormatter(new CustomFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}