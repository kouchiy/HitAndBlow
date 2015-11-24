package number;

public class Num {

	private int num;

	private Num(int num) {
		this.num = num;
	}

	public static Num getNew(Num nums) {
		return get(nums.num);
	}

	public static Num get(int i) {

		switch (i) {
		case 0: return new Num(0);
		case 1: return new Num(1);
		case 2: return new Num(2);
		case 3: return new Num(3);
		case 4: return new Num(4);
		case 5: return new Num(5);
		case 6: return new Num(6);
		case 7: return new Num(7);
		case 8: return new Num(8);
		case 9: return new Num(9);
		default: return null;
		}

	}

	@Override
	public String toString() {
		return String.valueOf(this.num);
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Num) && this.num == ((Num)obj).num;
	}

	@Override
	public int hashCode() {
		return this.num;
	}

}
