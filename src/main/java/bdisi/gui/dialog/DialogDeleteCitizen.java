package bdisi.gui.dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Locale;

public class DialogDeleteCitizen extends JDialog implements ActionListener {
    private final Connection connection;
    private final String status;
    private JTextField textFieldPesel;

    public DialogDeleteCitizen(Connection connection, String status) {
        this.connection = connection;
        this.status = status;

        initUI();
        initLabel();
        initTextField();
        initButton();

        this.setVisible(true);
    }

    protected void initUI() {
        this.setTitle("Census [delete " + status.toLowerCase(Locale.ROOT) + "]");
        this.setSize(300, 250);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    protected void initLabel() {
        JLabel label = new JLabel("Enter PESEL number of a " + status.toLowerCase(Locale.ROOT));
        label.setBounds(45, 25, 300, 30);
        this.add(label);
    }

    protected void initTextField() {
        textFieldPesel = new JTextField();
        textFieldPesel.setBounds(50, 90, 180, 30);
        this.add(textFieldPesel);

        textFieldPesel.addActionListener(this);
    }

    protected void initButton() {
        JButton button = new JButton("OK");
        button.setBounds(90, 140, 100, 50);
        this.add(button);

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pesel = textFieldPesel.getText();

        JOptionPane.showMessageDialog(this, deleteBureaucrat(pesel));

        this.dispose();
    }

    private String deleteBureaucrat(String pesel) {
        try {
            CallableStatement cstmt = connection.prepareCall("{CALL deleteCitizen(?, ?, ?)}");
            cstmt.setString(1, pesel);
            cstmt.setString(2, status);
            cstmt.registerOutParameter(3, Types.VARCHAR);

            cstmt.execute();
            String result = cstmt.getString(3);
            cstmt.close();
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Error";
        }
    }
}