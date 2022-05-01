import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;


public class ClientHandler implements Runnable {

    private static Class info;
    private static Object concur;
    private Socket clientSocket;
    private BufferedReader in = null;
    private static ArrayList<Submission> submissions;

    public ClientHandler(Socket client) {
        this.clientSocket = client;
    }

    @Override
    public void run() {
        info = new Class(CourseInfoHandler.readCourseInfo());
        //submissions = readSubmissions(info);
        concur = new Object();
        try {

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

                    if (message.equalsIgnoreCase("Get ArrayList")) {
                        sendArrayList(clientSocket);
                    } else if (message.equalsIgnoreCase("Sending Arraylist")) {
                        receiveArrayList(clientSocket);
                        System.out.printf(info.getCourses().get(0).getCourseName());
                    } else if (message.equalsIgnoreCase("Update Arraylist")) {
                        synchronized (concur) {
                            sendArrayList(clientSocket);
                            receiveArrayList(clientSocket);
                            System.out.printf(info.getCourses().get(0).getCourseName());
                        }
                    } else if (message.equalsIgnoreCase("Get Submissions Arraylist")) {
                        sendArrayList(clientSocket);
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

            System.out.println("File " + fileName + " received from client."); //file stored into the server
        } catch (Exception ex) {
            System.err.println("Client error. Connection closed.");
        }
    }
}


