import java.util.LinkedHashMap;

public class Numbers extends LinkedHashMap<Character, Number> {

//	public HashMap<Character, Number> map;

	public Numbers() {
		for (Number.No num : Number.No.values()) {
			try {
				this.put(num.num, new Number(num.num));
			} catch (Exception e) {
				System.out.println("public Numbers()" + e);;
			}
		}

	}



//	public synchronized HashMap<Character, Number> getNumbers(byte keta) {
//		String nums = "";
//		lotorry();
//		for (Number.No num : Number.No.values()) {
//			nums += num;
//			if (nums.length() == keta) {
//				break;
//			}
//		}
//		return nums;
//	}
//
//	private static void lotorry() {
//		map.clear();
//		for (Number.No no : Number.No.values()) {
//			no.setSort();
//			map.put(no.sort, no.toString());
//		}
//	}

//	enum No {
//
//		ONE(0.0, 1)
//		,TWO(0.0, 2)
//		,THREE(0.0, 3)
//		,FOUR(0.0, 4)
//		,FIVE(0.0, 5)
//		,SIX(0.0, 6)
//		,SEVEN(0.0, 7)
//		,EIGHT(0.0, 8)
//		,NINE(0.0, 9)
//		,ZERO(0.0, 0)
//		;
//
//		double sort;
//		byte num;
//
//		private No (double _sort, int _num) {
//			this.sort = _sort;
//			this.num = (byte)_num;
//		};
//
//		@Override
//		public String toString() {
//			return ((Byte)this.num).toString();
//		}
//
//		public void setSort() {
//			this.sort = Math.random();
//		}
//
//	}

}
