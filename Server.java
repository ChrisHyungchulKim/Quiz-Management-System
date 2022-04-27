import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static Class info;
    private static Object concur;

    public static void main(String[] args) {

        info = new Class(CourseInfoHandler.readCourseInfo());
        concur = new Object();

        try {

            ServerSocket serverSocket = new ServerSocket(3005);
            System.out.println("Server Socket Created");

            while (true) {

                Socket socket = serverSocket.accept();

                ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
                objectOutput.writeObject(getInfoCourseArray());

                System.out.printf("connected");
                while (true) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    String message = null;
                    while (message == null) {
                        message = reader.readLine();
                    }

                    if (message.equals("Get ArrayList")) {
                        sendArrayList(socket);
                    } else if (message.equals("Sending Arraylist")) {
                        receiveArrayList(socket);
                        System.out.printf(info.getCourses().get(0).getCourseName());
                    } else if (message.equals("Update Arraylist")) {
                        synchronized (concur) {
                            sendArrayList(socket);
                            System.out.println("test 1");
                            receiveArrayList(socket);
                            System.out.println("test 2");
                            System.out.printf(info.getCourses().get(0).getCourseName());
                        }
                    }

                }

            }

        } catch (IOException ioe) {
            System.out.println("Error");
            ioe.printStackTrace();
        }

    }

    public static void updateCourse(ArrayList<Course> courses) {
        synchronized (concur) {
            info.setCourses(courses);
        }
    }

    /**
     *
     *
     * @param socket - The socket the that the server communication is started on
     */
    public static void sendArrayList(Socket socket) {
        try {
            ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
            objectOutput.writeObject(getInfoCourseArray());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     *
     * @param socket - the socket the that the server communication is started on
     */
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

    /**Getter for the Courses Arraylist
     *
     * @return - Courses Arraylist
     */
    public static ArrayList<Course> getInfoCourseArray() {
        return info.getCourses();
    }

    /**Sets the info objects courses arraylist field to the new one provided by the parameter
     *
     * @param courses - new version of the courses Arraylist
     */
    public static void setInfoCourseArray(ArrayList<Course> courses) {
        info.setCourses(courses);
        CourseInfoHandler.writeCourseInfo(info);
    }


    /*
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        // Constructor
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            PrintWriter out = null;
            BufferedReader in = null;
            try {

                // get the outputstream of client
                out = new PrintWriter(
                        clientSocket.getOutputStream(), true);

                // get the inputstream of client
                in = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()));

                String line;
                while ((line = in.readLine()) != null) {

                    // writing the received message from
                    // client
                    System.out.printf(
                            " Sent from the client: %s\n",
                            line);
                    out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    */

    /*
    while (true) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    String message = null;

                    while (message == null) {
                        message = reader.readLine();
                    }

                    if (message.equals("Increment x")) {
                        x++;
                    } else if (message.equals("Decrement x")) {
                        x--;
                    } else if (message.equals("Get x")) {
                        writer.write(x + "\nend");
                        writer.println();
                        writer.flush();

                    } else if (message.contains("echo ")) {
                        writer.write(message.substring(message.indexOf('o') +2) + "\nend");
                        writer.println();
                        writer.flush();
                    } else {
                        writer.write("Error: Unexpected input, Try Again \nend");
                        writer.println();
                        writer.flush();
                    }

                }
     */

}
