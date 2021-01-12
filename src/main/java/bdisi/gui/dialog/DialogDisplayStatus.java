package bdisi.gui.dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class DialogDisplayStatus extends JDialog implements ActionListener {
    private JTextField textField;

    public DialogDisplayStatus() {
        initUI();
        initLabel();
        initTextField();
        initButton();
        this.setVisible(true);
    }

    private void initUI() {
        this.setTitle("Census [display user's status]");
        this.setSize(300, 250);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void initLabel() {
        JLabel label = new JLabel("Enter PESEL number of the user");
        label.setBounds(50, 30, 200, 30);
        this.add(label);
    }

    private void initTextField() {
        textField = new JTextField();
        textField.setBounds(50, 80, 200, 30);
        this.add(textField);

        textField.addActionListener(this);
    }

    private void initButton() {
        JButton button = new JButton("OK");
        button.setBounds(100, 130, 100, 50);
        this.add(button);

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pesel = textField.getText();
        //TODO: call a method that checks the pesel
        if (true /* invalid pesel */) {
            JOptionPane.showMessageDialog(this, "User with this PESEL number does not exist.");
        } else {
            String status = "Bureaucrat";
            //TODO: call a method that gets the status
            JOptionPane.showMessageDialog(this, "User with PESEL number "
                    + pesel + " is a " + status.toLowerCase(Locale.ROOT) + ".");
        }
        this.dispose();
    }
}