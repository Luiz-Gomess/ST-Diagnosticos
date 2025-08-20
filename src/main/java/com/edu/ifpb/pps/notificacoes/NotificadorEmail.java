package com.edu.ifpb.pps.notificacoes;

import java.util.Properties;

import com.edu.ifpb.pps.Configuracoes;
import com.edu.ifpb.pps.models.Paciente;

import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class NotificadorEmail extends NotificadorBase {

    private Session session;
    // private static final String REMETENTE = "lfernandoagomes@gmail.com";
    private final String REMETENTE = Configuracoes.getEmailRemetente();
    private final String SENHA = Configuracoes.getSenhaEmail();
    Properties props = new Properties();

    public NotificadorEmail(INotificador encapsulado) {
        super(encapsulado);
                
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

    @Override
    public void notificar (Paciente paciente){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(REMETENTE));

            Address[] receiver = InternetAddress.parse(paciente.getEmail());

            message.setRecipients(Message.RecipientType.TO, receiver);
            message.setSubject("Laudo Médico Disponível");
            message.setText(String.format(
                """

                Olá %s,

                Seu Laudo Médico foi emitido e já está disponível no sistema.
                        """
            ));
            Transport.send(message);
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
}
