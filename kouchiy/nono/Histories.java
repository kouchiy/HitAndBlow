package nono;

import java.util.ArrayList;
import java.util.List;

import nono.dto.History;

public class Histories<E> extends ArrayList<History> {

	public String[][] getQuestionsNumArray() {

		List<String[]> outer = new  ArrayList<>();
		for (History his : this) {
			List<String> inner = new  ArrayList<>();
			for (char num : his.question.toCharArray()) {
				inner.add(String.valueOf(num));
			}
			outer.add(inner.toArray(new String[inner.size()]));
		}
		return outer.toArray(new String[outer.size()][]);
	}

//	@Override
//	public boolean add(History e) {
//
//		return super.add(e);
//	}

}
