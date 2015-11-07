import dto.Answer;


public class History {

	public int strike = 0;
	public int ball = 0;
	public double point = 0.0;

	public History(Answer answer) {
		this.strike = answer.strike;
		this.ball = answer.ball;
	}

	public void setPoint(double point) {
		this.point = point;
	}

}
