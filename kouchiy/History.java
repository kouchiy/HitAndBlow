import dto.QA;


public class History {

	public int strike = 0;
	public int ball = 0;
	public double point = 0.0;
	public String question = "";

	public History(QA qa) {
		this.question = qa.question;
		this.strike = qa.answer.strike;
		this.ball = qa.answer.ball;
	}

	public void setPoint(double point) {
		this.point = point;
	}

}
