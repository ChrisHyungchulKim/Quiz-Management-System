import java.util.ArrayList;

public class Course {
    private String courseName;

    private ArrayList<Quiz> quizzes = new ArrayList<Quiz>();

    public Course(String courseName, User owner, ArrayList<Quiz> quizzes) {
        this.courseName = courseName;
        this.quizzes = quizzes;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }

    public void removeQuiz(String name) {
        quizzes.removeIf(quiz -> quiz.getName().equals(name));
    }
}
