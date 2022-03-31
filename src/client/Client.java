package client;

import entity.Message;
import entity.Constants.CURRENCY;
import java.util.Random;
import entity.Constants;

public class Client {
    private String breaker = "|";

    public Client() {
    }

    public int generateMessageId() {
        if (Constants.AT_MOST_ONCE == true){
            return 1000;
        } else {
            Random rnd = new Random();
            int number = rnd.nextInt(99999);
            return number;
        }
    }

    public Message createRequestMessage() {
        return new Message(generateMessageId(), null);
    }

    public Message createAccount(String userName, String userPassword, CURRENCY selectedCurrency) {
        Message message = createRequestMessage();
        StringBuilder builder = new StringBuilder();
        builder.append(1);
        builder.append(breaker);
        builder.append(userName);
        builder.append(breaker);
        builder.append(userPassword);
        builder.append(breaker);
        builder.append(selectedCurrency);
        builder.append(breaker);
        String messageString = builder.toString();
        message.setContent(messageString);
        return message;
    }

    public Message closeAccount(String userName, int userAccount, String userPassword) {
        Message message = createRequestMessage();
        StringBuilder builder = new StringBuilder();
        builder.append(2);
        builder.append(breaker);
        builder.append(userName);
        builder.append(breaker);
        builder.append(userAccount);
        builder.append(breaker);
        builder.append(userPassword);
        builder.append(breaker);
        String messageString = builder.toString();
        message.setContent(messageString);

        return message;
    }

    public Message depositMoney(String userName, int userAccount, String userPassword, CURRENCY selectedCurrency,
            double depositSum) {
        Message message = createRequestMessage();
        // to send message
        StringBuilder builder = new StringBuilder();
        builder.append(3);
        builder.append(breaker);
        builder.append(userName);
        builder.append(breaker);
        builder.append(userAccount);
        builder.append(breaker);
        builder.append(userPassword);
        builder.append(breaker);
        builder.append(selectedCurrency);
        builder.append(breaker);
        builder.append(depositSum);
        builder.append(breaker);
        String messageString = builder.toString();
        message.setContent(messageString);

        return message;
    }

    public Message withdrawMoney(String userName, int userAccount, String userPassword, CURRENCY selectedCurrency,
            double withdrawSum) {
        Message message = createRequestMessage();
        // to send message
        StringBuilder builder = new StringBuilder();
        builder.append(4);
        builder.append(breaker);
        builder.append(userName);
        builder.append(breaker);
        builder.append(userAccount);
        builder.append(breaker);
        builder.append(userPassword);
        builder.append(breaker);
        builder.append(selectedCurrency);
        builder.append(breaker);
        builder.append(withdrawSum);
        builder.append(breaker);
        String messageString = builder.toString();
        message.setContent(messageString);

        // after receiving reply from server
        return message;
    }

    public Message monitorUpdates(double interval) {
        Message message = createRequestMessage();
        // to send message
        StringBuilder builder = new StringBuilder();
        builder.append(7);
        builder.append(breaker);
        builder.append(interval);
        builder.append(breaker);
        String messageString = builder.toString();
        message.setContent(messageString);
        return message;
    }

    public Message endMonitorUpdates() {
        Message message = createRequestMessage();
        // to send message
        StringBuilder builder = new StringBuilder();
        builder.append(8);
        builder.append(breaker);
        String messageString = builder.toString();
        message.setContent(messageString);
        return message;
    }

    public Message transferMoney(String userName, int userAccount, String userPassword, CURRENCY selectedCurrency,
            int recipientAcc, double transAmount) {
        Message message = createRequestMessage();

        // to send message
        StringBuilder builder = new StringBuilder();
        builder.append(5);
        builder.append(breaker);
        builder.append(userName);
        builder.append(breaker);
        builder.append(userAccount);
        builder.append(breaker);
        builder.append(userPassword);
        builder.append(breaker);
        builder.append(selectedCurrency);
        builder.append(breaker);
        builder.append(recipientAcc);
        builder.append(breaker);
        builder.append(transAmount);
        builder.append(breaker);
        String messageString = builder.toString();
        message.setContent(messageString);

        return message;
    }

    public Message checkBalanceOfAccount(String userName, int userAccount, String userPassword) {
        Message message = createRequestMessage();
        // to send message
        StringBuilder builder = new StringBuilder();
        builder.append(6);
        builder.append(breaker);
        builder.append(userName);
        builder.append(breaker);
        builder.append(userAccount);
        builder.append(breaker);
        builder.append(userPassword);
        builder.append(breaker);
        String messageString = builder.toString();
        message.setContent(messageString);

        return message;
    }

}
