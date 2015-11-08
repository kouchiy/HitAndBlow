package dto;

public class Answer {

	public int strike;
	public int ball;

	public void addStrike() {
		this.strike++;
	}
	public void addBall() {
		this.ball++;
	}

	@Override
	public String toString() {

		String strikeCnt = this.formatCnt(this.strike, "❶", "❷", "❸", "❹", "❺");
		String ballCnt= this.formatCnt(this.ball, "①", "②", "③", "④",  "⑤");

		String SB = "" + this.strike+"S" + this.ball+"B" + " " +strikeCnt + ballCnt;

		return SB;
	}
	private String formatCnt(int cnt, String... outStr) {

		int reverseCnt = 5 - cnt;

		String result = "";

		for (String s : outStr) {
			if (reverseCnt >= 5) {
				s = "  ";
			}
			result += s;
			reverseCnt++;
		}


		return result;
	}

}
