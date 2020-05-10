package ui;

import javax.swing.*;

public class AddLabelsTextFields {
    JPanel panel;

    public JPanel createLabelTextFields(JLabel label, JTextField textField) {
        panel = new JPanel();
        panel.add(label);
        panel.add(textField);
        return panel;
    }
}
