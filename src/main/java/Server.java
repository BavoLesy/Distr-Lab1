
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5000)){ // TCP port
            System.out.println("listening to port:5000");
            int clientNumber = 1; // used to name files
            while(true) {
                Socket clientSocket = serverSocket.accept(); // connect
                System.out.println(clientSocket + " connected.");
                new ServerThread(clientSocket, clientNumber).start(); //TCP multithreading
                clientNumber++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}