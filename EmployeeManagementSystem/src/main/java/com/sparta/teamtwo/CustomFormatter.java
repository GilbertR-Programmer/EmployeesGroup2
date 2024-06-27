package com.sparta.teamtwo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {
    static int index = 0;
    @Override
    public String format(LogRecord record) {
        index++;
        index++;
        if (index >= 6){
            index = 0;
        }
        DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("dd MMM");
        DateTimeFormatter myTimeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        String[] colour = {"\u001B[31m","\u001B[33m","\u001B[32m"
                ,"\u001B[34m","\u001B[36m","\u001B[35m","\u001B[37m"};


        return colour[index] + LocalDate.now().format(myDateFormat)
                + " " + LocalTime.now().format(myTimeFormat)
                + " in application: " + record.getSourceClassName()
                + "\n" + colour[index+1] + record.getLevel()
                + " " + record.getMessage()
                + "\n";
    }
}
/*
Color Name 	Color code 	Background Color 	Background Color code
BLACK 	\u001B[30m 	BLACK_BACKGROUND 	\u001B[40m
RED 	\u001B[31m 	RED_BACKGROUND 	    \u001B[41m
GREEN 	\u001B[32m 	GREEN_BACKGROUND 	\u001B[42m
YELLOW 	\u001B[33m 	YELLOW_BACKGROUND 	\u001B[43m
BLUE 	\u001B[34m 	BLUE_BACKGROUND 	\u001B[44m
PURPLE 	\u001B[35m 	PURPLE_BACKGROUND 	\u001B[45m
CYAN 	\u001B[36m 	CYAN_BACKGROUND 	\u001B[46m
WHITE 	\u001B[37m 	WHITE_BACKGROUND 	\u001B[47m
 */
