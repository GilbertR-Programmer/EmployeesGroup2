package com.sparta.teamtwo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomFormatterTest {
    private static final Logger logger = Logger.getLogger(CustomFormatterTest.class.getName());
    @Test
    @DisplayName("Input string /'Dad' Returns true")
    void testDadReturnsTrue() {

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
