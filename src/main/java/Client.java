import java.net.*;
import java.io.*;
public class Client {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void run(String filename) {
        try(Socket socket = new Socket("localhost",5000)) { //connect to socket
            dataInputStream = new DataInputStream(socket.getInputStream()); //data input stream
            dataOutputStream = new DataOutputStream(socket.getOutputStream()); //data output stream

            sendFile("C:\\Data\\Java\\6-Distributed Systems\\lab1distTCP.txt"); // send file from client to server
            receiveFile(filename); // receive file from server
            dataInputStream.close();
            dataOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void sendFile(String path) throws Exception{
        int bytes = 0;
        File file = new File(path); //get file
        FileInputStream fileInputStream = new FileInputStream(file); //open input stream
        // send file size
        dataOutputStream.writeLong(file.length()); //write file length into output stream
        // break file into chunks
        byte[] buffer = new byte[4*1024];
        while ((bytes=fileInputStream.read(buffer))!=-1){ //while not empty, write into output stream
            dataOutputStream.write(buffer,0,bytes); // write buffer into output stream
            dataOutputStream.flush();
        }
        fileInputStream.close();
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
}