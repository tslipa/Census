package bdisi.gui.dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class DialogChangePassword extends JDialog implements ActionListener {
    private final Connection connection;
    private final String pesel;
    private JTextField textFieldOld;
    private JTextField textFieldNew;
    private JTextField textFieldNewRepeat;

    public DialogChangePassword(Connection connection, String pesel) {
        this.connection = connection;
        this.pesel = pesel;

        initUI();
        initLabel();
        initTextField();
        initButton();

        this.setVisible(true);
    }

    protected void initUI() {
        this.setTitle("Census [change password]");
        this.setSize(300, 420);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    protected void initLabel() {
        JLabel labelDescription = new JLabel("Change your password");
        labelDescription.setBounds(75, 20, 250, 30);
        this.add(labelDescription);

        JLabel labelOld = new JLabel("Old password");
        labelOld.setBounds(50, 110, 180, 30);
        this.add(labelOld);

        JLabel labelNew = new JLabel("New password");
        labelNew.setBounds(50, 180, 180, 30);
        this.add(labelNew);

        JLabel labelNewRepeat = new JLabel("Repeat new password");
        labelNewRepeat.setBounds(50, 250, 180, 30);
        this.add(labelNewRepeat);
    }

    protected void initTextField() {
        textFieldOld = new JPasswordField();
        textFieldOld.setBounds(50, 80, 190, 30);
        this.add(textFieldOld);

        textFieldNew = new JPasswordField();
        textFieldNew.setBounds(50, 150, 190, 30);
        this.add(textFieldNew);

        textFieldNewRepeat = new JPasswordField();
        textFieldNewRepeat.setBounds(50, 220, 190, 30);
        this.add(textFieldNewRepeat);

        textFieldOld.addActionListener(this);
        textFieldNew.addActionListener(this);
        textFieldNewRepeat.addActionListener(this);
    }

    protected void initButton() {
        JButton button = new JButton("OK");
        button.setBounds(90, 320, 100, 50);
        this.add(button);

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String oldPassword = textFieldOld.getText();
        String newPassword = textFieldNew.getText();
        String newPasswordRepeat = textFieldNewRepeat.getText();

        if (!newPassword.equals(newPasswordRepeat)) {
            JOptionPane.showMessageDialog(this, "New password is not the same in both fields.");
        } else if (newPassword.length() < 6 || newPassword.length() > 20) {
            JOptionPane.showMessageDialog(this, "New password should be between 6 and 20 characters!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (changePassword(oldPassword, newPassword)) {
            JOptionPane.showMessageDialog(this, "Password changed.");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Wrong old password!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        textFieldOld.setText("");
        textFieldNew.setText("");
        textFieldNewRepeat.setText("");
    }

    private boolean changePassword(String oldPassword, String newPassword) {
        try {
            CallableStatement cstmt = connection.prepareCall("{CALL changePassword(?, ?, ?, ?)}");
            cstmt.setString(1, pesel);
            cstmt.setString(2, oldPassword);
            cstmt.setString(3, newPassword);
            cstmt.registerOutParameter(4, Types.INTEGER);

            cstmt.execute();
            int result = cstmt.getInt(4);

            cstmt.close();
            return (result == 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}