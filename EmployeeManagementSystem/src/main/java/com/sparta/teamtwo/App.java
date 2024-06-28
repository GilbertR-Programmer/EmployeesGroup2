package com.sparta.teamtwo;

import com.sparta.teamtwo.gui.EmployeeManagementSystemGUI;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Logger;

import static com.sparta.teamtwo.logging.LoggerInitialiser.logger;
import static com.sparta.teamtwo.logging.LoggerInitialiser.setUpLogFINEST;

public class App {

    public static void main(String[] args) {
        LinkedList<EmployeeRecord> employeeRecords;

        setUpLogFINEST();
        try {
            employeeRecords = EmployeeParser.getParsedEmployees(1000);
            logger.info(Integer.toString(employeeRecords.size()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!employeeRecords.isEmpty()) {
            EmployeeManagementSystemGUI gui = new EmployeeManagementSystemGUI(employeeRecords);
            gui.showGUI();
        }
    }
}