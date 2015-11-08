import java.util.Iterator;

import dto.QA;


/**
 * @author yutaka kocchi
 *
 */
public class Historia {

	public static boolean isSameHistory(QA qa, Histories<?> histories) {

		Histories<?> hiss = histories;
		for (Iterator<History> i = hiss.iterator(); i.hasNext();) {

			History his = i.next();

			if (qa.question.equals(his.question)) {
				return true;
			}
		}
		return false;
	}

	public static void write(QA qa, Histories<QAHistory> histories) {

		History newHistory = new QAHistory(qa);

		histories.add(newHistory);
	}

	public static void write(QA qa, Histories<NumHistory> histories, Position pos) {

		History newHistory = new NumHistory(qa, pos);

		histories.add(newHistory);
	}

	public static void write(QA qa, Histories<PositionsHistory> histories, Number num) {

		History newHistory = new PositionsHistory(qa, num);

		histories.add(newHistory);
	}

}
