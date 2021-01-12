package bdisi.gui.dialog;

import org.jboss.jandex.Index;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class DialogDisplayYearStats extends JDialog implements ActionListener {
    private final Connection connection;
    private JTextField textField;

    public DialogDisplayYearStats(Connection connection) {
        this.connection = connection;

        initUI();
        initLabel();
        initTextField();
        initButton();

        this.setVisible(true);
    }

    private void initUI() {
        this.setTitle("Census [display year stats]");
        this.setSize(300, 250);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void initLabel() {
        JLabel label = new JLabel("Enter year of birth");
        label.setBounds(90, 30, 120, 30);
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
        String text = textField.getText();

        try {
            int year = Integer.parseInt(text);

            if (year < 1900 || year > 2021) {
                throw new IndexOutOfBoundsException();
            }

            int quantity = displayYearStats(year);
            JOptionPane.showMessageDialog(this, year + " is the year of birth of " + quantity + " people.");
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "The given year is invalid."
                    + " Year should be a number between 1900 and 2021.");
        }

        this.dispose();
    }

    private static int displayYearStats(int year) {
        int quantity = 0;

        return quantity;
    }
}