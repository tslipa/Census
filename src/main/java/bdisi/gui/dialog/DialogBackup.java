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

public class DialogBackup extends JDialog {
    public DialogBackup() {
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
            String executeCmd = "\"C:/Program Files/MariaDB 10.5/bin/mysqldump.exe\" census --complete-insert --result-file=" + filePath  + " --skip-add-locks --skip-lock-tables --disable-keys --skip-add-drop-table --extended-insert --user=root --host=127.0.0.1 --port=3306";
            System.out.println(executeCmd);

            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                System.out.println("Backup Complete");
            } else {
                System.out.println("Backup Failure");
            }

        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
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