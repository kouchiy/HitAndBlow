import dto.Answer;


public class NumbersHistory extends History {

	public Position position;

	public NumbersHistory(Answer answer, Position position) {
		super(answer);
		this.position = position;
	}

}
