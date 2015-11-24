package players;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import number.Positions;
import service.NumberFactory;
import dto.Play;
import dto.QA;

public class Player {

	protected byte keta;
	protected byte numCnt;
	protected Sheet sheet;
	public boolean firstQRandomMode;

	public Player(byte keta, byte numCnt) {
		if (numCnt < keta) {
			numCnt = keta;
		} else if (numCnt > 10) {
			numCnt = 10;
		}
		this.keta = keta;
		this.numCnt = numCnt;

		sheet = new Sheet(keta, numCnt);
	}

	// サブクラスがオーバーライドするメソッド
	public String run(Play play) throws Exception {
		System.out.println("PlayerSuperClass  public String run(Play play) error");
		throw new Exception();
	}

	protected String getRandomNumbers(Play play) {
		return NumberFactory.getNumbers(this.keta, this.numCnt);
	}

//	protected List<String> getPossibleAndFirstNumberList(Play play) {
//
//		List<String> numbers = new ArrayList<>();
//
//		for (String number : this.sheet.getNumbers()) {
//
//			boolean isOverLap = false;
//			for (QA qa : play) {
//				String question = qa.question;
//				if (number.equals(question)) {
//					isOverLap = true;
//					break;
//				}
//			}
//			if (isOverLap) {
//				continue;
//			} else {
//				numbers.add(number);
//			}
//		}
//
//		return numbers;
//	}

	protected final List<String> getPossibleAndFirstNumberList(Play play, Positions argPositions) {

		Positions positions = argPositions;
		if (positions == null) {
			positions = this.sheet;
		}

		List<String> numbers = new ArrayList<>();

		for (String number : positions.getNumbers()) {

			boolean isOverLap = false;
			for (QA qa : play) {
				String question = qa.question;
				if (number.equals(question)) {
					isOverLap = true;
					break;
				}
			}
			if (isOverLap) {
				continue;
			} else {
				numbers.add(number);
			}
		}

		return numbers;
	}

	public final static Player getInstance(byte keta, byte numCnt, String classN) {

		Player player;

		Class<? extends Player> clazz;
		try {
			clazz = Class.forName(classN).asSubclass(Player.class);
			Class<?>[] types = {byte.class, byte.class};
			@SuppressWarnings("unchecked")
			Constructor<Player> constructor = (Constructor<Player>) clazz.getConstructor(types);

			if (numCnt < keta) {
				numCnt = keta;
			}
			Object[] args = {keta, numCnt};
			player = constructor.newInstance(args);

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}

		return player;
	}

}
