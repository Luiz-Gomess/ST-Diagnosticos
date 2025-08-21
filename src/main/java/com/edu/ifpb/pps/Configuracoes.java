package com.edu.ifpb.pps;

import io.github.cdimascio.dotenv.Dotenv;

public class Configuracoes {

    private static final Dotenv dotenv = Dotenv.load();

    public static String getTelegramBotToken() {
        return dotenv.get("TELEGRAM_BOT_TOKEN");
    }

    public static String getTelegramChatId() {
        return dotenv.get("LUIZ_TELEGRAM_ID");
    }

    public static String getEmailRemetente() {
        return dotenv.get("REMETENTE_EMAIL");
    }

    public static String getSenhaEmail() {
        return dotenv.get("REMETENTE_SENHA");
    }
}