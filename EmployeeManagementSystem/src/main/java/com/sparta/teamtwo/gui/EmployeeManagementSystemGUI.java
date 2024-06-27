package com.sparta.teamtwo.gui;

import com.sparta.teamtwo.EmployeeRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;

public class EmployeeManagementSystemGUI {
    private JTextField searchBarField;
    private JTextField dateBeforeField;
    private JTextField dateAfterField;
    private JTextField lowerAgeRangeField;
    private JTextField upperAgeRangeField;
    private JScrollPane recordsTable;

    public EmployeeManagementSystemGUI(LinkedList<EmployeeRecord> recordList) {
        buildDisplayTable(recordList);
    }

    private void buildDisplayTable(LinkedList<EmployeeRecord> displayList) {

        String[][] data = new String[displayList.size()][10];
        for (int i = 0; i < displayList.size(); i++) {
            data[i][0] = displayList.get(i).empId();
            data[i][1] = displayList.get(i).prefix();
            data[i][2] = displayList.get(i).middleInitial().toString();
            data[i][3] = displayList.get(i).lastName();
            data[i][4] = displayList.get(i).firstName();
            data[i][5] = displayList.get(i).gender().toString();
            data[i][6] = displayList.get(i).email();
            data[i][7] = displayList.get(i).dateOfBirth().toString();
            data[i][8] = displayList.get(i).joiningDate().toString();
            data[i][9] = displayList.get(i).salary().toString();
        }

        String[] columnNames = {
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


        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        recordsTable = new JScrollPane(table);
    }

    public void setupGUI() {
        // Create the frame
        JFrame frame = new JFrame("Linked List Table Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        frame.add(recordsTable, BorderLayout.CENTER);

        // Create a panel for buttons and text field
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Create a text field
        JTextField textField = new JTextField(20);
        panel.add(textField);

        // Create an add button
        JButton addButton = new JButton("Add");
        panel.add(addButton);

        // Create a remove button
        JButton removeButton = new JButton("Remove");
        panel.add(removeButton);

        // Add the panel to the frame above the table
        frame.add(panel, BorderLayout.NORTH);

        // Add button action listener
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String text = textField.getText();
//                if (!text.isEmpty()) {
//                    linkedList.add(text);
//                    model.addRow(new Object[]{text});
//                    textField.setText("");
//                }
//            }
//        });
//
//        // Remove button action listener
//        removeButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int selectedRow = table.getSelectedRow();
//                if (selectedRow != -1) {
//                    linkedList.remove(selectedRow);
//                    model.removeRow(selectedRow);
//                }
//            }
//        });

        // Set the frame visibility
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 160, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JTextField passText = new JTextField(20);
        passText.setBounds(100, 50, 160, 25);
        panel.add(passText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);
    }
}
