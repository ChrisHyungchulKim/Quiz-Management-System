import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static ServerSocket serverSocket;
    private static Socket clientSocket = null;

    public static void main(String[] args) throws IOException {

        try {
            serverSocket = new ServerSocket(1234);
            System.out.println("Server started.");
        } catch (Exception e) {
            System.err.println("Port already in use.");
            System.exit(1);
        }


        try {
            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("Accepted connection : " + clientSocket); //checking the socket info

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("Accepted thread :" + t); //checking which thread is currently on run
                //Should see diff thread address each time you run Client.
                //ex) Thread[Thread-0,5,main]
                //Thread[Thread-1,5,main]
            }
        } catch (Exception e) {
            System.err.println("Error in connection attempt.");
        }

    }
}