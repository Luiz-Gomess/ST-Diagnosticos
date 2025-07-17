package com.edu.ifpb.pps.notificacoes.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.edu.ifpb.pps.Configuracoes;
import com.edu.ifpb.pps.enums.MeiosNotificacao;
import com.edu.ifpb.pps.models.Paciente;
import com.edu.ifpb.pps.notificacoes.NotificadorHandler;

public class EnvioTelegram extends NotificadorHandler{

    private static final String TELEGRAM_API_URL = "https://api.telegram.org/bot";
    private final String botToken = Configuracoes.getTelegramBotToken();
    private String chatId = Configuracoes.getTelegramChatId();
    private final HttpClient httpClient = HttpClient.newHttpClient();



    @Override
    public void notificar(Paciente paciente, String titulo, String mensagem) {
        if (paciente.getNotificadores().contains(MeiosNotificacao.TELEGRAM)){

            String textoCodificado = URLEncoder.encode(mensagem, StandardCharsets.UTF_8);
            String url = TELEGRAM_API_URL + this.botToken + "/sendMessage?chat_id=" + this.chatId + "&text=" + textoCodificado;
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

            HttpResponse<String> response;

            try {
                response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    System.out.println("Notificação enviada com sucesso!");
                    System.out.println("Resposta: " + response.body());
                } else {
                    System.err.println("Erro ao enviar notificação. Código de status: " + response.statusCode());
                    System.err.println("Resposta: " + response.body());
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Enviando notificação por Telegram para: " + paciente.getTelefone() + " - " + mensagem);
        }
        encaminhar(paciente, titulo, mensagem);
    }
    
}
