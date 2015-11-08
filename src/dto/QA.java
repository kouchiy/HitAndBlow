package dto;

public class QA {

	public QA(String question) {
		this.question = question;
	}
	public String question;
	public Answer answer = new Answer();

	@Override
	public String toString() {
		String qa = "  [ "+ this.question + " ]  " + this.answer;
		return qa;
	}

}
