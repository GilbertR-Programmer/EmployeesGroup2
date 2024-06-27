package com.sparta.teamtwo;


import com.sparta.teamtwo.logging.CustomFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.sparta.teamtwo.logging.LoggerInitialiser.*;

public class CustomFormatterTest {

    @Test
    void testLoggerWorks() {
        setUpLogFINEST();
        setUpLogINFO();

        logger.info("This is also an info message");
        logger.warning("This is a warning message");
        logger.fine("We're all fine here thank you");
        logger.fine("We're all fine here thank you");
        logger.fine("We're all fine here thank you");
    }


}
