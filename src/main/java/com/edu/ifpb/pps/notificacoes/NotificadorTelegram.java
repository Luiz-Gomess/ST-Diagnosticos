package com.edu.ifpb.pps.notificacoes;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import com.edu.ifpb.pps.Configuracoes;
import com.edu.ifpb.pps.models.Paciente;

public class NotificadorTelegram extends NotificadorBase{

    private String destinatarioChatId;
    private String botToken = Configuracoes.getTelegramBotToken();
    private final String API_URL = "https://api.telegram.org/bot" + botToken + "/sendMessage";

    public NotificadorTelegram(INotificador encapsulado, String destinatarioChatId) {
        super(encapsulado);
        this.destinatarioChatId = destinatarioChatId;

    }

    @Override
    public void notificar(Paciente paciente, String caminhoDoArquivo) {
        
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Olá, ").append(paciente.getNome()).append(" \n\n");
            sb.append("O Laudo do seu exame está disponível!\n");

            String mensagem = sb.toString();
            String textoCodificado = URLEncoder.encode(mensagem, StandardCharsets.UTF_8);

            String urlCompleta = String.format("%s?chat_id=%s&text=%s", API_URL, destinatarioChatId, textoCodificado);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlCompleta))
                    .build();

            System.out.println("Enviando notificação para o Telegram...");
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("✅ Notificação enviada com sucesso via Telegram!");
            } else {
                System.err.println("❌ Falha ao enviar notificação. Status: " + response.statusCode());
                System.err.println("Resposta do servidor: " + response.body());
            }

        } catch (Exception e) {
            System.err.println("❌ Erro ao construir a requisição para o Telegram.");
            e.printStackTrace();
        }

    }
    
    public static void main(String[] args) {
        Paciente paciente = new Paciente(1, "7235", "Luiz Fernando", null, "lfernandoagomes@gmail.com", "83987999851", LocalDate.of(2005, 06, 8));

        INotificador email = new NotificadorTelegram(null, Configuracoes.getTelegramChatId());
        email.notificar(paciente, "/home/luiz/pps/projeto/laudo_ressonancia.txt");
    }

}
