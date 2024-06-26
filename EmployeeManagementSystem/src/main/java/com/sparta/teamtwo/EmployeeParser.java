package com.sparta.teamtwo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

public class EmployeeParser {
    public static LinkedList<EmployeeRecord> getParsedEmployees(int employeeCount) throws IOException {
        String[] employeeRecStrings = EmployeeFactory.getEmployees(employeeCount);
        return null;
    }

    private static String parseEmpId(String empId){

        return null;
    }
    private static String parsePrefix(String prefix){

        return null;
    }
    private static String parseFirstName(String firstName){

        return null;
    }
    private static String parseMiddleInitial(String midInitial){

        return null;
    }
    private static String parseLastName(String lastName){

        return null;
    }
    private static char parseGender(String gender){

        return ' ';
    }
    private static String parseEmail(String email){

        return null;
    }
    private static LocalDate parseBirthday(String birthday){

        return null;
    }
    private static LocalDate parseJoiningDate(String joinDate){

        return null;

    }
    private static int Salary (String salary){

        return 0;
    }
}
