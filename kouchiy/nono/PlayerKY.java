package nono;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import nono.constants.Num;
import nono.constants.Pos;
import nono.dto.History;
import nono.dto.MyQuestion;
import nono.dto.Number;
import nono.dto.QAHistory;
import nono.worker.Fixer;
import nono.worker.Historia;
import nono.worker.Searcher;
import players.Player;
import players.PlayerInterface;
import dto.Play;
import dto.QA;

public class PlayerKY extends Player implements PlayerInterface {

	private Histories<QAHistory> qaHistories;
	private Positions positions;
	private Numbers numbers;
	private MyQuestion myQ;

	private boolean demo = false;

	public PlayerKY(byte keta, int numCnt, boolean... demo) {
		this(keta, numCnt);
		if (demo.length > 0)
			this.demo = demo[0];
	}

	public PlayerKY(byte keta, int numCnt) {
		super(keta, numCnt);
		this.qaHistories = new Histories<QAHistory>();
		this.positions = new Positions(keta, numCnt);
		this.numbers = new Numbers(keta, numCnt);
		this.myQ = new MyQuestion(keta);
	}

	@SuppressWarnings("unused")
	@Override
	public String run(Play play) throws Exception {

		if (this.demo) {
			return super.getRandomNumbers(play);
		}

		for (QA qa : play) {
			// 現時点の全てのQAをロード
			this.loadQA(qa);

		}

		boolean srcPossiblityResult = false;
		srcPossiblityResult = this.searchPossibility(this.numbers.values()
				.toArray(new Number[super.numCnt]));

		// factから得られたpossibilityが更新されたら
		// if (srcPossiblityResult) {
		// 次は fix 及び pFix を決定する
		Fixer.filter(this.qaHistories, this.numbers, this.positions);

		// }

		PlayerTest.testPossibilityView(this.numbers, this.positions);

		String result = "";

		final int roopCntRimit = 10;
		int roopCnt = 0;
		// myQuestion を作る
		while (true) {
			roopCnt++;

			if (this.myQ.isSet) {
				result = this.myQ.getQuestion();
				break;
			} else {

				if (roopCnt != 10) {
					continue;
				}
				// ランダム番号で初期化
				result = super.getRandomNumbers(play);
				break;
			}
		}

		return result;
	}

	private boolean searchPossibility(Number... srcs) {

		boolean possibilityUpdate = false;

		for (History his : this.qaHistories) {

			possibilityUpdate = this.searchNoNoOrAllCnt(his.question, his.ball,
					his.strike);
		}

		for (Number src : srcs) {

			// src以外のnumbersで allCnt < keta ないか(=この番号のfixを走査)

			boolean is1stQuestion = this.qaHistories.size() == 0;
			// 未だ１度も質問していないならfix走査できない
			if (is1stQuestion)
				return false;

			// 準備----start
			Deque<Number> otherNums = new LinkedList<>(this.numbers.values());

			for (Iterator<Number> i = otherNums.iterator(); i.hasNext();) {
				if (src.equals(i.next())) {
					otherNums.poll();
					break;
				}
			}

			// if (otherNums.remove(src)) {
			// System.out.println("srcを消せました");
			// }

			// 過去質問登場しているか
			boolean isQuestion = src.histories.size() != 0;

			// 準備----end

			// ◆srcのhistory=0 且つ 他numbersのみでallCntがあればpossible=false
			if (!isQuestion) {
				// ◇srcのhistory=0...つまり他numbersのみ履歴しかない

				if (Searcher.isQuestion(otherNums)) {
					// ◇他numbersは全て質問登場している
					if (Searcher.isAllCntInOthers(src, this.qaHistories,
							otherNums, this.numbers.getAllNumTotal())) {
						// ◇qaHistoryに他numbers内でallCntあり...つまりsrcはpossible=false
						this.removePossibility(src.toString());
						possibilityUpdate = true;
					}
				}

			}

			// ◆srcのhistory=strikeのquestionがある 且つ
			// そのquestionの他numbersがpossible=false ...Pfix
			// TODO
			// ◆srcのhistory=ballのquestionがある 且つ
			// そのquestionの他numbersがpossible=false ...fix
			// TODO


		}
		return possibilityUpdate;
	}

	private void loadQA(QA qa) {

		// 同じhistoryは記録しない
		if (Historia.isSameHistory(qa.question, this.qaHistories)) {
			return;
		} else {
			// 新たなhistoryを記録する
			Historia.write(qa, this.qaHistories);
		}

		byte pCnt = 0; // 位置数
		// 質問の番号の分
		for (char numChar : qa.question.toCharArray()) {

			Num num = Num.get(numChar);
			Pos pos = null;
			for (Pos _pos : Pos.values()) {
				if (_pos.ordinal() == pCnt)
					pos = _pos;
			}

			pCnt++;
			// 番号各々のhistoryへ記録 1スト1ボーを
			Historia.write(qa, this.numbers.get(num).histories,
					this.positions.get(pos));
			Historia.write(qa, this.positions.get(pos).histories,
					this.numbers.get(num));

		}

	}

	/**
	 * カウントNoNoまたはAllCntを探す その場合は、そのquestionの可能性をゼロにする
	 *
	 * @param question
	 * @param ball
	 * @param strike
	 */
	private boolean searchNoNoOrAllCnt(String question, int ball, int strike) {

		boolean possibilityUpdate = false;

		// nono
		if (strike == 0 && ball == 0) {
			// このquestionの可能性をゼロに
			this.removePossibility(question);
			possibilityUpdate = true;

		}

		// ball only
		if (strike == 0 && ball >= 1) {
			// このqaの現在の位置の可能性をゼロに
			this.removePossibilityNowPosition(question);
			possibilityUpdate = true;

		}

		// all cnt
		if (strike + ball == question.length()) {
			// このqaの逆を可能性をゼロに
			this.removePossibilityReverse(question);
			possibilityUpdate = true;

		}

		return possibilityUpdate;
	}

	private void removePossibilityNowPosition(String question) {

		byte pCnt; // 位置数

		for (pCnt = 0; pCnt < question.length(); pCnt++) {
			Num num = Num.get(question.toCharArray()[pCnt]);
			Pos pos = Pos.get(pCnt);

			this.numbers.get(num).removePos(pos);
			this.positions.get(pos).removeNum(num);

		}

	}

	private void removePossibility(String question) {

		for (char numChar : question.toCharArray()) {

			Num num = Num.get(numChar);

			this.numbers.removeAllPos(num);
			this.positions.removeNumAllPos(num);

		}

	}

	private void removePossibilityReverse(String question) {

		String reverse = "";
		for (Number no : this.numbers.values()) {
			boolean isAdd = false;

			for (char numChar : question.toCharArray()) {

				Num num = Num.get(numChar);
				if (no.equals(num)) {
					isAdd = false;
					break;
				}
				isAdd = true;
			}
			if (isAdd) {
				reverse += String.valueOf(no);
			}
		}

		this.removePossibility(reverse);

	}

}
