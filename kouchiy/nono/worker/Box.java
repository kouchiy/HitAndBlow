package nono.worker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import nono.dto.Number;

public class Box {

	private Collection<Number> numbers;
	private Number[][] box;
	private boolean out1st = false;
	private Box inBox;
	private byte keta;
	private Deque<Number> numDeque;
	private Number[] blackBox;

	public Box(byte keta, Collection<Number> numbers) {
		this.keta = keta;
		this.numbers = numbers;
//		this.qaHistories = qaHistories;

//		// 引数桁×桁のサイズで初期化
//		this.box = new Number[keta][keta];

		// 引数のnumbersを入れる
		this.in(keta, this.numbers);

	}

	private void in(byte keta, Collection<Number> numbers) {

		if (keta == 0) {
			return;
		}

		this.numDeque = new LinkedList<>(numbers);

		List<Number> out1st = new ArrayList<>(keta);

		// this.box[0]つまり一列だけ先ず作成
		boolean roopBreak = false;
		boolean roopContinue = true;
		for (int roop = 0; roopContinue; roop++) {
			roopBreak = true;
			roopContinue =false;

			if (this.numDeque.element().isPossible()) {
				// possible true
				roopContinue = out1st.size() < keta;
				roopBreak = this.numDeque.size() + keta < roop;

				if (this.numDeque.element().isTarget()) {
					// target true
					out1st.add(this.numDeque.poll());

				} else {
					// target false

					Number src = this.numDeque.poll();
					// あとまわし
					this.numDeque.addLast(src);
					roopContinue = true;
				}

			} else {
				// possible false
//				this.blackBox.
			}

			if (roopBreak) break;
		}

		if (out1st.size() >= keta) {
			// 引数桁×桁のサイズで初期化
			this.box = new Number[keta][keta];
			this.box[0] = (Number[]) out1st.toArray(new Number[keta]);
			this.out1st = true;
			this.inBox = new Box(--keta, this.numDeque);
		} else {
			return;
		}

	}

	public Number[] get(int index) {

		if (index >= this.box.length) {
			return null;
		}

		return this.out(index);

	}

	private Number[] out(int outer) {

		if (outer == 0 && this.out1st) {
			return this.box[outer];
		}


		// this.box[1][0]つまり二列目の一個目だけ決める
		Number nextOuter1st = null;
		for (int i = this.numDeque.size(); i != 0; i--) {
			if (this.numDeque.element().isPossible()) {
				nextOuter1st = this.numDeque.poll();
				break;
			}
		}

		// 二列目の一個目がない = 二列目が作れない = 一列目の番号がallCnt状態
		if (nextOuter1st == null) {
			return null;
		}

		List<Number> next = new ArrayList<>(this.keta);
		next.add(nextOuter1st);

		// this.box[1][1]つまり二列の二個目以降はinBoxから次の列の残りを受け取る
		Number[] innerOut = this.inBox.out(0);
		// inBoxのoutがnull = 二列目の二個目以降が作れない
		if (innerOut == null) {
			// possible以外でblackBox作成
			this.inBlackBox();
			// blackBoxからの番号をaddしてout作成
			for (int i = this.keta; i < this.keta; i--) {
				next.add(this.blackBox[i]);
			}

		} else {
			// inBoxからoutがあれば
			for (Number num : innerOut) {
				next.add(num);
			}
		}

		this.box[outer] = (Number[]) next.toArray(new Number[keta]);

		return this.box[outer];
	}

	private void inBlackBox() {

		if (this.blackBox != null) {
			// 既にblackBoxに番号あるなら作る必要ない
			return;
		}

		this.blackBox = (Number[]) this.numDeque.toArray(new Number[this.numDeque.size()]);
	}

}
