package entity;

public class Message {

	private static int messageID;
	private static Object[] messageContent;
	public Message(int messageID, Object[] messageContent) {
		this.messageID = messageID;
		this.messageContent = messageContent;
	}

	public int getMessageID() {
		return this.messageID;
	}
	public Object[] getContent(){
		return this.messageContent;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public void setContent(Object[] content) {
		this.messageContent = content;
	}

	public static Message replyMessage() {
		System.out.println("message from server");
		 return new Message(messageID, messageContent);
	}
}
