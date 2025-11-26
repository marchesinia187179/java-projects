package org.example.strategy.messaging;

public class MessagingStrategyMain {
    public static void main(String[] args) {
        // Sets the context data (the message)
        MessageContext messageContext = new MessageContext(
                "Santa Claus", "Elf", "Hi!"
        );

        // Executes the WhatsApp, Telegram and Messenger Strategy
        messageContext.executeSend(new WhatsAppStrategy());
        messageContext.executeSend(new TelegramStrategy());
        messageContext.executeSend(new MessengerStrategy());
    }
}
