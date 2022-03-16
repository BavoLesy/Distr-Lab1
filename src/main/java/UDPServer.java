import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        byte[] buf = new byte[65535];
        try(DatagramSocket socket = new DatagramSocket(4445)){ //UDP
            System.out.println("listening to port:4445");
            int clientNumber = 1;
            while(true) {
                /* UDP */
                DatagramPacket packet = new DatagramPacket(buf, buf.length); //UDP
                new UDPServerThread(socket, packet, clientNumber).start();
                clientNumber++; //used for filenames
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}