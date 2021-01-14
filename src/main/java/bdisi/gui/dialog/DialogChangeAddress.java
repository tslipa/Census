package bdisi.gui.dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class DialogChangeAddress extends JDialog implements ActionListener {
    private final Connection connection;
    private final String pesel;

    private JTextField textFieldCity;
    private JTextField textFieldStreet;
    private JTextField textFieldHouse;
    private JTextField textFieldFlat;

    public DialogChangeAddress(Connection connection, String pesel) {
        this.connection = connection;
        this.pesel = pesel;

        initUI();
        initLabel();
        initTextField();
        initButton();

        this.setVisible(true);
    }

    protected void initUI() {
        this.setTitle("Census [change address]");
        this.setSize(400, 430);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    protected void initLabel() {
        JLabel labelDescription = new JLabel("Enter your new address");
        labelDescription.setBounds(120, 20, 150, 30);
        this.add(labelDescription);

        JLabel labelCity = new JLabel("City");
        labelCity.setBounds(50, 110, 200, 30);
        this.add(labelCity);

        JLabel labelNew = new JLabel("Street");
        labelNew.setBounds(50, 180, 180, 30);
        this.add(labelNew);

        JLabel labelHouse = new JLabel("House");
        labelHouse.setBounds(50, 250, 50, 30);
        this.add(labelHouse);

        JLabel labelFlat = new JLabel("Flat");
        labelFlat.setBounds(210, 250, 50, 30);
        this.add(labelFlat);
    }

    protected void initTextField() {
        textFieldCity = new JTextField();
        textFieldCity.setBounds(50, 80, 290, 30);
        this.add(textFieldCity);

        textFieldStreet = new JTextField();
        textFieldStreet.setBounds(50, 150, 290, 30);
        this.add(textFieldStreet);

        textFieldHouse = new JTextField();
        textFieldHouse.setBounds(50, 220, 130, 30);
        this.add(textFieldHouse);

        textFieldFlat = new JTextField();
        textFieldFlat.setBounds(210, 220, 130, 30);
        this.add(textFieldFlat);

        textFieldCity.addActionListener(this);
        textFieldStreet.addActionListener(this);
        textFieldHouse.addActionListener(this);
        textFieldFlat.addActionListener(this);
    }

    protected void initButton() {
        JButton button = new JButton("OK");
        button.setBounds(140, 310, 100, 50);
        this.add(button);

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String city = textFieldCity.getText();
        String street = textFieldStreet.getText();
        int house;
        int flat = 0;

        try {
            house = Integer.parseInt(textFieldHouse.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "House should be a number!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (textFieldFlat.getText().length() > 0) {
            try {
                flat = Integer.parseInt(textFieldFlat.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Flat should be a number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        changeAddress(city, street, house, flat);
        JOptionPane.showMessageDialog(this, "Address changed");
        this.dispose();
    }

    private void changeAddress(String city, String street, int house, Integer flat) {
        try {
            CallableStatement cstmt = connection.prepareCall("{CALL changeAddress(?, ?, ?, ?, ?)}");
            cstmt.setString(1, pesel);
            cstmt.setString(2, city);
            cstmt.setString(3, street);
            cstmt.setInt(4, house);
            cstmt.setInt(5, flat);

            cstmt.execute();
            cstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}