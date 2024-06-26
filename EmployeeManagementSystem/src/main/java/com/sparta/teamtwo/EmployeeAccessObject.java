package com.sparta.teamtwo;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class EmployeeAccessObject implements Searchable {

    private List<EmployeeRecord> employeeRecords = new LinkedList<>();

    public EmployeeAccessObject(List<EmployeeRecord> employeeRecords) {
        this.employeeRecords = employeeRecords;
    }

    @Override
    public List<EmployeeRecord> getEmployees() {
        return new LinkedList<>(employeeRecords);
    }

    @Override
    public EmployeeRecord getEmployee(Integer empId) {
        for (EmployeeRecord employee : employeeRecords) {
            if (employee.empId().equals(empId.toString())) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public List<EmployeeRecord> getEmployees(String lastName) {
        for (EmployeeRecord employee : employeeRecords) {
            if (employee.lastName().equalsIgnoreCase(lastName)) {
                return employeeRecords;
            }
        }
        return null;
    }

    @Override
    public List<EmployeeRecord> getEmployees(LocalDate hiredAfter, LocalDate hiredBefore) {
        return List.of();
    }

    @Override
    public List<EmployeeRecord> getEmployees(Integer olderThan, Integer youngerThan) {
        return List.of();
    }
}