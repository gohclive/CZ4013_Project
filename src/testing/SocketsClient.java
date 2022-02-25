package testing;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class SocketsClient
{
    public static void main(String args[]) throws IOException
    {
        Scanner sc = new Scanner(System.in); // get user input
        // constant  = destination IP address, port number
        // Step 1:Create the socket object for
        // carrying the data.
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost(); // get server ip
        byte buf[] = null;
        System.out.println("Entering the Client while loop");
        // loop while user not enters "bye"
        while (true)
        {
            String inp = sc.nextLine();
  
            // convert the String input into the byte array.
            buf = inp.getBytes();
  
            // Step 2 : Create the datagramPacket for sending
            // the data.
            DatagramPacket DpSend =
                  new DatagramPacket(buf, buf.length, ip, 6854);
  
            // Step 3 : invoke the send call to actually send
            // the data.
            ds.send(DpSend);
  
            // break the loop if user enters "bye"
            if (inp.equals("bye"))
                break;

    }
}
}