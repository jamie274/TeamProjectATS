package LoginPages;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class TwoFactorAuthentication {
    public static void Send2FAEmail(String email, String code){
        // Set mail properties
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "don000935@gmail.com";
        String password = "vutevuyqdgbgmrdd";
        String fromAddress = "don000935@gmail.com";
        String toAddress = email;

        // Set mail properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Create a session with mail properties
        Session session = Session.getInstance(props);

        try {
            // Create a message object
            Message message = new MimeMessage(session);

            // Set the message details
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject("Your 2FA Code");
            message.setText("Your 2FA code is: " + code); // replace with your actual 2FA code

            // Send the message
            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("2FA code sent successfully!");

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
