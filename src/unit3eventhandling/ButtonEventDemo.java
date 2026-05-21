package unit3eventhandling;

import javax.swing.*;
import java.awt.event.*;

public class ButtonEventDemo extends JFrame
        implements ActionListener, MouseListener {

    JLabel label;
    JButton button;

    public ButtonEventDemo() {

        setTitle("Event Handling Demo");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel("Click button or mouse");
        label.setBounds(100, 40, 250, 30);

        button = new JButton("Click Me");
        button.setBounds(120, 100, 120, 40);

        // Button event
        button.addActionListener(this);

        // Mouse event
        addMouseListener(this);

        add(label);
        add(button);

        setVisible(true);
    }

    // Button click event
    @Override
    public void actionPerformed(ActionEvent e) {
        label.setText("Button Clicked!");
    }

    // Mouse click event
    @Override
    public void mouseClicked(MouseEvent e) {
        label.setText("Mouse Clicked at: X="
                + e.getX() + " Y=" + e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        new ButtonEventDemo();
    }
}