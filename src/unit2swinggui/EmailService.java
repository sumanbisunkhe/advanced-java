package unit2swinggui;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;



import jakarta.activation.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;


public class EmailService {

    private final String senderEmail = "clikeflowers@gmail.com";
    private final String senderPassword = "obpn lqhq rrnu xckn";

    public boolean sendEmail(String to, String subject, String messageText, String filePath) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            // Email body
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(messageText);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);

            // Attachment
            if (filePath != null && !filePath.isEmpty()) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                DataSource source = new FileDataSource(filePath);
                attachmentPart.setDataHandler(new DataHandler(source));
                attachmentPart.setFileName(new File(filePath).getName());
                multipart.addBodyPart(attachmentPart);
            }

            message.setContent(multipart);

            // Send email
            Transport.send(message);

            // Save in DB
            saveEmail(to, subject, messageText, filePath);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void saveEmail(String to, String subject, String message, String attachment) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO sent_emails(recipient, subject, message, attachment) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, to);
            pst.setString(2, subject);
            pst.setString(3, message);
            pst.setString(4, attachment);
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}