package nono.worker;

import nono.Histories;
import nono.Numbers;
import nono.Positions;
import nono.dto.Number;
import nono.dto.QAHistory;



public class Fixer {

	public static void filter(Histories<QAHistory> qaHistories,
			Numbers numbers, Positions positions) {

		// possibility からfix or Pfix を決定する

		for (Number number : numbers.values()) {

			if (number.getPossiblities().size() == 1) {
//				number.setFix();
//				number.setPFix(positions.get(number.getPossiblities().get(0)));
			}
		}


	}

}
