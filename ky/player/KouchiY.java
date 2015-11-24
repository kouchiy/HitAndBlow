package player;

import java.util.List;
import java.util.Random;

import players.Player;
import players.PlayerInterface;
import players.Sheet;
import dto.Play;
import dto.QA;

public class KouchiY extends Player implements PlayerInterface {

	private boolean demo = false;
	private Sheet filter;


	public KouchiY(byte keta, byte numCnt, boolean... demo) {
		this(keta, numCnt);
		if (demo.length > 0)
			this.demo = demo[0];
	}

	public KouchiY(byte keta, byte numCnt) {
		super(keta, numCnt);

	}

	@Override
	public String run(Play play) throws Exception {

		// ◆削る
		this.eliminate(play);

		// 現時点で可能性がある且つ、未だquestionしたことがないnumberのListを取得
		List<String> choicesNumberList = super.getPossibleAndFirstNumberList(play, null);

//		System.out.println(super.sheet);

		// ◆選ぶ
		String question = this.choice(choicesNumberList, play);

		// 質問を返却
		return question;
	}

	private void eliminate(Play play) {

		// playのQAリストをループ
		int qaTimes = 0;
		for (QA qa : play) {

			// strike,ball 判定の為answerから取得
			int strike = qa.answer.strike;
			int ball = qa.answer.ball;

			// questionを取得
			String qNumber = qa.question;

			// qNumberを分解し番号の配列にする
			char[] numberCharArray = qNumber.toCharArray();

			// nono(0 STRIKE && 0 BALL)か判定
			if (strike + ball == 0) {

				// nonoの場合
				for (char numChar : numberCharArray) {

					super.sheet.erase(numChar);
				}

			} else if (strike + ball == qNumber.length()) {
				// allCntか判定

				// allCntの場合
				super.sheet.erase(false, numberCharArray);


			}

			if (strike == 0) {
				// no strike か判定

				// no strikeの場合
				// このnumberの現在の位置の可能性をゼロに

				for (byte position = 0; position < qNumber.length(); position++) {

					super.sheet.erase(position, qNumber.charAt(position));

				}

			} else {
				// strike がある場合

				// 次のquestionの候補から外す
				for (byte position = 0; position < qNumber.length(); position++) {

					if (this.filter == null) {
						this.filter = new Sheet(keta, numCnt);
					}

					if (strike == 1 && ball == 0) {
						// 1Sのみの場合

						// 全て外す
						this.filter.erase(position, qNumber.charAt(position));

						if (position == 0) {
							// そしてP0の番号は全てのPから外す
							this.filter.erase(qNumber.charAt(position));
						}

					} else {
						// 1S以上の場合

						if (position != 0) {
							// このフィルタはqa回数が前半のものは判断の材料にしない
							if (play.size() > 6 && qaTimes < 6) {
								qaTimes++;
								continue;
							}
							// P0以外の番号は全てのPから外す
							this.filter.erase(qNumber.charAt(position));
						} else {
							// そしてP0の番号を次はP1で質問する
							this.filter.erase((byte)(1 + position), false, qNumber.charAt(position));

						}

					}

				}

			}

			qaTimes++;
		}
		super.sheet.drop();
		if (this.filter != null) {
			this.filter.drop();
		}
	}

	private String choice(List<String> choicesNumberList, Play play) {

		int x = 2;

		x = x + new Random().nextInt(2);

		 // ランダムにList中央あたり～先頭を採用
		String question = choicesNumberList.get(Math.round(choicesNumberList.size() / x));

		if (this.filter != null) {
			List<String> filterList = super.getPossibleAndFirstNumberList(play, this.filter);
			filterList.retainAll(choicesNumberList);
			this.filter = null;
			String filterQuestion = "";
			if (filterList.size() != 0) {
//				filterQuestion  = filterList.get(filterList.size() - 1);
				filterQuestion  = filterList.get(0);
				return filterQuestion;
			}
		}

		return question;
	}

}
