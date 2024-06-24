# EmployeesGroup2

## A group Project for managing employee data

### Requirements

1. **Add the provided factory class, `EmployeeFactory.java`, to a suitable package within your project:**
   - Add the provided sample data file, `employees.csv`, to the `src/main/resources` folder in your project.

2. **Generate Random Employee Data:**
   - Call `EmployeeFactory.getEmployees(n)` to generate an array of random employee data of size n (1 <= n <= 1000).

3. **Employee Data Format:**
   - Employee data is provided as an array of Strings, each representing:
     - Emp ID (up to 8 digits)
     - Prefix
     - First Name
     - Middle Initial
     - Last Name
     - Gender (M or F)
     - E-mail (in standard email format)
     - Date of Birth (YYYY-MM-DD calendar date format)
     - Date of Joining (YYYY-MM-DD calendar date format)
     - Salary
   - Values for each employee are separated by commas within the String.

     Example:
     ```
     "387647,Drs.,Shanika,D,Tejada,F,shanika.tejada@gmail.com,8/16/1958,5/23/1995,81253"
     ```

4. **Convert to Employee Objects:**
   - Convert each element in the array into an Employee object using a suitable DTO class and store it in a List using a suitable concrete type.
   - Ensure data sanitization to handle corrupted entries. Inform the user of the number of corrupted employee records.

5. **Create DAO Class:**
   - Create a DAO class to interact with the collection of employees, designed using an interface before implementation.
   - Provide APIs for the following operations:
     - Search for an employee by Emp ID.
     - Search for employees by Last Name (partial match).
     - Search for employees hired within a date range.
     - Search for employees by age range.
   - You are free to add more methods as needed.
