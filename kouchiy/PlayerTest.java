

public class PlayerTest {

	public static void testHistoryView(Numbers numbers, Positions positions) {

		for (Number num : numbers.values()) {

			System.out.printf("【 %s 】fix=%b \n", num.getCharValue(), num.fix);

			int hisCnt = 0;
			for (History his : num.histories) {
				hisCnt++;

				System.out.printf("    -- %d   strike=%d, ball=%d, point=%.1f, \n", hisCnt, his.strike, his.ball, his.point);
			}

		}

	}

	public static void outMyQuestion(MyQuestion myQ) {

		try {
			String question = "";
			if (myQ.isSet) {
				question = myQ.question;
			}
			System.out.printf("myQuestion=《 isSet=%b, %s 》\n", myQ.isSet, question);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
