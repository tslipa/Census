package bdisi.gui.dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class DialogPrintMyPersonalData extends JDialog {
    private final Connection connection;
    private final String pesel;

    public DialogPrintMyPersonalData(Connection connection, String pesel) {
        this.connection = connection;
        this.pesel = pesel;

        JOptionPane.showMessageDialog(this, printPersonalData());
    }

    private String printPersonalData() {
        try {
            CallableStatement cstmt = connection.prepareCall("{CALL printPersonalData(?, ?, ?, ?)}");
            cstmt.setString(1, pesel);
            cstmt.registerOutParameter(2, Types.VARCHAR);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.registerOutParameter(4, Types.VARCHAR);

            cstmt.execute();
            String result = "PESEL number:\n" + pesel + "\n";
            result += "\nName and surname:\n" + cstmt.getString(2) + "\n";
            result += "\nAddress:\n" + cstmt.getString(3) + "\n";
            result += "\nBirthday:\n" + cstmt.getString(4);

            cstmt.close();

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Error";
        }
    }
}