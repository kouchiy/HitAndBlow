
import java.util.HashMap;

import players.Player;
import players.PlayerInterface;
import dto.Play;
import dto.QA;

public class PlayerKY extends Player implements PlayerInterface {

	private Positions positions;
	private Numbers numbers;
	private HashMap<Character, Number> noNoMap;
	private HashMap<Character, Number> picUpMap;
	private MyQuestion myQ;

	private int stage;

	public PlayerKY(byte keta) {
		super(keta);
		this.positions = new Positions(keta);
		this.numbers = new Numbers();
		this.noNoMap = new HashMap<>();
		this.picUpMap = new HashMap<>();
		this.myQ = new MyQuestion(keta);
	}

	@Override
	public String run(Play play) throws Exception {

		final int STAGE1 = 1;
//		this.test1();

		// まずランダム番号で初期化
		String answer = super.getRandomNumbers(play);

		for (QA qa : play) {
			this.loadQa(qa);
		}

		// ステージ初期化
		stage = 1;

//		// エンドループ防止でとりあえず回数決める
//		int roop = 1;
//		for (; roop != 0;) {
//			 roop--;

//			System.out.print("ループ" + roop);

			Sorter.sort(stage, numbers);

			switch (stage) {
			case STAGE1: // ノバラ x 3
				this.noOverLap();

				break;

			default:
				break;
			}

			// TODO 保留　イメージは番号（候補）をPUできたらそれらを並び替えてMyQuestionを決定する
			if (this.picUpNumber(stage)) {

//				this.sortingQuestion();
			}

//			PlayerTest.testHistoryView(this.numbers, this.positions);


			PlayerTest.outMyQuestion(this.myQ);
			if (this.myQ.isSet) {
				answer =  this.myQ.getQuestion();
			}

//		}

		return answer;
	}

	private void noOverLap() {

		for (Number num : this.numbers.values()) {
			if (num.histories.size() == 0) {
				this.myQ.addNum(num.toString());
				if (this.myQ.isSet) return;
			}
		}
	}

	private boolean picUpNumber(int _stage) {

		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	private void test1() throws Exception {

		Number no1 = new Number('1');
		Number no1_ = new Number('1');

		if (no1.equals(no1_)) {
			System.out.println("Number equals test success");
//			return;
		}
		Position pos1 = new Position((byte) 1);
		Position pos1_ = new Position((byte) 1);

		if (pos1.equals(pos1_)) {
			System.out.println("Position equals test success");
			return;
		}
		throw new Exception();

	}

	private void loadQa(QA qa) {

		// 質問の番号の分
		byte pCnt = 0; // 位置数
		for (char num : qa.question.toCharArray()) {

			pCnt++;

			// 番号各々のhistoryへ記録 1スト1ボーを
			HistoryWriter.write(this.numbers.get(num), qa.answer, this.positions.get(pCnt));

		}


		if (Searcher.isNoNo(qa)) {
			this.addNoNoList(qa);
		}

		if (Searcher.isAllBall(qa)) {
			// 逆値をNoNoListへadd
			this.addNoNoListReverse(qa);

			// TODO qaListから過去のstrikeを走査し、strikeを決定する

		}

		if (Searcher.isAllCnt(qa)) {
			// 逆値をNoNoListへadd
			this.addNoNoListReverse(qa);


		}
		// TODO 自動生成されたメソッド・スタブ

	}

	private void addNoNoListReverse(QA qa) {

		try {
			for (Number no : this.numbers.values()) {

				for (char c : qa.question.toCharArray()) {

					Number num = new Number(c);
					if (no.equals(num)) {
						continue;
					}
					this.noNoMap.put(no.getCharValue(), no);
				}
			}

		} catch (Exception e) {
			System.out.println("private void addNoNoListReverse(QA qa)" + e);
		}

	}

	private void addNoNoList(QA qa) {

		for (char c : qa.question.toCharArray()) {
			try {
				this.noNoMap.put(new Character(c), new Number(c));
			} catch (Exception e) {
				System.out.println("private void addNoNoList(QA qa)" + e);
			}
		}

	}

}
