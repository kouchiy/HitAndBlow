package service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import players.Player;
import dto.CorrectAnswerList;
import dto.GameRsults;
import dto.HabResultDto;
import dto.Play;
import dto.PlayList;
import dto.QA;
import dto.QuestionDto;

/**
 * 運営が実装するクラス プレイヤーサービスが返却した、クエスチョンをもとに 1S1Bの結果を返却する
 */
public class ManagementService {

	/**
	 * 引数のクエスチョンDtoをもとに1S1Bの結果を返却する
	 *
	 * @param questionDtoクエスチョンDto
	 * @return 1S1Bの結果Dto
	 */
	public HabResultDto createHabResult(QuestionDto questionDto) {

		return null;
	}

	public GameRsults game(byte argKeta, List<String> argPlayerNames, int... optionalArgs) {

		int defultArgPlayTimes = 1;
		int defultArgQLmitTimes = 100;

		if (optionalArgs.length == 2) {
			defultArgPlayTimes = optionalArgs[0];
			defultArgQLmitTimes = optionalArgs[1];
		} else if (optionalArgs.length == 1) {
			defultArgPlayTimes = optionalArgs[0];
		}

		return this.gamePrivate(argKeta, argPlayerNames, defultArgPlayTimes, defultArgQLmitTimes);
	}

	private GameRsults gamePrivate(byte argKeta, List<String> argPlayerNames,
			int argPlayTimes, int argQLimitTimes) {

		System.out.printf("%d桁 player=%d プレイ数=%d 質限=%d \n\n",
				argKeta, argPlayerNames.size(), argPlayTimes,
				argQLimitTimes);

		GameRsults gameResult = new GameRsults(argKeta, argPlayTimes);

		ExecutorService threadPool = Executors.newFixedThreadPool(argPlayerNames
				.size());

		Collection<Callable<Void>> processes = new LinkedList<>();

		CorrectAnswerList correctAnswerList = new CorrectAnswerList(
				argPlayTimes, argKeta);

		for (String playerName0 : argPlayerNames) {

			final String playerName = playerName0;

			PlayList playList = new PlayList(playerName, correctAnswerList);

			processes.add(new Callable<Void>() {
				@Override
				public Void call() {

					int playtime = 0; // TODO test
					for (Play play : playList.plays) {

						// playの度にplayerインスタンス生成
						Player player = Player.getInstance(argKeta, playerName);

						System.out.println("◆ player : " + playerName + " play : " + ++playtime);

						boolean isFirstQRandomMode = player.firstQRandomMode;

						if (isFirstQRandomMode) {
							Player demoPlayer = Player.getInstance(argKeta, "DemoPlayer");

							while (true) {

								QA qa;
								try {
									qa = new QA(demoPlayer.run(play));
								} catch (Exception e) {
									System.out.println("public Void call()" + e.getMessage());
									break;
								}
								play.addLast(qa);

								if (play.finish) {
									play.pop();
									continue;
								} else
									break;
							}
						}

						while (true) {
							QA qa;
							try {
								qa = new QA(player.run(play));
							} catch (Exception e) {
								System.out.println("public Void call()" + e.getStackTrace());
								break;
							}
							play.addLast(qa);

							if (play.finish
									|| (isFirstQRandomMode && play.size() >= argQLimitTimes + 1)
									|| (!isFirstQRandomMode && play.size() >= argQLimitTimes)) {
								// // test
								//
								if (isFirstQRandomMode) {
									// System.out.println(play.size() + " "
									// + play.peekLast());
									play.pollFirst();
								}
								//
								// System.out.println(play.size() + " "
								// + play.peekLast());

								break;
							}
						}
					}
					return null;
				}
			});

			gameResult.playLists.add(playList);
		}

		try {
			threadPool.invokeAll(processes);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			threadPool.shutdown();
		}
		return gameResult;

	}
}
