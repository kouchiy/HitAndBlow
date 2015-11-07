package players;
import players.Player;
import players.PlayerInterface;
import service.NumberFactory;
import dto.Play;


public class DemoPlayer extends Player implements PlayerInterface {

	public DemoPlayer(byte keta) {
		super(keta);
	}

	@Override
	public String run(Play play) throws Exception {
		return this.getRandomNumber(play);
	}

	protected String getRandomNumber(Play play) {
		return demo();
	}

	private String demo() {

		String demoNum;

		demoNum = NumberFactory.getNumbers(keta);

		return demoNum;
	}

}
