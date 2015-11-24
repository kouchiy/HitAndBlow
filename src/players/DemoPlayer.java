package players;
import java.util.List;

import dto.Answer;
import dto.Play;
import dto.QA;


public class DemoPlayer extends Player implements PlayerInterface {

	public DemoPlayer(byte keta, byte numCnt) {
		super(keta, numCnt);
	}

	@Override
	public String run(Play play) throws Exception {

		String timePoint = null;
		long start = 0;
		long end = 0;

		timePoint = "削り開始";
		start = System.nanoTime();

		// ◆削る
		this.eliminateNew(play);

		end = System.nanoTime();
		timePoint = timePoint + "-終了";
//		System.out.println(timePoint + " Time:" + (end - start) / 1000000f + "ms");

		timePoint = "選ぶ開始";
		start = System.nanoTime();

		// 現時点で可能性がある且つ、未だquestionしたことがないnumberのListを取得
		List<String> choicesNumberList = super.getPossibleAndFirstNumberList(play, null);

		end = System.nanoTime();
		timePoint = timePoint + "-終了";
//		System.out.println(timePoint + " Time:" + (end - start) / 1000000f + "ms");

		// ◆選ぶ
		String question = this.choice(choicesNumberList);

		// 質問を返却
		return question;
	}
	private void eliminateNew(Play play) {

		// playのQAリストをループ
		for (QA qa : play) {

			// strike,ball 判定の為answerを取得
			Answer answer = qa.answer;

			// questionを取得
			String qNumber = qa.question;

			// qNumberを分解し番号の配列にする
			char[] numberCharArray = qNumber.toCharArray();

			// nono(0 STRIKE && 0 BALL)か判定
			if (answer.strike + answer.ball == 0) {

				// nonoの場合

				/** 削るデモンストレーション①
				 *
				 * superクラスのsheetからnumをひとつずつ消す
				 *
				 */
				for (char numChar : numberCharArray) {

					super.sheet.erase(numChar);
				}

				/** 削るデモンストレーション②
				 *
				 * superクラスのsheet.dropに一度にnumの配列を渡す
				 *
				 */
				super.sheet.erase(numberCharArray);
//				super.sheet.drop(nonoNumber.toCharArray()); // もちろんこれでもOK
//				super.sheet.drop(numberCharArray[0], numberCharArray[1], ..); // 可変長引数だからこんな感じでもOK
//				super.sheet.drop(0, 1, ..); // charでなくintもOK
//				super.sheet.drop("0", "1"); // StringもOK

				/** 削るデモンストレーション③
				 *
				 * 場所と番号を指定してピンポイントに消せます
				 *
				 */
				for (byte position = 0; position < qNumber.length(); position++) {

					super.sheet.erase(position, qNumber.charAt(position));
//					super.sheet.drop((byte)0, '9');// 例：0番目の'9'の可能性を消す
//					super.sheet.drop((byte)0, "9", "8");// 例：0番目の"9"と"8"の可能性を消す(Stringでも、配列でもOK)

				}

			} else if (answer.strike + answer.ball == qNumber.length()) {
				// allCntか判定

				// allCntの場合

				/**
				 * 削るデモンストレーション④
				 *
				 * 他の番号の可能性を消せます
				 *
				 */
				super.sheet.erase(false, numberCharArray);

			}
		}
super.sheet.drop();
	}


//	private void eliminate(Play play) {
//
//		// playのQAリストをループ
//		for (QA qa : play) {
//
//			// strike,ball 判定の為answerを取得
//			Answer answer = qa.answer;
//
//			// questionを取得
//			String qNumber = qa.question;
//
//			// qNumberを分解し番号の配列にする
//			char[] numberCharArray = qNumber.toCharArray();
//
//			// nono(0 STRIKE && 0 BALL)か判定
//			if (answer.strike + answer.ball == 0) {
//
//				// nonoの場合
//
//				/** 削るデモンストレーション①
//				 *
//				 * superクラスのsheetからnumをひとつずつ消す
//				 *
//				 */
//				for (char numChar : numberCharArray) {
//
//					super.sheet.drop(numChar);
//				}
//
//				/** 削るデモンストレーション②
//				 *
//				 * superクラスのsheet.dropに一度にnumの配列を渡す
//				 *
//				 */
//				super.sheet.drop(numberCharArray);
////				super.sheet.drop(nonoNumber.toCharArray()); // もちろんこれでもOK
////				super.sheet.drop(numberCharArray[0], numberCharArray[1], ..); // 可変長引数だからこんな感じでもOK
////				super.sheet.drop(0, 1, ..); // charでなくintもOK
////				super.sheet.drop("0", "1"); // StringもOK
//
//				/** 削るデモンストレーション③
//				 *
//				 * 場所と番号を指定してピンポイントに消せます
//				 *
//				 */
//				for (byte position = 0; position < qNumber.length(); position++) {
//
//					super.sheet.drop(position, qNumber.charAt(position));
////					super.sheet.drop((byte)0, '9');// 例：0番目の'9'の可能性を消す
////					super.sheet.drop((byte)0, "9", "8");// 例：0番目の"9"と"8"の可能性を消す(Stringでも、配列でもOK)
//
//				}
//
//			} else if (answer.strike + answer.ball == qNumber.length()) {
//				// allCntか判定
//
//				// allCntの場合
//
//				/**
//				 * 削るデモンストレーション④
//				 *
//				 * 他の番号の可能性を消せます
//				 *
//				 */
//				super.sheet.drop(false, numberCharArray);
//
//			}
//		}
//
//	}

	private String choice(List<String> choicesNumberList) {

		String question = choicesNumberList.get(Math.round(choicesNumberList.size() / 2)); // 常にList中央を採用

		return question;
	}

}
