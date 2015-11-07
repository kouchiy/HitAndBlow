public class MyQuestion {

	public String question;

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
		this.question = question;
		this.isSet = this.question.length() == keta;
	}

	public void addNum(String num) {
		this.question += num;
		this.isSet = this.question.length() == keta;
	}

}
