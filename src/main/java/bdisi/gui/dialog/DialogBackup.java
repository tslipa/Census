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
        initButton();

        this.setVisible(true);
    }

    protected void initUI() {
        this.setTitle("Census [do backup]");
        this.setSize(450, 320);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    protected void initLabel() {
        JLabel labelDescription = new JLabel("Enter path to mysqldump and backup file");
        labelDescription.setBounds(85, 20, 300, 30);
        this.add(labelDescription);

        JLabel labelMySqlDump = new JLabel("path to mysqldump");
        labelMySqlDump.setBounds(50, 110, 180, 30);
        this.add(labelMySqlDump);

        JLabel labelBackup = new JLabel("path to backup file");
        labelBackup.setBounds(50, 190, 180, 30);
        this.add(labelBackup);
    }

    protected void initTextField() {
        textFieldMySqlDump = new JTextField();
        textFieldMySqlDump.setBounds(50, 80, 180, 30);
        this.add(textFieldMySqlDump);

        textFieldBackup = new JTextField();
        textFieldBackup.setBounds(50, 160, 180, 30);
        this.add(textFieldBackup);


        textFieldMySqlDump.addActionListener(this);
        textFieldBackup.addActionListener(this);
    }

    protected void initButton() {
        okButton = new JButton("OK");
        okButton.setBounds(150, 220, 100, 50);
        this.add(okButton);

        mySqlDumpButton = new JButton("browse");
        mySqlDumpButton.setBounds(250, 80, 120, 30);
        this.add(mySqlDumpButton);

        backupButton = new JButton("browse");
        backupButton.setBounds(250, 160, 120, 30);
        this.add(backupButton);


        okButton.addActionListener(this);
        mySqlDumpButton.addActionListener(this);
        backupButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            try {
                String executeCmd = "\"" + textFieldMySqlDump.getText() + "\" " + " census --complete-insert --result-file=" + textFieldBackup.getText() + " --skip-add-locks --skip-lock-tables --skip-disable-keys --skip-add-drop-table --skip-extended-insert --skip-create-options --user=root --host=127.0.0.1 --port=3306";
                Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int processComplete = runtimeProcess.waitFor();

                if (processComplete == 0) {
                    System.out.println("Restore Complete");
                } else {
                    System.out.println("Restore Failure");
                }
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
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
                textFieldBackup.setText(backupChooser.getSelectedFile().getPath());
            }
        }
    }

    public static void main(String[] args) {
        new DialogBackup();
    }
}