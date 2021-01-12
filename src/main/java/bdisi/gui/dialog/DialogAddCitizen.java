package bdisi.gui.dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class DialogAddCitizen extends JDialog implements ActionListener {
    protected final Connection connection;
    protected JTextField textFieldPesel;
    protected JTextField textFieldName;
    protected JTextField textFieldSurname;
    protected JTextField textFieldPassword;
    protected JTextField textFieldCity;
    protected JTextField textFieldStreet;
    protected JTextField textFieldHouse;
    protected JTextField textFieldFlat;

    public DialogAddCitizen(Connection connection) {
        this.connection = connection;

        initUI();
        initLabel();
        initTextField();
        initButton();

        this.setVisible(true);
    }

    protected void initUI() {
        this.setTitle("Census [add new citizen]");
        this.setSize(500, 520);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    protected void initLabel() {
        JLabel labelDescription = new JLabel("Enter personal data of the new citizen");
        labelDescription.setBounds(135, 20, 300, 30);
        this.add(labelDescription);

        JLabel labelPesel = new JLabel("PESEL number");
        labelPesel.setBounds(50, 110, 180, 30);
        this.add(labelPesel);

        JLabel labelPassword = new JLabel("Password");
        labelPassword.setBounds(270, 110, 180, 30);
        this.add(labelPassword);

        JLabel labelName = new JLabel("First name");
        labelName.setBounds(50, 190, 180, 30);
        this.add(labelName);

        JLabel labelSurname = new JLabel("Surname");
        labelSurname.setBounds(270, 190, 180, 30);
        this.add(labelSurname);

        JLabel labelCity = new JLabel("City");
        labelCity.setBounds(50, 270, 180, 30);
        this.add(labelCity);

        JLabel labelStreet = new JLabel("Street");
        labelStreet.setBounds(270, 270, 180, 30);
        this.add(labelStreet);

        JLabel labelHouse = new JLabel("House");
        labelHouse.setBounds(50, 350, 180, 30);
        this.add(labelHouse);

        JLabel labelFlat = new JLabel("Flat");
        labelFlat.setBounds(270, 350, 180, 30);
        this.add(labelFlat);
    }

    protected void initTextField() {
        textFieldPesel = new JTextField();
        textFieldPesel.setBounds(50, 80, 180, 30);
        this.add(textFieldPesel);

        textFieldPassword = new JPasswordField();
        textFieldPassword.setBounds(270, 80, 180, 30);
        this.add(textFieldPassword);

        textFieldName = new JTextField();
        textFieldName.setBounds(50, 160, 180, 30);
        this.add(textFieldName);

        textFieldSurname = new JTextField();
        textFieldSurname.setBounds(270, 160, 180, 30);
        this.add(textFieldSurname);

        textFieldCity = new JTextField();
        textFieldCity.setBounds(50, 240, 180, 30);
        this.add(textFieldCity);

        textFieldStreet = new JTextField();
        textFieldStreet.setBounds(270, 240, 180, 30);
        this.add(textFieldStreet);

        textFieldHouse = new JTextField();
        textFieldHouse.setBounds(50, 320, 180, 30);
        this.add(textFieldHouse);

        textFieldFlat = new JTextField();
        textFieldFlat.setBounds(270, 320, 180, 30);
        this.add(textFieldFlat);

        textFieldPesel.addActionListener(this);
        textFieldName.addActionListener(this);
        textFieldSurname.addActionListener(this);
        textFieldPassword.addActionListener(this);
        textFieldCity.addActionListener(this);
        textFieldStreet.addActionListener(this);
        textFieldHouse.addActionListener(this);
        textFieldFlat.addActionListener(this);
    }

    protected void initButton() {
        JButton button = new JButton("OK");
        button.setBounds(200, 410, 100, 50);
        this.add(button);

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pesel = textFieldPesel.getText();
        String password = textFieldPassword.getText();
        String name = textFieldName.getText();
        String surname = textFieldSurname.getText();
        String city = textFieldCity.getText();
        String street = textFieldStreet.getText();
        String sHouse = textFieldHouse.getText();
        String sFlat = textFieldFlat.getText();

        if (pesel.length() != 11 || !pesel.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "PESEL number is invalid.");
        } else if (password.length() < 6 || password.length() > 20) {
            JOptionPane.showMessageDialog(this, "Password should be between 6 and 20 characters.");
        } else if (name.length() == 0 || surname.length() == 0 || city.length() == 0 || street.length() == 0) {
            JOptionPane.showMessageDialog(this, "Fields: Name, Surname, City and Street can't be empty.");
        } else {
            try {
                int house = Integer.parseInt(sHouse);
                int flat = 0;
                if (!sFlat.isEmpty()) {
                    flat = Integer.parseInt(sFlat);
                }

                if (house <= 0 || (flat <= 0 && !sFlat.isEmpty())) {
                    throw new IndexOutOfBoundsException();
                }

                if (addCitizen(pesel, password, name, surname, city, street, house, flat)) {
                    JOptionPane.showMessageDialog(this, "Citizen has been added successfully.");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Citizen with this PESEL number already exists.");
                }

            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(this, "House and Flat parameters should be positive integers.");
            }
        }
    }

    protected boolean addCitizen(String pesel, String password, String name, String surname, String city, String street, int house, int flat) {
        //TODO: check if pesel already exists in database
        //TODO: take care of the "null" flat
        return true;
    }
}