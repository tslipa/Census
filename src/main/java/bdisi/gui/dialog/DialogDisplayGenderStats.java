package bdisi.gui.dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogDisplayGenderStats extends JDialog implements ActionListener {
    private JComboBox<String> comboBox;

    public DialogDisplayGenderStats() {
        initUI();
        initLabel();
        initComboBox();
        initButton();
        this.setVisible(true);
    }

    private void initUI() {
        this.setTitle("Census [display gender stats]");
        this.setSize(300, 250);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private void initLabel() {
        JLabel label = new JLabel("Choose a gender");
        label.setBounds(100, 30, 100, 30);
        this.add(label);
    }

    private void initComboBox() {
        String[] genders = {"Woman", "Man"};
        comboBox = new JComboBox<>(genders);
        comboBox.setBounds(50, 80, 200, 30);
        this.add(comboBox);
    }

    private void initButton() {
        JButton button = new JButton("OK");
        button.setBounds(100, 130, 100, 50);
        this.add(button);

        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String gender = (String) comboBox.getSelectedItem();
        int quantity = 0;
        //TODO: call a method that gets the number of inhabitants
        JOptionPane.showMessageDialog(this, gender + " is a gender of " + quantity + " people.");
        this.dispose();
    }
}