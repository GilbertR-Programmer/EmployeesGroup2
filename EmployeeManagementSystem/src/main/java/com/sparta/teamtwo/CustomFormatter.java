package com.sparta.teamtwo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {

        //return SimpleDateFormat("dd-mm-yyyy HH-mm-ss")
        //SimpleDateFormat myDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("dd MM yyyy");
//        YEAR MONTH DAY Class Level Message
//        20240626 com.sparta.kch.App INFO This is also an info message
//        20240626 com.sparta.kch.App WARNING This is a warning message
//        20240626 com.sparta.kch.App FINE We're all fine here thank you

        return LocalDate.now().format(myDateFormat)
                + " " + record.getSourceClassName()
                + " " + record.getLevel()
                + " " + record.getMessage()
                + "\n";
    }
}
