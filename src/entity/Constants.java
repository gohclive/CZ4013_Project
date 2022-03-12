package entity;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Constants {

	public enum CURRENCY {
		SGD, USD, HKD, EURO, JPY, GBP
	}

	public static InetAddress serverIP;
	static {
		try {
			serverIP = InetAddress.getByName(null);
		} catch (UnknownHostException ex) {

		}
	}
	// too much buffer size = slow and high chance cache miss
	public static final int serverPortNumber = 6666;
	public static final int messageLength = 65535;
	public static final int requestTimeout = 10; // millisecond
	public static final String delimiter = "|";
	public static int clientPort = 122;
	public static final Charset commonCharset = StandardCharsets.UTF_8;
	public static int messageIdentifer = 0;

	public static void main(String[] args) {
		System.out.println(serverIP);
	}

}
