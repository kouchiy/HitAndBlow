package service.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import service.report.dto.AggResults;
import dto.Play;
import dto.PlayList;

public class Aggregater {

	public static AggResults aggregate(PlayList playList, byte keta, int playTimes, int qLimitTimes, int numCnt) {

		AggResults results = new AggResults();

		int finishTimesTotal = 0;
		int questionTimesTotal = 0;
		TreeMap<Integer, Integer> qTimesMap = new TreeMap<>();
		List<Integer> allQTimesList = new ArrayList<>();
		for (Play play : playList.plays) {

			if (play.finish)
				finishTimesTotal++;

			int qt = play.size();
			allQTimesList.add(qt);
			questionTimesTotal += qt;

			int cnt = 1;
			if (qTimesMap.containsKey(qt)) {
				cnt += qTimesMap.get(qt);
			}

			qTimesMap.put(qt, cnt);

		}
		double qTimesAvg = divide(questionTimesTotal, playList.plays.size());

		int[][] qTimeModes = mode(qTimesMap);

		double qTimesMedian = 0.0;

		List<Integer> qTimesSorted = new ArrayList<>(qTimesMap.keySet());
		if (qTimesMap.size() % 2 == 0) {
			qTimesMedian = ((double) qTimesSorted.get(qTimesSorted.size() / 2) + (double) qTimesSorted
					.get(qTimesSorted.size() / 2 - 1)) / 2;
		} else {
			qTimesMedian = (double) qTimesSorted.get(qTimesSorted.size() / 2);
		}

		double questionTimesDevAvg = getDeviationValue(
				(Integer[]) allQTimesList.toArray(new Integer[0]), qTimesAvg);

		results.setPlayerName(playList.playerName);
		results.setPlayTimesTotal(playList.plays.size());
		results.setFinishTimesTotal(finishTimesTotal);
		results.setQuestionTimesTotal(questionTimesTotal);
		results.setQuestionTimesAvg(qTimesAvg);
		results.setQuestionTimesMedian(qTimesMedian);
		results.setQuestionTimesDevAvg(questionTimesDevAvg);
		results.setQuestionTimesMode(qTimeModes);

		double point = calcPoint(results
				                , keta
				                , playTimes
				                , qLimitTimes
				                , numCnt
				                );

		results.setPoint(point);

		return results;
	}


	public static double calcPoint(AggResults results, double keta, double playTimes, double qLimitTimes, double numCnt) {
		double point = 0;

		double x = (double)results.getFinishTimesTotal() / playTimes; // 正解数/プレイ数
		double y = playTimes * qLimitTimes / (double)results.getQuestionTimesTotal(); //  プレイ数 * 質限数 / 質問回数合計
		double z = qLimitTimes - results.getQuestionTimesMedian(); // 質限数 - 質問数中央値
		double a = keta * numCnt / results.getQuestionTimesDevAvg(); // 桁 * 数 / 質問標準偏差

		point = (x * 5) * (y * 3) * (z * 1) * (a * 1);

		return point;
	}

	private static int[][] mode(TreeMap<Integer, Integer> qTimesMap) {
		// 最頻値Top3を求める。
		int maxCnt1 = 0;
		int maxCnt2 = 0;
		int maxCnt3 = 0;
		int maxQtime1 = 0;
		int maxQtime2 = 0;
		int maxQtime3 = 0;

		for (Entry<Integer, Integer> entry : qTimesMap.entrySet()) {

			int value = entry.getValue();

			if (value >= maxCnt1) {
				maxCnt3 = maxCnt2;
				maxCnt2 = maxCnt1;
				maxCnt1 = value;

				maxQtime3 = maxQtime2;
				maxQtime2 = maxQtime1;
				maxQtime1 = entry.getKey();
				continue;
			}
			if (value >= maxCnt2) {
				maxCnt3 = maxCnt2;
				maxCnt2 = value;

				maxQtime3 = maxQtime2;
				maxQtime2 = entry.getKey();
				continue;
			}
			if (value > maxCnt3) {
				maxCnt3 = value;

				maxQtime3 = entry.getKey();
				continue;
			}

		}

		int[][] o = { { maxQtime1, maxCnt1 }, { maxQtime2, maxCnt2 },
				{ maxQtime3, maxCnt3 } };
		return o;
	}

	private static double divide(int questionTimesTotal, int size) {
		// BigDecimalの生成
		BigDecimal a = new BigDecimal(size);
		BigDecimal c = new BigDecimal(questionTimesTotal);

		// 割り算（c ÷ a を小数第３位で切上げ）
		double val = c.divide(a, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
		return val;
	}

	/**
	 * 標準偏差を返すメソッド 求め方：[（各要素-平均値）^2 の合計]÷要素数の平方根
	 *
	 * @param array
	 *            標準偏差を計算する配列
	 * @return 標準偏差
	 */
	private static double getDeviationValue(Integer[] array, double qTimesAvg) {
		// 平均値を取得
		double dblAve = qTimesAvg;

		double dblSum = 0.0;

		// 要素数の数だけ繰り返す
		for (int i = 0; i < array.length; i++) {
			// (平均値-値)^2を足していく
			dblSum += Math.pow((dblAve - array[i]), 2.0);
		}

		// 標準偏差を返す
		double result = Math.sqrt(dblSum / array.length);
		return result;
	}

}
