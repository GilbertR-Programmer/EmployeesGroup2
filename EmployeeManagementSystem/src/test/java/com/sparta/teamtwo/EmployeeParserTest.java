package com.sparta.teamtwo;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeParserTest {
    @Test
    @DisplayName("Test when Parsing Valid Employee Record")
    public void testParseValidEmployeeRecord() {
        String employeeString = "123416,Mr.,Tam,A,Parlak,M,tam@gmail.com,1/1/1998,4/4/2024,80000";

        Optional<EmployeeRecord> optionalEmployee = EmployeeParser.parseEmployeeRecord(employeeString);

        assertTrue(optionalEmployee.isPresent(), "EmployeeRecord should be present");

        EmployeeRecord employee = optionalEmployee.get();

        assertEquals("123416", employee.empId());
        assertEquals("Mr.", employee.prefix());
        assertEquals("Tam", employee.firstName());
        assertEquals('A', employee.middleInitial());
        assertEquals("Parlak", employee.lastName());
        assertEquals('M', employee.gender());
        assertEquals("tam@gmail.com", employee.email());
        assertEquals(LocalDate.of(1998, 1, 1), employee.dateOfBirth());
        assertEquals(LocalDate.of(2024, 4, 4), employee.joiningDate());
        assertEquals(80000, employee.salary());
    }

    @Test
    @DisplayName("Test when Parsing Valid Employee Record")
    public void testInvalidEmpId() {

    }

}