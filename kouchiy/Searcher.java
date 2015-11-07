import dto.QA;


public class Searcher {

	public static boolean isNoNo(QA qa) {
		if (qa.answer.strike == 0 && qa.answer.ball == 0) return true;
		return false;
	}

	public static boolean isAllBall(QA qa) {
		if (qa.answer.strike == 0 && qa.answer.ball == qa.question.length()) return true;
		return false;
	}

	public static boolean isAllCnt(QA qa) {
		if (qa.answer.strike + qa.answer.ball == qa.question.length()) return true;
		return false;
	}

}
