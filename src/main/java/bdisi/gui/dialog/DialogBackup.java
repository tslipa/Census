package bdisi.gui.dialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Locale;

public class DialogBackup extends JDialog implements ActionListener {
    protected JTextField textFieldMySqlDump;
    protected JTextField textFieldBackup;

    private JButton okButton;
    private JButton mySqlDumpButton;
    private JButton backupButton;

    public DialogBackup() {
        initUI();
        initLabel();
        initTextField();
        initButtons();

        this.setVisible(true);
    }

    protected void initUI() {
        this.setTitle("Census [do backup]");
        this.setSize(550, 320);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    protected void initLabel() {
        JLabel labelDescription = new JLabel("Enter path to mysqldump.exe and backup file");
        labelDescription.setBounds(125, 35, 300, 30);
        this.add(labelDescription);

        JLabel labelMySqlDump = new JLabel("Path to mysqldump");
        labelMySqlDump.setBounds(30, 100, 120, 30);
        this.add(labelMySqlDump);

        JLabel labelBackup = new JLabel("Path to backup file");
        labelBackup.setBounds(30, 150, 120, 30);
        this.add(labelBackup);
    }

    protected void initTextField() {
        textFieldMySqlDump = new JTextField();
        textFieldMySqlDump.setBounds(165, 100, 215, 30);
        this.add(textFieldMySqlDump);

        textFieldBackup = new JTextField();
        textFieldBackup.setBounds(165, 150, 215, 30);
        this.add(textFieldBackup);
    }

    protected void initButtons() {
        mySqlDumpButton = new JButton("Browse");
        mySqlDumpButton.setBounds(410, 100, 100, 30);
        this.add(mySqlDumpButton);

        backupButton = new JButton("Browse");
        backupButton.setBounds(410, 150, 100, 30);
        this.add(backupButton);

        okButton = new JButton("OK");
        okButton.setBounds(220, 220, 100, 50);
        this.add(okButton);

        mySqlDumpButton.addActionListener(this);
        backupButton.addActionListener(this);
        okButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            if (textFieldBackup.getText().equals("") || textFieldMySqlDump.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Choose the paths.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String executeCmd = "\"" + textFieldMySqlDump.getText() + "\" " + " census --complete-insert --result-file=" + textFieldBackup.getText() + " --skip-disable-keys --user=root --password=pepet --host=127.0.0.1 --port=3306";
                Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int processComplete = runtimeProcess.waitFor();

                if (processComplete == 0) {
                    JOptionPane.showMessageDialog(this, "Backup Complete");
                } else {
                    JOptionPane.showMessageDialog(this, "Backup Failure");
                }
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
            this.dispose();
        } else if (e.getSource() == mySqlDumpButton) {
            JFileChooser mySqlDumpChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".exe files only", "exe");
            mySqlDumpChooser.setFileFilter(filter);
            int option = mySqlDumpChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                textFieldMySqlDump.setText(mySqlDumpChooser.getSelectedFile().getPath());
            }
        } else if (e.getSource() == backupButton) {
            JFileChooser backupChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".sql files only", "sql");
            backupChooser.setFileFilter(filter);
            int option = backupChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                String path = backupChooser.getSelectedFile().getPath();
                path = path + sqlExtAdd(path);
                textFieldBackup.setText(path);
            }
        }
    }

    private String sqlExtAdd(String name) {
        System.out.println(name);
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ".sql";
        }
        if (!(name.substring(lastIndexOf)).equals(".sql")) {
            return ".sql";
        }
        return "";
    }
}