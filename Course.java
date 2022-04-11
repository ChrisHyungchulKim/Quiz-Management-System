import java.util.ArrayList;
/**
 * Project 4
 *
 * @author Corey Tuinstra
 * @version April 11, 2022
 */
public class Course {
    private String courseName;
    private User owner;
    private ArrayList<Quiz> quizzes;

    public Course(String courseName, ArrayList<Quiz> quizzes) {
        this.courseName = courseName;
        this.quizzes = quizzes;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public  ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


    public void removeQuiz(Course course, Quiz quiz) {
        course.getQuizzes().remove(quiz);
    }
}
