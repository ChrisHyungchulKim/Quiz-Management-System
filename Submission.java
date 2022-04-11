import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Project 4 - Submission.java Class
 *
 * @author Corey Tuinstra
 * @version March 29, 2022
 */
public class Submission {
    private User student;
    private ArrayList<String> responses;
    private String time;
    private boolean graded;
    private Quiz quizBeingTaken;
    Course courseOfQuiz;
    ArrayList<String> grades;

    public Submission(User student, Quiz quizBeingTaken, Course courseOfQuiz,
                      ArrayList<String> responses, String time, boolean graded, ArrayList<String> grades) {
        this.student = student;
        this.responses = responses;
        this.time = time;
        this.graded = graded;
        this.quizBeingTaken = quizBeingTaken;
        this.courseOfQuiz = courseOfQuiz;
        this.grades = grades;
    }


    public ArrayList<String> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<String> grades) {
        this.grades = grades;
    }

    public void addCertainGrade(String grade) {
        grades.add(grade);
    }

    public Course getCourseOfQuiz() {
        return courseOfQuiz;
    }

    public void setCourseOfQuiz(Course courseOfQuiz) {
        this.courseOfQuiz = courseOfQuiz;
    }

    public Quiz getQuizBeingTaken() {
        return quizBeingTaken;
    }

    public void setQuizBeingTaken(Quiz quizBeingTaken) {
        this.quizBeingTaken = quizBeingTaken;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public ArrayList<String> getResponses() {
        return responses;
    }

    public void setResponses(ArrayList<String> responses) {
        this.responses = responses;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isGraded() {
        return graded;
    }

    public void setGraded(boolean graded) {
        this.graded = graded;
    }

    public void writeSubmission(Submission submission, boolean edit) {

        try {
            FileWriter fileWriter = new FileWriter("SubmissionDetails.txt", edit);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String readableResponses = "";
            String readableGrades = "";
            for (int i = 0; i < getResponses().size(); i++) {
                readableResponses += submission.getResponses().get(i) + ",";
            }
            for (int i = 0; i < getGrades().size(); i++) {
                readableGrades += submission.getGrades().get(i) + ",";
            }
            String submissionDetail = String.format("Student: %s\nResponses: %s\nCourse: %s\nQuiz: %s\n"+
                                                    "Time: %s\nGraded: %s\nGrades: %s\n",
                                                    submission.getStudent().getUsername(), readableResponses,
                                                    submission.getCourseOfQuiz().getCourseName(),
                                                    submission.getQuizBeingTaken().getName(),
                                                    submission.getTime(), submission.isGraded(), readableGrades);
            bufferedWriter.write(submissionDetail);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
