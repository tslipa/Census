package bdisi.gui.dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class DialogChangePassword extends JDialog implements ActionListener {
    private final Connection connection;
    private final String pesel;

    private JTextField textFieldOld;
    private JTextField textFieldNew;

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
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    protected void initLabel() {
        JLabel labelDescription = new JLabel("Enter new and old passwords");
        labelDescription.setBounds(50, 20, 300, 30);
        this.add(labelDescription);

        JLabel labelOld = new JLabel("Old Password");
        labelOld.setBounds(50, 90, 180, 30);
        this.add(labelOld);

        JLabel labelNew = new JLabel("New Password");
        labelNew.setBounds(50, 150, 180, 30);
        this.add(labelNew);
    }

    protected void initTextField() {
        textFieldOld = new JTextField();
        textFieldOld.setBounds(50, 60, 180, 30);
        this.add(textFieldOld);

        textFieldNew = new JTextField();
        textFieldNew.setBounds(50, 120, 180, 30);
        this.add(textFieldNew);

        textFieldOld.addActionListener(this);
        textFieldNew.addActionListener(this);
    }

    protected void initButton() {
        JButton button = new JButton("OK");
        button.setBounds(80, 190, 100, 50);
        this.add(button);

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String oldPassword = textFieldOld.getText();
        String newPassword = textFieldNew.getText();

        if (newPassword.length() < 6 || newPassword.length() > 20) {
            JOptionPane.showMessageDialog(this, "Password should be between 6 and 20 characters!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (changePassword(oldPassword, newPassword)) {
            JOptionPane.showMessageDialog(this, "Password changed");
        } else {
            JOptionPane.showMessageDialog(this, "Wrong password!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        this.dispose();
    }

    private boolean changePassword(String oldPassword, String newPassword) {
        return true;
    }
}