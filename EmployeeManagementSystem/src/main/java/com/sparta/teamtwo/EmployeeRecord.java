package com.sparta.teamtwo;

import java.time.LocalDate;

public record EmployeeRecord(String empId,
                             String prefix, String firstName, Character middleInitial, String lastName,
                             Character gender, String email,
                             LocalDate dateOfBirth, LocalDate joiningDate,
                             Integer salary) {
}