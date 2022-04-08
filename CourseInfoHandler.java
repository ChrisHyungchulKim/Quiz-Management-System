import java.io.*;
import java.util.ArrayList;

public class CourseInfoHandler {
    private ArrayList<Course> courseList;


    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public void writeCourseInfo () {

        String courseDetails = "";
        int index = 0;

        try {
            File courseInfoFile = new File("CourseDetails");
            FileWriter fileWriter = new FileWriter(courseInfoFile);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Course c : this.courseList) {
                if (index == 0) {
                    courseDetails += "Course: " + c.getCourseName();
                } else {
                    courseDetails += "\nCourse: " + c.getCourseName();
                }
                    //TODO:PRINT OWNER INFO
                    //courseDetails += "\nOwner" + c.get();
                for (Quiz q : c.getQuizzes()) {
                    courseDetails += "\nQuiz Name: " + q.getName();
                    for (Question question : q.getQuestions()) {
                        courseDetails += "\nQuestion: " + question.getPrompt();
                        courseDetails += "\nWeight: " + question.getWeight();
                        for (int i = 0; i < question.getResponses().size(); i++){
                            if(i == question.getAnswer()) {
                                courseDetails += "\nCorrect Answer: " + question.getResponses().get(i);
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

    public void ReadCourseInfo () {

        String line;
        String courseName;
        String Owner;

        int courseNumber;

        try {
            File courseInfoFile = new File("CourseDetails");
            FileReader fileReader = new FileReader(courseInfoFile);
            BufferedReader bfr = new BufferedReader(fileReader);

            courseNumber = 0;
            line = bfr.readLine();

            while (line != null) {
                if(line.contains("Course: ")) {
                    //courseList.add(new Course(courseName, ,null));
                }

                courseNumber++;
            }



        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }

}
