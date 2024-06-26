package com.sparta.teamtwo;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class App {
    public static void main(String[] args) {
        try {
            LinkedList<EmployeeRecord> peeps = EmployeeParser.getParsedEmployees(10);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
