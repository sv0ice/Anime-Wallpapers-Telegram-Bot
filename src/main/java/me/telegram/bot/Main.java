package me.telegram.bot;

import com.pengrad.telegrambot.TelegramBot;

public class Main {


    private static final String BOT_TOKEN = "5518356496:AAFyQRyqyXlFcMnA8RymNockmX2DUUktoMo";

    public static void main(String[] args) {

        TelegramBotApplication application = TelegramBotApplication.builder()
                .botToken(BOT_TOKEN)
                .build();

        application.run();
    }

}