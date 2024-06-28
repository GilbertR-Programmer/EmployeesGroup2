package com.sparta.teamtwo;

import java.io.IOException;
import java.util.LinkedList;


import static com.sparta.teamtwo.logging.LoggerInitialiser.logger;
import static com.sparta.teamtwo.logging.LoggerInitialiser.setUpLogFINEST;

public class App {

    public static void main(String[] args) {
        setUpLogFINEST();
        try {
            LinkedList<EmployeeRecord> peeps = EmployeeParser.getParsedEmployees(1000);
            logger.info("Employee Records Parsed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}