package bdisi.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Logger extends JFrame implements ActionListener {
    private Connection connection;
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
        try {
            String URL = "jdbc:mysql://localhost:3306/Census?user=Logger&password=reggoL1&noAccessToProcedureBodies=true";
            connection = DriverManager.getConnection(URL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String login = textFieldLogin.getText();
        String password = textFieldPassword.getText();

        if (correctData(login, password) == 1) {
            String status = getStatus(login);
            Logger.this.dispose();

            assert status != null;
            UserWindow window = new UserWindow(status, login);
        } else {
            textFieldLogin.setText("");
            textFieldPassword.setText("");
            JOptionPane.showMessageDialog(this, "Invalid user login or password. Try again.");
        }

        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private int correctData(String pesel, String password) {
        try {
            CallableStatement cstmt = connection.prepareCall("{CALL correctLoginData(?, ?, ?)}");
            cstmt.setString(1, pesel);
            cstmt.setString(2, password);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.execute();
            int result = cstmt.getInt(3);
            cstmt.close();
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    private String getStatus(String pesel) {
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
