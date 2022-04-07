import java.util.ArrayList;

public class Course {
    private String courseName;
    private Teacher owner;
    private int quizzes;
    private ArrayList<Quiz> quizList = new ArrayList<Quiz>();

    public Course(String courseName, Teacher owner, int quizzes, ArrayList<Quiz> quizList) {
        this.courseName = courseName;
        this.owner = owner;
        this.quizzes = quizzes;
        this.quizList = quizList;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Teacher getOwner() {
        return owner;
    }

    public void setOwner(Teacher owner) {
        this.owner = owner;
    }

    public int getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(int quizzes) {
        this.quizzes = quizzes;
    }

    public ArrayList<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(ArrayList<Quiz> quizList) {
        this.quizList = quizList;
    }
}
