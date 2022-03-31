package entity;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class Constants {

	public enum CURRENCY {
		SGD, USD, EURO
	}
	public static InetAddress serverIP;
	static {
		try {
			serverIP = InetAddress.getByName(null);
		} catch (UnknownHostException ex) {

		}
	}
	// too much buffer size = slow and high chance cache miss
	public static final int serverRefuseRate = 5;
	public static final int serverFailPerformRate = 5;
	public static boolean AT_LEAST_ONCE = false;
	public static boolean AT_MOST_ONCE = true;
	// public static final int messageID = 123;
	public static final int serverPortNumber = 6666;
	public static final int messageLength = 128;
	public static final int requestTimeout = 200; // millisecond
	public static final int retry = 3;
	public static final String delimiter = "|";
	public static final Charset commonCharset = StandardCharsets.UTF_8;

	//currency conversion value
	public static final double USDSGD = 1.35;
	public static final double SGDUSD = 0.74;
	public static final double USDEURO = 0.90;
	public static final double EUROUSD = 1.12;
	public static final double EUROSGD = 1.51;
	public static final double SGDEURO = 0.66;

	
	//error messages
	public static final String ACCOUNTNOTFOUND = "|ERROR|"+ "Account not found!" + "|";
	public static final String INCORRECTNAME = "|ERROR|"+ "Account name incorrect!" + "|";
	public static final String INCORRECTPASSWORD = "|ERROR|"+ "Account password incorrect!" + "|";
	public static final String INSUFFICIENTBAL ="|ERROR|"+ "Account has insufficent balance!" + "|";
	public static final String SUCCESSMESSAGE = "Operation is successful!";
	public static final String ERRORMESSAGE = "Operation is unsuccessful";
	

}
