package bdisi.gui;

import bdisi.gui.dialog.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;


public class UserWindow extends JFrame implements ActionListener {
    private Connection connection = null;
    private final String pesel;

    private JButton printMyPersonalDataButton;
    private JButton changePasswordButton;
    private JButton changeAddressButton;

    private JButton printPersonalDataButton;
    private JButton addCitizenButton;
    private JButton deleteCitizenButton;
    private JButton displayCityStatsButton;
    private JButton displayGenderStatsButton;
    private JButton displayYearStatsButton;
    private JButton displayStatusButton;

    private JButton addBureaucratButton;
    private JButton deleteBureaucratButton;
    private JButton changeStatusButton;
    private JButton rollbackButton;
    private JButton backupButton;

    public UserWindow(String status, String pesel) {
        this.pesel = pesel;
        setTitle("Census [" + this.pesel + ", " + status + "]");

        switch (status) {
            case "Citizen":
                setupCitizen();
                break;
            case "Bureaucrat":
                setupBureaucrat();
                break;
            case "Admin":
                setupAdmin();
                break;
            default:
                dispose();
                break;
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setupCitizen() {
        setLayout(new GridLayout(1,3));
        setBounds(500, 400, 600, 100);

        try {
            String URL = "jdbc:mysql://localhost:3306/Census?user=Citizen&password=nezitiC2&noAccessToProcedureBodies=true";
            connection = DriverManager.getConnection(URL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        addCitizenButtons();
    }

    private void setupBureaucrat() {
        setLayout(new GridLayout(2,5));
        setBounds(300, 350, 1000, 200);

        try {
            String URL = "jdbc:mysql://localhost:3306/Census?user=Bureaucrat&password=tarcuareuB3&noAccessToProcedureBodies=true";
            connection = DriverManager.getConnection(URL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        addBureaucratButtons();
    }

    private void setupAdmin() {
        setLayout(new GridLayout(3,5));
        setBounds(300, 300, 1000, 300);

        try {
            String URL = "jdbc:mysql://localhost:3306/Census?user=Admin&password=nimdA4&noAccessToProcedureBodies=true";
            connection = DriverManager.getConnection(URL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        addAdminButtons();
    }

    private void addCitizenButtons() {
        printMyPersonalDataButton = new JButton("Print my data");
        printMyPersonalDataButton.addActionListener(this);
        add(printMyPersonalDataButton);

        changePasswordButton = new JButton("Change my password");
        changePasswordButton.addActionListener(this);
        add(changePasswordButton);

        changeAddressButton = new JButton("Change my address");
        changeAddressButton.addActionListener(this);
        add(changeAddressButton);
    }

    private void addBureaucratButtons() {
        addCitizenButtons();

        printPersonalDataButton = new JButton("Print somebody's data");
        printPersonalDataButton.addActionListener(this);
        add(printPersonalDataButton);

        addCitizenButton = new JButton("Add new citizen");
        addCitizenButton.addActionListener(this);
        add(addCitizenButton);

        deleteCitizenButton = new JButton("Delete a citizen");
        deleteCitizenButton.addActionListener(this);
        add(deleteCitizenButton);

        displayCityStatsButton = new JButton("Display stats of a city");
        displayCityStatsButton.addActionListener(this);
        add(displayCityStatsButton);

        displayGenderStatsButton = new JButton("Display stats of a gender");
        displayGenderStatsButton.addActionListener(this);
        add(displayGenderStatsButton);

        displayYearStatsButton = new JButton("Display stats of a year");
        displayYearStatsButton.addActionListener(this);
        add(displayYearStatsButton);

        displayStatusButton = new JButton("Display information of status");
        displayStatusButton.addActionListener(this);
        add(displayStatusButton);
    }

    private void addAdminButtons() {
        addBureaucratButtons();

        addBureaucratButton = new JButton("Add new bureaucrat");
        addBureaucratButton.addActionListener(this);
        add(addBureaucratButton);

        deleteBureaucratButton = new JButton("Delete a bureaucrat");
        deleteBureaucratButton.addActionListener(this);
        add(deleteBureaucratButton);

        changeStatusButton = new JButton("Change one's status");
        changeStatusButton.addActionListener(this);
        add(changeStatusButton);

        rollbackButton = new JButton("Do a rollback");
        rollbackButton.addActionListener(this);
        add(rollbackButton);

        backupButton = new JButton("Make a backup");
        backupButton.addActionListener(this);
        add(backupButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == printMyPersonalDataButton) {
            new DialogPrintMyPersonalData(connection, this.pesel);
        } else if (e.getSource() == changePasswordButton) {
            new DialogChangePassword(connection, this.pesel);
        } else if (e.getSource() == changeAddressButton) {
            new DialogChangeAddress(connection, this.pesel);
        } else if (e.getSource() == printPersonalDataButton) {
            new DialogPrintAnyPersonalData(connection);
        } else if (e.getSource() == addCitizenButton) {
            new DialogAddCitizen(connection, "Citizen");
        } else if (e.getSource() == deleteCitizenButton) {
            new DialogDeleteCitizen(connection, "Citizen");
        } else if (e.getSource() == displayCityStatsButton) {
            new DialogDisplayCityStats(connection);
        } else if (e.getSource() == displayGenderStatsButton) {
            new DialogDisplayGenderStats(connection);
        } else if (e.getSource() == displayYearStatsButton) {
            new DialogDisplayYearStats(connection);
        } else if (e.getSource()  == displayStatusButton) {
            new DialogDisplayStatus(connection);
        } else if (e.getSource() == addBureaucratButton) {
            new DialogAddCitizen(connection, "Bureaucrat");
        } else if (e.getSource() == deleteBureaucratButton) {
            new DialogDeleteCitizen(connection, "Bureaucrat");
        } else if (e.getSource() == changeStatusButton) {
            new DialogChangeStatus(connection);
        } else if (e.getSource() == rollbackButton) {
            // new DialogRollback(connection);
        } else if (e.getSource() == backupButton) {
            // new DialogBackup(connection);
        }
    }

    //TODO: listener close connection
}