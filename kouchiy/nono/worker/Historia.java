package nono.worker;

import java.util.Iterator;

import nono.Histories;
import nono.dto.History;
import nono.dto.NumHistory;
import nono.dto.Number;
import nono.dto.Position;
import nono.dto.PositionsHistory;
import nono.dto.QAHistory;
import dto.QA;


/**
 * @author yutaka kocchi
 *
 */
public class Historia {

	public static boolean isSameHistory(String question, Histories<?> histories) {

		Histories<?> hiss = histories;
		for (Iterator<History> i = hiss.iterator(); i.hasNext();) {

			History his = i.next();

			if (question.equals(his.question)) {
//				System.out.println("---- Same Question ----");
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
