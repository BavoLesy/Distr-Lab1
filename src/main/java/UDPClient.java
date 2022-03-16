import java.io.*;
import java.net.*;


public class UDPClient {
    private DatagramPacket UDPsendPacket;
    private InetAddress address;
    DatagramSocket UDPsocket;

    public void run(String filename) {
        try {
            UDPsocket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
            sendFile("C:\\Data\\Java\\6-Distributed Systems\\lab1distUDP.txt");
            receiveFile(filename);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void sendFile(String path) throws Exception{
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = fileInputStream.readAllBytes();
        UDPsendPacket = new DatagramPacket(buffer, buffer.length, address,4445);
        UDPsocket.send(UDPsendPacket);
        fileInputStream.close();
    }

    private void receiveFile(String fileName) throws Exception {
        byte[] buf = new byte[65535];
        DatagramPacket UDPreceivePacket = new DatagramPacket(buf, buf.length);
        UDPsocket.receive(UDPreceivePacket);
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        int size = UDPreceivePacket.getLength();
        byte[] buffer = UDPreceivePacket.getData();
        fileOutputStream.write(buffer, 0, size);
        fileOutputStream.close();
    }
}