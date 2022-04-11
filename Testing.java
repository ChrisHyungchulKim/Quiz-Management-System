import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class Testing {

    //testing for right modifiers
    //if necessary
//     @Test(timeout = 1000)
//     public void simpleTest() {
//         Class<?> quizClazz = Quiz.class;
//         Class<?> questionClazz = Question.class;
//         Class<?> quizMenuClazz = QuizMenu.class;
//         Class<?> courseClazz = Course.class;
//         Class<?> courseInfoHandlerClazz = CourseInfoHandler.class;
//         Class<?> userClazz = User.class;
//         Class<?> loginClazz = LoggingIn.class;


//         if (quizClazz.getModifiers() != Modifier.PUBLIC) {
//             Assert.fail("Was not the right modifiers!");
//             return;
//         }
//         if (questionClazz.getModifiers() != Modifier.PUBLIC) {
//             Assert.fail("Was not the right modifiers!");
//             return;
//         }
//         if (quizMenuClazz.getModifiers() != Modifier.PUBLIC) {
//             Assert.fail("Was not the right modifiers!");
//             return;
//         }
//         if (courseClazz.getModifiers() != Modifier.PUBLIC) {
//             Assert.fail("Was not the right modifiers!");
//             return;
//         }
//         if (courseInfoHandlerClazz.getModifiers() != Modifier.PUBLIC) {
//             Assert.fail("Was not the right modifiers!");
//             return;
//         }
//         if (userClazz.getModifiers() != Modifier.PUBLIC) {
//             Assert.fail("Was not the right modifiers!");
//             return;
//         }
//         if (loginClazz.getModifiers() != Modifier.PUBLIC) {
//             Assert.fail("Was not the right modifiers!");
//             return;
//         }


//     }

   public static void main(String[] args) {


       //for User class
       String username = "";
       String password = "";
       boolean teacher = true;
       User user = new User(username, password, teacher);

       user.setUsername("Teacher");
       user.getUsername();
       user.setPassword("1122");
       user.getPassword();
       user.setTeacher(true);
       user.isTeacher();

       if (!user.getUsername().equals("Teacher")) {
           System.out.println("Not the expected output");
       }
       if (!user.getPassword().equals("1122")) {
           System.out.println("Not the expected output");
       }
       if (!user.isTeacher() == true) {
           System.out.println("Not the expected output");
       }
   }
    
    //for Submission class

        User student = null;
        ArrayList<String> responses = new ArrayList<>();
        ArrayList<Quiz> quizzes = new ArrayList<>();
        ArrayList<Question> questions = new ArrayList<>();
        String time = "";
        boolean graded = true;
        Quiz quizBeingTaken = new Quiz("Quiz 1", questions);
        Course courseOfQuiz = null;
        ArrayList<String> grades = null;



        Submission submission = new Submission(student, quizBeingTaken, courseOfQuiz, responses, time, graded, grades);

        grades.set(0, "100");
        submission.setGrades(grades);
        submission.getGrades();
        System.out.println(submission.getGrades());

        submission.setCourseOfQuiz(new Course("Math", quizzes));
        submission.getCourseOfQuiz();
        System.out.println(submission.getCourseOfQuiz());

        submission.setQuizBeingTaken(new Quiz("Quiz 2", questions));
        submission.getQuizBeingTaken();
        System.out.println(quizBeingTaken);

        submission.setStudent(new User("Teacher", "1122", true));
        submission.getStudent();
        System.out.println(submission.getStudent());

        responses.set(0, "answers");
        submission.setResponses(responses);
        submission.getResponses();
        System.out.println(submission.getResponses());

        submission.setTime("10");
        submission.getTime();
        System.out.println(submission.getTime());

        submission.setGraded(false);
        submission.isGraded();
        System.out.println(submission.isGraded());



}















