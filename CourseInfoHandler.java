import java.io.*;
import java.util.ArrayList;

public class CourseInfoHandler {

    public static void writeCourseInfo(Class totalClass) {

        String courseDetails;
        int index = 0;

        try {
            File courseInfoFile = new File("CourseDetails.txt");
            FileWriter fileWriter = new FileWriter(courseInfoFile);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (int in = 0; in < totalClass.getCourses().size(); in++) {
                courseDetails = "";
                Course c = totalClass.getCourses().get(in);
                if (index == 0) {
                    courseDetails += "Course: " + c.getCourseName();
                    index++;
                } else {
                    courseDetails += "\nCourse: " + c.getCourseName();
                }
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

                    printWriter.print(courseDetails);
                    printWriter.flush();
                }



            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public static ArrayList<Course> readCourseInfo() {
        ArrayList<Course> courses = new ArrayList<>();
        String line;
        String courseName;
        //String owner;
        String quizName;
        String prompt;
        int weight;
        int answer;
        ArrayList<Question> questionList;
        ArrayList<String> answerChoices;
        ArrayList<Quiz> quizList;


        try {
            File courseInfoFile = new File("CourseDetails.txt");
            FileReader fileReader = new FileReader(courseInfoFile);
            BufferedReader bfr = new BufferedReader(fileReader);

            weight = 0;
            answer = 0;
            line = bfr.readLine();

            while (line!= null) {

                if(line.contains("Course: ")) {
                    quizList = new ArrayList<Quiz>();
                    courseName = line.substring(line.indexOf(' ') + 1);

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
                                if (line == null) {
                                    break;
                                }
                            }
                            questionList.add(new Question(prompt, answerChoices, answer, weight));
                            if (line == null) {
                                break;
                            }
                        }
                        quizList.add(new Quiz(quizName, questionList));
                        if (line == null) {
                            break;
                        }
                    }
                    courses.add(new Course(courseName, quizList));
                    if (line == null) {
                        break;
                    }
                }
                if (line.isEmpty()) {
                    line = bfr.readLine();
                }

            }

            bfr.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return courses;
    }

}
