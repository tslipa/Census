package bdisi.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Logger extends JFrame implements ActionListener {
    private JTextField textFieldLogin;
    private JTextField textFieldPassword;

    public Logger() {
        initUI();

        initLabels();
        initTextFields();
        initButton();
        setVisible(true);
    }

    private void initUI() {
        setTitle("Census [log in]");
        setSize(new Dimension(400, 320));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void initLabels() {
        JLabel labelTitle = new JLabel("Census - log in to the app");
        labelTitle.setBounds(80, 20, 280, 40);
        labelTitle.setFont(new Font(labelTitle.getName(), Font.BOLD, 18));
        this.add(labelTitle);

        JLabel labelLogin = new JLabel("Login");
        labelLogin.setBounds(40, 100, 70, 30);
        this.add(labelLogin);

        JLabel labelPassword = new JLabel("Password");
        labelPassword.setBounds(40, 140, 70, 30);
        this.add(labelPassword);
    }

    private void initTextFields() {
        textFieldLogin = new JTextField();
        textFieldLogin.setBounds(140, 100, 200, 30);
        this.add(textFieldLogin);

        textFieldPassword = new JPasswordField();
        textFieldPassword.setBounds(140, 140, 200, 30);
        this.add(textFieldPassword);

        textFieldLogin.addActionListener(this);
        textFieldPassword.addActionListener(this);
    }

    private void initButton() {
        JButton button = new JButton("OK");
        button.setBounds(150, 200, 100, 50);
        this.add(button);

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String login = textFieldLogin.getText();
        String password = textFieldPassword.getText();

        //TODO: Add calls to methods that check if the user exists and if the password is valid.
        if (true) {
            //TODO: Add call to method that gets the user's status.
            String status = "Admin";
            Logger.this.dispose();
            UserWindow window = new UserWindow(status, login);
        } else {
            textFieldLogin.setText("");
            textFieldPassword.setText("");
            JOptionPane.showMessageDialog(this, "Invalid user login or password. Try again.");
        }
    }
}
