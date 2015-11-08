import java.util.HashMap;

import players.Player;
import players.PlayerInterface;
import constants.Num;
import constants.Pos;
import dto.Play;
import dto.QA;

public class PlayerKY extends Player implements PlayerInterface {

	private Histories<QAHistory> qaHistories;
	private Positions positions;
	private Numbers numbers;
	private HashMap<Character, Number> noNoMap;
	private HashMap<Character, Number> picUpMap;
	private MyQuestion myQ;

	private int stage;
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
		this.noNoMap = new HashMap<>();
		this.picUpMap = new HashMap<>();
		this.myQ = new MyQuestion(keta);
	}

	@Override
	public String run(Play play) throws Exception {

		if (this.demo) {
			return super.getRandomNumbers(play);
		}

		final int STAGE1 = 1;
		final int STAGE2 = 2;

		// まずランダム番号で初期化
		String answer = super.getRandomNumbers(play);

		for (QA qa : play) {
			// 現時点の全てのQAをロード
			this.loadQA(qa);

			// 同時にクリティカルを探す
			this.search(qa);
		}

		// ステージ初期化
		stage = 1;

		// // エンドループ防止でとりあえず回数決める
		// int roop = 1;
		// for (; roop != 0;) {
		// roop--;

		// System.out.print("ループ" + roop);

		// 優先とか決めるはずだった　今は方向性変りそう
		Sorter.sort(stage, numbers);

		switch (stage) {
		case STAGE1: // ノバラ
			if (this.noOverLap()) {
				break;
			}
		case STAGE2: // ノバラfalseならnumFixを探す
			if (this.findNumFix()) {

			}

		default:
			break;
		}

		// TODO 保留　イメージは番号（候補）をPUできたらそれらを並び替えてMyQuestionを決定する
		if (this.picUpNumber(stage)) {

			// this.sortingQuestion();
		}

		// TODO
		// PlayerTest.testHistoryView(this.numbers, this.positions);

		if (this.myQ.isSet) {
			answer = this.myQ.getQuestion();
		}

//		System.out.println("質問 " + (play.size() + 1) + " 【 " + answer + " 】");

//		if (play.size() % 2 == 0)
			PlayerTest.testPossibilityView(this.numbers, this.positions);

		// }

		return answer;
	}

	private void search(QA qa) {

		if (Searcher.isNoNo(qa)) {
			// NoNo
			this.removeNoNo(qa.question);

			// TODO
		}

		if (Searcher.isAllBall(qa)) {
			// 逆値をNoNoListへadd
			this.removeNoNoReverse(qa.question);

			// TODO qaListから過去のstrikeを走査し、NPFixを探す

		}

		if (Searcher.isAllCnt(qa)) {
			// 逆値をNoNoListへadd
			this.removeNoNoReverse(qa.question);

		}
	}

	/**
	 * @return numFixなければfalse
	 */
	private boolean findNumFix() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	/**
	 * @return ノバラ終わってもMyQが完成しないならfalse
	 */
	private boolean noOverLap() {

		for (Number num : this.numbers.values()) {
			if (num.histories.size() == 0) {
				this.myQ.addNum(num.toString());
				if (this.myQ.isSet)
					return true;
			}
		}
		return false;
	}

	private boolean picUpNumber(int _stage) {

		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	private void loadQA(QA qa) {

		// 同じhistoryは記録しない
		if (Historia.isSameHistory(qa, this.qaHistories)) {
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

	private void removeNoNoReverse(String question) {

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

		this.removeNoNo(reverse);

	}

	private void removeNoNo(String question) {

		for (char numChar : question.toCharArray()) {

			Num num = Num.get(numChar);

			this.numbers.removeAllPos(num);
			this.positions.removeNumAllPos(num);

		}

	}

}
