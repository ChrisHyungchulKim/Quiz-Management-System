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
                switch (clientSelection) {
                    case "1" -> receiveFile();
                    case "2" -> {
                        String receivingFileName;
                        while ((receivingFileName = in.readLine()) != null) {
                            sendFile(receivingFileName);
                        }
                    }
                    default -> JOptionPane.showMessageDialog(null, "Not a valid input", "Error",
                            JOptionPane.PLAIN_MESSAGE);
                }
//
                in.close();
                break;
            }

        } catch (IOException ex) {
            System.out.println("Critical error!");
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
        } catch (IOException ex) {
            System.err.println("Client error. Connection closed.");
        }
    }

    public void sendFile(String fileName) {
        try {
            //handle file read
            File myFile = new File(fileName);
            byte[] byteArrayFile = new byte[(int) myFile.length()]; //making the file into a byte array to be read

            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);


            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(byteArrayFile, 0, byteArrayFile.length); //this readFully will ensure to read exactly the requested number of bytes.


            //handle file send over socket
            OutputStream os = clientSocket.getOutputStream();

            //Sending "file name" and "file size" to the server
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(myFile.getName()); //sending filename using writeUTF
            dos.writeLong(byteArrayFile.length); //sending file size using writeLong
            dos.write(byteArrayFile, 0, byteArrayFile.length);
            dos.flush();
            System.out.println("File "+fileName+" sent to client.");
        } catch (Exception e) {
            System.err.println("File does not exist!");
        }
    }
}