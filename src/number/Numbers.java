package number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public class Numbers extends WeakHashMap<Num, Numbers> {

	public Numbers(Positions positions) {

		for (Entry<Num, Position> entry : positions.entrySet()) {
			Num myNum = null;
			Position poz = entry.getValue();
			for (Pos pos : poz.keySet()) {
				if (0 == pos.ordinal()) {
					myNum = entry.getValue().get(pos);
					break;
				}
			}
			if (myNum == null) continue; // 自身のpositionにこのNumがないなら次のNum
			// このNumがあるならNumをkeyに自身にput
			Num[] nums = positions.getDefNums().toArray(new Num[10]);
			Num[] yourKeys = positions.getNoOverLapNum(Pos.P1, new Num[]{myNum}, nums);
			this.put(myNum, new Numbers(Pos.P1, positions, yourKeys, myNum));

		}
	}

	public Numbers(Pos pos, Positions positions, Num[] myKeys, Num...parentKeys) {

		if (pos == null) return;

		Pos nextPos = positions.getNextPos(pos);
		for (Num myKey : myKeys) {

			if (nextPos == null) {
				this.put(myKey, null);
				continue;
			}

			List<Num> parentKeysAndMyKeysList = new ArrayList<>();
			parentKeysAndMyKeysList.addAll(Arrays.asList(parentKeys));
			parentKeysAndMyKeysList.add(positions.getSameNumFromPosition(nextPos, myKey));
			Num[] parentKeysAndMyKey = parentKeysAndMyKeysList.toArray(new Num[parentKeysAndMyKeysList.size()]);
			Num[] yourKeys = positions.getNoOverLapNum(nextPos, parentKeysAndMyKey, positions.getDefNums().toArray(new Num[10]));

			this.put(myKey, new Numbers(nextPos, positions, yourKeys, parentKeysAndMyKey));
		}
	}

	public List<String> getQuestion() {
		List<String> result = new ArrayList<>();

		for (Iterator<Entry<Num, Numbers>> ite = this.entrySet().iterator(); ite.hasNext();) {
			Entry<Num, Numbers> entry = ite.next();
			String myKey = entry.getKey().toString();
			Numbers childNmbs = entry.getValue();
			if (childNmbs != null) {
				StringBuffer sb = null;
				for (String nextQuestion : childNmbs.getQuestion()) {
					sb = new StringBuffer(myKey);
					sb.append(nextQuestion);
					result.add(sb.toString());
				}

			} else {
				result.add(myKey);
			}
		}
		return result;
	}

}
