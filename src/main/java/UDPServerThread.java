import java.io.*;
import java.net.*;

public class UDPServerThread extends Thread {
    private final DatagramSocket UDPsocket; // FOR UDP
    private final DatagramPacket UDPreceivePacket;
    private final int clientNumber;

    public UDPServerThread(DatagramSocket UDPsocket, DatagramPacket packet, int clientNumber) {
        this.UDPsocket = UDPsocket;
        this.UDPreceivePacket = packet;
        this.clientNumber = clientNumber;
    }
    public void run() {
        try {
            receiveFile("ClientToServer_UDP"+clientNumber+".txt");
            sendFile("ClientToServer_UDP"+clientNumber+".txt");

        } catch (Exception e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void receiveFile(String fileName) throws Exception {
        UDPsocket.receive(UDPreceivePacket);
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        int size = UDPreceivePacket.getLength();
        byte[] buffer = UDPreceivePacket.getData();
        fileOutputStream.write(buffer, 0, size);
        fileOutputStream.close();
    }
    private void sendFile(String path) throws Exception{
        InetAddress address = UDPreceivePacket.getAddress();
        int port = UDPreceivePacket.getPort();
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = fileInputStream.readAllBytes();
        DatagramPacket UDPsendPacket = new DatagramPacket(buffer, buffer.length, address, port);
        UDPsocket.send(UDPsendPacket);
        fileInputStream.close();
    }
}
