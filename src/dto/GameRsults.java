package dto;

import java.util.ArrayList;
import java.util.List;

public class GameRsults {

	public byte keta;
	public int playTimes;
	public List<PlayList> playLists = new ArrayList<>();

	public GameRsults(byte keta, int playTimes) {
		this.keta = keta;
		this.playTimes = playTimes;

	}

}
