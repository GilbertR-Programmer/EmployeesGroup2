package com.sparta.teamtwo;

import org.junit.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeFactoryTest {

    @Test
    public void testGetEmployees() throws IOException {
        String[] employees;
        employees = EmployeeFactory.getEmployees(10);

        assertEquals(10, employees.length);
    }

    @Test
    public void testEmployeeIsOne() throws IOException {
        String[] employees;
        employees = EmployeeFactory.getEmployees(1);

        assertEquals(1, employees.length);
    }
}