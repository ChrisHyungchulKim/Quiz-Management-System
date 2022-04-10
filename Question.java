import java.util.*;

public class Question {
	// Four question fields: question prompt, arraylist of answer choices
	// integer indicating whih choice is correct, and point value/weight
	private static String prompt;
	private static ArrayList<String> responses;
	private static int answer;
	private static int weight;
	
	// Single constructor
	public Question(String prompt, ArrayList<String> responses, int answer, int weight) {
		this.prompt = prompt;
		this.responses = responses;
		this.answer = answer;
		this.weight = weight;
	}

	// accessors and mutators
	public static String getPrompt() {
		return prompt;
	}

	public static ArrayList<String> getResponses() {
		return responses;
	}

	public static int getAnswer() {
		return answer;
	}

	public static int getWeight() {
		return weight;
	}

	public static void setPrompt(String qPrompt) {
		prompt = qPrompt;
	}

	public static void setResponses(ArrayList<String> qResponses) {
		responses = qResponses;
	}

	public static void setAnswer(int qAnswer) {
		answer = qAnswer;
	}

	public static void setWeight(int qWeight) {
		weight = qWeight;
	}

	// randomizes order of answer choices and ensures index changes with it
	public static void randomize() {
		String correctResponse = responses.get(answer);
		Collections.shuffle(responses);
		for (int i = 0; i < responses.size(); i++) {
			if (responses.get(i).equals(correctResponse)) {
				answer = i;
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