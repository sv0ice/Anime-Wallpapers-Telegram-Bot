package me.telegram.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;

import javax.swing.*;
import java.util.Optional;
import java.util.Random;

public class TelegramBotApplication extends TelegramBot {
    static Random rand = new Random(); //https://wallpaper.dog/download-wallpaper/20468925_aesthetic-anime
    private String[] wallpapers = {"https://winzoro.net/uploads/posts/2021-11/1638108259_preview.jpg", "https://img1.akspic.ru/crops/1/7/9/6/5/156971/156971-yarkoe_anime-anime-illustracia-rukav-oblako-3840x2160.jpg", "https://img3.akspic.ru/crops/8/1/9/6/6/166918/166918-2021-atmosfera-luna-mir-svet-3840x2160.jpg"};
    private String[] aesthetic = {"https://wallpaper.dog/large/20468925.jpg"};
    private String[] couple = {"https://aniyuki.com/wp-content/uploads/2022/04/aniyuki-anime-couple-avatars-83.jpg", "https://aniyuki.com/wp-content/uploads/2022/04/aniyuki-anime-couple-avatars-82.jpg"};
    @lombok.Builder
    public TelegramBotApplication(String botToken) {
        super(botToken);
    }

    public void run() {
//        SetWebhook request = new SetWebhook();
//        this.execute(request);
        this.setUpdatesListener(updates -> {
            updates.forEach(this::process);     // лямбда - -> {},  оператор квадроточие - ссылка на метод
//            DeleteWebhook
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private void process(Update update) {
        Message message = update.message();
        if (message != null) {
            String text = message.text();
            Optional.ofNullable(text)
                    .ifPresent(commandName -> this.serveCommand(commandName, message.chat().id()));

        }
    }

    public static String getRandom(String[] array) {
        return array[rand.nextInt(array.length)];
    }
    public static String getFirst(String[] array) {
        return array[0];
    }
    public static String getSecond(String[] array) {
        return array[1];
    }
    Icon icon = new ImageIcon("src/main/java/me/telegram/bot/wallpapersden.com_demon-slayer-kimetsu-no-yaiba-team_2000x1200.jpg");
    private void serveCommand(String commandName, Long id) {
        switch (commandName) {
            case "Обои": {
                /* Установка вебхука. Чтобы убрать вебхук, нужно убрать все три метода, и оставить SetWebhook() без параметров
                SetWebhook request = new SetWebhook();
                        //.url("");     //  wallpapersden.com_demon-slayer-kimetsu-no-yaiba-team_2000x1200.jpg
                        //.certificate(new byte[]{}) // byte[]
                        //.certificate(new File("path")); // or file
                this.execute(request);
                 */

                SendPhoto request = new SendPhoto(id, getRandom(wallpapers)); // "https://winzoro.net/uploads/posts/2021-11/1638108259_preview.jpg"
                this.execute(request);
                break;
            }
            case "Эстетика": {
                SendPhoto request = new SendPhoto(id, getRandom(aesthetic)); // "https://winzoro.net/uploads/posts/2021-11/1638108259_preview.jpg"
                this.execute(request);
                break;
            }
            case "Парные авы": {
                SendPhoto request = new SendPhoto(id, getFirst(couple)); // "https://winzoro.net/uploads/posts/2021-11/1638108259_preview.jpg"
                SendPhoto request1 = new SendPhoto(id, getSecond(couple));
                this.execute(request);
                this.execute(request1);
                break;

            }
            case "Привет": {
                SendMessage response = new SendMessage(id, "Привет!\n-Введи слово \"Помощь\" и получи список команд~");
                this.execute(response);
                break;
            }
            case "Помощь": {
                SendMessage response = new SendMessage(id, "Мой список команд:\n-Обои\n-Эстетика\n-Парные авы");
                this.execute(response);
                break;
            }

            default: {
                SendMessage response = new SendMessage(id, "Чта-чта? Повтори ещё раз!\n\nМой список команд:\n-Обои\n-Эстетика\n-Парные авы");
                this.execute(response);
                break;
            }
        }
    }

}
