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
		int playTimes = 1;
		CorrectAnswerList correctAnswerList = new CorrectAnswerList(playTimes,
				keta, "940");

		String className = "PlayerKY";

		Player player = Player.getInstance(keta, className);

		PlayList playList = new PlayList(className, correctAnswerList);
		try {
			for (Play play : playList.plays) {
				play.addLast(new QA(player.run(play))); // play.addLast(new QA("123")); // ノバラ1 : nono : nonoAdd 1 2 3
				play.addLast(new QA(player.run(play))); // play.addLast(new QA("456")); // ノバラ2 : 1b   : 目標候補 get
				play.addLast(new QA(player.run(play)));  //play.addLast(new QA("789")); // ノバラ3 : 1b   : 目標候補 get, 0fix
				play.addLast(new QA("143")); // 目標候補から1つPU Pずらし target P2 and 4: 1s   : P2fix and 4fix = 4P2fix = nonoAdd 8 , nonoAdd 5 6
				// この時点で、*4*
				// 候補 0fix, 7, 9
				// 正解候補 047, 049, 740, 749, 940, 947
				// if (他fixあり) else ●
				// 0fix の 考察[P]
				// if (履歴あり) ●
				// else 正解候補しぼり
				// 正解候補 047, 049, 740, xxx, 940, xxx
				// fix以外の 7 9 考察[survive]
				// 履歴 7 1, 9 1
				// 7 の履歴 789 1b 7P1x
                // 正解候補 047, 049, xxx, xxx, 940, xxx
				// 9 の履歴 789 1b 9P3x
				// 正解候補 047, xxx, xxx, xxx, 940, xxx

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
		GameRsults gameResults = new GameRsults(keta, playTimes);
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

		PlayList testdata = new PlayList("", new CorrectAnswerList(0, (byte) 1)) {
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
		int qLimitTimes = 5;

		int playTimes = 3;

		List<String> players = new ArrayList<>();
		for (int i = 1; i != 0; i--) {
			 players.add("players.DemoPlayer");
			players.add("PlayerKY");
		}

		GameRsults gameResults = new ManagementService().game(keta, players, playTimes, qLimitTimes);

		List<AggResults> aggResultsList = new ArrayList<>();
		for (PlayList playList : gameResults.playLists) {

			aggResultsList.add(Aggregater.aggregate(playList));
			System.out.println(aggResultsList.get(aggResultsList.size() - 1));

		}

	}

}
