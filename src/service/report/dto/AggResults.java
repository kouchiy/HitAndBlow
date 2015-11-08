package service.report.dto;

public class AggResults {

	private String playerName;
	private int finishTimesTotal;
	private int playTimesTotal;
	private int questionTimesTotal;
	private double questionTimesAvg;
	private double questionTimesMedian;
	private double questionTimesDevAvg;
	private int[][] questionTimesMode;

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("\n");
		sb.append("  プレイヤー: " + this.playerName + "\n");
		sb.append("    プレイ数: " + this.playTimesTotal + "\n");
		sb.append("      正解数: " + this.finishTimesTotal + "\n");
		sb.append("質問回数合計: " + this.questionTimesTotal + "\n");
		sb.append("平均質問回数: " + this.questionTimesAvg + "\n");
		sb.append("質問数中央値: " + this.questionTimesMedian + "\n");
		sb.append("質問標準偏差: " + this.questionTimesDevAvg + "\n");
		sb.append("質問数最頻値: " + this.questionTimesMode[0][0] + " 回  / " + this.questionTimesMode[0][1] + " play\n"
				+ "              " + this.questionTimesMode[1][0] + " 回  / " + this.questionTimesMode[1][1] + " play\n"
				+ "              " + this.questionTimesMode[2][0] + " 回  / " + this.questionTimesMode[2][1] + " play\n" + "\n");


		return sb.toString();
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public int getPlayTimesTotal() {
		return playTimesTotal;
	}
	public void setPlayTimesTotal(int playTimesTotal) {
		this.playTimesTotal = playTimesTotal;
	}
	public int getFinishTimesTotal() {
		return finishTimesTotal;
	}
	public void setFinishTimesTotal(int finishTimesTotal) {
		this.finishTimesTotal = finishTimesTotal;
	}
	public int getQuestionTimesTotal() {
		return questionTimesTotal;
	}
	public void setQuestionTimesTotal(int questionTimesTotal) {
		this.questionTimesTotal = questionTimesTotal;
	}
	public double getQuestionTimesAvg() {
		return questionTimesAvg;
	}
	public void setQuestionTimesAvg(double qTimesAvg) {
		this.questionTimesAvg = qTimesAvg;
	}
	public double getQuestionTimesMedian() {
		return questionTimesMedian;
	}
	public void setQuestionTimesMedian(double questionTimesMedian) {
		this.questionTimesMedian = questionTimesMedian;
	}
	public double getQuestionTimesDevAvg() {
		return questionTimesDevAvg;
	}
	public void setQuestionTimesDevAvg(double questionTimesDevAvg) {
		this.questionTimesDevAvg = questionTimesDevAvg;
	}
	public int[][] getQuestionTimesMode() {
		return questionTimesMode;
	}
	public void setQuestionTimesMode(int[][] questionTimesMode) {
		this.questionTimesMode = questionTimesMode;
	}



}
