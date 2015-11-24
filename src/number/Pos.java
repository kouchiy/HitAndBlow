package number;

public enum Pos {

		P0
		,P1
		,P2
		,P3
		,P4
		,P5
		,P6
		,P7
		,P8
		,P9
		,PNULL
		;

		public static Pos get(int pCnt) {

			switch (pCnt) {
			case 0: return P0;
			case 1: return P1;
			case 2: return P2;
			case 3: return P3;
			case 4: return P4;
			case 5: return P5;
			case 6: return P6;
			case 7: return P7;
			case 8: return P8;
			case 9: return P9;
			default: return PNULL;
			}

		}

//		@Override
//		public String toString() {
//			return String.valueOf(this.ordinal());
//		}
}
