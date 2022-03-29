package entity;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import utils.Marshal;

public class Constants {

	public enum CURRENCY {
		SGD, USD, HKD, EURO, JPY, GBP
	}
	
	public static final int PACKETLOSTPROB = 0;

	public static InetAddress serverIP;
	static {
		try {
			serverIP = InetAddress.getByName(null);
		} catch (UnknownHostException ex) {

		}
	}
	// too much buffer size = slow and high chance cache miss
	public static final int serverPortNumber = 6666;
	public static final int messageLength = 512;
	public static final int requestTimeout = 15000; // millisecond
	public static final String delimiter = "|";
	public static int clientPort = 122;
	public static final Charset commonCharset = StandardCharsets.UTF_8;
	public static int messageIdentifer = 1000;	
	
	//error messages
	public static final String ACCOUNTNOTFOUND = "|ERROR|"+ "Account not found!" + "|";
	public static final String INCORRECTNAME = "|ERROR|"+ "Account name incorrect!" + "|";
	public static final String INCORRECTPASSWORD = "|ERROR|"+ "Account password incorrect!" + "|";
	public static final String INSUFFICIENTBAL ="|ERROR|"+ "Account has insufficent balance!" + "|";
	

	public static void main(String[] args) {
		System.out.println(serverIP);
		System.out.println(CURRENCY.valueOf("USD"));
		System.out.println(CURRENCY.valueOf("SGD"));
		String msg = "1|clivegoh|12345678|SGD";
		String[] decoded = Marshal.decodeForServer(msg);
		System.out.println(decoded[3]);
		System.out.println(CURRENCY.values()[0]);
	}
		

}
