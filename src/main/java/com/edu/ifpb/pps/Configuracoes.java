package com.edu.ifpb.pps;

import io.github.cdimascio.dotenv.Dotenv;

public class Configuracoes {

    private static final Dotenv dotenv = Dotenv.load();

    public static String getTelegramBotToken() {
        return dotenv.get("TELEGRAM_BOT_TOKEN");
    }

    public static String getTelegramChatId() {
        return dotenv.get("TELEGRAM_CHAT_ID");
    }

    public static String getDbUrl() {
        return dotenv.get("DB_URL");
    }

    public static String getDbUser() {
        return dotenv.get("DB_USER");
    }

    public static String getDbPassword() {
        return dotenv.get("DB_PASSWORD");
    }
    
    public static String getApiUrl() {
        return dotenv.get("API_URL");
    }
}