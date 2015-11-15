package nono.constants;

public enum Num {

	ONE
	,TWO
	,THREE
	,FOUR
	,FIVE
	,SIX
	,SEVEN
	,EIGHT
	,NINE
	,ZERO
	;

	public static Num get(char numChar) {

		switch (numChar) {
		case '1': return ONE;
		case '2': return TWO;
		case '3': return THREE;
		case '4': return FOUR;
		case '5': return FIVE;
		case '6': return SIX;
		case '7': return SEVEN;
		case '8': return EIGHT;
		case '9': return NINE;
		case '0': return ZERO;
		default: return ZERO;
		}

	}

	@Override
	public String toString() {
		int num = this.ordinal() + 1;
		if (num > 9) {
			return String.valueOf(0);
		}
		return String.valueOf(num);
	}


	public int toInt() {
		int num = this.ordinal() + 1;
		if (num > 9) {
			return 0;
		}
		return num;
	}

	public boolean is(Num num) {
		return this == num;
	}

	public boolean isBig(int numCnt) {

		int num = this.ordinal() + 1;
		if (num > numCnt) {
			return true;
		}

		return false;
	}

}
