import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;


public class ClientHandler implements Runnable {

    private static Class info;
    private static Objects concur;
    private Socket clientSocket;
    private BufferedReader in = null;

    public ClientHandler(Socket client) {
        this.clientSocket = client;
    }

    @Override
    public void run() {
        info = new Class(CourseInfoHandler.readCourseInfo());
        try {

//            in = new BufferedReader(new InputStreamReader(
//                    clientSocket.getInputStream()));
//            String clientSelection;
//            while ((clientSelection = in.readLine()) != null) {
//
//
//                if (clientSelection.equals("1")) {
//                    receiveFile();
//
////                switch (clientSelection) {
////                    case "1" -> receiveFile();
////                    case "2" -> {
////                        String receivingFileName;
////                        while ((receivingFileName = in.readLine()) != null) {
////                            sendFile(receivingFileName);
////                        }
////
////                    }
//                } else {
//                    JOptionPane.showMessageDialog(null, "Not a valid input", "Error",
//                            JOptionPane.PLAIN_MESSAGE);
//                }
//            }
//
////
//            in.close();
            while (true) {


                ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
                objectOutput.writeObject(getInfoCourseArray());


                while (true) {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                    String message = null;
                    while (message == null) {
                        message = reader.readLine();
                    }

                    if (message.equals("Get ArrayList")) {
                        sendArrayList(clientSocket);
                    } else if (message.equals("Sending Arraylist")) {
                        receiveArrayList(clientSocket);
                        System.out.printf(info.getCourses().get(0).getCourseName());
                    } else if (message.equals("Update Arraylist")) {
                        synchronized (concur) {
                            sendArrayList(clientSocket);
                            receiveArrayList(clientSocket);
                            System.out.printf(info.getCourses().get(0).getCourseName());
                        }
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void sendArrayList(Socket socket) {
        try {
            ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
            objectOutput.writeObject(getInfoCourseArray());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void receiveArrayList(Socket socket) {
        try {
            ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
            Object object = objectInput.readObject();
            setInfoCourseArray((ArrayList<Course>) object);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Course> getInfoCourseArray() {
        return info.getCourses();
    }

    public static void setInfoCourseArray(ArrayList<Course> courses) {
        info.setCourses(courses);
        CourseInfoHandler.writeCourseInfo(info);
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


//    public void sendFile(String fileName) {
//        try {
//            //handle file read
//            File myFile = new File(fileName);
//            byte[] byteArrayFile = new byte[(int) myFile.length()]; //making the file into a byte array to be read
//
//            FileInputStream fis = new FileInputStream(myFile);
//            BufferedInputStream bis = new BufferedInputStream(fis);
//
//
//            DataInputStream dis = new DataInputStream(bis);
//            dis.readFully(byteArrayFile, 0, byteArrayFile.length); //this readFully will ensure to read exactly the requested number of bytes.
//
//
//            //handle file send over socket
//            OutputStream os = clientSocket.getOutputStream();
//
//            //Sending "file name" and "file size" to the server
//            DataOutputStream dos = new DataOutputStream(os);
//            dos.writeUTF(myFile.getName()); //sending filename using writeUTF
//            dos.writeLong(byteArrayFile.length); //sending file size using writeLong
//            dos.write(byteArrayFile, 0, byteArrayFile.length);
//            dos.flush();
//            System.out.println("File "+fileName+" sent to client.");
//        } catch (Exception e) {
//            System.err.println("File does not exist!");
//        }
//    }
}
