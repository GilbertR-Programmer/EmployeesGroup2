package com.sparta.teamtwo.gui;

import com.sparta.teamtwo.EmployeeAccessObject;
import com.sparta.teamtwo.EmployeeRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;

public class EmployeeManagementSystemGUI {
    private static final DateTimeFormatter MDY_DATE_FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy");

    private static final String[] COLUMN_NAMES = {
            "Employee ID",
            "Prefix",
            "First Name",
            "Middle Initial",
            "Last Name",
            "Gender",
            "eMail",
            "DOB",
            "Start Date",
            "Salary"};

    EmployeeAccessObject employeeAccessObject;
    private JFrame window;
    private JPanel uiPanel;
    private JComboBox<String> searchSelector;
    private JTextField searchBarField;
    private JTextField lowerRangeField;
    private JTextField upperRangeField;
    private JButton searchButton;
    private JButton resetButton;
    private JTable recordsTable;
    private JScrollPane scrollableRecordsTable;

    public EmployeeManagementSystemGUI(LinkedList<EmployeeRecord> recordList) {
        employeeAccessObject = new EmployeeAccessObject(recordList);
        initialiseWindow(1280, 720);
        buildDisplayTable(recordList);
        setupUI();
    }

    private void initialiseWindow(int width, int height) {
        window = new JFrame("R'lyeh Employee Management System");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(width, height);
    }

    private void buildDisplayTable(LinkedList<EmployeeRecord> displayList) {
        String[][] data = getDisplayListData(displayList);

        DefaultTableModel model = new DefaultTableModel(data, COLUMN_NAMES);
        recordsTable = new JTable(model);

        scrollableRecordsTable = new JScrollPane(recordsTable);
    }

    private void updateDisplayTable(LinkedList<EmployeeRecord> displayList) {
        if (displayList == null) return;

        String[][] data = getDisplayListData(displayList);
        ((DefaultTableModel) recordsTable.getModel()).setDataVector(data, COLUMN_NAMES);
    }

    private static String[][] getDisplayListData(LinkedList<EmployeeRecord> displayList) {
        String[][] data = new String[displayList.size()][10];
        for (int i = 0; i < displayList.size(); i++) {
            data[i][0] = displayList.get(i).empId();
            data[i][1] = displayList.get(i).prefix();
            data[i][2] = displayList.get(i).firstName();
            data[i][3] = displayList.get(i).middleInitial().toString();
            data[i][4] = displayList.get(i).lastName();
            data[i][5] = displayList.get(i).gender().toString();
            data[i][6] = displayList.get(i).email();
            data[i][7] = displayList.get(i).dateOfBirth().format(MDY_DATE_FORMATTER);
            data[i][8] = displayList.get(i).joiningDate().format(MDY_DATE_FORMATTER);
            data[i][9] = displayList.get(i).salary().toString();
        }
        return data;
    }

