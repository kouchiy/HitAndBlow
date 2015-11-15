package nono.dto;

import dto.QA;


public class NumHistory extends History {

	public Position position;
	public double point = 0.0;

	public NumHistory(QA qa, Position position) {
		super(qa);
		this.position = position;
	}

}
