package unit3eventhandling;

import javax.swing.*;
import java.awt.event.*;

public class ButtonEventDemo extends JFrame implements ActionListener {

    JLabel label;
    JButton button;

    public ButtonEventDemo() {
        setTitle("Button Event Demo");
        setSize(400, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel("Click the button");
        label.setBounds(130, 40, 200, 30);

        button = new JButton("Click Me");
        button.setBounds(130, 90, 120, 30);

        // Add event listener
        button.addActionListener(this);

        add(label);
        add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        label.setText("Button Clicked!");
    }

    public static void main(String[] args) {
        new ButtonEventDemo();
    }
}