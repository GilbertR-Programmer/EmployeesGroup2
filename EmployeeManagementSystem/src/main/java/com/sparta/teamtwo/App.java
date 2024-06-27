package com.sparta.teamtwo;

import com.sparta.teamtwo.gui.EmployeeManagementSystemGUI;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args) {
        LinkedList<EmployeeRecord> peeps;

        try {
            peeps = EmployeeParser.getParsedEmployees(1000);
            Logger.getLogger(App.class.getName()).info(Integer.toString(peeps.size()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!peeps.isEmpty()) {
            EmployeeManagementSystemGUI gui = new EmployeeManagementSystemGUI(peeps);
            gui.showGUI();
        }
    }
}
