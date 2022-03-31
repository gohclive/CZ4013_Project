package client;

import entity.Message;
import entity.Constants.CURRENCY;
import java.util.Random;
import entity.Constants;

public class Client {
    private String breaker = "|";

    public Client() {
    }

    // generate new random message id to be sent to the server
    public int generateMessageId() {
            Random rnd = new Random();
            int number = rnd.nextInt(99999);
            return number;
    }

    // this will set the new message id for the Message class
    public Message createRequestMessage() {
        return new Message(generateMessageId(), null);
    }

    // create new bank account
    // this function will take in all the user inputs and append it into a string to
    // be sent to the server for the server to process and execute the operation for
    // this service
    public Message createAccount(String userName, String userPassword, CURRENCY selectedCurrency) {
        // call function to generate unique message id for the message
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
        // return the message to be sent to the server
        return message;
    }

    // close exisiting bank account in the database
    // this function will take in all the user inputs and append it into a string to
    // be sent to the server for the server to process and execute the operation for
    // this service
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
        // set the content message into the message
        message.setContent(messageString);
        // return the message to be sent to the server
        return message;
    }

    // deposit money into an existing bank account
    // this function will take in all the user inputs requested ( username, account
    // number, password and currency of the account)
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
        // this function will generate and return the message to be sent to server
        return message;
    }

    // withdraw money from an existing bank account
    public Message withdrawMoney(String userName, int userAccount, String userPassword, CURRENCY selectedCurrency,
            double withdrawSum) {
        // call function to generate unique message id for the message
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
        return message;
    }

    // will send the period of time sent by user to monitor updates within this
    // period
    // this function will generate the message to be sent to the server
    public Message monitorUpdates(double interval) {
        // call function to generate unique message id for the message
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

    // when the monitor updates ends (as it reaches the end of the period) it will
    // send a message to the server
    // so that server will know when to end the service of updating the client
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

    // this function will send a message to the server when user wants to transfer
    // money to another client
    public Message transferMoney(String userName, int userAccount, String userPassword, CURRENCY selectedCurrency,
            int recipientAcc, double transAmount) {
        // will request for unique message id
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
        // will return the message to send to the server including message id
        return message;
    }

    // check the balance of the account
    // user will just need to input the username, user account and user password
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
