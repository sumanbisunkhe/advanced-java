package unit2swinggui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmailSenderUI extends JFrame {

    private JTextField toField, subjectField, attachmentField;
    private JTextArea messageArea;
    private JButton browseButton, sendButton, refreshButton;
    private JTable emailTable;
    private DefaultTableModel tableModel;

    private String selectedFilePath = "";

    public EmailSenderUI() {
        setTitle("Swing Email Sender");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Send Email"));

        toField = new JTextField();
        subjectField = new JTextField();
        attachmentField = new JTextField();
        attachmentField.setEditable(false);

        messageArea = new JTextArea(5, 20);
        JScrollPane messageScroll = new JScrollPane(messageArea);

        browseButton = new JButton("Browse");
        sendButton = new JButton("Send Email");
        refreshButton = new JButton("Refresh Sent List");

        formPanel.add(new JLabel("To:"));
        formPanel.add(toField);

        formPanel.add(new JLabel("Subject:"));
        formPanel.add(subjectField);

        formPanel.add(new JLabel("Message:"));
        formPanel.add(messageScroll);

        formPanel.add(new JLabel("Attachment:"));

        JPanel attachPanel = new JPanel(new BorderLayout());
        attachPanel.add(attachmentField, BorderLayout.CENTER);
        attachPanel.add(browseButton, BorderLayout.EAST);
        formPanel.add(attachPanel);

        formPanel.add(sendButton);
        formPanel.add(refreshButton);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Recipient", "Subject", "Attachment", "Sent Date"}, 0);
        emailTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(emailTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Sent Email History"));

        // Layout
        setLayout(new BorderLayout(10, 10));
        add(formPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);

        // Button Actions
        browseButton.addActionListener(e -> chooseFile());
        sendButton.addActionListener(e -> sendEmail());
        refreshButton.addActionListener(e -> loadEmails());

        loadEmails();
    }

    private void chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            selectedFilePath = file.getAbsolutePath();
            attachmentField.setText(selectedFilePath);
        }
    }

    private void sendEmail() {
        String to = toField.getText();
        String subject = subjectField.getText();
        String message = messageArea.getText();

        if (to.isEmpty() || subject.isEmpty() || message.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields.");
            return;
        }

        EmailService emailService = new EmailService();
        boolean sent = emailService.sendEmail(to, subject, message, selectedFilePath);

        if (sent) {
            JOptionPane.showMessageDialog(this, "Email sent successfully!");
            clearForm();
            loadEmails();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to send email.");
        }
    }

    private void clearForm() {
        toField.setText("");
        subjectField.setText("");
        messageArea.setText("");
        attachmentField.setText("");
        selectedFilePath = "";
    }

    private void loadEmails() {
        tableModel.setRowCount(0);
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sent_emails ORDER BY id DESC");

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("recipient"),
                        rs.getString("subject"),
                        rs.getString("attachment"),
                        rs.getTimestamp("sent_date")
                });
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmailSenderUI().setVisible(true));
    }
}