package client;

import entity.Message;
import entity.Constants.CURRENCY;
import main.MainFunction;
import entity.Account;

public class Client {
	private int serverIPadd;
	private int portNumber;
	private int sendMessageId = 0;
//	private CURRENCY selectedCurrency;
//	private String userName = "";
//	private int userAccount = 0;
//	private String userPassword = 0;
//	private String userCurrencyType;
	
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

	public Object[] createAccount(String userName, String userPassword, CURRENCY selectedCurrency){
        Message message = createRequestMessage();
        message.setContent(new Object[]{userName, userPassword, selectedCurrency});

        Message reply = replyFromServer();
        return reply.getContent(); //reply message from server;
    }

    public Object[] closeAccount(String userName, int userAccount, String userPassword){
        Message message = createRequestMessage();
        
        Message reply = replyFromServer();
        return reply.getContent(); //reply message from server;
    }
    
    public Object[] depositMoney(String userName, int userAccount, String userPassword, CURRENCY selectedCurrency, double depositSum) {
        Message message = createRequestMessage();
        
        Message reply = replyFromServer();
        return reply.getContent(); //reply message from server;
    }
    
    public Object[] withdrawMoney(String userName, int userAccount, String userPassword, CURRENCY selectedCurrency, double withdrawSum) {
        Message message = createRequestMessage();
        
        Message reply = replyFromServer();
        return reply.getContent(); //reply message from server;
    }

    public void monitorUpdates(int interval){
        Message message = createRequestMessage();

    }

    public Object[] transferMoney(String userName, int userAccount, String userPassword, CURRENCY selectedCurrency, int recipentAcc, double transAmount){
        Message message = createRequestMessage();
        
        Message reply = replyFromServer();
        return reply.getContent(); //reply message from server;
    }
    
    public Object[] checkBalanceOfAccount(String userName, int userAccount, String userPassword){
        Message message = createRequestMessage();
        
        Message reply = replyFromServer();
        return reply.getContent(); //reply message from server;
    }
}
