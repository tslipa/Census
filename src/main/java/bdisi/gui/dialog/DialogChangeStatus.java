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
        this.setSize(300, 350);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    protected void initLabel() {
        JLabel labelDescription = new JLabel("Change status of a non-admin");
        labelDescription.setBounds(55, 25, 300, 30);
        this.add(labelDescription);

        JLabel labelPesel = new JLabel("PESEL number");
        labelPesel.setBounds(50, 110, 180, 30);
        this.add(labelPesel);

        JLabel labelStatus = new JLabel("New status");
        labelStatus.setBounds(50, 190, 180, 30);
        this.add(labelStatus);
    }

    private void initComboBox() {
        String[] statuses = {"Citizen", "Bureaucrat"};
        comboBox = new JComboBox<>(statuses);
        comboBox.setBounds(50, 160, 190, 30);
        this.add(comboBox);
    }

    protected void initTextField() {
        textFieldPesel = new JTextField();
        textFieldPesel.setBounds(50, 80, 190, 30);
        this.add(textFieldPesel);

        textFieldPesel.addActionListener(this);
    }

    protected void initButton() {
        JButton button = new JButton("OK");
        button.setBounds(80, 250, 100, 50);
        this.add(button);

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pesel = textFieldPesel.getText();
        String status = (String) comboBox.getSelectedItem();

        if (changeStatus(pesel, status)) {
            JOptionPane.showMessageDialog(this, "Status changed.");
        } else {
            JOptionPane.showMessageDialog(this, "This PESEL number does not exist in the database" +
                    " or you are not allowed to change status of this user.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        this.dispose();
    }

    private boolean changeStatus(String pesel, String status) {
        try {
            CallableStatement cstmt = connection.prepareCall("{CALL changeStatus(?, ?, ?)}");
            cstmt.setString(1, pesel);
            cstmt.setString(2, status);
            cstmt.registerOutParameter(3, Types.INTEGER);

            cstmt.execute();
            int result = cstmt.getInt(3);
            cstmt.close();

            return (result == 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}