package nono.worker;

import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;

import nono.Histories;
import nono.dto.History;
import nono.dto.Number;
import nono.dto.QAHistory;


public class Searcher {

//	public static boolean isNoNo(QA qa) {
//		if (qa.answer.strike == 0 && qa.answer.ball == 0) return true;
//		return false;
//	}

//	public static boolean isBallOnly(QA qa) {
//		if (qa.answer.strike == 0 && qa.answer.ball >= 1) return true;
//		return false;
//	}

//	public static boolean isAllCnt(QA qa) {
//		if (qa.answer.strike + qa.answer.ball == qa.question.length()) return true;
//		return false;
//	}

	public static boolean isQuestion(Collection<Number> numbers) {

		for (Number number : numbers) {
			if (number.histories.size() == 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean isAllCntInOthers(Number src, Histories<QAHistory> qaHistories,
			Deque<Number> others, int allNumTotal) {

		Histories<QAHistory> cntQaHisList = new Histories<>();

		// qa roop
		for (History qaHis : qaHistories) {

			// そもそもNoNoならisAllCnt判断できない
			if (qaHis.isNoNo) continue;

			// srcのhistoryならこのqaだけではisAllCntInOthers判断できない
			if (Historia.isSameHistory(qaHis.question, src.histories)) continue;

			// judge1 isAllCnt
			if (qaHis.isAllCnt) return true;

			// cnt有りqaHisを保持
			cntQaHisList.add(qaHis);

		}

		// cnt有りqaHistListが0なら現在のhistoryからisAllCntInOthers判断できない
		if (cntQaHisList.size() == 0) return false;

		if (isNoOverLap(src.toInt(), cntQaHisList.getQuestionsNumArray(), allNumTotal)) {
			return true;
		}

		return false;
	}

	/**
	 * 条件：
	 * 	・questionsは全てcntあること
	 * 	・questions内にnumがないこと
	 * @param num
	 * @param questions
	 * @param allNumTotal
	 * @return
	 */
	private static boolean isNoOverLap(int num, String[][] questions, int allNumTotal) {

		for (String[] question : questions) {
			HashMap<String, Integer> noOverLapNums = new LinkedHashMap<>();


			for (String qNum : question) {
				noOverLapNums.put(qNum, Integer.parseInt(qNum));
			}
			for (String[] srcNums : questions) {
				if (isNumInQuestion(question, srcNums)) {
					continue;
				}
				for (String srcNum : srcNums) {
					noOverLapNums.put(srcNum, Integer.parseInt(srcNum));
				}

			}

			int noOverLapNumTotal = 0;
			for (int noOverLapNum : noOverLapNums.values()) {
				noOverLapNumTotal += noOverLapNum;
			}

			if ((allNumTotal - num) == noOverLapNumTotal) {
				return true;
			}

		}

		return false;
	}

	private static boolean isNumInQuestion(String[] question, String... srcNum) {

		for (String qNum : question) {
			for (String src : srcNum) {
				if (qNum.equals(src)) {
					return true;
				}
			}
		}

		return false;
	}

}
