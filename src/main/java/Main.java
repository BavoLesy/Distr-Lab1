public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
    void start(){
        //TCP
        /*
        Client client1 = new Client();
        Client client2 = new Client();
        client1.run("ServerToClient_TCP1.txt");
        client2.run("ServerToClient_TCP2.txt");
        */
        //UDP

        UDPClient client3 = new UDPClient();
        UDPClient client4 = new UDPClient();
        client3.run("ServerToClient_UDP3.txt");
        client4.run("ServerToClient_UPD4.txt");




    }

}
