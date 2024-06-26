package com.sparta.teamtwo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {

        DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("dd MMM");
        DateTimeFormatter myTimeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        return LocalDate.now().format(myDateFormat)
                + "\u001B[34m"
                + " " + LocalTime.now().format(myTimeFormat)
                + " in application: " + record.getSourceClassName()
                + " " + record.getLevel()
                + " " + record.getMessage()
                + "\n";
    }
}
/*
Color Name 	Color code 	Background Color 	Background Color code
BLACK 	\u001B[30m 	BLACK_BACKGROUND 	\u001B[40m
RED 	\u001B[31m 	RED_BACKGROUND 	\u001B[41m
GREEN 	\u001B[32m 	GREEN_BACKGROUND 	\u001B[42m
YELLOW 	\u001B[33m 	YELLOW_BACKGROUND 	\u001B[43m
BLUE 	\u001B[34m 	BLUE_BACKGROUND 	\u001B[44m
PURPLE 	\u001B[35m 	PURPLE_BACKGROUND 	\u001B[45m
CYAN 	\u001B[36m 	CYAN_BACKGROUND 	\u001B[46m
WHITE 	\u001B[37m 	WHITE_BACKGROUND 	\u001B[47m
 */
