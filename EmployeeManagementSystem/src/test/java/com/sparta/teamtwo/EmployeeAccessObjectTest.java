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
                        LocalDate.of(1985, 5, 15), LocalDate.of(2020, 7, 1), 60000));
        employeeRecordsAsList.add(new EmployeeRecord("E002", "Ms", "Jane", 'A', "Smith", 'F', "jane.smith@example.com",
                LocalDate.of(1990, 8, 20), LocalDate.of(2010, 3, 10), 75000));
        employeeRecordsAsList.add(new EmployeeRecord("E003", "Dr", "Michael", 'J', "Johnson", 'M',
                "michael.johnson@example.com", LocalDate.of(2000, 12, 10), LocalDate.of(2008, 10, 5), 80000));
        employeeRecordsAsList.add(new EmployeeRecord("7H4F", "Mr", "John", 'F', "Kentucky", 'M',
                "michael.johnson@example.com", LocalDate.of(1990, 12, 10), LocalDate.of(2008, 8, 5), 80000));
        employeeRecordsAsList.add(new EmployeeRecord("HJ85", "Mr", "Bobby", 'F', "Kentucky", 'M',
                "barnubus.johnson@example.com", LocalDate.of(1980, 12, 10), LocalDate.of(2008, 8, 5), 80000));
    }

    @Test
    void testBasicGetEmployees() {
        //Arrange
        EmployeeAccessObject accessObject = new EmployeeAccessObject(employeeRecordsAsList);
        //Act
        List<EmployeeRecord> actualOutput = accessObject.getEmployees();
        //Assert
        assertEquals(employeeRecordsAsList,actualOutput);
    }

    @ParameterizedTest
    @MethodSource("getSingleEmployeeByIdData")
    void testGetEmployeeById(String input, EmployeeRecord expectedOutput) {
        //Arrange
        EmployeeAccessObject accessObject = new EmployeeAccessObject(employeeRecordsAsList);
        //Act
        EmployeeRecord actualOutput = accessObject.getEmployee(input);
        //Assert
        assertEquals(expectedOutput,actualOutput);
    }

    static Stream<Arguments> getSingleEmployeeByIdData() {
        return Stream.of(
                Arguments.of(
                        "E001", new EmployeeRecord("E001", "Mr", "John", 'M', "Doe", 'M', "john.doe@example.com",
                                LocalDate.of(1985, 5, 15), LocalDate.of(2020, 7, 1), 60000)

                ),
                Arguments.of(
                        "E002",new EmployeeRecord("E002", "Ms", "Jane", 'A', "Smith", 'F', "jane.smith@example.com",
                                LocalDate.of(1990, 8, 20), LocalDate.of(2010, 3, 10), 75000)

                )
        );
    }

    @ParameterizedTest
    @MethodSource("getEmployeesByLastNameData")
    void testGetEmployeesByLastName(String input, List<EmployeeRecord> expectedOutput) {
        //Arrange
        EmployeeAccessObject accessObject = new EmployeeAccessObject(employeeRecordsAsList);
        //Act
        List<EmployeeRecord> actualOutput = accessObject.getEmployees(input);
        //Assert
        assertEquals(expectedOutput,actualOutput);
    }

    static Stream<Arguments> getEmployeesByLastNameData() {
        return Stream.of(
                Arguments.of(
                        "Doe",List.of(new EmployeeRecord("E001", "Mr", "John", 'M', "Doe", 'M', "john.doe@example.com",
                                LocalDate.of(1985, 5, 15), LocalDate.of(2020, 7, 1), 60000))

                ),
                Arguments.of(
                        "Smith",List.of(new EmployeeRecord("E002", "Ms", "Jane", 'A', "Smith", 'F', "jane.smith@example.com",
                                LocalDate.of(1990, 8, 20), LocalDate.of(2010, 3, 10), 75000))

                ),
                Arguments.of(
                        "tuck",List.of(new EmployeeRecord("7H4F", "Mr", "John", 'F', "Kentucky", 'M',
                                        "michael.johnson@example.com", LocalDate.of(1990, 12, 10), LocalDate.of(2008, 8, 5), 80000),
                                new EmployeeRecord("HJ85", "Mr", "Bobby", 'F', "Kentucky", 'M',
                                        "barnubus.johnson@example.com", LocalDate.of(1980, 12, 10), LocalDate.of(2008, 8, 5), 80000)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getEmployeesByDateData")
    void testGetEmployeeByDate(LocalDate startDate, LocalDate endDate, List<EmployeeRecord> expectedOutput) {
        //Arrange
        EmployeeAccessObject accessObject = new EmployeeAccessObject(employeeRecordsAsList);
        //Act
        List<EmployeeRecord> actualOutput = accessObject.getEmployees(startDate, endDate);
        //Assert
        assertEquals(expectedOutput,actualOutput);
    }

    static Stream<Arguments> getEmployeesByDateData() {
        return Stream.of(
                Arguments.of(
                        LocalDate.of(2000,1,1),LocalDate.of(2012,1,1),List.of(new EmployeeRecord("E002", "Ms", "Jane", 'A', "Smith", 'F', "jane.smith@example.com",
                                LocalDate.of(1990, 8, 20), LocalDate.of(2010, 3, 10), 75000),
                                new EmployeeRecord("E003", "Dr", "Michael", 'J', "Johnson", 'M',
                                        "michael.johnson@example.com", LocalDate.of(2000, 12, 10), LocalDate.of(2008, 10, 5), 80000),
                                new EmployeeRecord("7H4F", "Mr", "John", 'F', "Kentucky", 'M',
                                        "michael.johnson@example.com", LocalDate.of(1990, 12, 10), LocalDate.of(2008, 8, 5), 80000),
                                new EmployeeRecord("HJ85", "Mr", "Bobby", 'F', "Kentucky", 'M',
                                        "barnubus.johnson@example.com", LocalDate.of(1980, 12, 10), LocalDate.of(2008, 8, 5), 80000)

                                )

                ),
                Arguments.of(
                        LocalDate.of(2020,1,1),LocalDate.of(2023,1,1),List.of(new EmployeeRecord("E001", "Mr", "John", 'M', "Doe", 'M', "john.doe@example.com",
                                LocalDate.of(1985, 5, 15), LocalDate.of(2020, 7, 1), 60000))

                )
        );
    }

    @ParameterizedTest
    @MethodSource("getEmployeesByAgeData")
    void testGetEmployeeByAge(int youngestAge, int oldestAge, List<EmployeeRecord> expectedOutput) {
        //Arrange
        EmployeeAccessObject accessObject = new EmployeeAccessObject(employeeRecordsAsList);
        //Act
        List<EmployeeRecord> actualOutput = accessObject.getEmployees(youngestAge, oldestAge);
        //Assert
        assertEquals(expectedOutput,actualOutput);
    }

    static Stream<Arguments> getEmployeesByAgeData() {
        return Stream.of(
                Arguments.of(
                        17,35,List.of(
                                new EmployeeRecord("E002", "Ms", "Jane", 'A', "Smith", 'F', "jane.smith@example.com",
                                        LocalDate.of(1990, 8, 20), LocalDate.of(2010, 3, 10), 75000),
                                new EmployeeRecord("E003", "Dr", "Michael", 'J', "Johnson", 'M',
                                        "michael.johnson@example.com", LocalDate.of(2000, 12, 10), LocalDate.of(2008, 10, 5), 80000),
                                new EmployeeRecord("7H4F", "Mr", "John", 'F', "Kentucky", 'M',
                                "michael.johnson@example.com", LocalDate.of(1990, 12, 10), LocalDate.of(2008, 8, 5), 80000)

                        )
                )

        );
    }



}
