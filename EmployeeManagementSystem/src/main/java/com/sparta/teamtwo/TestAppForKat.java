package com.sparta.teamtwo;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestAppForKat {
    private static final Logger logger = Logger.getLogger(TestAppForKat.class.getName());

    public static void main(String[] args) {
//        logger.log(Level.INFO, "This is an info message");
        logger.setUseParentHandlers(false); //We don't change the level for the default parent handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        logger.addHandler(consoleHandler);  //A single logger can have multiple handlers with different levels
        consoleHandler.setFormatter(new CustomFormatter());
        //Can change the format of the outputs
        logger.setLevel(Level.ALL);         //Logger logs and gives a level
        consoleHandler.setLevel(Level.ALL); //Handlers deal with what to do with this level

        logger.info("This is also an info message");
        logger.warning("This is a warning message");
        logger.fine("We're all fine here thank you");

    }
}
