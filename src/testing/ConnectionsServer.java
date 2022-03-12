package testing;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.SocketException;

import utils.Connections;
import entity.Constants;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionsServer {
    public static void main(String[] args) {
        try {
            // DatagramSocket ds = new DatagramSocket(Constants.serverPortNumber);
            // byte[] receive = new byte[65535];

            // DatagramPacket DpReceive = null;
            // System.out.println("Starting Server...");
            // while (true)
            // {
            // DpReceive = new DatagramPacket(receive, receive.length);
            // ds.receive(DpReceive);

            // System.out.println("Client:-" + data(receive));
            // }
            int max_length = 65535;
            DatagramSocket mysSocket = new DatagramSocket(Constants.serverPortNumber);
            byte[] recvBuffer = new byte[max_length];
            while (true) {
                DatagramPacket packet = new DatagramPacket(recvBuffer, max_length);
                mysSocket.receive(packet);
                String recvmsg = new String(recvBuffer);
                System.out.println("\n" + recvmsg);

                // reply back to sender
                InetAddress senderAddr = packet.getAddress();
                int senderport = packet.getPort();
                String messsagetosend = "Cow is sad";
                byte[] sendbuffer = messsagetosend.getBytes();
                DatagramPacket datagram = new DatagramPacket(sendbuffer, sendbuffer.length,
                        senderAddr, senderport);
                mysSocket.send(datagram);
                // mysSocket.close();
            }
            // Queue<DatagramPacket> packetReceive = new LinkedList<>();
            // Connections.serverListen(packetReceive);
            // DatagramPacket checking = packetReceive.peek();
            // System.out.println("This is the port that was retrived " +
            // checking.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static StringBuilder data(byte[] a) {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}