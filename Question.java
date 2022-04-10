import java.util.*;

public class Question {
	// Four question fields: question prompt, arraylist of answer choices
	// integer indicating whih choice is correct, and point value/weight
	private String prompt;
	private ArrayList<String> responses;
	private int answer;
	private int weight;
	
	// Single constructor
	public Question(String prompt, ArrayList<String> responses, int answer, int weight) {
		this.prompt = prompt;
		this.responses = responses;
		this.answer = answer;
		this.weight = weight;
	}

	// accessors and mutators
	public String getPrompt() {
		return prompt;
	}

	public ArrayList<String> getResponses() {
		return responses;
	}

	public int getAnswer() {
		return answer;
	}

	public int getWeight() {
		return weight;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public void setResponses(ArrayList<String> responses) {
		this.responses = responses;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	// randomizes order of answer choices and ensures index changes with it
	public void randomize() {
		String correctResponse = responses.get(answer);
		Collections.shuffle(responses);
		for (int i = 0; i < responses.size(); i++) {
			if (responses.get(i).equals(correctResponse)) {
				this.answer = i;
			}
		}
	}

	// equals method ensures correct objects can be removed from arraylist of questions
	public boolean equals(Object o) {
		boolean equal = false;
		if (o instanceof Question) {
			Question ques = (Question) o;
			if (this.prompt.equalsIgnoreCase(ques.prompt) && this.responses == ques.responses && 
				this.answer == ques.answer && this.weight == ques.weight) {
				equal = true;
			}
		}
		return equal;
	}

	// toString allows question to be printed all at once if anyone wants to see it
	public String toString() {
		String format = "Question{prompt = %s, responses = [";
		for (int i = 0; i < responses.size(); i++) {
			format += responses.get(i) + ", ";
		}
		format = format.substring(0, format.length() - 2) + "], answer = %s, weight = %d}";
		String answerString = responses.get(answer);
		return String.format(format, this.prompt, answerString, this.weight);
	}
}