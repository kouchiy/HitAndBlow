package number;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

public class Positions extends HashMap<Num, Position> {

	private byte keta;
	protected List<Num> defNums = new ArrayList<>();
	private Numbers numbers;

	public Positions(byte keta, byte numCnt) {
		super(numCnt);
		this.keta = keta;
		for (int num = 0; num < numCnt; num++) {

			Num nums = Num.get(num);
			this.put(nums, new Position(keta, nums));
			defNums.add(Num.get(num));
		}
	}

	@Override
	public String toString() {

		String str = "";

		for (java.util.Map.Entry<Num, Position> entry : this.entrySet()) {
			str += entry.getKey() + "[ " + entry.getValue() + "]\n";
		}

		return str;
	}

	List<Num> getDefNums() {
		return defNums;
	}

	public String[] getNumbers() {
		if (this.numbers == null) {
			this.numbers = new Numbers(this);
		}
		return this.numbers.getQuestion().toArray(new String[0]);
	}

	Num[] getNoOverLapNum(Pos nextPos, Num[] myNums, Num...nums) {
		List<Num> numList = new ArrayList<>(this.size());

		for (Num num : this.keySet()) {

			boolean isOk = false;
			for (Num okNum : nums) {
				if (num.equals(okNum)) {
					isOk = true;
					break;
				}
			}
			if (!isOk) continue;

			boolean isOverLap = false;
			for (Num myNum : myNums) {
				if (num.equals(myNum)) {
					isOverLap = true;
					break;
				}
			}
			if (isOverLap) continue;

			Position poz = this.get(num);
			if (poz != null && poz.get(nextPos) != null) {
				numList.add(poz.get(nextPos));
			}
		}
		return numList.toArray(new Num[numList.size()]);
	}

	Pos getNextPos(Pos pos) {

		if (keta == pos.ordinal() + 1) return null;

		return Pos.get(pos.ordinal() + 1);
	}

	protected void drop(Pos pos, Integer...removeNums) {
		this.drop(pos, true, removeNums);
	}

	protected void drop(Pos pos, boolean mode, Integer...nums) {

		Integer[] removeNums = null;

		if (mode) {
			removeNums = nums;
		} else {
			List<Integer> removeNumList = new ArrayList<>(defNums.size() - nums.length);
			for (Num no : this.defNums) {
				boolean isAdd = false;

				for (int num : nums) {

					if (no.hashCode() == num) {
						isAdd = false;
						break;
					}
					isAdd = true;
				}
				if (isAdd) {
					removeNumList.add(no.hashCode());
				}
			}
			removeNums = removeNumList.toArray(new Integer[removeNumList.size()]);
		}

		for (int removeNum : removeNums) {
			Num num = Num.get(removeNum);
			Position poz = this.get(num);
			if (poz != null) {
				if (pos == Pos.PNULL) {
					for (Pos _pos : poz.keySet()) {
						poz.replace(_pos, null);
					}
				} else {
					poz.replace(pos, null);
				}
			}
		}

		System.gc();

	}

	Num getSameNumFromPosition(Pos pos, Num num) {

		Position poz = this.get(num);
		if (poz != null && poz.get(pos) != null) {
			return poz.get(pos);
		}
		return null;
	}

	public void drop(List<EnumSet<Pos>> erase) {

		int n = -1;
		for (EnumSet<Pos> pos : erase) {
			n++;
			Position poz = this.get(Num.get(n));
			if (poz != null) {
				for (Pos _pos : poz.keySet()) {
					if (pos.contains(Pos.PNULL) || pos.contains(_pos)) {
						poz.replace(_pos, null);
					}
				}

			}

		}

		System.gc();

	}
}
