package utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.regex.Pattern;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import entity.Constants;
import entity.Message;

public class Connections {

    /**
     * send message to server
     * 
     * @param messagetosend
     * @param clientSocket
     * @param serverIp
     */
    public static void sendMsgToServer(Message messagetosend, DatagramSocket clientSocket, InetAddress serverIp) {
        try {
            byte[] buf = null;
            
            String msg = messagetosend.MessageToString();
            buf = Marshal.stringToByte(msg,null);
            DatagramPacket dpmsg = new DatagramPacket(buf, buf.length, serverIp, Constants.serverPortNumber);
            clientSocket.send(dpmsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * send message to client
     * 
     * @param messagetosend
     * @param clientPacket
     */
    public static void sendMsgToClient(String messagetosend, DatagramPacket clientPacket) {
        try {
            InetAddress clientAddr = clientPacket.getAddress();
            int clientPort = clientPacket.getPort();
            DatagramSocket msg = new DatagramSocket();
            byte buf[] = null;
            buf = Marshal.stringToByte(messagetosend,null);
            DatagramPacket dpmsg = new DatagramPacket(buf, buf.length, clientAddr, clientPort);
            msg.send(dpmsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * return who is connected to the server
     * 
     * @param client
     * @return
     */
    public static String connectedHost(DatagramPacket client){
        String connectedHost = (client.getSocketAddress()).toString();
        return connectedHost;
    }

    /**
     * Receive message from server 
     * 
     * @param clientSocket
     * @return
     * @throws IOException
     */
    public static String clientToReceive(DatagramSocket clientSocket) throws IOException {
        String result = null;
        try {
            byte[] recvbuffer = new byte[Constants.messageLength];
            DatagramPacket dataRecv = new DatagramPacket(recvbuffer, Constants.messageLength);
            clientSocket.receive(dataRecv);
            String recvdString = Marshal.byteToString(recvbuffer);
            result = recvdString;
            //System.out.println(recvdString);
        } catch (SocketTimeoutException e) {
            System.out.println("No message Received from Server! Retransmitting data...");
            //e.printStackTrace();
        }
        return result;
    }
    
    
    /**
     * set an unused socket port to the client socket
     * @param clientsocket
     * @return
     */
    public static String clientAddrPort(DatagramSocket clientsocket) {
        int port = clientsocket.getLocalPort();
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName(null);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String addr = ip.toString();
        String[] ipOnly = addr.split(Pattern.quote("/"));
        String addrPort = ipOnly[1] + "|" + port;
        //System.out.println("addrport is : " + addrPort);
        return addrPort;
    }

    /**
     * create new a socket port for client
     * @param socket
     * @return
     */
    public static DatagramSocket clientSocketPort(DatagramSocket socket) {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return socket;
    }

    

}