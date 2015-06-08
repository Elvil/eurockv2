package fr.utbm.info.vi51.general.tree;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

import fr.utbm.info.vi51.framework.environment.ShapedObject;
import fr.utbm.info.vi51.framework.environment.SpatialDataStructure;
import fr.utbm.info.vi51.framework.math.Rectangle2f;
import fr.utbm.info.vi51.framework.math.Shape2f;
import fr.utbm.info.vi51.general.tree.iterator.IteratorAllNode;
import fr.utbm.info.vi51.general.tree.iterator.IteratorData;
import fr.utbm.info.vi51.general.tree.iterator.frustumIterator;

public class QuadTree <D extends ShapedObject> implements SpatialDataStructure<D>, Iterable<QuadTreeNode<D>>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1974012345073013800L;

	private QuadTreeNode<D> root;
	private Rectangle2f bound;
	
	public QuadTree(QuadTreeNode<D> root){
		this.root = root;
		this.root.createChildren();
	}
	
	public QuadTree(){
	}
	
	@Override
	public void initialize(Rectangle2f worldSize) {
		this.bound = worldSize.clone();
		this.root = new QuadTreeNode<D>(worldSize);
		this.root.createChildren();
	}
	

	public void setRoot(QuadTreeNode<D> root2) {
		this.root = root2;
	}

	@Override
	public Rectangle2f getBounds() {
		return this.bound;
	}
	
	public QuadTreeNode<D> getRoot(){
		return this.root;
	}

	@Override
	public boolean addData(D data) {
		return this.root.addData(data);
	}


	@Override
	public boolean removeData(D data) {
		if (data == null) {
			return false;
		}
		QuadTreeNode<D> root = getRoot();
		if (root != null) {
			return root.removeData(data);
		}
		return false;
	}

	@Override
	public Iterator<D> dataIterator() {
		// TODO Auto-generated method stub
		return new IteratorData<D>(this);
	}

	@Override
	public Iterable<D> getData() {
		return new Iterable<D>() {
			@Override
			public Iterator<D> iterator() {
				return dataIterator();
			}
		};
	}

	@Override
	public Iterator<D> dataIterator(Shape2f<?> bounds) {
		return new frustumIterator<D>(this,bounds);
	}
	
	@Override
	public Iterator<QuadTreeNode<D>> iterator() {
		return new IteratorAllNode<D>(this);
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		Iterator<QuadTreeNode<D>> it = iterator();
		while (it.hasNext()) {
			QuadTreeNode<D> n = it.next();
			b.append("NODE ");
			b.append(n.getData());
			b.append("\n");
		}
		return b.toString();
	}
	
}
