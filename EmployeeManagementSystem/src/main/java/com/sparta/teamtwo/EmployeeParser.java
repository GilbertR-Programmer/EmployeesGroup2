package com.sparta.teamtwo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Optional;
import java.util.logging.Logger;

public class EmployeeParser {
    private static final Logger LOGGER = Logger.getLogger(EmployeeParser.class.getName());
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy");

    public static LinkedList<EmployeeRecord> getParsedEmployees(int employeeCount) throws IOException {
        LinkedList<EmployeeRecord> parsedRecords = new LinkedList<>();
        String[] employeeRecStrings = EmployeeFactory.getEmployees(employeeCount);
        int corruptedEntryCounter = 0;
        for (String employeeString : employeeRecStrings) {
            Optional<EmployeeRecord> employeeRecord = parseEmployeeRecord(employeeString);
            if (employeeRecord.isPresent()) {
                parsedRecords.add(employeeRecord.get());
            } else {
                corruptedEntryCounter++;
            }
        }

        LOGGER.info("Corrupted entries: " + corruptedEntryCounter);
        return parsedRecords;
    }

    public static Optional<EmployeeRecord> parseEmployeeRecord(String employeeString) {
        if (employeeString == null) return Optional.empty();

        String[] csvValues = employeeString.split(",");
        String empId = parseEmpId(csvValues[0]);
        String prefix = parsePrefix(csvValues[1]);
        String firstName = parseFirstName(csvValues[2]);
        Character midInitial = parseMiddleInitial(csvValues[3]);
        String lastName = parseLastName(csvValues[4]);
        Character gender = parseGender(csvValues[5]);
        String email = parseEmail(csvValues[6]);
        LocalDate birthday = parseBirthday(csvValues[7]);
        LocalDate joinDate = parseJoiningDate(csvValues[8]);
        int salary = parseSalary(csvValues[9]);

        if (empId == null
                || prefix == null
                || firstName == null
                || midInitial.equals('¬')
                || lastName == null
                || gender.equals('¬')
                || email == null
                || birthday == null
                || joinDate == null
                || salary < 0) {
            return Optional.empty();
        }

        return Optional.of(new EmployeeRecord(empId,
                prefix,
                firstName,
                midInitial,
                lastName,
                gender,
                email,
                birthday,
                joinDate,
                salary
        ));
    }

    public static String parseEmpId(String empId) {
        String parsedId = empId.replaceAll("[^0-9]", "");

        if (parsedId.length() == 6) {
            return parsedId;
        } else {
            LOGGER.warning("Invalid empId " + empId);
            return null;
        }
    }


    public static String parsePrefix(String prefix) {
        if (!prefix.endsWith(".") || prefix.length() < 3 || prefix.length() > 5) {
            LOGGER.warning("Invalid prefix: " + prefix);
            return null;
        } else {
            return prefix;
        }
    }

    public static String parseFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            LOGGER.warning("Invalid first name: " + firstName);
            return null;
        }
        return firstName;
    }

    public static Character parseMiddleInitial(String midInitial) {
        if (midInitial == null || midInitial.length() != 1) {
            LOGGER.warning("Invalid middle initial: " + midInitial);
            return '¬';  // Using a placeholder character to indicate invalid middle initial
        }
        return midInitial.charAt(0);
    }

    public static String parseLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            LOGGER.warning("Invalid last name: " + lastName);
            return null;
        }
        return lastName;
    }

    public static char parseGender(String gender) {
        if(gender.equals("M") || gender.equals("F")){
            return gender.charAt(0);
        } else {
            LOGGER.warning("Invalid gender: " + gender);
            return '¬';
        }
    }

    public static String parseEmail(String email) {
        int firstAtSignIndex = email.indexOf('@');
        int lastAtSignIndex = email.lastIndexOf('@');
        int periodIndex = email.lastIndexOf('.');
        if (lastAtSignIndex < periodIndex && firstAtSignIndex == lastAtSignIndex) {
            return email;
        } else {
            LOGGER.warning("Invalid email: " + email);
            return null;
        }
    }

    public static LocalDate parseBirthday(String birthday) {
        LocalDate parsedDate = null;
        try {
            parsedDate = LocalDate.parse(birthday, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            LOGGER.warning("Invalid Date of Birth format: " + birthday);
        }
        return parsedDate;
    }

    public static LocalDate parseJoiningDate(String joinDate) {
        LocalDate parsedDate = null;
        try {
            parsedDate = LocalDate.parse(joinDate, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            LOGGER.warning("Invalid Date of Joining format: " + joinDate);
        }
        return parsedDate;
    }

    private static int parseSalary(String salary) {
        if (salary.matches("\\d+") && (!salary.isEmpty() || Integer.parseInt(salary) > 0)) {
            return Integer.parseInt(salary);
        } else LOGGER.warning("Invalid Salary: " + salary);
        return -1;
    }
}
