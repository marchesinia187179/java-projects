package org.example.strategy.messaging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MessageContext {
    private String sender;
    private String receiver;
    private String message;

    public void executeSend(MessagingStrategy messagingStrategy) {
        messagingStrategy.send(sender, receiver, message);
    }
}
