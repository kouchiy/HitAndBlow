package dto;

import service.NoNo;


public class Play extends PlayNoNo {

	/**
	 * 残念！！隠蔽されてます！！
	 */
	public String correctAns = "";

	public boolean finish;

	public Play(String correctAns) {
		super.correctAns = correctAns;
		for (int i = correctAns.length(); i != 0; i--) {
			this.correctAns += "*";
		}
	}

	@Override
	public void addLast(QA qa) {
		super.addLast(qa);

		NoNo noNo = new NoNo(super.correctAns, this.getLast());

		if (noNo.run()) {
			finish = true;
			this.correctAns = super.correctAns;
		}
	}



}
