import dto.Answer;



public class PositionsHistory extends History {

	public Number number;

	public PositionsHistory(Answer answer, Number number) {
		super(answer);
		this.number = number;
	}



}
