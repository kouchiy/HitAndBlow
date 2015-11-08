import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import constants.Num;
import constants.Pos;


public class Position implements NoNoObject<Number> {

	/**
	 * 目標
	 */
	public boolean isTarget;

	public HashMap<Num, Boolean> numWithBoolMap;

	/**
	 * 番号fix
	 */
	public NoNoObject<Number> fix;

	// 位置
	private Pos pos;

	// 履歴
	public Histories<PositionsHistory> histories;

	public Position(Pos pos, int numCnt) throws Exception {
		this.pos = pos;
		this.histories = new Histories<PositionsHistory>();
		this.numWithBoolMap = Numbers.getNums(numCnt);
	}

	@Override
	public boolean equals(Object obj) {
        if((obj instanceof Position) && (((Position)obj).pos == this.pos)){
            return true ;
        }
        else{
            return false ;
        }
	}

	@Override
	public int hashCode() {
		return Integer.parseInt("" + this.pos);
	}


	@Override
	public boolean isMatch(Number obj) {
		if (obj == null || this.fix == null) {
			return false;
		}
		return fix.equals(obj);
	}

	public boolean isFix() {
		return this.fix != null;
	}

	public boolean isPossible() {

		List<Num> possibilities = this.getPossiblities();

		if (possibilities.size() == 0) {
			return false;
		}

		return true;
	}

	public List<Num> getPossiblities() {

		List<Num> results = new ArrayList<>();
		for (Entry<Num, Boolean> set : this.numWithBoolMap.entrySet()) {
			if (set.getValue()) {
				results.add(set.getKey());
			}
		}

		return results;
	}

	public void removeNum(Num num) {
		this.numWithBoolMap.put(num, false);
	}


}
