import java.util.Map.Entry;

import constants.Num;
import constants.Pos;




public class PlayerTest {

	public static void testHistoryView(Numbers numbers, Positions positions) {

		for (Number num : numbers.values()) {

			System.out.printf("【 %s 】fix=%b \n", num, num.isPossible());

			Histories<?> hiss = num.histories;

			int hisCnt = 0;
			for (History his : hiss) {
				hisCnt++;

				System.out.printf("    -- %d   strike=%d, ball=%d, point=%.1f, \n", hisCnt, his.strike, his.ball, his.point);
			}

		}

	}

	public static void outMyQuestion(MyQuestion myQ) {

		try {
			String question = "";
			if (myQ.isSet) {
				question = myQ.getQuestion();
			}
			System.out.printf("myQuestion=《 isSet=%b, %s 》\n", myQ.isSet, question);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testPossibilityView(Numbers numbers, Positions positions) {

		System.out.println();
		for (Entry<Num, Number> set : numbers.entrySet()) {
			System.out.println("    " + set.getKey() +  " " + set.getValue().getPossiblities());
		}

		for (Entry<Pos, Position> set : positions.entrySet()) {
			System.out.println("   " + set.getKey() +  " " + set.getValue().getPossiblities());
		}
		System.out.println();


	}

}
