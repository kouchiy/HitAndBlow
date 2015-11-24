package players;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import number.Num;
import number.Pos;
import number.Positions;

public class Sheet extends Positions {

	public List<EnumSet<Pos>> erase = new ArrayList<>();

	public Sheet(byte keta, byte numCnt) {
		super(keta, numCnt);
		erase = new ArrayList<>(numCnt);
		for (int i = numCnt; i != 0; i--) {
			this.erase.add(EnumSet.noneOf(Pos.class));
		}
	}

	public void drop() {
		super.drop(erase);
	}

	public void erase(char...removeNums) {

		for (char num : removeNums) {

			this.eraseAdd(Pos.PNULL, Character.getNumericValue(num));
		}
	}

	public void erase(byte position, char...removeNums) {

		for (char num : removeNums) {

			this.eraseAdd(Pos.get(position), Character.getNumericValue(num));
		}
	}

	public void erase(boolean mode, char...nums) {
		this.erase(Pos.PNULL, mode, nums);
	}

	public void erase(byte position, boolean mode, char...nums) {
		this.erase(Pos.get(position), mode, nums);
	}

	private void erase(Pos pos, boolean mode, char...nums) {

		List<Integer> numList = new ArrayList<>();
		for (char num : nums) {
			numList.add(Character.getNumericValue(num));
		}
		List<Integer> removeNumList = this.makeRemoveNumList(mode, numList);

		for (int num : removeNumList) {

			this.eraseAdd(pos, num);
		}

	}

	private List<Integer> makeRemoveNumList(boolean mode, List<Integer> numList) {

		List<Integer> removeNumList = null;

		if (mode) return numList;

		removeNumList = new ArrayList<>(super.defNums.size() - numList.size());
		for (Num no : super.defNums) {
			boolean isAdd = false;

			for (int num : numList) {

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

		return removeNumList;
	}

	private void eraseAdd(Pos pos, int num) {

		this.erase.get(num).add(pos);
	}


}
