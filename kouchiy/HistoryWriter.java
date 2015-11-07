import dto.Answer;


/**
 * @author yutaka kocchi
 *
 */
public class HistoryWriter {


	/**
	 * @param number
	 * @param answer
	 * @param position
	 */
	public static void write(Number number, Answer answer, Position position) {

		History numHistory = new NumbersHistory(answer, position);
		number.histories.add(numHistory);
		History posHistory = new PositionsHistory(answer, number);
		position.histories.add(posHistory);

	}

}
