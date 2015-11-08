import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import constants.Num;
import constants.Pos;




public class Number implements NoNoObject<Position> {

	/**
	 * 目標
	 */
	public boolean isTarget;

	public HashMap<Pos, Boolean> posWithBoolMap;

	/**
	 * 位置fix
	 */
	public NoNoObject<Position> fix;

	// 番号
	private Num num;

	// 履歴
	public Histories<NumHistory> histories;

	public Number(Num num, byte keta) throws Exception {
		this.num = num;
		this.histories = new Histories<NumHistory>();
		this.posWithBoolMap = Positions.getPoss(keta);
	}

	@Override
	public String toString() {
		return this.num.toString();
	}

	@Override
	public boolean equals(Object obj) {
        if((obj instanceof Num) && (((Num)obj) == this.num)){
            return true ;
        }
        else{
            return false ;
        }
	}

	@Override
	public int hashCode() {
		return Integer.parseInt("" + this.num);
	}

	@Override
	public boolean isMatch(Position pos) {
		if (pos == null || this.fix == null) {
			return false;
		}
		return fix.equals(pos);
	}

	public boolean isFix() {
		return this.fix != null;
	}

	public boolean isPossible() {

		List<Pos> possibilities = this.getPossiblities();

		if (possibilities.size() == 0) {
			return false;
		}

		return true;
	}

	public List<Pos> getPossiblities() {

		List<Pos> results = new ArrayList<>();
		for (Entry<Pos, Boolean> set : this.posWithBoolMap.entrySet()) {
			if (set.getValue()) {
				results.add(set.getKey());
			}
		}

		return results;
	}

	public void removeAllPos() {

		for (Entry<Pos, Boolean> set : this.posWithBoolMap.entrySet()) {
			set.setValue(false);
		}

	}

}
