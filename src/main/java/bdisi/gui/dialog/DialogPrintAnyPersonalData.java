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

    private JTextField textFieldPesel;

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
        JLabel labelDescription = new JLabel("Enter a pesel");
        labelDescription.setBounds(50, 20, 300, 30);
        this.add(labelDescription);

        JLabel labelPesel = new JLabel("Pesel");
        labelPesel.setBounds(50, 90, 180, 30);
        this.add(labelPesel);
    }

    protected void initTextField() {
        textFieldPesel = new JTextField();
        textFieldPesel.setBounds(50, 60, 180, 30);
        this.add(textFieldPesel);

        textFieldPesel.addActionListener(this);
    }

    protected void initButton() {
        JButton button = new JButton("OK");
        button.setBounds(80, 140, 100, 50);
        this.add(button);

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pesel = textFieldPesel.getText();

        if (checkPesel(pesel)) {
            JOptionPane.showMessageDialog(this, printPersonalData(pesel));
        } else {
            JOptionPane.showMessageDialog(this, "No such pesel in the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        this.dispose();
    }

    private boolean checkPesel(String pesel) {
        try {
            CallableStatement cstmt = connection.prepareCall("{CALL checkPesel(?, ?)}");
            cstmt.setString(1, pesel);

            boolean result;
            cstmt.registerOutParameter(2, Types.INTEGER);
            result = cstmt.getInt(2) == 1;
            cstmt.execute();
            cstmt.close();
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private String printPersonalData(String pesel) {
        String result = "Error";
        try {
            CallableStatement cstmt = connection.prepareCall("{CALL printPersonalData(?, ?)}");
            cstmt.setString(1, pesel);

            cstmt.registerOutParameter(2, Types.VARCHAR);
            cstmt.execute();
            result = cstmt.getString(2);
            cstmt.close();
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return result;
        }
    }
}