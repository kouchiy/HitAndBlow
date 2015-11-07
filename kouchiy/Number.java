


public class Number implements NoNoObject {

	// 決定 *優先度とは別物 TODO
	public boolean fix;

	// 位置 *使いどころがまだわからない TODO
	public Positions positions;

	// 番号
	private No no;

	// 履歴
	public Histories histories;

	public Number(char no) throws Exception {
		this.no = No.valueOf(no);
		this.histories = new Histories();
	}

	@Override
	public String toString() {
		return this.no.toString();
	}

	@Override
	public boolean equals(Object obj) {
        if((obj instanceof Number) && (((Number)obj).no == this.no)){
            return true ;
        }
        else{
            return false ;
        }
	}

	@Override
	public int hashCode() {
		return Integer.parseInt("" + this.no);
	}


	enum No {

		ONE(0.0, '1')
		,TWO(0.0, '2')
		,THREE(0.0, '3')
		,FOUR(0.0, '4')
		,FIVE(0.0, '5')
		,SIX(0.0, '6')
		,SEVEN(0.0, '7')
		,EIGHT(0.0, '8')
		,NINE(0.0, '9')
		,ZERO(0.0, '0')
		;

		double sort;
		char num;

		private No (double _sort, char _num) {
			this.sort = _sort;
			this.num = _num;
		};

		public static No valueOf(char no) throws Exception {

			switch (no) {
			case '0': return ZERO;
			case '1': return ONE;
			case '2': return TWO;
			case '3': return THREE;
			case '4': return FOUR;
			case '5': return FIVE;
			case '6': return SIX;
			case '7': return SEVEN;
			case '8': return EIGHT;
			case '9': return NINE;
			}
			throw new Exception();
		}

		@Override
		public String toString() {
			return String.valueOf(this.num);
		}

		public void setSort(double _sort) {
			this.sort = _sort;
		}
}


	public Character getCharValue() {
		return this.no.num;
	}

}
