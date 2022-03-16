
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static void main(String[] args) {
        byte[] buf = new byte[65535];
        try(ServerSocket serverSocket = new ServerSocket(5000)){ // TCP
            System.out.println("listening to port:5000");
            int clientNumber = 1;
            while(true) {
                Socket clientSocket = serverSocket.accept(); //TCP
                System.out.println(clientSocket + " connected."); //TCP
                new ServerThread(clientSocket, clientNumber).start(); //TCP
                clientNumber++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}