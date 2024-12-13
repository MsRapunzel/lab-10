package com.msrapunzel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.jms.*;
import jakarta.annotation.Resource;

@Named
@ApplicationScoped
public class MessageSenderBean {

    // 1. localhost:4848 -> Resources JMS Resources -> Connection Factories
    @Resource(lookup = "jms/jakartaEEmyFactory")
    private ConnectionFactory connectionFactory;

    // 2. localhost:4848 -> Resources JMS Resources -> Destination Resources
    @Resource(lookup = "jms/jakartaEEmyQueue")
    private Queue jakartaEEmyQueue;

    private String lastMessageText;
    private String lastMessageSelector;

    public void sendMessage(String messageText, String selector) {
        try (JMSContext context = connectionFactory.createContext()) {
            Message message = context.createTextMessage(messageText);

            if (selector != null && !selector.isEmpty()) {
                if (selector.contains("type:")) {
                    String type = selector.split(":")[1];
                    message.setStringProperty("MessageType", type);
                }
            }

            context.createProducer().send(jakartaEEmyQueue, message);

            this.lastMessageText = messageText;
            this.lastMessageSelector = selector;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLastMessageText() {
        return lastMessageText;
    }

    public String getLastMessageSelector() {
        return lastMessageSelector;
    }
}
