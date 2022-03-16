import java.io.*;
import java.net.*;


public class UDPClient {
    private DatagramPacket UDPsendPacket;
    private InetAddress address;
    DatagramSocket UDPsocket;

    public void run(String filename) {
        try {
            UDPsocket = new DatagramSocket(); // new UDP socket
            address = InetAddress.getByName("localhost"); // get address
            sendFile("C:\\Data\\Java\\6-Distributed Systems\\lab1distUDP.txt");
            receiveFile(filename);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void sendFile(String path) throws Exception{
        File file = new File(path); //open file
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = fileInputStream.readAllBytes(); // write file into buffer
        UDPsendPacket = new DatagramPacket(buffer, buffer.length, address,4445); //make packet from buffer data
        UDPsocket.send(UDPsendPacket); //send packet
        fileInputStream.close();
    }

    private void receiveFile(String fileName) throws Exception {
        byte[] buf = new byte[65535];
        DatagramPacket UDPreceivePacket = new DatagramPacket(buf, buf.length); // make packet
        UDPsocket.receive(UDPreceivePacket); // receive packet
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        int size = UDPreceivePacket.getLength();
        byte[] buffer = UDPreceivePacket.getData(); // read packet into buffer
        fileOutputStream.write(buffer, 0, size); //write buffer into file
        fileOutputStream.close();
    }
}