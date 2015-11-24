package player;

import number.Numbers;
import number.Positions;
import players.Sheet;

public class Test {

	public static void positionNewInstanceTest() {

//		test1();
		test2();
//		test3();



	}

	private static void test2() {
		byte keta = 0;
		byte numCnt = 0;

//		keta = 2; numCnt = 3;
//		keta = 3; numCnt = 5;
//		keta = 3; numCnt = 7;
//		keta = 5; numCnt = 10;
//		keta = 4; numCnt = 5;
//		keta = 4; numCnt = 7;
		keta = 3; numCnt = 4;

		int expectedQuestionCnt = numCnt;
		for (byte b = 1; b < keta; b++) {
			byte numCntBy = (byte) (numCnt - b);
			expectedQuestionCnt = (numCntBy) * expectedQuestionCnt;
		}

		Sheet sheet = new Sheet(keta, numCnt);

		System.out.println(sheet);
		testRemove(sheet);
		System.out.println(sheet);

		String[] questions = sheet.getNumbers();

		System.out.println("QustionCnt= " + questions.length);
		if (questions.length != expectedQuestionCnt) System.out.println("QustionCnt error");

		overLapTest(questions);

	}

	private static void test3() {
		byte keta = 0;
		byte numCnt = 0;

//		keta = 2; numCnt = 3;
//		keta = 3; numCnt = 5;
//		keta = 3; numCnt = 7;
//		keta = 5; numCnt = 10;
//		keta = 4; numCnt = 5;
//		keta = 4; numCnt = 7;
		keta = 3; numCnt = 4;

		int expectedQuestionCnt = numCnt;
		for (byte b = 1; b < keta; b++) {
			byte numCntBy = (byte) (numCnt - b);
			expectedQuestionCnt = (numCntBy) * expectedQuestionCnt;
		}

		Positions positions = new Positions(keta, numCnt);

		System.out.println(positions);
		testRemove(positions);
		System.out.println(positions);

		Numbers numbers = new Numbers(positions);
		String[] questions = numbers.getQuestion().toArray(new String[0]);

		System.out.println("QustionCnt= " + questions.length);
		if (questions.length != expectedQuestionCnt) System.out.println("QustionCnt error");

		overLapTest(questions);

	}

	private static void testRemove(Sheet sheet) {

//		sheet.erase(Pos.PNULL, 3);
//		sheet.erase(Pos.get(0), 1, 2, 3, 4, 5, 6, 7, 8, 9);
//		sheet.erase(Pos.get(1), 1, 2, 3, 4, 5, 6, 7, 0);
//		sheet.erase(Pos.get(2), 1, 0, 3, 4, 5, 6, 7, 8, 9);
//		sheet.erase(Pos.get(0), false, 0);
//		sheet.erase(Pos.get(1), false, 1);
//		sheet.erase(Pos.get(2), false, 3);

		sheet.erase('3'); // sheet.erase(Pos.PNULL, 3);
//		sheet.erase((byte) 0, 1, 2, 3, 4, 5, 6, 7, 8, 9); // sheet.erase(Pos.get(0), 1, 2, 3, 4, 5, 6, 7, 8, 9);
		sheet.erase((byte) '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
//		sheet.erase(Pos.get(1), 1, 2, 3, 4, 5, 6, 7, 0);
//		sheet.erase(Pos.get(0), false, 0);
		sheet.erase((byte) 1, false, '1'); // sheet.erase(Pos.get(1), false, 1);
//		sheet.erase(Pos.get(2), false, 3);
	}

	private static void testRemove(Positions positions) {

//		positions.erase(Pos.PNULL, 3);
//		positions.erase(Pos.get(0), 1, 2, 3, 4, 5, 6, 7, 8, 9);
//		positions.erase(Pos.get(1), 1, 2, 3, 4, 5, 6, 7, 0);
//		positions.erase(Pos.get(2), 1, 0, 3, 4, 5, 6, 7, 8, 9);
//		positions.erase(Pos.get(0), false, 0);
//		positions.erase(Pos.get(1), false, 1);
//		positions.erase(Pos.get(2), false, 3);

	}

	private static boolean overLapTest(String[] questions) {
		boolean test = true;
		int index = 0;
		for (String _question : questions ) {
			if (isOverLap(index, _question, questions)) {
				_question += " is over lap!";
				test = false;
			}
			char[] chars = _question.toCharArray();
			int cIndex = 0;
			for (char c : chars) {
				if (isOverLap(cIndex, c, chars)) {
					_question += " is over lap!";
					test = false;
				}
				cIndex++;
			}
			System.out.println(_question);
			index++;
		}

		if (test) {
			System.out.println("over lap test = SUCCESS");
		} else {
			System.out.println("over lap test = FALLS");
		}

		return test;
	}

	private static boolean isOverLap(int index, char chk, char[] srcs) {

		int _index = 0;
		for (char src : srcs) {

			if (index == _index) continue;

			if (chk == src) {
				return true;
			}
			_index++;
		}

		return false;
	}

	public static boolean isOverLap(int index, String chk, String...srcs) {

		int _index = 0;
		for (String src : srcs) {

			if (index == _index) continue;

			if (chk.equals(src)) {
				return true;
			}
			_index++;
		}

		return false;
	}

}
