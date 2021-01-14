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