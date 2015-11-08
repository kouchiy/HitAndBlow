package players;
import dto.Play;


public class DemoPlayer extends Player implements PlayerInterface {

//	public DemoPlayer(byte keta) {
//		super(keta, 10);
//	}

	public DemoPlayer(byte keta, int numCnt) {
		super(keta, numCnt);
	}

	@Override
	public String run(Play play) throws Exception {
		return this.getRandomNumber(play);
	}

	protected String getRandomNumber(Play play) {
		return super.getRandomNumbers(play);
	}

//	private String demo() {
//
//		String demoNum;
//
//		demoNum = NumberFactory.getNumbers(this.keta, this.numCnt);
//
//		return demoNum;
//	}

}
