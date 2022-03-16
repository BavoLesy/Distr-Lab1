public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    void start(){
        Client client1 = new Client();
        Client client2 = new Client();
        client1.run("File_client1");
        client2.run("File_client2");
    }

}
