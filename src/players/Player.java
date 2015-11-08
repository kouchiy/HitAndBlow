package players;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import service.NumberFactory;
import dto.Play;

public class Player {

	protected byte keta;
	protected int numCnt;
	public boolean firstQRandomMode;

//	public Player(byte keta) {
//		this.keta = keta;
//		this.numCnt = 10;
//	}

	public Player(byte keta, int numCnt) {
		if (numCnt < keta) {
			numCnt = keta;
		} else if (numCnt > 10) {
			numCnt = 10;
		}
		this.keta = keta;
		this.numCnt = numCnt;
	}

	// サブクラスがオーバーライドするメソッド
	public String run(Play play) throws Exception {
		System.out.println("PlayerSuperClass  public String run(Play play) error");
		throw new Exception();
	}

	protected String getRandomNumbers(Play play) {
		return NumberFactory.getNumbers(this.keta, this.numCnt);
	}

	public static Player getInstance(byte keta, int numCnt, String classN) {

		Player player;

		Class<? extends Player> clazz;
		try {
			clazz = Class.forName(classN).asSubclass(Player.class);
			Class<?>[] types = {byte.class, int.class};
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
