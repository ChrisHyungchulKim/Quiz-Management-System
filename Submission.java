import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Submission {
    private User student;
    private ArrayList<String> responses;
    private String time;
    private boolean graded;
    private Quiz quizBeingTaken;
    Course courseOfQuiz;

    public Submission(User student, Quiz quizBeingTaken, Course courseOfQuiz,
                      ArrayList<String> responses, String time, boolean graded) {
        this.student = student;
        this.responses = responses;
        this.time = time;
        this.graded = graded;
        this.quizBeingTaken = quizBeingTaken;
        this.courseOfQuiz = courseOfQuiz;
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

    public void writeSubmission(Submission submission) {

        try {
            FileWriter fileWriter = new FileWriter("SubmissionDetails.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String readableResponses = "";
            for (int i = 0; i < getResponses().size(); i++) {
                readableResponses += submission.getResponses().get(i) + ",";
            }
            String submissionDetail = String.format("Student: %s\nResponses: %s\nTime: %s\nGraded: %s\nQuiz: %s",
                                                    submission.getStudent().getUsername(), readableResponses,
                                                    submission.getTime(), submission.isGraded(),
                                                    submission.getQuizBeingTaken());
            bufferedWriter.write(submissionDetail);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
