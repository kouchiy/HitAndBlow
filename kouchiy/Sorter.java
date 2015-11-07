public class Sorter {

	public static void sort(int stage, Numbers numbers) {

		for (Number number : ((Numbers) numbers).values()) {
			switch (stage) {
			case 1:
				simple(number);
				break;

			default:
				break;
			}
		}

		// TODO 自動生成されたメソッド・スタブ

	}

	private static void simple(NoNoObject nono) {

		Histories histories = null;
		if (nono instanceof Number) {
			histories = ((Number) nono).histories;
		} else if (nono instanceof Position) {
			histories = ((Position) nono).histories;
		}

		for (History his : histories) {
			// strikeとballのカウントが材料
			double point = 0.0;
			point += his.strike * 1.0;
			point += his.ball * 0.1;
			his.setPoint(point);
		}

	}

}
