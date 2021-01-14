package bdisi.gui.dialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class DialogRollback extends JDialog {
    public DialogRollback() {
        try {
            String filePath = "";
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".sql files only", "sql");
            chooser.setFileFilter(filter);
            int option = chooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                if (getFileExtension(file).equals(".sql"))
                    filePath = file.getPath();
                else
                    filePath = file.getPath() + ".sql";
            }
            String executeCmd = "\"C:/Program Files/MariaDB 10.5/bin/mysqldump.exe\" --database=music --user=root --host=127.0.0.1 --port=3306 < " + filePath;
            System.out.println(executeCmd);

            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                System.out.println("Restore Complete");
            } else {
                System.out.println("Restore Failure");
            }

        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Restore" + ex.getMessage());
        }
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1)
            return "";
        return name.substring(lastIndexOf);
    }
}