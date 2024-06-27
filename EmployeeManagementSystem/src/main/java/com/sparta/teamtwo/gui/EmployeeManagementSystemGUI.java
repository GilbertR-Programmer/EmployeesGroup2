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
import java.util.LinkedList;

public class EmployeeManagementSystemGUI {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy");

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
        String[][] data = new String[displayList.size()][10];
        for (int i = 0; i < displayList.size(); i++) {
            data[i][0] = displayList.get(i).empId();
            data[i][1] = displayList.get(i).prefix();
            data[i][2] = displayList.get(i).firstName();
            data[i][3] = displayList.get(i).middleInitial().toString();
            data[i][4] = displayList.get(i).lastName();
            data[i][5] = displayList.get(i).gender().toString();
            data[i][6] = displayList.get(i).email();
            data[i][7] = displayList.get(i).dateOfBirth().toString();
            data[i][8] = displayList.get(i).joiningDate().toString();
            data[i][9] = displayList.get(i).salary().toString();
        }

        DefaultTableModel model = new DefaultTableModel(data, COLUMN_NAMES);
        recordsTable = new JTable(model);

        scrollableRecordsTable = new JScrollPane(recordsTable);
    }

    private void updateDisplayTable(LinkedList<EmployeeRecord> displayList) {
        if (displayList == null) return;

        String[][] data = new String[displayList.size()][10];
        for (int i = 0; i < displayList.size(); i++) {
            data[i][0] = displayList.get(i).empId();
            data[i][1] = displayList.get(i).prefix();
            data[i][2] = displayList.get(i).firstName();
            data[i][3] = displayList.get(i).middleInitial().toString();
            data[i][4] = displayList.get(i).lastName();
            data[i][5] = displayList.get(i).gender().toString();
            data[i][6] = displayList.get(i).email();
            data[i][7] = displayList.get(i).dateOfBirth().toString();
            data[i][8] = displayList.get(i).joiningDate().toString();
            data[i][9] = displayList.get(i).salary().toString();
        }
        ((DefaultTableModel) recordsTable.getModel()).setDataVector(data, COLUMN_NAMES);
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

                //TODO: search logic

                String selectedOption = (String) searchSelector.getSelectedItem();
                assert selectedOption != null;

                String searchText = searchBarField.getText();
                switch (selectedOption) {
                    case "ID":
                        if (!searchText.isEmpty()) {
                            LinkedList<EmployeeRecord> results = new LinkedList<>();
                            results.push(employeeAccessObject.getEmployee(searchText));
                            updateDisplayTable(results);
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
                        if (!lowerDateRangeText.isEmpty() && !upperDateRangeText.isEmpty()) {
                            LocalDate lower = LocalDate.parse(lowerDateRangeText, DATE_FORMATTER);
                            LocalDate upper = LocalDate.parse(upperDateRangeText, DATE_FORMATTER);
                            updateDisplayTable((LinkedList<EmployeeRecord>) employeeAccessObject.getEmployees(lower, upper));
                        }
                        break;
                    case "Age":
                        int lowerAgeRange = Integer.parseInt(lowerRangeField.getText());
                        int upperAgeRange = Integer.parseInt(upperRangeField.getText());
                        if (lowerAgeRange >= 0 && upperAgeRange >= 0 && upperAgeRange >= lowerAgeRange) {
                            updateDisplayTable((LinkedList<EmployeeRecord>) employeeAccessObject.getEmployees(lowerAgeRange, upperAgeRange));
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

    public void showGUI() {
        window.add(scrollableRecordsTable, BorderLayout.CENTER);
        window.add(uiPanel, BorderLayout.NORTH);
        window.setVisible(true);
    }
}
