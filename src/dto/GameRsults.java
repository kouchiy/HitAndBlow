package dto;

import java.util.ArrayList;
import java.util.List;

public class GameRsults {

	public byte keta;
	public int numCnt;
	public int playTimes;
	public int qLimitTimes;
	public List<PlayList> playLists = new ArrayList<>();

	public GameRsults(byte keta,  int numCnt, int playTimes, int qLimitTimes) {
		this.keta = keta;
		this.playTimes = playTimes;
		this.numCnt = numCnt;
		this.qLimitTimes = qLimitTimes;
	}

}
