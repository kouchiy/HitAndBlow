package service;

import java.util.Map;
import java.util.TreeMap;

public class NumberFactory {

	static Map<Double, Integer> numbers = new TreeMap<>();

	public static String getNumbers(byte keta, int numCnt) {

		if (numCnt < keta) {
			numCnt = keta;
		}
		return getNumbersPrivate(keta, numCnt);
	}

	public static String getNumbers(byte keta) {
		return getNumbersPrivate(keta, 10);
	}

	private static synchronized String getNumbersPrivate(byte keta, int numCnt) {
		String nums = "";
		lotorry(numCnt);
		for (int num : numbers.values()) {

//			if (numCnt < num) {
//				continue;
//			}
			nums += num;
			if (nums.length() == keta) {
				break;
			}
		}
		return nums;
	}

	private static void lotorry(int numCnt) {
		numbers.clear();
		for (No no : No.values()) {
			no.setSort();
			numbers.put(no.sort, (int) no.num);
			if (numCnt == numbers.size()) {
				return;
			}
		}
	}

	enum No {

		ONE(0.0, 1)
		,TWO(0.0, 2)
		,THREE(0.0, 3)
		,FOUR(0.0, 4)
		,FIVE(0.0, 5)
		,SIX(0.0, 6)
		,SEVEN(0.0, 7)
		,EIGHT(0.0, 8)
		,NINE(0.0, 9)
		,ZERO(0.0, 0)
		;

		double sort;
		byte num;

		private No (double _sort, int _num) {
			this.sort = _sort;
			this.num = (byte)_num;
		};

//		public int getInt() {
//			return this.num;
//		}

		@Override
		public String toString() {
			return ((Byte)this.num).toString();
		}

		public void setSort() {
			this.sort = Math.random();
		}

	}
}

