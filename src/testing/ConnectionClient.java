package testing;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import utils.Constants;

public class ConnectionClient
{
    public static void main(String args[]){
        try{
        
            Scanner sc = new Scanner(System.in);
            // DatagramSocket ds = new DatagramSocket();
            // byte buf[] = null;
            // System.out.println("Starting Client...");
            // while (true)
            // {
            //     String inp = sc.nextLine();
            //     buf = inp.getBytes();
            //     DatagramPacket DpSend = new DatagramPacket(buf, buf.length,Constants.serverIP, Constants.serverPortNumber);
            //     ds.send(DpSend);
            //     if (inp.equals("gg"))
            //         break;

            String message = sc.nextLine();
            DatagramSocket mySocket = new DatagramSocket(); 
            byte[] buf = message.getBytes( ); 
            DatagramPacket packet = new DatagramPacket(buf, buf.length, Constants.serverIP, Constants.serverPortNumber); 
            mySocket.send(packet); 

            // to receive a message 
            int MESSAGE_LEN = 65535; 
            byte[ ] recvBuffer = new byte[MESSAGE_LEN];
            DatagramPacket datagram = new DatagramPacket(recvBuffer, MESSAGE_LEN); 
            mySocket.receive(datagram); 
            String recvdString = new String(recvBuffer); 
            System.out.println("\n" + recvdString); 
            
            //mySocket.close( ); 
            } 
            catch(Exception e){ e.printStackTrace( ); } 
    }
}