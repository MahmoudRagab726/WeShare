package com.menainnovations.weshare.services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendEmail {
    private static final String HOST_SMTP = "smtp.office365.com";
    private static final int PORT = 587;
    private static final String SENDER_EMAIL = "donotreply@weshareeg.com";
    private static final String SENDER_PSW = "weshare@@123456";

    private static final String from = "donotreply@weshareeg.com";


    public int sendEmail(String to) {
        // final String to = "mahmoudragab726@gmail.com";
         final String subject = "Verification Code WE Share App";
         int code =(int)(Math.random()*10000);
         final String messageContent = "your code is : "+code+"";
        final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PSW);
            }

        });

        try {
            final Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(messageContent);
            message.setSentDate(new Date());
            Transport.send(message);
        } catch (Exception e) {
            return 0;
        }
        return code;
    }

    public Properties getEmailProperties() {
        final Properties config = new Properties();
        config.put("mail.smtp.auth", "true");
        config.put("mail.smtp.starttls.enable", "true");
        config.put("mail.smtp.host", HOST_SMTP);
        config.put("mail.smtp.port", PORT);
        return config;
    }
}
