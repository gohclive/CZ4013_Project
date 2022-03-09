package client;

import entity.Message;
import entity.Constants.CURRENCY;
import main.MainFunction;
import entity.Account;

public class Client {
	private int serverIPadd;
	private int portNumber;
	private int sendMessageId = 0;
	private String breaker = "|";
	
	public Client(int serverIPadd, int portNumber) {
		this.serverIPadd = serverIPadd;
		this.portNumber = portNumber;
	}
	
	public void sendMessageToServer() {

	}
	
	public Message replyFromServer() {
		
		Message replyMessage = Message.replyMessage();
		//return reply Message From Server
		return replyMessage;
	}

	public Message createRequestMessage(){
		return new Message(sendMessageId++, null);
	}

	public String createAccount(String userName, String userPassword, CURRENCY selectedCurrency){
        Message message = createRequestMessage();
        StringBuilder builder = new StringBuilder();
        builder.append(1);
        builder.append(breaker);
        builder.append(userName);
        builder.append(breaker);
        builder.append(userPassword);
        builder.append(breaker);
        builder.append(selectedCurrency);
        String messageString = builder.toString();
        System.out.println("\ncreating account: " + messageString + "\n");
        message.setContent(messageString);


        Message reply = replyFromServer();
        return reply.getContent(); //reply message from server;
    }

    public String closeAccount(String userName, int userAccount, String userPassword){
        Message message = createRequestMessage();
        StringBuilder builder = new StringBuilder();
        builder.append(2);
        builder.append(breaker);
        builder.append(userName);
        builder.append(breaker);
        builder.append(userAccount);
        builder.append(breaker);
        builder.append(userPassword);
        String messageString = builder.toString();
        message.setContent(messageString);
        
        
        Message reply = replyFromServer();
        return reply.getContent(); //reply message from server;
    }
    
    public String depositMoney(String userName, int userAccount, String userPassword, CURRENCY selectedCurrency, double depositSum) {
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
        String messageString = builder.toString();
        message.setContent(messageString);
        
        Message reply = replyFromServer();
        return reply.getContent(); //reply message from server;
    }
    
    public String withdrawMoney(String userName, int userAccount, String userPassword, CURRENCY selectedCurrency, double withdrawSum) {
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
        String messageString = builder.toString();
        message.setContent(messageString);
        
        //after receiving reply from server 
        Message reply = replyFromServer();
        return reply.getContent(); //reply message from server;
    }

    public void monitorUpdates(int interval){
        Message message = createRequestMessage();
        //to send message 
        StringBuilder builder = new StringBuilder();
        builder.append(7);
        builder.append(breaker);
        builder.append(interval);
        String messageString = builder.toString();
        message.setContent(messageString);

    }

    public String transferMoney(String userName, int userAccount, String userPassword, CURRENCY selectedCurrency, int recipientAcc, double transAmount){
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
        String messageString = builder.toString();
        message.setContent(messageString);
        
        Message reply = replyFromServer();
        return reply.getContent(); //reply message from server;
    }
    
    public String checkBalanceOfAccount(String userName, int userAccount, String userPassword){
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
        String messageString = builder.toString();
        message.setContent(messageString);
        
        Message reply = replyFromServer();
        return reply.getContent(); //reply message from server;
    }
}
