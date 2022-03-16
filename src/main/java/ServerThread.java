import java.io.*;
import java.net.*;

/**
 * This thread is responsible to handle client connection.
 *
 * @author www.codejava.net
 */
public class ServerThread extends Thread {
    private Socket clientSocket;
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public ServerThread(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            receiveFile("NewFile1.pdf");
            //receiveFile("NewFile2.pdf");
            sendFile("NewFile1.pdf");
            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();


        } catch (Exception e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }

    }
    private static void receiveFile(String fileName) throws Exception{
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long size = dataInputStream.readLong();     // read file size
        byte[] buffer = new byte[4*1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer,0,bytes);
            size -= bytes;      // read upto file size
        }
        fileOutputStream.close();
    }
    private static void sendFile(String path) throws Exception{
        int bytes = 0;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        // send file size
        dataOutputStream.writeLong(file.length());
        // break file into chunks
        byte[] buffer = new byte[4*1024];
        while ((bytes=fileInputStream.read(buffer))!=-1){
            dataOutputStream.write(buffer,0,bytes);
            dataOutputStream.flush();
        }
        fileInputStream.close();
    }
}