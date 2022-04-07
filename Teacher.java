import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


//I need the User and the Course class to actually go in detail for these methods.
//For now, I'll just comment the sections that I need from the other classes.
//Comment any methods that you think could be useful.
public class Teacher implements User {
    Scanner scanner = new Scanner(System.in);

    private String name;
    private ArrayList<Question> questions;

    private ArrayList<String> courses = new ArrayList<String>();

    //calling Quiz constructor
    Quiz quiz = new Quiz(name, questions);
    

    //temporary constructor
    public Teacher(String name, ArrayList<String> courses) {
        this.name = name;
        this.courses = courses;
    }

    //Maybe I should just call the Course constructor to begin with, but for now I just made one.
    public boolean createCourse(String filename, ArrayList<Quiz> quizzes) {

        try {
            File file = new File("Course");

            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter printWriter = new PrintWriter(file);

        } catch (IOException e) {
            e.printStackTrace();

        }
        return true;
    }


    public boolean addQuiz(String filename, ArrayList<Question> questions) {
        System.out.println("At what index do you want to create a quiz?");
        int i = scanner.nextInt(); //the index at which the teacher desires to add the quiz within the course arraylist
        scanner.nextLine(); //e.g <Course 1, Course 2, ..., Course n>


        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            courses.add(i, quiz.getName()); //Adds the name of the Quiz in the Course arraylist.
            //** does not really add the actual quiz (file), will work on this


        } catch (FileNotFoundException f) {
            f.printStackTrace();
        }
        return true;
    }

    //edit quiz
    public boolean EditQuiz(String filename, ArrayList<Question> questions) {

        try {
            while (true) {
                System.out.println("Choose:\n1.Remove quiz\nEdit quiz");
                int choices = scanner.nextInt();
                scanner.nextLine();
                if (choices == 1) {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
                    bufferedReader.readLine();
                    //Will work on this part more


                    break;
                } else {
                    System.out.println("At what index do you want to edit a quiz?");
                    int i = scanner.nextInt(); //the index at which the teacher desires to edit the quiz within the course arraylist
                    scanner.nextLine();
                    //here as well
                    break;
                }



            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;


    }

    Student student = new Student (); //imaginary student constructor
    public String seeSubmission (String filename) {

        return student.getName().getSubmission();
    }


}


 @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public void setFirstName(String firstName) {

    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public void setLastName(String lastName) {

    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public int getUserID() {
        return 0;
    }

    @Override
    public void setUserID(int userID) {

    }
}
