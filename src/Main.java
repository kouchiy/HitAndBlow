import java.util.ArrayList;
import java.util.List;

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

		byte keta = 3;
		int playTimes = 10;
		int qLimitTimes = 15;
		int numCnt = 8;
		AggResults aggResults = Aggregater.aggregate(testdata.makeTestData(), keta, playTimes, qLimitTimes, numCnt);
		System.out.println(aggResults.toString());
	}

	private static void test1() {

		byte keta = (byte) 3;
		int numCnt = 5;
		int qLimitTimes = 15;

		int playTimes = 500;

		List<String> players = new ArrayList<>();
		for (int i = 1; i != 0; i--) {
//			 players.add("players.DemoPlayer");
//			players.add("nono.PlayerKY");
//			players.add("player.KouchiY");
			players.add("player.KouchiY");
		}

		GameRsults gameResults = new ManagementService().game(keta, players, playTimes, qLimitTimes, numCnt);

		List<AggResults> aggResultsList = new ArrayList<>();
		for (PlayList playList : gameResults.playLists) {

			aggResultsList.add(Aggregater.aggregate(playList, keta, playTimes, qLimitTimes, numCnt));
			System.out.println(aggResultsList.get(aggResultsList.size() - 1));

		}

		for (AggResults point : aggResultsList) {
			System.out.println("   point: " + point.getPoint() + "  " + point.getPlayerName());
		}

	}

}
