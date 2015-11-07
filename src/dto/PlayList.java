package dto;

import java.util.ArrayList;
import java.util.List;

public class PlayList {

	public String playerName;
	public List<Play> plays;

	public PlayList(String playerName, CorrectAnswerList correctAnswerList) {
		this.playerName = playerName;
		if (correctAnswerList == null) {
			throw new IllegalArgumentException("正解リストを作成して渡して下さい。");
		}
		this.plays = new ArrayList<>(correctAnswerList.size());
		for (String correctAnswer: correctAnswerList) {
			this.plays.add(new Play(correctAnswer));
		}
	}

	public PlayList makeTestData() {
		return this;
	}
}
