package bdisi;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class UserWindow extends JFrame implements ActionListener {
  private String pesel;

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
    setTitle("Census[" + this.pesel + ", " + status + "]");

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
    addCitizenButtons();
  }

  private void setupBureaucrat() {
    setLayout(new GridLayout(2,5));
    setBounds(300, 350, 1000, 200);
    addCitizenButtons();
    addBureaucratButtons();
  }

  private void setupAdmin() {
    setLayout(new GridLayout(3,5));
    setBounds(300, 300, 1000, 300);
    addCitizenButtons();
    addBureaucratButtons();
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
    // nie da się switcha jak coś
    if (e.getSource() == printMyPersonalDataButton) {
      // new DialogPrintPersonalData(this.pesel);
    } else if (e.getSource() == changePasswordButton) {
      // new DialogChangePassword(this.pesel);
    } else if (e.getSource() == changeAddressButton) {
      // new DialogChangeAddress(this.pesel);
    } else if (e.getSource() == printPersonalDataButton) {
      // new DialogPrintPersonalData(this.pesel);
    } else if (e.getSource() == addCitizenButton) {
      // new DialogAddCitizen();
    } else if (e.getSource() == deleteCitizenButton) {
      // new DialogDeleteCitizen();
    } else if (e.getSource() == displayCityStatsButton) {
      // new DialogDisplayCityStats();
    } else if (e.getSource() == displayGenderStatsButton) {
      // new DialogDisplayGenderStats();
    } else if (e.getSource() == displayYearStatsButton) {
      // new DialogDisplayYearStats();
    } else if (e.getSource()  == displayStatusButton) {
      // new DialogDisplayStatus();
    } else if (e.getSource() == addBureaucratButton) {
      // new DialogAddBureaucrat();
    } else if (e.getSource() == deleteBureaucratButton) {
      // new DialogDeleteBureaucrat();
    } else if (e.getSource() == changeStatusButton) {
      // new DialogChangeStatus();
    } else if (e.getSource() == rollbackButton) {
      // new DialogRollback();
    } else if (e.getSource() == backupButton) {
      // new DialogBackup();
    }
  }

  //just to test
  public static void main(String[] args) {
    new UserWindow("Admin", "123455678901");
    new UserWindow("Bureaucrat", "123455678901");
    new UserWindow("Citizen", "123455678901");
  }
}