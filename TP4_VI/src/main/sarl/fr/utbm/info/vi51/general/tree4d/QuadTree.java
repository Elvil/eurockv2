package fr.utbm.info.vi51.general.tree4d;

import java.util.Iterator;

import fr.utbm.info.vi51.framework.environment.ShapedObject;
import fr.utbm.info.vi51.framework.environment.SpatialDataStructure;
import fr.utbm.info.vi51.framework.math.Rectangle2f;
import fr.utbm.info.vi51.framework.math.Shape2f;

public class QuadTree <D extends ShapedObject> implements SpatialDataStructure<D>/*, Iterable<QuadTreeNode<D> > */ {

	QuadTreeNode<D> root = null;
	Iterator<QuadTreeNode<?>> it =  null;
	
	public QuadTree() {
		
	}
	
	
	public QuadTree(QuadTreeNode root) {
		this.root = root;
	}

	public void initialize(Rectangle2f worldSize) {
		root = new QuadTreeNode<D>(worldSize);
//		it = new FrustrumCullerTreeIterator(root, new CircleFrustum());
//		it = new LeafTreeIterator(root);
	}


	
	@Override
	public Rectangle2f getBounds() {
		if (root != null)
			return root.getBounds();
		return null;
	}

	public Iterator dataIterator() {
		//return new LeafTreeIterator(root);
		return null;
	}

	public Iterable<D> getData() {
		return new Iterable<D>() {
			@Override
			public Iterator<D> iterator() {
				return dataIterator();
			}
		};
	}

	public Iterator<D> dataIterator(Shape2f<?> bounds) {
		//return new FrustumCullerTreeIterator<D>(root, bounds);
		return null;
	}

	public void setRoot(QuadTreeNode<D> newRoot) {
		root = newRoot;
	}

	public QuadTreeNode<D> getRoot() {
		return root;
	}

	// retourne true si la valeur a bien été supprimée
	public boolean removeData(D data) {
		return root.remove(data);
	}

	public boolean addData(D data) {
		root.add(data);
		return true;
	}

	public Iterator iterator() {
		return it;
	}
}

