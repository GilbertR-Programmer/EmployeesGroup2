package com.sparta.teamtwo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeAccessObjectTest {

    private static LinkedList<EmployeeRecord> employeeRecordsAsList;


    @BeforeAll
    @DisplayName("Set of stock employees that can be tested against so they don't have to be remade")
    static void setUp() {
        employeeRecordsAsList = new LinkedList<>();
        employeeRecordsAsList.add(new EmployeeRecord("E001", "Mr", "John", 'M', "Doe", 'M', "john.doe@example.com",
                        LocalDate.of(1985, 5, 15), LocalDate.of(2010, 7, 1), 60000));
        employeeRecordsAsList.add(new EmployeeRecord("E002", "Ms", "Jane", 'A', "Smith", 'F', "jane.smith@example.com",
                LocalDate.of(1990, 8, 20), LocalDate.of(2015, 3, 10), 75000));
        employeeRecordsAsList.add(new EmployeeRecord("E003", "Dr", "Michael", 'J', "Johnson", 'M',
                "michael.johnson@example.com", LocalDate.of(1982, 12, 10), LocalDate.of(2008, 10, 5), 80000));




    }

    @ParameterizedTest
    @MethodSource("getBasicEmployeeData")
    void testGetEmployees(List<EmployeeRecord> input, List<EmployeeRecord> expectedOutput) {
        //Arrange
        EmployeeAccessObject accessObject = new EmployeeAccessObject(input);
        //Act
        List<EmployeeRecord> actualOutput = accessObject.getEmployees();
        //Assert
        assertEquals(expectedOutput,actualOutput);
    }


    static Stream<Arguments> getBasicEmployeeData() {
        List<EmployeeRecord> listWithAddedEmployees = new LinkedList<>();
        listWithAddedEmployees.add(new EmployeeRecord("7H4F", "Mr", "John", 'F', "Kentucky", 'M',
                "michael.johnson@example.com", LocalDate.of(1982, 12, 10), LocalDate.of(2008, 8, 5), 80000));
        listWithAddedEmployees.add(new EmployeeRecord("HJ85", "Mr", "Bobby", 'F', "Kentucky", 'M',
                "barnubus.johnson@example.com", LocalDate.of(1982, 12, 10), LocalDate.of(2008, 8, 5), 80000));
        return Stream.of(
                Arguments.of(
                employeeRecordsAsList,employeeRecordsAsList
                ),
                Arguments.of(
                        listWithAddedEmployees,listWithAddedEmployees
                )
        );
    }

}

