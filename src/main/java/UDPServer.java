import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        byte[] buf = new byte[65535];
        try(DatagramSocket socket = new DatagramSocket(4445)){ //UDP
            // try(ServerSocket serverSocket = new ServerSocket(5000)){ // TCP
            System.out.println("listening to port:4445");
            int clientNumber = 1;
            while(true) {
                /* UDP */
                DatagramPacket packet = new DatagramPacket(buf, buf.length); //UDP
                new UDPServerThread(socket, packet, clientNumber).start();
                //Socket clientSocket = serverSocket.accept(); //TCP
                //System.out.println(clientSocket + " connected."); //TCP
                //new ServerThread(clientSocket, clientNumber).start(); //TCP
                clientNumber++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}