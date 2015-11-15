package nono.dto;

public class MyQuestion {

	private String question;

	public boolean isSet;

	public final byte keta;

	public MyQuestion(byte keta) {
		this.question = "";
		this.keta = keta;
	}

	public String getQuestion() throws Exception {
		if (this.isSet) {

			String returnQuestion = new String(this.question);

			this.initialize();

			return returnQuestion;
		}
		throw new Exception();
	}

	private void initialize() {
		this.setQuestion("");
	}

	public void setQuestion(String question) {
		this.isSet = question.length() == keta;
		if (this.isSet) {
			this.question = question;
		}
	}

	public void addNum(String num) {
		this.question += num;
		this.isSet = this.question.length() == keta;
	}

}
