package nono;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import nono.constants.Num;
import nono.constants.Pos;
import nono.dto.History;
import nono.dto.MyQuestion;
import nono.dto.Number;
import nono.dto.Position;




public class PlayerTest {

	public static void testHistoryView(Numbers numbers, Positions positions) {

		for (Number num : numbers.values()) {

			System.out.printf("【 %s 】fix=%b \n", num, num.isPossible());

			Histories<?> hiss = num.histories;

			int hisCnt = 0;
			for (History his : hiss) {
				hisCnt++;

				System.out.printf("    -- %d   strike=%d, ball=%d, \n", hisCnt, his.strike, his.ball);
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

//		Map<Num, Number> numMap = numbers;

		Map<Num, Number> numTreeMap = new TreeMap<>((Map<Num, Number>)numbers);
		Map<Pos, Position> posTreeMap = new TreeMap<>((Map<Pos, Position>)positions);

		System.out.println();
		for (Entry<Num, Number> set : numTreeMap.entrySet()) {
			String fix = "";
			String pFix = "";
			if (set.getValue().isFix()) {
				fix = "【FIX】";
			}
			if (set.getValue().isPFix()) {
				Position pos = (Position) set.getValue().getPFix();
				pFix = pos.toString();
			}
			System.out.println("    " + set.getKey() +  " " + set.getValue().getPossiblities() +  " " + fix +  " " + pFix);
		}

		for (Entry<Pos, Position> set : posTreeMap.entrySet()) {
			System.out.println("   " + set.getKey() +  " " + set.getValue().getPossiblities());
		}
		System.out.println();


	}

}
