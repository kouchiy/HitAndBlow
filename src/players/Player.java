package players;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import service.NumberFactory;
import dto.Play;

public class Player {

	protected byte keta;
	public boolean firstQRandomMode;

	public Player(byte keta) {
		this.keta = keta;
	}

	// サブクラスがオーバーライドするメソッド
	public String run(Play play) throws Exception {

//		return this.getQuestion(play);
		return null;

	}

	protected String getRandomNumbers(Play play) {
		return NumberFactory.getNumbers(keta);
	}

	public static Player getInstance(byte keta, String classN) {

		Player player;

		Class<? extends Player> clazz;
		try {
			clazz = Class.forName(classN).asSubclass(Player.class);
			Class<?>[] types = {byte.class};
			@SuppressWarnings("unchecked")
			Constructor<Player> constructor = (Constructor<Player>) clazz.getConstructor(types);

			Object[] args = {keta};
			player = constructor.newInstance(args);

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}

		return player;
	}

}
