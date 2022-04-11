import java.io.*;
import java.util.*;
import java.sql.Timestamp;

/**
 * Project 4 -- Quiz.java class
 *
 * This is an object that holds questions with a quiz name. The Quiz class includes several methods 
 * that allow users to interact with quizzes in a variety of ways. The Constructors let teachers 
 * create quizzes manually or by entering a quiz file. The methods provide the functionality to edit quizzes, 
 * calculate the max score of quizzes, randomize the order of quiz questions, create submission strings for 
 * both teachers and students to view individual quiz results, and to access the submissions for a 
 * specific student. There is also an equals method to help teachers delete quizzes and a toString method 
 * to print quiz details.
 
 *
 * @author Sean Nowak, lab sec L24 
 *
 * @version April 11, 2022
 *
 */

public class Quiz {
    // Three Quiz fields, name of quiz, arraylist of questions, and arraylist of submissions
    private String name;
    private ArrayList<Question> questions;
    private ArrayList<String> submissions;

    // first constructor creates quiz object from existing name and arraylist
    public Quiz(String name, ArrayList<Question> questions) {
        this.name = name;
        this.questions = questions;
        this.submissions = new ArrayList<>();
    }

    // second quiz constructor allows teacher to create quiz object by entering fileName
    public Quiz(String fileName) {
        String name = null;
        ArrayList<Question> questions = new ArrayList<>();
        // opens file for reading
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String readName = br.readLine();
            name = readName.substring(readName.indexOf(' ') + 1);
            String line = br.readLine();
            // while loops parse through file looking for specific information
            while (line != null) {
                while (line != null && line.contains("Question: ")) {
                	// if file can't find necessary details question elements are set to 0 or null
                    int weight = 0;
                    int answer = 0;
                    ArrayList<String> responses = new ArrayList<>();
                    String prompt = line.substring(line.indexOf(' ') + 1);
                    line = br.readLine();
                    if (line != null && line.contains("Weight: ")) {
                        weight = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
                        line = br.readLine();
                        while(line != null && line.contains("Answer: ")) {
                            if (line.contains("Correct_Answer: ")) {
                                answer = responses.size() - 1;
                            }
                            responses.add(line.substring(line.indexOf(' ') + 1));
                            line = br.readLine();
                        }
                    }
                    // Once all necessary details are found questions are created and added to quiz arraylist
                    questions.add(new Question(prompt, responses, answer, weight));
                }
            }
        // catches possible exceptions
        } catch (IOException i) {
            i.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.name = name;
        this.questions = questions;
        this.submissions = new ArrayList<String>();
    }


    // allows user to access name field
    public String getName() {
        return name;
    }

    // allows user to access arraylist of questions field
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    // allows user to access entire arraylist of submissions field
    public ArrayList<String> getAllSubmissions() {
        return submissions;
    }

    // allows user to change quiz name
    public void setName(String quizName) {
        name = quizName;
    }

    // allows user to change quiz questions arrayList
    public void setQuestions(ArrayList<Question> quizQuestions) {
        questions = quizQuestions;
    }

    // allows user to change quiz submissions arrayList
    public void setSubmissions(ArrayList<String> quizSubmissions) {
        submissions = quizSubmissions;
    }

    // addQuestion method allows user to add question to quiz
    // can add question to specific index in arrayList or to end of list
    public void addQuestion(Question question, int index) {
        if (index >= 0) {
            questions.add(index, question);
        } else {
            questions.add(question);
        }
    }

    // remove method calls question equals method to remove specific question objects from list
    public void removeQuestion(Question question) {
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).equals(question)) {
                questions.remove(i);
            }
        }
    }

    // calculates highest possible score of quiz
    public int calcMaxScore() {
        int maxScore = 0;
        int inScore;
        for (int m = 0; m < questions.size(); m++) {
            inScore = questions.get(m).getWeight();
            maxScore += inScore;
        }
        return maxScore;
    }

    // creates submission trace string that includes all results of quiz taken
    public void addSubmission(String userName, ArrayList<Question> correctAnswers) {
    	// creates submission string and initializes necessary values
        String format = "Submission{User = %s";
        ArrayList<Question> results = new ArrayList<>();
        int pointsEarned = 0;
        int score = 0;
        String quesNum;
        String points;
        String prompt;
        String correct;
        // Checks if question objects are correct or not
        for (int q = 0; q < questions.size(); q++) {
            for (int c = 0; c < correctAnswers.size(); c++) {
            	// if question is correct adds points
                if (questions.get(q) == correctAnswers.get(c)) {
                    pointsEarned = questions.get(q).getWeight();
                } else {
                    pointsEarned = 0;
                }
            }
            // adds question-specific values to submission string
            quesNum = Integer.toString(q + 1);
            prompt = questions.get(q).getPrompt();
            correct = questions.get(q).getResponses().get(questions.get(q).getAnswer());
            points = Integer.toString(pointsEarned);
            format += ", Question " + quesNum + "{prompt = " + prompt;
            format += ", correct answer = " + correct + ", points earned = " + points + "}";
            score += pointsEarned;
        }
        format += ", Score = %d, Maximum Score = %d, Time = %s}";
        int maxScore = calcMaxScore();
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        // formats string and adds it to arraylist of submissions
        submissions.add(String.format(format, userName, score, maxScore, ts));
    }

    // filters through submissions and retrieves ones pertaining to specific student's username
    public ArrayList<String> getStudentSubmissions(String userName) {
        ArrayList<String> userSubs = new ArrayList<>();
        for (int i = 0; i < submissions.size(); i++) {
            String user = "User = " + userName;
            if (submissions.get(i).contains(user)) {
                userSubs.add(submissions.get(i));
            }
        }
        return userSubs;
    }

    // shuffles order of quiz questions
    public void randomize() {
        Collections.shuffle(questions);
    }

    // substitute for deleteQuiz method
    // equals method allows course class to simply delete quiz from arraylist of quiz objects
    public boolean equals(Object o) {
        boolean equal = false;
        if (o instanceof Quiz) {
            Quiz quiz = (Quiz) o;
            if (this.name.equalsIgnoreCase(quiz.name) && this.questions == quiz.questions &&
                    this.submissions == quiz.submissions) {
                equal = true;
            }
        }
        return equal;
    }

    // toString allows quiz to be printed all at once if users want to view it
    public String toString() {
        String format = "Quiz{name = %s,";
        for (int i = 0; i < questions.size(); i++) {
            format = format + "question " + Integer.toString(i + 1) + " = " + questions.get(i).toString() + ", ";
        }
        format = format.substring(0, format.length() - 2) + "}";
        return String.format(format, this.name);
    }

}
