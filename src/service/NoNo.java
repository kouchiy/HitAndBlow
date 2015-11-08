package service;

import dto.Answer;
import dto.QA;

public class NoNo {

	/*****************
	 * フィールド変数
	 *****************/
	/* static */

	/* 非static */

	// 正解
	public String correctAns;

	// QA
	private QA qa;

	/*****************
	 * コンストラクタ
	 *****************/

	public NoNo(String correctAns, QA qa) {
		this.correctAns = correctAns;
		this.qa = qa;
	}

	/*****************
	 * メソッド
	 *****************/

	public boolean run() {
		return this.play();
	}

	private boolean play() {

		if (this.qa.question.isEmpty()) {
			return false;
		}

		// // nullチェック　文字数チェック
		// if (true) {
		// return false;
		// }

		// 形式チェック
		// if (true) {
		// return false;
		// }

		// 比較
		Answer answer = new Answer();
		Validate.strikeBallJudge((byte) this.correctAns.length(),
				this.correctAns, this.qa.question, answer);
		this.qa.answer = answer;

//		if (this.correctAns.equals(qa.question)) {
			if (this.qa.answer.strike == this.qa.question.length()) {
			return true;
		} else {
			return false;
		}

	}

}

class Validate {
	// ストライク/ボール判定
	public static void strikeBallJudge(byte keta, String correctAns,
			String question, Answer answer) {

		for (int i = 0; i < keta; ++i) {

			for (int j = 0; j < keta; ++j) {

				if (question.charAt(i) == correctAns.charAt(j)) {

					if (i == j) {
						answer.addStrike();
					} else {
						answer.addBall();
					}
					break;

				}
			}
		}
	}
}
