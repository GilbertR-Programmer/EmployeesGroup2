package com.sparta.teamtwo;

import com.sparta.teamtwo.gui.EmployeeManagementSystemGUI;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Logger;

import static com.sparta.teamtwo.logging.LoggerInitialiser.logger;
import static com.sparta.teamtwo.logging.LoggerInitialiser.setUpLogFINEST;

public class App {

    public static void main(String[] args) {
        LinkedList<EmployeeRecord> peeps;

        setUpLogFINEST();
        try {
            peeps = EmployeeParser.getParsedEmployees(1000);
            logger.info(Integer.toString(peeps.size()));
            logger.info("Hello");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!peeps.isEmpty()) {
            EmployeeManagementSystemGUI gui = new EmployeeManagementSystemGUI(peeps);
            gui.showGUI();
        }
    }
}