package fr.utbm.info.vi51.general.tree.iterator;

import java.util.Iterator;
import java.util.Stack;

import fr.utbm.info.vi51.framework.environment.ShapedObject;
import fr.utbm.info.vi51.general.tree.QuadTree;
import fr.utbm.info.vi51.general.tree.QuadTreeNode;

public class IteratorAllNode<D extends ShapedObject> implements Iterator<QuadTreeNode<D>>
{
	private Stack<QuadTreeNode<D>> stack = new Stack<QuadTreeNode<D>>();
	
	public IteratorAllNode(QuadTree<D> tree){
		stack.add(tree.getRoot());
	}

	public IteratorAllNode(QuadTreeNode<D> quadTreeNode) {
		stack.add(quadTreeNode);
	}

	@Override
	public boolean hasNext() {
		if(!stack.isEmpty()){
			return true;	
		}else{
			return false;
		}
	}

	@Override
	public QuadTreeNode<D> next() {
		QuadTreeNode<D> tmp;
		tmp = stack.pop();
		if(!tmp.isLeaf()){
			for(int i = 0; i < 4 ; i++ ){
				stack.add(tmp.getOneChildren(i));
			}
		}
		return tmp;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	
}