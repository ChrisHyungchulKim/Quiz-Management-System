import javax.swing.*;
import java.io.*;
import java.net.Socket;


public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private BufferedReader in = null;

    public ClientHandler(Socket client) {
        this.clientSocket = client;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));
            String clientSelection;
            while ((clientSelection = in.readLine()) != null) {
                if (clientSelection.equals("1")) {
                    receiveFile();

                } else {
                    JOptionPane.showMessageDialog(null, "Not a valid input", "Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }

                in.close();

            } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void receiveFile() {
        try {
            int bytesRead;

            DataInputStream clientData = new DataInputStream(clientSocket.getInputStream());

            String fileName = clientData.readUTF();
            OutputStream output = new FileOutputStream(("received_from_client_" + fileName));
            long size = clientData.readLong();
            byte[] buffer = new byte[1024];
            while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                output.write(buffer, 0, bytesRead);
                size -= bytesRead;
            }

            output.close(); //closing OS
            clientData.close(); //closing DIS

            System.out.println("File "+fileName+" received from client."); //file stored into the server
        } catch (Exception ex) {
            System.err.println("Client error. Connection closed.");
        }
    }


}
