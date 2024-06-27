package com.sparta.teamtwo;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args) {
        try {
            LinkedList<EmployeeRecord> peeps = EmployeeParser.getParsedEmployees(1000);
            Logger.getLogger(App.class.getName()).info(Integer.toString(peeps.size()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}