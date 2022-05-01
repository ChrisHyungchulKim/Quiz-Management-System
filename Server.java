import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Server {

    private static Class info;
    private static ArrayList<Submission> submissions;
    private static Object concur;

    public static void main(String[] args) {

        info = new Class(CourseInfoHandler.readCourseInfo());
        submissions = readSubmissions(info);
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

                    if (message.equalsIgnoreCase("Get ArrayList")) {
                        sendArrayList(socket);
                    } else if (message.equalsIgnoreCase("Sending Arraylist")) {
                        receiveArrayList(socket);
                        System.out.printf(info.getCourses().get(0).getCourseName());
                    } else if (message.equalsIgnoreCase("Update Arraylist")) {
                        synchronized (concur) {
                            sendArrayList(socket);
                            receiveArrayList(socket);
                            System.out.printf(info.getCourses().get(0).getCourseName());
                        }
                    } else if (message.equalsIgnoreCase("Get Submissions Arraylist")){
                        sendArrayList(socket);
                    }

                }

            }

        } catch (IOException ioe) {
            System.out.println("Error");
            ioe.printStackTrace();
        }

    }


    public void writeSubmissions(Socket socket, Submission submission) {

    }

    public void sendSubmissions(Socket socket) {
        try {
            ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
            objectOutput.writeObject(readSubmissions(info));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void updateCourse(ArrayList<Course> courses) {
        synchronized (concur) {
            info.setCourses(courses);
        }
    }

    /**
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
     * @param socket - the socket the that the server communication is started on
     */
    public static void receiveArrayList(Socket socket) {
        try {
            ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
            Object object = objectInput.readObject();
            setInfoCourseArray((ArrayList<Course>) object);
            info.setCourses((ArrayList<Course>) object);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for the Courses Arraylist
     *
     * @return - Courses Arraylist
     */
    public static ArrayList<Course> getInfoCourseArray() {
        return info.getCourses();
    }

    /**
     * Sets the info objects courses arraylist field to the new one provided by the parameter
     *
     * @param courses - new version of the courses Arraylist
     */
    public static void setInfoCourseArray(ArrayList<Course> courses) {
        info.setCourses(courses);
        CourseInfoHandler.writeCourseInfo(info);
    }

    public static ArrayList<Submission> readSubmissions(Class currentClass) {
        BufferedReader reader;
        try {
            FileReader filereader = new FileReader("SubmissionDetails.txt");
            reader = new BufferedReader(filereader);
            String line = reader.readLine();
            ArrayList<User> users = LoggingIn.readUserInfo();


            String studentName = "";
            String courseName = "";
            Course courseUsed = null;
            Quiz currentQuiz = null;
            User student = null;
            ArrayList<String> responses;
            ArrayList<String> grades = null;
            String time = "";
            String quizBeingTaken = "";
            boolean graded = false;
            double grade = 0;
            String currentQuestion = null;
            Question theQuestion = null;
            Submission newSubmission = null;
            ArrayList<Submission> submissions = new ArrayList<>();
            while (line != null) {
                if (line.contains("Student: ")) {
                    responses = new ArrayList<String>();
                    studentName = line.substring(line.indexOf(' ') + 1);
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getUsername().equals(studentName)) {
                            student = users.get(i);
                        }
                    }
                    line = reader.readLine();

                    if (line.contains("Responses: ")) {
                        String[] responsesArray = line.split(",");
                        responses = new ArrayList<String>(Arrays.asList(responsesArray));
                        line = reader.readLine();
                        if (line.contains("Course: ")) {
                            courseName = line.substring(line.indexOf(' ') + 1);
                            for (int i = 0; i < currentClass.getCourses().size(); i++) {
                                if (currentClass.getCourses().get(i).getCourseName().equals(courseName)) {
                                    courseUsed = currentClass.getCourses().get(i);
                                }
                            }
                            line = reader.readLine();

                            if (line.contains("Quiz: ")) {
                                quizBeingTaken = line.substring(line.indexOf(' ') + 1);
                                for (int i = 0; i < courseUsed.getQuizzes().size(); i++) {
                                    if (courseUsed.getQuizzes().get(i).getName().equals(quizBeingTaken)) {
                                        currentQuiz = courseUsed.getQuizzes().get(i);
                                    }
                                }
                                line = reader.readLine();

                                if (line.contains("Time: ")) {
                                    time = line.substring(line.indexOf(' ') + 1);
                                    line = reader.readLine();
                                    if (line.contains("Graded: ")) {
                                        graded = Boolean.parseBoolean(line.substring(line.indexOf(' ') + 1));
                                        line = reader.readLine();

                                        if (line.contains("Grades:")) {
                                            if (line.contains(",")) {
                                                String gradeSplit = line.substring(line.indexOf(' ') + 1);
                                                String[] gradesArray = gradeSplit.split(",");
                                                grades = new ArrayList<String>(Arrays.asList(responsesArray));
                                            } else {
                                                grades = new ArrayList<>();
                                            }

                                            line = reader.readLine();
                                        }

                                    }
                                }


                            }
                        }
                    }
                    newSubmission = new Submission(student, currentQuiz, courseUsed,
                            responses, time, graded, grades);
                    submissions.add(newSubmission);
                    //line = reader.readLine();
                }

            }
            reader.close();
            return submissions;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


