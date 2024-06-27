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
    @DisplayName("Test when Parsing Valid Employee ID")
    public void testValidEmpId() {
        String empId = "123456";

        String parsedId = EmployeeParser.parseEmpId(empId);

        assertEquals("123456", parsedId);
    }

    @Test
    @DisplayName("Test when Parsing Invalid Employee ID")
    public void testInvalidEmpId() {
        String empId = "asd434123n222";

        String parsedId = EmployeeParser.parseEmpId(empId);

        assertNull(parsedId);
    }

    @Test
    @DisplayName("Test when Parsing Valid Prefix")
    public void testValidPrefix() {
        String prefix = "Mr.";

        String parsedPrefix = EmployeeParser.parsePrefix(prefix);

        assertEquals("Mr.", parsedPrefix);
    }

    @Test
    @DisplayName("Test when Parsing Invalid Prefix (does not end with '.')")
    public void testInvalidPrefixNotEndsWithDot() {
        String prefix = "Mrs";

        String parsedPrefix = EmployeeParser.parsePrefix(prefix);

        assertNull(parsedPrefix);
    }

    @Test
    @DisplayName("Test when Parsing Invalid Prefix (length < 3)")
    public void testInvalidPrefixLengthLessThan3() {
        String prefix = "Mr";

        String parsedPrefix = EmployeeParser.parsePrefix(prefix);

        assertNull(parsedPrefix);
    }

    @Test
    @DisplayName("Test when Parsing Invalid Prefix (length > 5)")
    public void testInvalidPrefixLengthGreaterThan5() {
        String prefix = "Ms.abcde";

        String parsedPrefix = EmployeeParser.parsePrefix(prefix);

        assertNull(parsedPrefix);
    }

    @Test
    @DisplayName("Test when Parsing valid Gender")
    public void testValidGender() {
        String gender = "M";

        char parsedGender = EmployeeParser.parseGender(gender);

        assertEquals('M', parsedGender);
    }

    @Test
    @DisplayName("Test when Parsing Invalid Gender")
    public void testInvalidGender() {
        String gender = "P";

        char parsedGender = EmployeeParser.parseGender(gender);

        assertEquals('-', parsedGender);
    }

    @Test
    @DisplayName("Test when parsing a valid email")
    public void testValidEmail() {
        String email = "example@domain.com";
        assertEquals(email, EmployeeParser.parseEmail(email));
    }

    @Test
    @DisplayName("Test when parsing an invalid email")
    public void testInvalidEmail() {
        String email = "example@domain@com";
        assertNull(EmployeeParser.parseEmail(email));
    }

    @Test
    @DisplayName("Test when parsing a valid birthday")
    public void testValidBirthday() {
        String birthday = "1/1/1990";
        assertEquals(LocalDate.of(1990, 1, 1), EmployeeParser.parseBirthday(birthday));
    }

    @Test
    @DisplayName("Test when parsing an invalid birthday")
    public void testInvalidBirthday() {
        String birthday = "1990/01/01";
        assertNull(EmployeeParser.parseBirthday(birthday));
    }

    @Test
    @DisplayName("Test when parsing a valid joining date")
    public void testValidJoiningDate() {
        String joinDate = "1/1/2020";
        assertEquals(LocalDate.of(2020, 1, 1), EmployeeParser.parseJoiningDate(joinDate));
    }

    @Test
    @DisplayName("Test when parsing an invalid joining date")
    public void testInvalidJoiningDate() {
        String joinDate = "2020-01-01";
        assertNull(EmployeeParser.parseJoiningDate(joinDate));
    }

    @Test
    @DisplayName("Test when parsing a valid salary")
    public void testValidSalary() {
        String salary = "50000";
        assertEquals(50000, EmployeeParser.parseSalary(salary));
    }

    @Test
    @DisplayName("Test when parsing an invalid salary")
    public void testInvalidSalary() {
        String salary = "-50000";
        assertEquals(0, EmployeeParser.parseSalary(salary));
    }

}