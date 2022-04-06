import java.util.ArrayList;

public class Question {
	private String prompt;
	private ArrayList<String> responses;
	private int answer;
	private int weight;
	
	public Question(String prompt, ArrayList<String> responses, int answer, int weight) {
		this.prompt = prompt;
		this.responses = responses;
		this.answer = answer;
		this.weight = weight;
	}

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