    private void setupUI() {
        // Create a panel for buttons and text fields
        uiPanel = new JPanel();
        uiPanel.setLayout(new FlowLayout());

        String[] dropdownItems = {"ID", "Last Name", "Start Date", "Age"};
        searchSelector = new JComboBox<>(dropdownItems);
        uiPanel.add(searchSelector);

        searchSelector.addActionListener(new ActionListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> searchDropdown = (JComboBox<String>) e.getSource();
                assert searchDropdown != null;
                String selectedOption = (String) searchDropdown.getSelectedItem();

                assert selectedOption != null;
                searchBarField.setVisible(selectedOption.equals("ID") || selectedOption.equals("Last Name"));
                lowerRangeField.setVisible(selectedOption.equals("Start Date") || selectedOption.equals("Age"));
                upperRangeField.setVisible(selectedOption.equals("Start Date") || selectedOption.equals("Age"));
                window.revalidate();
            }
        });

        // Create a text field
        searchBarField = new JTextField(20);
        uiPanel.add(searchBarField);
        searchBarField.setVisible(true);

        // Create a text field
        lowerRangeField = new JTextField(20);
        uiPanel.add(lowerRangeField);
        lowerRangeField.setVisible(false);

        // Create a text field
        upperRangeField = new JTextField(40);
        uiPanel.add(upperRangeField);
        upperRangeField.setVisible(false);

        // Create a search button
        searchButton = new JButton("Search");
        uiPanel.add(searchButton);

        // Add button action listener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) searchSelector.getSelectedItem();
                assert selectedOption != null;

                String searchText = searchBarField.getText();
                switch (selectedOption) {
                    case "ID":
                        if (searchText.length() == 6) {
                            LinkedList<EmployeeRecord> results = new LinkedList<>();
                            EmployeeRecord employee = employeeAccessObject.getEmployee(searchText);
                            if (employee != null) {
                                results.push(employee);
                                updateDisplayTable(results);
                            } else {
                                showErrorPopup(window, "No employee found with provided ID.");
                            }
                        } else {
                            showErrorPopup(window, "Invalid EmployeeID. Please ensure there are 6 numbers in the ID.");
                        }
                        break;
                    case "Last Name":
                        if (!searchText.isEmpty()) {
                            updateDisplayTable((LinkedList<EmployeeRecord>) employeeAccessObject.getEmployees(searchText));
                        }
                        break;
                    case "Start Date":
                        String lowerDateRangeText = lowerRangeField.getText();
                        String upperDateRangeText = upperRangeField.getText();
                        dateSearch(lowerDateRangeText, upperDateRangeText);
                        break;
                    case "Age":
                        String lowerAgeRangeText = lowerRangeField.getText();
                        String upperAgeRangeText = upperRangeField.getText();

                        if (!lowerAgeRangeText.isEmpty() && !upperAgeRangeText.isEmpty()) {
                            int lowerAgeRange = Integer.parseInt(lowerAgeRangeText);
                            int upperAgeRange = Integer.parseInt(upperAgeRangeText);
                            if (lowerAgeRange >= 0 && upperAgeRange >= 0 && upperAgeRange >= lowerAgeRange) {
                                updateDisplayTable((LinkedList<EmployeeRecord>) employeeAccessObject.getEmployees(lowerAgeRange, upperAgeRange));
                            } else {
                                showErrorPopup(window, "Invalid age range. Please ensure that the lower range(left) is greater than or equal to zero, and that upper range(right) is greater than the lower range.");
                            }
                        }
                        break;
                    default:
                        return;
                }
            }

        });


        // Create a search button
        resetButton = new JButton("Reset");
        uiPanel.add(resetButton);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDisplayTable((LinkedList<EmployeeRecord>) employeeAccessObject.getEmployees());
            }
        });
    }

    private void dateSearch(String lowerDateRangeText, String upperDateRangeText) {
        if (!lowerDateRangeText.isEmpty() && !upperDateRangeText.isEmpty()) {
            LocalDate lower;
            LocalDate upper;

            try {
                lower = LocalDate.parse(lowerDateRangeText, MDY_DATE_FORMATTER);
                upper = LocalDate.parse(upperDateRangeText, MDY_DATE_FORMATTER);
                if (lower.isBefore(upper)) {
                    updateDisplayTable((LinkedList<EmployeeRecord>) employeeAccessObject.getEmployees(lower, upper));
                } else {
                    showErrorPopup(window, "Invalid date range. Please ensure that the lower range(left) is before the upper range(right).");
                }
            } catch (DateTimeParseException e) {
                showErrorPopup(window, "Invalid date format used. Please use (mm/dd/yyyY).");
            }
        }
    }

    private static void showErrorPopup(JFrame parentFrame, String errorMessage) {
        // Show an error popup with the specified message
        JOptionPane.showMessageDialog(parentFrame,
                errorMessage,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public void showGUI() {
        window.add(scrollableRecordsTable, BorderLayout.CENTER);
        window.add(uiPanel, BorderLayout.NORTH);
        window.setVisible(true);
    }
}
