import java.util.ArrayList;
import java.util.List;

import players.Player;
import service.ManagementService;
import service.report.Aggregater;
import service.report.dto.AggResults;
import dto.CorrectAnswerList;
import dto.GameRsults;
import dto.Play;
import dto.PlayList;
import dto.QA;

public class Main {

	public static void main(String[] args) {

		 test1();
//		 test2();
//		test3();

	}

	private static void test3() {

		byte keta = (byte) 3;
		int numCnt = 10;
		int playTimes = 1;
		CorrectAnswerList correctAnswerList = new CorrectAnswerList(playTimes,
				keta, numCnt, "839");

		String className = "PlayerKY";


		PlayList playList = new PlayList(className, correctAnswerList);
		try {
			for (Play play : playList.plays) {
				Player player = Player.getInstance(keta, numCnt, className);

				// test
//				play.addLast(new QA(player.run(play)));
//				play.addLast(new QA(player.run(play)));
//				play.addLast(new QA(player.run(play)));
				 play.addLast(new QA("012")); // ノバラ1 : nono : nonoAdd 0 1 2
				 play.addLast(new QA("345")); // ノバラ2 : 1b   : 目標候補 get
				play.addLast(new QA("678")); // ノバラ3 : 1b   : 目標候補 get, 9fix
//				play.addLast(new QA("032")); // 目標候補から1つPU Pずらし target P1 and 3: 1s   : P1fix and 3fix = 3P1fix = nonoAdd 7 , nonoAdd 4 5
				// この時点で、*3*
				// 候補 9fix, 6, 8
				// 正解候補 936, 938, 639, 638, 839, 836
				// if (他fixあり) else ●
				// 9fix の 考察[P]
				// if (履歴あり) ●
				// else 正解候補しぼり
				// 正解候補 936, 938, 639, xxx, 839, xxx
				// fix以外の 6 8 考察[survive]
				// 履歴 6 1, 8 1
				// 6 の履歴 678 1b 6P0x
                // 正解候補 936, 938, xxx, xxx, 839, xxx
				// 8 の履歴 678 1b 8P2x
				// 正解候補 936, xxx, xxx, xxx, 839, xxx

				int testCnt = 1;
				while (true) {
					testCnt--;
					QA qa = new QA(player.run(play));
					play.addLast(qa);
					if (play.finish || testCnt == 0) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		GameRsults gameResults = new GameRsults(keta, numCnt, playTimes);
		gameResults.playLists.add(playList);

		List<AggResults> aggResultsList = new ArrayList<>();
		for (PlayList playL : gameResults.playLists) {

			aggResultsList.add(Aggregater.aggregate(playL));
			System.out.println(aggResultsList.get(aggResultsList.size() - 1));
		}

	}

	private static void test2() {

		int[][] array = { { 15, 2 }, { 1, 1 }, { 9, 5 }, { 12, 1 }, { 13, 3 },
				{ 1, 1 }, };

		PlayList testdata = new PlayList("", new CorrectAnswerList(0, (byte) 1, 1)) {
			@Override
			public PlayList makeTestData() {

				for (int[] row : array) {
					for (int p = row[1]; p != 0; p--) {
						Play play = new Play("");
						for (int q = row[0]; q != 0; q--) {
							play.addFirst(new QA(""));
						}
						this.plays.add(play);
					}
				}

				return this;
			}

		};

		AggResults aggResults = Aggregater.aggregate(testdata.makeTestData());
		System.out.println(aggResults.toString());
	}

	private static void test1() {

		byte keta = (byte) 3;
		int numCnt = 10;
		int qLimitTimes = 30;

		int playTimes = 10;

		List<String> players = new ArrayList<>();
		for (int i = 1; i != 0; i--) {
//			 players.add("players.DemoPlayer");
			players.add("nono.PlayerKY");
		}

		GameRsults gameResults = new ManagementService().game(keta, players, playTimes, qLimitTimes, numCnt);

		List<AggResults> aggResultsList = new ArrayList<>();
		for (PlayList playList : gameResults.playLists) {

			aggResultsList.add(Aggregater.aggregate(playList));
			System.out.println(aggResultsList.get(aggResultsList.size() - 1));

		}

	}

}
