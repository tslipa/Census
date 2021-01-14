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

public class DialogRestore extends JDialog implements ActionListener {
    protected JTextField textFieldMySql;
    protected JTextField textFieldRestore;

    private JButton okButton;
    private JButton mySqlButton;
    private JButton restoreButton;

    public DialogRestore() {
        initUI();
        initLabel();
        initTextField();
        initButton();

        this.setVisible(true);
    }

    protected void initUI() {
        this.setTitle("Census [do restore]");
        this.setSize(450, 320);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    protected void initLabel() {
        JLabel labelDescription = new JLabel("Enter path to mysql.exe and backup file");
        labelDescription.setBounds(85, 20, 300, 30);
        this.add(labelDescription);

        JLabel labelMySqlDump = new JLabel("path to mysql");
        labelMySqlDump.setBounds(50, 110, 180, 30);
        this.add(labelMySqlDump);

        JLabel labelBackup = new JLabel("path to backup file");
        labelBackup.setBounds(50, 190, 180, 30);
        this.add(labelBackup);
    }

    protected void initTextField() {
        textFieldMySql = new JTextField();
        textFieldMySql.setBounds(50, 80, 180, 30);
        this.add(textFieldMySql);

        textFieldRestore = new JTextField();
        textFieldRestore.setBounds(50, 160, 180, 30);
        this.add(textFieldRestore);


        textFieldMySql.addActionListener(this);
        textFieldRestore.addActionListener(this);
    }

    protected void initButton() {
        okButton = new JButton("OK");
        okButton.setBounds(150, 220, 100, 50);
        this.add(okButton);

        mySqlButton = new JButton("browse");
        mySqlButton.setBounds(250, 80, 120, 30);
        this.add(mySqlButton);

        restoreButton = new JButton("browse");
        restoreButton.setBounds(250, 160, 120, 30);
        this.add(restoreButton);


        okButton.addActionListener(this);
        mySqlButton.addActionListener(this);
        restoreButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            try {
                String executeCmd = "\"" + textFieldMySql.getText() + "\" " + " --database=census --user=root --password=pepet --host=127.0.0.1 --port=3306  < " + textFieldRestore.getText();
                Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int processComplete = runtimeProcess.waitFor();

                if (processComplete == 0) {
                    JOptionPane.showMessageDialog(this, "Restore Complete");
                } else {
                    JOptionPane.showMessageDialog(this, "Restore Failure");
                }
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
            this.dispose();
        } else if (e.getSource() == mySqlButton) {
            JFileChooser mySqlDumpChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".exe files only", "exe");
            mySqlDumpChooser.setFileFilter(filter);
            int option = mySqlDumpChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                textFieldMySql.setText(mySqlDumpChooser.getSelectedFile().getPath());
            }
        } else if (e.getSource() == restoreButton) {
            JFileChooser backupChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".sql files only", "sql");
            backupChooser.setFileFilter(filter);
            int option = backupChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                textFieldRestore.setText(backupChooser.getSelectedFile().getPath());
            }
        }
    }

    public static void main(String[] args) {
        new DialogRestore();
    }
}