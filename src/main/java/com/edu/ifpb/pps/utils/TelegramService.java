package com.edu.ifpb.pps.utils;

import java.io.File;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramService extends TelegramLongPollingBot{

    private final String botToken;

    public TelegramService (String botToken) {
        super(botToken);
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update arg0) {
        System.out.println("Desnecessário");
    }

    @Override
    public String getBotUsername() {
        return "StDiagnosticosBot";
    }

    public void enviarDocumento (String chatId, String mensagem, String caminhoArquivo ) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setCaption(mensagem);
        sendDocument.setDocument(new InputFile(new File(caminhoArquivo)));

        try {
            System.out.println("Enviando documento para o Telegram...");
            execute(sendDocument);
            System.out.println("✅ Documento enviado com sucesso via Telegram!");
        } catch (TelegramApiException e) {
            System.err.println("❌ Falha ao enviar documento via Telegram.");
            e.printStackTrace();
        }
    }
    
}
