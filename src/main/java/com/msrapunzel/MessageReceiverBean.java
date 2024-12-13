package com.msrapunzel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.jms.*;
import jakarta.annotation.Resource;

@Named
@ApplicationScoped
public class MessageReceiverBean {
    @Resource(lookup = "jms/jakartaEEmyFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/jakartaEEmyQueue")
    private Queue jakartaEEmyQueue;

    private String lastReceivedMessage;
    private String lastUsedSelector;

    public void receiveMessageWithSelector(String selector) {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSConsumer consumer;
            if (selector != null && !selector.isEmpty()) {
                consumer = context.createConsumer(jakartaEEmyQueue, selector);
                lastUsedSelector = selector;
            } else {
                consumer = context.createConsumer(jakartaEEmyQueue);
                lastUsedSelector = "No selector";
            }

            Message message = consumer.receive(5000);

            if (message == null) {
                lastReceivedMessage = "No message received within the time frame or no matching message for selector.";
            } else if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                lastReceivedMessage = textMessage.getText();
            }
        } catch (Exception e) {
            e.printStackTrace();
            lastReceivedMessage = "Error receiving message: " + e.getMessage();
        }
    }



    public String getLastReceivedMessage() {
        return lastReceivedMessage;
    }

    public String getLastUsedSelector() {
        return lastUsedSelector;
    }
}