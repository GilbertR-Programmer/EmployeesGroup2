# EmployeesGroup2
A group Project for managing employee data

**Requirements**
Add the provided factory class, EmployeeFactory.java, to a suitable package within your project

Add the provided sample data file, employees.csv, to the src/main/resources folder in your project

Call EmployeeFactory.getEmployees(n) to generate an array of random employee data of size n (1 <= n <= 1000)

Employee data is provided as an array of Strings, returned from getEmployees(), each String representing

Emp ID(up to 8 digits)
Prefix
First Name
Middle Initial
Last Name
Gender (stored as M or F)
E Mail (in standard email format)
Date of Birth (YYYY-MM-DD calendar date format)
Date of Joining (YYYY-MM-DD calendar date format)
Salary
The values for the employee data will be separated by commas within the String

For example, "387647,Drs.,Shanika,D,Tejada,F,shanika.tejada@gmail.com,8/16/1958,5/23/1995,81253"
Convert each element in the array into an Employee object, having created a suitable DTO class, and store it in a List using a suitable concrete type

There is a risk that some of the data entries are corrupted. You will need to sanitise the data and ensure that only correct and complete records are added to the list. You should also inform the user of how many employee records are corrupted

You will next need to create a DAO class to interact with the collection of employees. This class will need to provide apis for the following:

Search for employee by Emp ID
Search for employees by Last Name (partial match)
Search for employees hired within a date range
Search for employees by age range
This class should be designed using an interface before being implemented. You are free to add more methods which you think will be useful
