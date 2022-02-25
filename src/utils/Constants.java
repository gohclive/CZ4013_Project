package utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Constants {
    
    public static InetAddress serverIP;
    static {
        try {
            serverIP = InetAddress.getByName(null);
         }
            catch (UnknownHostException ex) {

            }
        }
    public static final int serverPortNumber = 6666;
    public static final int messageLength = 65535;
    public static int clientPort = 122;
}
