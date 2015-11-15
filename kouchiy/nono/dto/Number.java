package nono.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import nono.Histories;
import nono.Positions;
import nono.constants.Num;
import nono.constants.Pos;




public class Number {

	public HashMap<Pos, Boolean> posWithBoolMap;

	/**
	 * 位置fix
	 */
	private Position pFix;

	private boolean fix;

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

	public int toInt() {
		return this.num.toInt();
	}

	@Override
	public boolean equals(Object obj) {
        if((obj instanceof Number) && ((((Number)obj)).num == this.num)){
            return true ;
        }
        else{
            return false ;
        }
	}

	@Override
	public int hashCode() {
		return this.toInt();
	}

	public boolean isMatch(Position pos) {
		if (pos == null || this.pFix == null) {
			return false;
		}
		return pFix.equals(pos);
	}


	public boolean isTarget() {

		// target定義

		// isPFixならfalse
		if (isPFix()) return false;

		// 判断材料がないならfalse
		if (isJudgable()) return false;

		return true;
	}

	private boolean isJudgable() {

		// isJudgable定義

		// 過去Cntがあればfalse
		for (History his : this.histories) {
			if (his.ball != 0 || his.strike != 0) {
				return true;
			}
		}

		return false;
	}

	public void setPFix(Position position) {
		this.set(position);
	}

	private void set(Position position) {
		this.pFix = position;
	}

	public Position getPFix() {
		return this.pFix;
	}

	public boolean isPFix() {
		return this.pFix != null;
	}

	public boolean isFix() {
		return this.fix;
	}

	public void setFix() {
		this.fix = true;
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

	public void removePos(Pos pos) {
		this.posWithBoolMap.put(pos, false);
	}


}
