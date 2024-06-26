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
        return employeeRecords; // I was googling, and it seemed to think I needed to make a copy of this, and not send it directly back?
//        return new LinkedList<>(employeeRecords);
    }

    @Override
    public EmployeeRecord getEmployee(String empId) {
        for (EmployeeRecord employee : employeeRecords) {
            if (employee.empId().equals(empId)) {
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
        List<EmployeeRecord> result = new LinkedList<>();
        for (EmployeeRecord employee : employeeRecords) {
            if (employee.joiningDate().isAfter(hiredAfter) && employee.joiningDate().isBefore(hiredBefore)) {
                result.add(employee);
            }
        }
        return result;
    }

    @Override
    public List<EmployeeRecord> getEmployees(Integer olderThan, Integer youngerThan) {
        List<EmployeeRecord> result = new LinkedList<>();
        LocalDate now = LocalDate.now();
        for (EmployeeRecord employee : employeeRecords) {
            int age = now.getYear() - employee.dateOfBirth().getYear();
            if (age > olderThan && age < youngerThan) {
                result.add(employee);
            }
        }
        return result;
    }

}