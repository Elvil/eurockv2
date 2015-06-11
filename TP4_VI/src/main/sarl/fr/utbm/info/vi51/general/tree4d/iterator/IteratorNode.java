package fr.utbm.info.vi51.general.tree4d.iterator;

import java.util.Iterator;
import java.util.Stack;

import fr.utbm.info.vi51.framework.environment.ShapedObject;
import fr.utbm.info.vi51.general.tree4d.QuadTree;
import fr.utbm.info.vi51.general.tree4d.QuadTreeNode;


public class IteratorNode<D extends ShapedObject> implements Iterator<QuadTreeNode<D>>
{
private Stack<QuadTreeNode<D>> stack = new Stack<QuadTreeNode<D>>();

public IteratorNode(QuadTree<D> tree){
stack.add(tree.getRoot());
}

public IteratorNode(QuadTreeNode<D> quadTreeNode) {
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
stack.add(tmp.getChildren()[i]);
}
}
return tmp;
}

@Override
public void remove() {
	// TODO Auto-generated method stub
	
}


}

