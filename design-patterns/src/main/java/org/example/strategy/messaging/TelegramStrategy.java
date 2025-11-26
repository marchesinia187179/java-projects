package org.example.strategy.messaging;

public class TelegramStrategy implements MessagingStrategy {
    @Override
    public void send(String sender, String receiver, String message) {
        System.out.println("The message '" + message + "' was sent to " + receiver + " from " + sender + " by Telegram");
    }
}
