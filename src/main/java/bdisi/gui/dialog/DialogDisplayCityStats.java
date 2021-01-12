package bdisi.gui.dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogDisplayCityStats extends JDialog implements ActionListener {
    private JTextField textField;

    public DialogDisplayCityStats() {
        initUI();
        initLabel();
        initTextField();
        initButton();
        this.setVisible(true);
    }

    private void initUI() {
        this.setTitle("Census [display city stats]");
        this.setSize(300, 250);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void initLabel() {
        JLabel label = new JLabel("Enter name of the city");
        label.setBounds(80, 30, 140, 30);
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
        String city = textField.getText();
        int inhabitants = 0;
        //TODO: call a method that gets the number of inhabitants
        JOptionPane.showMessageDialog(this, "City " + city + " has " + inhabitants + " inhabitants.");
        this.dispose();
    }
}