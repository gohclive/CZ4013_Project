package client;

import entity.Message;
import entity.Constants.CURRENCY;
import main.MainFunction;
import java.net.InetAddress;
import entity.Account;
import entity.Constants;

public class Client {
	private InetAddress serverIPadd;
	private int portNumber;
	private int MessageId = 0;
	private String breaker = "|";
	
	public Client(InetAddress serverIPadd, int portNumber) {
		this.serverIPadd = serverIPadd;
		this.portNumber = portNumber;
	}
	
	
	public int getMessageId() {
		upMessageId();
		return MessageId-1;
	}


	public void upMessageId() {
		MessageId ++;
	}


	public Message createRequestMessage(){
		return new Message(getMessageId(), null);
	}


	public Message createAccount(String userName, String userPassword, CURRENCY selectedCurrency){
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
        System.out.println("\ncreating account: " + messageString + "\n");
        message.setContent(messageString);
//        message.setMessageID(123);
        return message;
    }

    public Message closeAccount(String userName, int userAccount, String userPassword){
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
    
    public Message depositMoney(String userName, int userAccount, String userPassword, CURRENCY selectedCurrency, double depositSum) {
        Message message = createRequestMessage();
        //to send message 
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
    
    public Message withdrawMoney(String userName, int userAccount, String userPassword, CURRENCY selectedCurrency, double withdrawSum) {
        Message message = createRequestMessage();
        //to send message 
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
        
        //after receiving reply from server 
        return message;
    }

    public Message monitorUpdates(double interval){
        Message message = createRequestMessage();
        //to send message 
        StringBuilder builder = new StringBuilder();
        builder.append(7);
        builder.append(breaker);
        builder.append(interval);
        builder.append(breaker);
        String messageString = builder.toString();
        message.setContent(messageString);
        return message;
    }

    public Message endMonitorUpdates(){
        Message message = createRequestMessage();
        //to send message 
        StringBuilder builder = new StringBuilder();
        builder.append(8);
        builder.append(breaker);
        String messageString = builder.toString();
        message.setContent(messageString);
        return message;
    }

    public Message transferMoney(String userName, int userAccount, String userPassword, CURRENCY selectedCurrency, int recipientAcc, double transAmount){
        Message message = createRequestMessage();
        
        //to send message 
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
    
    public Message checkBalanceOfAccount(String userName, int userAccount, String userPassword){
        Message message = createRequestMessage();
        //to send message 
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
