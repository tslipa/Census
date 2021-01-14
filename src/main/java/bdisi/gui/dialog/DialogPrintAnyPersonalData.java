package bdisi.gui.dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class DialogPrintAnyPersonalData extends JDialog implements ActionListener {
    private final Connection connection;
    private JTextField textField;

    public DialogPrintAnyPersonalData(Connection connection) {
        this.connection = connection;

        initUI();
        initLabel();
        initTextField();
        initButton();

        this.setVisible(true);
    }

    protected void initUI() {
        this.setTitle("Census [print personal data]");
        this.setSize(300, 250);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    protected void initLabel() {
        JLabel label = new JLabel("Enter the PESEL number");
        label.setBounds(64, 30, 170, 30);
        this.add(label);
    }

    protected void initTextField() {
        textField = new JTextField();
        textField.setBounds(50, 80, 200, 30);
        this.add(textField);

        textField.addActionListener(this);
    }

    protected void initButton() {
        JButton button = new JButton("OK");
        button.setBounds(100, 130, 100, 50);
        this.add(button);

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pesel = textField.getText();

        JOptionPane.showMessageDialog(this, printPersonalData(pesel));

        this.dispose();
    }

    private String printPersonalData(String pesel) {
        try {
            CallableStatement cstmt = connection.prepareCall("{CALL printPersonalData(?, ?, ?, ?)}");
            cstmt.setString(1, pesel);
            cstmt.registerOutParameter(2, Types.VARCHAR);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, Types.VARCHAR);

            cstmt.execute();
            String result = "PESEL number:\n" + pesel + "\n";
            String names = cstmt.getString(2);
            String address = cstmt.getString(3);
            String birthday = cstmt.getString(4);
            if (address != null) {
                result = result + "\nName and surname:\n" + names + "\n";
                result = result + "\nAddress:\n" + address + "\n";
                result = result + "\nBirthday:\n" + birthday;
            } else {
                result = result + "\n" + names;
            }

            cstmt.close();

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Error";
        }
    }
}