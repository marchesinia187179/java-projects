package org.example.strategy.messaging;

public interface MessagingStrategy {
    void send(String sender, String receiver, String message);
}
