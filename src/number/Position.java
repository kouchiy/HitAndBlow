package number;

import java.util.EnumMap;

public class Position extends EnumMap<Pos, Num> {

	public Position(byte keta, Num nums) {
		super(Pos.class);

		for (int posNum = 0; posNum < keta; posNum++) {
			this.put(Pos.get(posNum), Num.getNew(nums));
		}

	}

	public boolean isFullNum() {

		for (Num num : this.values()) {
			if (num == null) return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String str = "";
		for (java.util.Map.Entry<Pos, Num> entry : this.entrySet()) {

			if (entry.getValue() == null) {
				str += "__ ";
			} else {
				str += entry.getKey() + " ";
			}

		}
		return str;
	}

}
