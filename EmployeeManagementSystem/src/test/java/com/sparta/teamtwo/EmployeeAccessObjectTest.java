package com.sparta.teamtwo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeAccessObjectTest {

    private EmployeeAccessObject employeeAccessObject;
    private List<EmployeeRecord> employeeRecords;


    @BeforeEach
    @DisplayName("Set of stock employees that can be tested against so they don't have to be remade")
    void setUp() {
        employeeRecords = Arrays.asList(
                new EmployeeRecord("E001", "Mr", "John", "M", "Doe", 'M', "john.doe@example.com",
                        LocalDate.of(1985, 5, 15), LocalDate.of(2010, 7, 1), 60000),
                new EmployeeRecord("E002", "Ms", "Jane", "A", "Smith", 'F', "jane.smith@example.com",
                        LocalDate.of(1990, 8, 20), LocalDate.of(2015, 3, 10), 75000),
                new EmployeeRecord("E003", "Dr", "Michael", "J", "Johnson", 'M',
                        "michael.johnson@example.com", LocalDate.of(1982, 12, 10), LocalDate.of(2008, 10, 5), 80000)
        );
        employeeAccessObject = new EmployeeAccessObject(employeeRecords);
    }

    @Test
    void testGetEmployees() {
        List<EmployeeRecord> result = employeeAccessObject.getEmployees();
        assertEquals(employeeRecords, result);
    }

    @ParameterizedTest
    @MethodSource("basicEmployeeData")
    @DisplayName("")
    void givenEmployeeListGetEmployeesReturnsWholeList(List<EmployeeRecord> input, List<EmployeeRecord> expectedOutput){
        //Arrange

        //Act

        //Assert

    }

    static Stream<Arguments> basicEmployeeData() {

        return Stream.of();
    }

}

