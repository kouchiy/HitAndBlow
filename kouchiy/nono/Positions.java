package nono;

import java.util.HashMap;

import nono.constants.Num;
import nono.constants.Pos;
import nono.dto.Position;

public class Positions extends HashMap<Pos, Position> {

	public Positions(byte keta, int numCnt) {
		for (Pos pos : Pos.values()) {
			if (keta == 0) {
				return;
			}
			try {
				this.put(pos, new Position(pos, numCnt));
				keta--;
			} catch (Exception e) {
				System.out.println("public Positions(byte keta)" + e);
				;
			}
		}
	}

	public static HashMap<Pos, Boolean> getPoss(byte keta) {

		HashMap<Pos, Boolean> poss = new HashMap<>(keta);

		for (Pos pos : Pos.values()) {
			if (keta == 0) {
				return poss;
			}
			try {
				poss.put(pos, true);
				keta--;
			} catch (Exception e) {
				System.out
						.println("public static HashMap<Pos, Boolean> getPoss(byte keta)"
								+ e);
			}
		}
		return null;
	}

	public void removeNumAllPos(Num num) {

		for (Position pos : this.values()) {

			pos.removeNum(num);

		}

	}

}
