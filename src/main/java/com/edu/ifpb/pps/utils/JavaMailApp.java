package com.edu.ifpb.pps.utils;

import java.util.Properties;

import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


public abstract class JavaMailApp {

    protected static Session session;
    // private static final String REMETENTE = "lfernandoagomes@gmail.com";
    private static final String REMETENTE = "lfernandoagomes@gsdsdmail.com";
    private static final String SENHA = "ubfz sfew yetc oqnt";
    
    static {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props,
        new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(REMETENTE, SENHA);
            }
        });
        
        session.setDebug(false);
        // session.setDebug(true);
    }

    public static void sendEmail(String assunto, String corpo, String destinatario) throws RuntimeException{
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(REMETENTE));

            Address[] receiver = InternetAddress.parse(destinatario);

            message.setRecipients(Message.RecipientType.TO, receiver);
            message.setSubject(assunto);
            message.setText(corpo);
            Transport.send(message);
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    } 


}
