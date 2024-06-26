package com.sparta.teamtwo;

import java.io.IOException;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        String[] peeps;
        try {
            peeps = EmployeeFactory.getEmployees(10);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Arrays.toString(peeps));
    }
}