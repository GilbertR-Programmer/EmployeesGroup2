package com.sparta.teamtwo;

import java.time.LocalDate;
import java.util.List;

public interface Searchable {
    List<EmployeeRecord> getEmployees();
    EmployeeRecord getEmployee(String employeeId);
    List<EmployeeRecord> getEmployees(String lastName);
    List<EmployeeRecord> getEmployees(LocalDate hiredAfter, LocalDate hiredBefore);
    List<EmployeeRecord> getEmployees(Integer olderThan, Integer youngerThan);
}