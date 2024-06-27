package com.sparta.teamtwo;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerInitialiser {
    static final Logger employeeLogger = Logger.getLogger(CustomFormatter.class.getName());

    static{
        employeeLogger.setUseParentHandlers(false); //We don't change the level for the default parent handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        employeeLogger.addHandler(consoleHandler);  //A single logger can have multiple handlers with different levels
        consoleHandler.setFormatter(new CustomFormatter());
        //Can change the format of the outputs
        employeeLogger.setLevel(Level.ALL);         //Logger logs and gives a level
        consoleHandler.setLevel(Level.ALL); //Handlers deal with what to do with this level
    }

}
