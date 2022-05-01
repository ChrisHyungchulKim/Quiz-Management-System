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
                        //reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        message = reader.readLine();
                    }

                    if (message.equalsIgnoreCase("Get ArrayList")) {
                        sendArrayList(socket);
                    } else if (message.equalsIgnoreCase("Sending Arraylist")) {
                        receiveArrayList(socket);
                        System.out.printf(info.getCourses().get(0).getCourseName());
                    } else if (message.equalsIgnoreCase("Update Arraylist")) {
                        synchronized (concur) {
                            sendArrayList(socket);
                            System.out.println("test 1");
                            receiveArrayList(socket);
                            System.out.println("test 2");
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
            objectOutput.writeObject(info.getCourses());
            objectOutput.flush();
            for (Course s: info.getCourses()) {
                System.out.println(s.getCourseName());
            }
            System.out.println("");
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
            info.setCourses((ArrayList<Course>) object);
            for (Course s: (ArrayList<Course>) object) {
                System.out.println(s.getCourseName());
            }
            System.out.println("");

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




}

