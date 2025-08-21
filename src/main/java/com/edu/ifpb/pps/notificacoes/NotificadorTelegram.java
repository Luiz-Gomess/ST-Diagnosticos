package com.edu.ifpb.pps.notificacoes;

import java.time.LocalDate;

import com.edu.ifpb.pps.Configuracoes;
import com.edu.ifpb.pps.models.Paciente;
import com.edu.ifpb.pps.utils.TelegramService;

public class NotificadorTelegram extends NotificadorBase{

    private String destinatarioChatId;
    private String botToken = Configuracoes.getTelegramBotToken();
    private TelegramService service = new TelegramService(botToken);

    public NotificadorTelegram(INotificador encapsulado, String destinatarioChatId) {
        super(encapsulado);
        this.destinatarioChatId = destinatarioChatId;

    }

    @Override
    public void notificar(Paciente paciente, String caminhoDoArquivo) {

        super.notificar(paciente, caminhoDoArquivo);
        
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Olá, ").append(paciente.getNome()).append(" \n\n");
            sb.append("O Laudo do seu exame está disponível!\n");

            String mensagem = sb.toString();
            
            service.enviarDocumento(destinatarioChatId, mensagem, caminhoDoArquivo);

        } catch (Exception e) {
            System.err.println("❌ Erro ao construir a requisição para o Telegram.");
            e.printStackTrace();
        }

    }
    
    public static void main(String[] args) {
        Paciente paciente = new Paciente(1, "7235", "Luiz Fernando", null, "lfernandoagomes@gmail.com", "83987999851", LocalDate.of(2005, 06, 8));

        INotificador base = 
        new NotificadorTelegram(
            new NotificadorEmail(
                new NotificadorSistema(null)
                ), Configuracoes.getTelegramChatId()
        );

        base.notificar(paciente, "/home/luiz/pps/projeto/laudo_ressonancia.txt");
    }

}
