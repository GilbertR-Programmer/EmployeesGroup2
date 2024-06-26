package com.sparta.teamtwo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Logger;

public class EmployeeParser {
    public static LinkedList<EmployeeRecord> getParsedEmployees(int employeeCount) throws IOException {
        LinkedList<EmployeeRecord> parsedRecords = new LinkedList<>();
        String[] employeeRecStrings = EmployeeFactory.getEmployees(employeeCount);

        for (String employeeString : employeeRecStrings) {
            String[] csvValues = employeeString.split(",");
            Logger.getLogger(EmployeeParser.class.getName()).warning(Arrays.toString(csvValues));

            parsedRecords.add(new EmployeeRecord(
                    parseEmpId(csvValues[0]),
                    parsePrefix(csvValues[1]),
                    parseFirstName(csvValues[2]),
                    parseMiddleInitial(csvValues[3]),
                    parseLastName(csvValues[4]),
                    parseGender(csvValues[5]),
                    parseEmail(csvValues[6]),
                    parseBirthday(csvValues[7]),
                    parseJoiningDate(csvValues[8]),
                    parseSalary(csvValues[9])
            ));
        }

        return parsedRecords;
    }

    private static String parseEmpId(String empId) {

        return null;
    }

    private static String parsePrefix(String prefix) {

        return null;
    }

    private static String parseFirstName(String firstName) {

        return null;
    }

    private static String parseMiddleInitial(String midInitial) {

        return null;
    }

    private static String parseLastName(String lastName) {

        return null;
    }

    private static char parseGender(String gender) {

        return ' ';
    }

    private static String parseEmail(String email) {

        return null;
    }

    private static LocalDate parseBirthday(String birthday) {

        return null;
    }

    private static LocalDate parseJoiningDate(String joinDate) {

        return null;

    }

    private static int parseSalary(String salary) {

        return 0;
    }
}
