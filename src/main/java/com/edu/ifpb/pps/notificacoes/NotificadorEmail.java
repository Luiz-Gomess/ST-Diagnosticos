package com.edu.ifpb.pps.notificacoes;

import java.time.LocalDate;
import java.util.Properties;

import com.edu.ifpb.pps.Configuracoes;
import com.edu.ifpb.pps.models.Paciente;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Address;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class NotificadorEmail extends NotificadorBase {

    private Session session;
    private final String REMETENTE = Configuracoes.getEmailRemetente();
    private final String SENHA = Configuracoes.getSenhaEmail();
    Properties props = new Properties();

    public NotificadorEmail(INotificador encapsulado) {
        super(encapsulado);
        
        // Prepara o ambiente 
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props,
        new jakarta.mail.Authenticator() {
            // Autentica as credenciais do remetente
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(REMETENTE, SENHA);
            }
        });
        
        session.setDebug(false);
        // session.setDebug(true);
    }

    @Override
    public void notificar (Paciente paciente, String caminhoDoArquivo){

        super.notificar(paciente, caminhoDoArquivo);
        try {
            // Inicia a crição do email
            Message message = new MimeMessage(session);
            
            // Escolhe o remetente
            message.setFrom(new InternetAddress(REMETENTE));

            // Escolhe n destinatários
            Address[] receiver = InternetAddress.parse(paciente.getEmail());
            message.setRecipients(Message.RecipientType.TO, receiver);

            // Define o titulo do email
            message.setSubject("Laudo Médico Disponível");

            // Define o corpo do email e o arquivo anexado
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(String.format(
                """
                Olá %s,

                Seu Laudo Médico foi emitido e já está disponível no sistema.
                        """
            , paciente.getNome()));

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(caminhoDoArquivo);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(source.getName()); // Define o nome do arquivo no e-mail

            // Adiciona o texto e o arquivo nos campos do email
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            message.setContent(multipart);

            // Envia
            Transport.send(message);

            System.out.println("✅ Email enviado!");
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Paciente paciente = new Paciente(1, "7235", "Luiz Fernando", null, "lfernandoagomes@gmail.com", "83987999851", LocalDate.of(2005, 06, 8));

        INotificador email = new NotificadorEmail(null);
        email.notificar(paciente, "/home/luiz/pps/projeto/laudo_ressonancia.txt");
    }
    
}
