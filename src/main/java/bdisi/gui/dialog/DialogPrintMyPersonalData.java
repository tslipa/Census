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
        //wyłuskać dane z bazy
        return "Dane";
    }
}