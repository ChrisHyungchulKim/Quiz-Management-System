import java.io.*;
import java.util.ArrayList;

public class Quiz {
	private String name;
	private ArrayList<Question> questions;

	public Quiz(String name, ArrayList<Question> questions) {
		this.name = name;
		this.questions = questions;
	}

	public Quiz(String fileName) {
		//read quiz details from file
		//this.name =
		//this.questions =
	}

	public String getName() {
		return name;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addQuestion(Question question, int index) {
		// if index is null, add question at end of list
	}

	public void removeQuestion(Question question) {

	}

	public void writeFile(String fileName, ArrayList<Question> questions) {

	}

	public int calcMaxScore(ArrayList<Question> questions) {
		// Not sure if this method is necessary yet
		//Chris: CalculateScore method could be useful for grading I think. 
		
		return maxScore;
	}

	public int takeQuiz(boolean random) {
		// Will probably handle console I/O from this method directly
		// Will return score which can be written to specific course file?
		return score;
	}

	public String toString() {

	}
	// No deleteQuiz or addQuiz methods 
	// addQuiz should just use a constructor and write method
	//Chris: So here you mean the teacher class should have the add/delete Quiz methods and doing so by calling the Quiz constructor you wrote above right?
	
	// If course class includes arraylist of quizzes, deleteQuiz should just remove one from list 
}
