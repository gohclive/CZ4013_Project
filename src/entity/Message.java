package entity;

public class Message {

	private static int messageID;
	private static String messageContent;
	private static String resMessage;
	public Message(int messageID, String messageContent) {
		Message.messageID = messageID;
		Message.messageContent = messageContent;
	}

	public int getMessageID() {
		return Message.messageID;
	}
	public String getContent(){
		return Message.messageContent;
	}

	public void setMessageID(int messageID) {
		Message.messageID = messageID;
	}
	
	public void setContent(String content) {
		Message.messageContent = content;
	}

	public void sendMessageToServer(){
		StringBuilder builder = new StringBuilder();
        builder.append(messageID);
		builder.append("|");
		builder.append(messageContent);
		String resMessage = builder.toString();
		setContent(resMessage);
		System.out.println("resMessage" + resMessage);
	}

	public static Message replyMessage() {
//		System.out.println("message from server" + messageID + messageContent);
		 return new Message(messageID, messageContent);
	}
}
