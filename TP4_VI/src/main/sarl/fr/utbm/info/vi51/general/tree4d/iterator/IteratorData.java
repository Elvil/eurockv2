package fr.utbm.info.vi51.general.tree4d.iterator;

import java.util.Iterator;
import java.util.Stack;

import fr.utbm.info.vi51.framework.environment.ShapedObject;
import fr.utbm.info.vi51.general.tree4d.QuadTree;
import fr.utbm.info.vi51.general.tree4d.QuadTreeNode;

public class IteratorData<D extends ShapedObject> implements Iterator<D> {
	private IteratorNode<D> it;
	private Stack<D> stack = new Stack<D>();

	public IteratorData(QuadTree<D> root) {
		it = new IteratorNode<D>(root);
		searchNext();
	}

	public void searchNext() {
		if (stack.isEmpty()) {
			while (it.hasNext() && stack.isEmpty()) {
				stack.addAll(it.next().getData());
			}
		}
	}

	@Override
	public boolean hasNext() {
		if (!stack.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public D next() {
		D tmp;
		tmp = stack.pop();
		searchNext();
		return tmp;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

}
