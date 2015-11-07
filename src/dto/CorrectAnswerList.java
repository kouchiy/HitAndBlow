package dto;

import java.util.ArrayList;

import service.NumberFactory;

public class CorrectAnswerList extends ArrayList<String> {

	public CorrectAnswerList(int playTimes, byte keta, String... demoCorrectAnswers) {

		for (String demoAns : demoCorrectAnswers) {
			this.add(demoAns);
		}

		if (this.size() != 0) {
			return;
		}

		for (; playTimes != 0; playTimes--) {
			String correctAns = NumberFactory.getNumbers(keta);
			this.add(correctAns);
		}
	}
}
