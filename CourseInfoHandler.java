import java.io.*;
import java.util.ArrayList;

public class CourseInfoHandler {

    private static ArrayList<Course> courseList;


    public static ArrayList<Course> getCourseList() {
        return courseList;
    }

    public static void addCourse(Course course) {
        courseList.add(course);
    }

    public static void setCourseList(ArrayList<Course> list) {
        courseList = list;
    }

    public static ArrayList<Course> getUsersCourses(User user) {
        ArrayList<Course> courses = new ArrayList<Course>();

        for(int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getOwner().equals(user)) {
                courses.add(courseList.get(i));
            }
        }

        return courses;
    }

    public static void deleteCourse(Course deleteCourse) {
        for(int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseName().equals(deleteCourse.getCourseName()) &&
                    courseList.get(i).getOwner().equals(deleteCourse.getOwner())) {
                courseList.remove(courseList.get(i));
            }
        }    }

    public static void editCourseList(Course course, Course editedCourse) {
        for(int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseName().equals(course.getCourseName()) &&
                    courseList.get(i).getOwner().equals(course.getOwner())) {
                courseList.remove(courseList.get(i));
                courseList.add(i, editedCourse);
            }
        }
    }

    public static boolean checkIfDuplicate(Course course) {
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseName().equals(course.getCourseName()) &&
                    courseList.get(i).getOwner().equals(course.getOwner())) {
                return true;
            }
        }
        return false;
    }

    public static void writeCourseInfo() {

        String courseDetails = "";
        int index = 0;

        try {
            File courseInfoFile = new File("CourseDetails.txt");
            FileWriter fileWriter = new FileWriter(courseInfoFile);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Course c : courseList) {
                if (index == 0) {
                    courseDetails += "Course: " + c.getCourseName();
                    index++;
                } else {
                    courseDetails += "\nCourse: " + c.getCourseName();
                }
                    courseDetails += "\nOwner_Username: " + c.getOwner().getUsername();
                for (Quiz q : c.getQuizzes()) {
                    courseDetails += "\nQuiz_Name: " + q.getName();
                    for (Question question : q.getQuestions()) {
                        courseDetails += "\nQuestion: " + question.getPrompt();
                        courseDetails += "\nWeight: " + question.getWeight();
                        for (int i = 0; i < question.getResponses().size(); i++){
                            if(i == question.getAnswer()) {
                                courseDetails += "\nCorrect_Answer: " + question.getResponses().get(i);
                            } else {
                                courseDetails += "\nAnswer: " + question.getResponses().get(i);
                            }
                        }
                    }
                }

                printWriter.println(courseDetails);
                printWriter.flush();
                printWriter.close();

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void readCourseInfo() {

        String line;
        String courseName;
        String owner;
        String quizName;
        String prompt;
        int weight;
        int answer;
        ArrayList<Question> questionList;
        ArrayList<String> answerChoices;
        ArrayList<Quiz> quizList;

        int courseNumber;

        try {
            File courseInfoFile = new File("CourseDetails.txt");
            FileReader fileReader = new FileReader(courseInfoFile);
            BufferedReader bfr = new BufferedReader(fileReader);

            weight = 0;
            answer = 0;
            courseNumber = 0;
            owner = "";
            line = bfr.readLine();

            while (line != null) {

                if(line.contains("Course: ")) {
                    quizList = new ArrayList<Quiz>();
                    courseName = line.substring(line.indexOf(' ') + 1);
                    line = bfr.readLine();
                    if(line.contains("Owner_Username: ")) {
                        owner = line.substring(line.indexOf(' ') + 1);
                    }
                    line = bfr.readLine();
                    while(line.contains("Quiz_Name: ")) {
                        questionList = new ArrayList<Question>();
                        quizName = line.substring(line.indexOf(' ') + 1);
                        line= bfr.readLine();
                        while(line.contains("Question: ")) {
                            answerChoices = new ArrayList<String>();
                            prompt = line.substring(line.indexOf(' ') + 1);
                            line = bfr.readLine();
                            if (line.contains("Weight: ")) {
                                weight = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
                            }
                            line = bfr.readLine();
                            while(line.contains("Answer: ")) {
                                if (line.contains("Correct_Answer: ")) {
                                    answerChoices.add(line.substring(line.indexOf(' ') + 1));
                                    answer = answerChoices.size() - 1;
                                } else {
                                    answerChoices.add(line.substring(line.indexOf(' ') + 1));
                                }
                                line = bfr.readLine();
                            }
                            questionList.add(new Question(prompt, answerChoices, answer, weight));
                        }
                        quizList.add(new Quiz(quizName, questionList));
                    }
                    courseList.add(new Course(courseName,LoggingIn.getUser(owner),quizList));
                }

                courseNumber++;
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
