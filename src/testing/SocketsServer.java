package testing;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import entity.Constants;
import utils.Connections;
import utils.Marshal;

public class SocketsServer {
    public static void main(String[] args) throws IOException {

        DatagramSocket ds = new DatagramSocket(Constants.serverPortNumber); // make socket and bind it to port
        byte[] receive = new byte[Constants.messageLength]; // buffer to receive msg

        DatagramPacket DpReceive = null; // make packet to receive datagrampacket
        System.out.println("Entering the Server while loop...");
        while (true) {
            DpReceive = new DatagramPacket(receive, receive.length); // packe to read buffer and length of buffer
            ds.receive(DpReceive); // to receive the msg

            System.out.println("Client:-" + Marshal.byteToString(receive)); // data that was received

            // Exit the server if the client sends "bye"
            if (Marshal.byteToString(receive).equals("bye")) {
                System.out.println("Client sent bye.....EXITING");
                break;
            }

            Connections.sendMsgToClient("message received", DpReceive); // reply msg to client

            // Clear the buffer after every message.
            receive = new byte[65535];
        }
    }
}