package bdisi.gui.dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class DialogChangeStatus extends JDialog implements ActionListener {
    private final Connection connection;
    private JComboBox<String> comboBox;

    private JTextField textFieldPesel;

    public DialogChangeStatus(Connection connection) {
        this.connection = connection;

        initUI();
        initLabel();
        initComboBox();
        initTextField();
        initButton();

        this.setVisible(true);
    }

    protected void initUI() {
        this.setTitle("Census [change status]");
        this.setSize(300, 300);
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

        JLabel labelStatus = new JLabel("Status");
        labelStatus.setBounds(50, 160, 180, 30);
        this.add(labelStatus);
    }

    private void initComboBox() {
        String[] statuses = {"Citizen", "Bureaucrat"};
        comboBox = new JComboBox<>(statuses);
        comboBox.setBounds(50, 130, 200, 30);
        this.add(comboBox);
    }

    protected void initTextField() {
        textFieldPesel = new JTextField();
        textFieldPesel.setBounds(50, 60, 180, 30);
        this.add(textFieldPesel);

        textFieldPesel.addActionListener(this);
    }

    protected void initButton() {
        JButton button = new JButton("OK");
        button.setBounds(80, 200, 100, 50);
        this.add(button);

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pesel = textFieldPesel.getText();
        String status = (String) comboBox.getSelectedItem();

        if (checkPesel(pesel) ) {
            changeStatus(pesel, status);
            JOptionPane.showMessageDialog(this, "Status changed.");
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
            cstmt.registerOutParameter(2, Types.VARCHAR);

            cstmt.execute();
            result = cstmt.getString(2) == "1";
            cstmt.close();
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void changeStatus(String pesel, String status) {
        try {
            CallableStatement cstmt = connection.prepareCall("{CALL addCitizen(?, ?)}");
            cstmt.setString(1, pesel);
            cstmt.setString(2, status);

            cstmt.execute();
            cstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}