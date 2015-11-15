package nono.dto;

import dto.QA;

public class History {

	public int strike = 0;
	public int ball = 0;
	public String question = "";
	public boolean isAllCnt;
	public boolean isNoNo;

	public History(QA qa) {
		this.question = qa.question;
		this.strike = qa.answer.strike;
		this.ball = qa.answer.ball;
	}

}
