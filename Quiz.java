import java.io.*;
import java.util.ArrayList; 
import java.util.Scanner;

public class Quiz {
	// Two Quiz fields, name of quiz and arraylist of questions
	private String name;
	private ArrayList<Question> questions;

	// first constructor creates quiz object from existing name and arraylist
	public Quiz(String name, ArrayList<Question> questions) {
		this.name = name;
		this.questions = questions;
	}

	// second constructor reads in quiz details from file to create object
	public Quiz(String fileName) {
		String readName = null;
		ArrayList<Question> quest = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			readName = br.readLine();
			String line;
			ArrayList<String> contents = new ArrayList<>();
			while ((line = br.readLine()) != null) {
				contents.add(line);
			}
			for (int i = 0; i < contents.size(); i++) {
				int count = 0;
				String prompt = null;
				int numResponses = 0;
				int answer = -1;
				int weight = -1;
				prompt = contents.get(count);
				count++;
				numResponses = Integer.parseInt(contents.get(count));
				count++;
				ArrayList<String> responses = new ArrayList<>();
				for (int x = 0; x < numResponses; x++) {
					responses.add(contents.get(count));
					count++;
				}
				answer = Integer.parseInt(contents.get(count));
				count++;
				weight = Integer.parseInt(contents.get(count));
				if (prompt != null && numResponses != 0 && answer != -1 && weight != -1) {
					Question question = new Question(prompt, responses, answer, weight);
					quest.add(question);
					for (int y = 0; y < (count + 1); y++) {
						contents.remove(y);
					}
				}
			}	
		} catch (IOException i) {
			i.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.name = readName;
		this.questions = quest;
	}

	// third constructor will create new object by asking teacher for quiz questions etc.
	public Quiz(Scanner scan) {
		//this.name =
		//this.questions = 
	}

	// accessors and mutators
	public String getName() {
		return name;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	// no editQuiz method, can just add and remove questions
	// questions can be added at index or at end of list (if index integer < 0)
	public void addQuestion(Question question, int index) {
		if (index >= 0) {
			this.questions.add(index, question);
		} else {
			this.questions.add(question);
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

	// method writes Quiz object to file in same format file is read in
	public void writeFile(String fileName) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
			pw.write(this.name);
			for (int q = 0; q < questions.size(); q++) {
				pw.write(questions.get(q).getPrompt());
				int numResponses = questions.get(q).getResponses().size();
				pw.write(Integer.toString(numResponses));
				for (int w = 0; w < numResponses; w++) {
					pw.write(questions.get(q).getResponses().get(w));
				}
				int answer = questions.get(q).getAnswer();
				pw.write(Integer.toString(answer));
				int weight = questions.get(q).getWeight();
				pw.write(Integer.toString(weight));
			}
		} catch (IOException i) {
			i.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// calculates max score of quiz
	public int calcMaxScore(ArrayList<Question> questions) {
		int maxScore = 0;
		int inScore;
		for (int m = 0; m < questions.size(); m++) {
			inScore = questions.get(m).getWeight();
			maxScore += inScore;
		}
		return maxScore;
	}

	// will include all console I/O for taking an existing quiz
	// may add randomize method to question class to help randomize individual questions' responses
	public int takeQuiz(boolean random, Scanner scan) {
		// Will probably handle console I/O from this method directly
		// Will return score which can be written to specific course file?
		int score = 0;
		return score;
	}

	// substitute for deleteQuiz method 
	// equals method allows course class to simply delete quiz from arraylist of quiz objects
	public boolean equals(Object o) {
		boolean equal = false;
		if (o instanceof Quiz) {
			Quiz quiz = (Quiz) o;
			if (this.name.equalsIgnoreCase(quiz.name) && this.questions == quiz.questions) {
				equal = true;
			}
		}
		return equal;
	}

	// toString allows quiz to be printed all at once if anyone wants to see it
	public String toString() {
		String format = "Quiz{name = %s,";
		for (int i = 0; i < questions.size(); i++) {
			format = format + "question " + Integer.toString(i + 1) + " = " + questions.get(i).toString() + ", ";
		}
		format = format.substring(0, format.length() - 2) + "}";
		return String.format(format, this.name);
	}
	// No deleteQuiz or addQuiz methods 
	// addQuiz should just use a constructor and write methods
	// If course class includes arraylist of quizzes, deleteQuiz should just remove one from list 
	// As of now both quiz and question classes compile
}