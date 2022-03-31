package entity;

/**
 * @author Clive
 *
 */
public class Message {

	private static int messageID;
	private static String messageContent;
	
	/*
	 * Constructor
	 */
	public Message(int messageID, String messageContent) {
		Message.messageID = messageID;
		Message.messageContent = messageContent;
	}

	/**
	 * @return messageId
	 */
	public int getMessageID() {
		return Message.messageID;
	}
	
	
	/**
	 * @return messageContent
	 */
	public String getContent(){
		return Message.messageContent;
	}

	/**
	 * @param messageID
	 */
	public void setMessageID(int messageID) {
		Message.messageID = messageID;
	}
	
	/**
	 * @param content the message content to set
	 */
	public void setContent(String content) {
		Message.messageContent = content;
	}

	/**
	 * @return String convert message object to string to send to server
	 */
	public String MessageToString(){
		if (messageContent.contains(":") == true){
			return messageContent;
		} else {
		StringBuilder builder = new StringBuilder();
        builder.append(messageID);
		builder.append(":");
		builder.append(messageContent);
		String resMessage = builder.toString();
		setContent(resMessage);
		return resMessage;}
		
	}


	public static Message replyMessage() {
//		System.out.println("message from server" + messageID + messageContent);
		 return new Message(messageID, messageContent);
	}

	/**
	 * print out full message id & data
	 */
	public void printData(){
		System.out.println("Request Message: " + messageID +":"+ messageContent);
	}
	/**
	 * print out message Id
	 */
	public void printIDOnly(){
		System.out.println("MessageID: " + messageID);
	}
	/**
	 * print out message data
	 */
	public void printDataOnly(){
		System.out.println("DataOnly: "+ messageContent);
	}
}
