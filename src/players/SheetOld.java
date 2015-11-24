package players;

import number.Positions;


public class SheetOld extends Positions {

	public SheetOld(byte numCnt) {
		super((byte)1, numCnt);
	}
//
//	public void drop(String...removeNums) {
//
//		for (String num : removeNums) {
//			this.drop(num.toCharArray());
//		}
//	}
//
//	public void drop(char...removeNums) {
//
//		for (char num : removeNums) {
//			this.drop(Character.getNumericValue(num));
//		}
//	}
//
//
//	public void drop(byte position, String...removeNums) {
//
//		for (String num : removeNums) {
//			this.drop(position, num.toCharArray());
//		}
//	}
//
//	public void drop(byte position, char...removeNums) {
//
//		for (char num : removeNums) {
//			this.drop(position, Character.getNumericValue(num));
//
//		}
//	}
//
//	public void drop(boolean mode, String...nums) {
//
//		List<Integer> list = new ArrayList<>();
//		for (String num : nums) {
//			list.add(Integer.parseInt(num));
//		}
//		super.drop(Pos.PNULL, list.toArray(new Integer[list.size()]));
//	}
//
//	public void drop(boolean mode, char...nums) {
//
//		List<Integer> list = new ArrayList<>();
//		for (char num : nums) {
//			list.add(Character.getNumericValue(num));
//		}
//		super.drop(Pos.PNULL, mode, list.toArray(new Integer[list.size()]));
//	}
//
//
//	/**
//	 * 全てのpositionのremoveNumsを消します
//	 * @param removeNums
//	 */
//	public void drop(Integer...removeNums) {
//		super.drop(Pos.PNULL, true, removeNums);
//	}
//
//	/**
//	 * 指定したpositionのremoveNumsを消します
//	 * @param position
//	 * @param removeNums
//	 */
//	public void drop(byte position, Integer...removeNums) {
//		super.drop(Pos.get(position), true, removeNums);
//	}
//
//	/**
//	 * mode = true
//	 *   全てのpositionのnumberの番号を消します
//	 *
//	 * mode = false
//	 *   number以外の番号の全てのpositionから消します
//	 * @param mode
//	 * @param number
//	 */
//	public void drop(boolean mode, String number) {
//		List<Integer> list = new ArrayList<>();
//		for (char num : number.toCharArray()) {
//			list.add(Character.getNumericValue(num));
//		}
//		this.drop(Pos.PNULL, mode, list.toArray(new Integer[list.size()]));
//	}
//
//	public void drop(byte position, boolean mode, Integer...nums) {
//		super.drop(Pos.get(position), mode, nums);
//	}




}
