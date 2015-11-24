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
	public synchronized void addLast(QA qa) {
		super.addLast(qa);

		NoNo noNo = new NoNo(super.correctAns, this.getLast());

		boolean _finish = false;
		if (noNo.run()) {
			_finish = true;
			this.correctAns = super.correctAns;
		}

		// TODO test
//		this.playView1();
		this.playView2(_finish);

		this.finish = _finish;
	}

	private void playView2(boolean finish) {
		if (!finish) return;

		String first = "";
		if (this.size() == 1) {
			first = "       " + super.correctAns + "\n";
		}
		System.out.print(first);
		String qTimes = "" + String.format("%1$5d  ", this.size());
		System.out.println(qTimes  + this.getLast().question + "  " + this.getLast().answer);

	}

	private void playView1() {
		String first = "";
		if (this.size() == 1) {
			first = "       " + super.correctAns + "\n";
		}
		System.out.print(first);
		String qTimes = "" + String.format("%1$5d  ", this.size());
		System.out.println(qTimes  + this.getLast().question + "  " + this.getLast().answer);
	}



}
