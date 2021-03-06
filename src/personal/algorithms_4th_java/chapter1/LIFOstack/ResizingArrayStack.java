package personal.algorithms_4th_java.chapter1.LIFOstack;

import java.awt.event.ItemEvent;
import java.util.Iterator;

/**
 * @author tce E-mail:
 * @version Create Time：2019年6月4日 下午2:31:14 Description:LIFO,动态调整数组大小的实现
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {
	private Item[] a = (Item[]) new Object[1];// 栈元素
	private int N = 0;// 元素数量

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	private void resize(int max) {
		// 将栈移动到一个大小为max的数组
		Item[] tmp = (Item[]) new Object[max];
		for (int i = 0; i < a.length; i++) {
			tmp[i] = a[i];
		}
		a = tmp;
	}

	public void push(Item item) {
		// 将元素添加到栈顶
		if (N == a.length)
			resize(2 * a.length);
		a[N++] = item;
	}

	public Item pop() {
		// 从栈顶删除元素
		Item item = a[--N];
		a[N] = null;// 避免对象游离？ 1.3.2.4
		if (N > 0 && N == a.length / 4)
			resize(a.length / 2);
		return item;
	}

	private class ReverseArrayIterator implements Iterator<Item> {
		// 支持后进先出的迭代
		private int i = N;

		public boolean hasNext() {
			return i > 0;
		}

		public Item next() {
			return a[--N];
		}

		public void remove() {

		}
	}

	@Override
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}
}
