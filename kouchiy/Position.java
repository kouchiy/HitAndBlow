import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Position implements NoNoObject {


	// 位置
	private Pos pos;

	// 履歴
	public Histories histories;

	public Position(byte positionNo) throws Exception {
		this.pos = Pos.valueOf(positionNo);
		this.histories = new Histories();
	}

	@Override
	public boolean equals(Object obj) {
        if((obj instanceof Position) && (((Position)obj).pos == this.pos)){
            return true ;
        }
        else{
            return false ;
        }
	}

	@Override
	public int hashCode() {
		return Integer.parseInt("" + this.pos);
	}

	enum Pos {

		P1(new Numbers(), 1)
		,P2(new Numbers(), 2)
		,P3(new Numbers(), 3)
		,P4(new Numbers(), 4)
		,P5(new Numbers(), 5)
		,P6(new Numbers(), 6)
		,P7(new Numbers(), 7)
		,P8(new Numbers(), 8)
		,P9(new Numbers(), 9)
		,P0(new Numbers(), 0)
		;

		Numbers numbers;
		byte positionNo;

		private Pos(Numbers _numbers, int _positionNo) {
			this.numbers = _numbers;
			this.positionNo = (byte)_positionNo;
		};

		public static Pos valueOf(byte positionNo) throws Exception {

			switch (positionNo) {
			case 1: return P1;
			case 2: return P2;
			case 3: return P3;
			case 4: return P4;
			case 5: return P5;
			case 6: return P6;
			case 7: return P7;
			case 8: return P8;
			case 9: return P9;
			case 0: return P0;
			}
			throw new Exception();
		}

		//
		public static List<Pos> getPositions(byte keta) {

			List<Pos> posList = Arrays.asList(Pos.values());
			List<Pos> posListResult = new ArrayList<>(keta);
			int index = 0;
			for (;keta != 0; keta--) {
				Pos pos = posList.get(keta - 1);
				posListResult.add(index, pos);
				index++;
			}
			return posListResult;
		}

//		@Override
//		public String toString() {
//			return ((Byte)this.positionNo).toString();
//		}

		public void setSort() {
			// TODO
		}

	}
}
