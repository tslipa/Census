package bdisi.gui.dialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
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
        this.setTitle("Census [restore database]");
        this.setSize(550, 320);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    protected void initLabel() {
        JLabel labelDescription = new JLabel("Enter path to mysql.exe and backup file");
        labelDescription.setBounds(140, 35, 300, 30);
        this.add(labelDescription);

        JLabel labelMySql = new JLabel("Path to mysql");
        labelMySql.setBounds(30, 100, 120, 30);
        this.add(labelMySql);

        JLabel labelRestore = new JLabel("Path to backup file");
        labelRestore.setBounds(30, 150, 120, 30);
        this.add(labelRestore);
    }

    protected void initTextField() {
        textFieldMySql = new JTextField();
        textFieldMySql.setBounds(165, 100, 215, 30);
        this.add(textFieldMySql);

        textFieldRestore = new JTextField();
        textFieldRestore.setBounds(165, 150, 215, 30);
        this.add(textFieldRestore);
    }

    protected void initButton() {
        mySqlButton = new JButton("Browse");
        mySqlButton.setBounds(410, 100, 100, 30);
        this.add(mySqlButton);

        restoreButton = new JButton("Browse");
        restoreButton.setBounds(410, 150, 100, 30);
        this.add(restoreButton);

        okButton = new JButton("OK");
        okButton.setBounds(220, 220, 100, 50);
        this.add(okButton);

        mySqlButton.addActionListener(this);
        restoreButton.addActionListener(this);
        okButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            if (textFieldRestore.getText().equals("") || textFieldMySql.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Choose the paths.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String comando = "\"" + textFieldMySql.getText() + "\" " + " --database=census --user=root --password=pepet --host=127.0.0.1 --port=3306  < " + textFieldRestore.getText();
                File f = new File("restore.bat");
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(comando.getBytes());
                fos.close();


                Process runtimeProcess = Runtime.getRuntime().exec("cmd /C start restore.bat");
                int processComplete = runtimeProcess.waitFor();
                Runtime.getRuntime().exec("taskkill /IM cmd.exe");

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
}