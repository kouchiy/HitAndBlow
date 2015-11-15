package nono;

import java.util.HashMap;
import java.util.LinkedHashMap;

import nono.constants.Num;
import nono.dto.Number;

public class Numbers extends LinkedHashMap<Num, Number> {

	public Numbers(byte keta, int numCnt) {
		for (Num num : Num.values()) {
			try {
				if (num.isBig(numCnt)) {
					continue;
				}
				this.put(num, new Number(num, keta));
			} catch (Exception e) {
				System.out.println("public Numbers()" + e);
			}
		}

	}

	public static HashMap<Num, Boolean> getNums(int numCnt) {
		HashMap<Num, Boolean> nums = new HashMap<Num, Boolean>();

		for (Num num : Num.values()) {
			if (num.isBig(numCnt)) {
				continue;
			}
			nums.put(num, true);
		}
		return nums;
	}

	public void removeAllPos(Num num) {

		if (this.containsKey(num)) {

			this.get(num).removeAllPos();

		}
	}

	public int getAllNumTotal() {
		int total = 0;
		for (Number number : this.values()) {
			total += number.toInt();
		}
		return total;
	}

}
