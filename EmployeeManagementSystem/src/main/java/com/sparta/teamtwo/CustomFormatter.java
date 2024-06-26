package com.sparta.teamtwo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {


//        YEAR MONTH DAY Class Level Message
//        20240626 com.sparta.kch.App INFO This is also an info message
//        20240626 com.sparta.kch.App WARNING This is a warning message
//        20240626 com.sparta.kch.App FINE We're all fine here thank you
        
        return LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)
                + " " + record.getSourceClassName()
                + " " + record.getLevel()
                + " " + record.getMessage()
                + "\n";
    }
}