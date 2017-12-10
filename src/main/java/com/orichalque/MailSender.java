package com.orichalque;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Vandorallen on 10/12/2017.
 */
public class MailSender {

    private Session session;
    private String exp;
    private String pw;

    public MailSender(String id, String pw) {
        this.exp = id;
        this.pw = pw;

        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", true); // added this line
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", id);
        props.put("mail.smtp.password", pw);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        session = Session.getInstance(props,null);

    }

    public void sendMail(String dest, String subject, String body) throws MessagingException {

        MimeMessage message = new MimeMessage(session);

        try {
            InternetAddress from = new InternetAddress(exp+"@gmail.com");
            message.setSubject(subject);
            message.setFrom(from);
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(dest));

            // Create a multi-part to combine the parts
            Multipart multipart = new MimeMultipart("alternative");

            // Create your text message part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            // Add the text part to the multipart
            multipart.addBodyPart(messageBodyPart);

            // Create the html part
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(body, "text/html");

            // Add html part to multi part
            multipart.addBodyPart(messageBodyPart);

            // Associate multi-part with message
            message.setContent(multipart);

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", exp, pw);
            transport.sendMessage(message, message.getAllRecipients());

        } catch (AddressException e) {
            Logger.getAnonymousLogger().log(Level.WARNING, "An error were caught when sending the mail", e);
        }
    }
}
