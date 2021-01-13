package bdisi.gui.dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Locale;

public class DialogDisplayStatus extends JDialog implements ActionListener {
    private final Connection connection;
    private JTextField textField;

    public DialogDisplayStatus(Connection connection) {
        this.connection = connection;

        initUI();
        initLabel();
        initTextField();
        initButton();

        this.setVisible(true);
    }

    private void initUI() {
        this.setTitle("Census [display user's status]");
        this.setSize(300, 250);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void initLabel() {
        JLabel label = new JLabel("Enter PESEL number of the user");
        label.setBounds(50, 30, 200, 30);
        this.add(label);
    }

    private void initTextField() {
        textField = new JTextField();
        textField.setBounds(50, 80, 200, 30);
        this.add(textField);

        textField.addActionListener(this);
    }

    private void initButton() {
        JButton button = new JButton("OK");
        button.setBounds(100, 130, 100, 50);
        this.add(button);

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pesel = textField.getText();

        if (pesel.length() == 11 && pesel.matches("[0-9]+")) {
            String status = displayStatus(pesel);

            if (status != null) {
                JOptionPane.showMessageDialog(this, "User with PESEL number "
                        + pesel + " is a(n) " + status.toLowerCase(Locale.ROOT) + ".");
            } else {
                JOptionPane.showMessageDialog(this, "User with PESEL number "
                        + pesel + " does not exist.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "This PESEL number is invalid.");
        }

        this.dispose();
    }

    private String displayStatus(String pesel) {
        try {
            CallableStatement cstmt = connection.prepareCall("{CALL displayStatus(?, ?)}");
            cstmt.setString(1, pesel);
            cstmt.registerOutParameter(2, Types.VARCHAR);
            cstmt.execute();
            String result = cstmt.getString(2);
            cstmt.close();
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}