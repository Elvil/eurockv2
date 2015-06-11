package fr.utbm.info.vi51.general.tree;

import java.io.Serializable;
import java.util.LinkedList;

import fr.utbm.info.vi51.framework.environment.ShapedObject;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Rectangle2f;
import fr.utbm.info.vi51.framework.math.Shape2f;

public class QuadTreeNode<D extends ShapedObject> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2426334400839627757L;
	
	private QuadTreeNode<D> root;
	private QuadTreeNode<D> children[];
	private Rectangle2f bounds;
	private LinkedList<D> objets;
	
	private static int totaleDataPerNode = 10;
	
	public QuadTreeNode(Rectangle2f rectangle2f) {
		this.bounds = rectangle2f;
		this.objets = new LinkedList<D>();
	}
	public QuadTreeNode<D> getParent() {
		return root;
	}
	public void setRoot(QuadTreeNode<D> root) {
		this.root = root;
	}
	public QuadTreeNode<D>[] getAllChildren() {
		return children;
	}
	public QuadTreeNode<D> getOneChildren(int nb) {
		if(nb > 4 || nb < 0){
			nb = 0;
			System.out.print("Probleme avec le nombre children demander dans :" + this.getClass().toString());
		}
		return children[nb];
	}
	public Rectangle2f getBounds() {
		return bounds;
	}
	public void setBounds(Rectangle2f bounds) {
		this.bounds = bounds;
	}
	public LinkedList<D> getData() {
		return objets;
	}
	public void setObjets(D objets) {
		this.objets.add(objets);
	}
	
	public boolean isLeaf(){
		return this.children == null;
	}
	
	public boolean removeData(D data){
		boolean drap = true;

		if (isLeaf()) {
			drap = this.getData().remove(data);
		} else {
			if (!removeFromChildWhenPossible(data)) {
				drap = this.getData().remove(data);
			}
		}
		if (drap) {
			EmptyChildrenRemove();
		}
		return drap;
	}
	
	private boolean removeFromChildWhenPossible(D data) {
		
		QuadTreeNode<D> selectedChild = null;
		
		for (QuadTreeNode<D> child : this.children) {
			
			if (child.getBounds().intersects(data.getShape())) {
				
				if (selectedChild != null) {
					return false;
				}
				selectedChild = child;
				
			}
			
		}
		if (selectedChild != null) {
			return selectedChild.removeData(data);
		}
		
		return false;
		
	}

	
	private boolean EmptyChildrenRemove() {
		boolean drap = true;
		if(!this.isLeaf()){
				for(QuadTreeNode<D> child : this.children){
					if (child != null && !(child.isLeaf() && child.getData().size() == 0)) {
						drap =  false;
					}
				}if(drap){
				for (int i = 0; i < this.getAllChildren().length; ++i) {
					if (this.children[i] != null) {
						this.children[i].setRoot(null);
						this.children[i] = null;
					}
				}
				this.children = null;
			}
		}
		return drap;
	}
	public boolean addData(D data){
		// Constante -> nombre de data dans un noeud
		// Si supérieur à la constante alors on redispatch dans les children
		// Sinon on ajoute
		if(this.isLeaf()){
			if(this.getData() != null && this.getData().size() < totaleDataPerNode){
				
				return this.getData().add(data);
				
			}else{
				LinkedList<D> tmp = new LinkedList<D>();
				tmp.addAll(this.getData());
				tmp.add(data);
				getData().clear();

				this.createChildren();
				
				for(D d : tmp){
					if(!this.addInChildWhenPossible(d)){
						this.getData().add(d);
					}
				}
				
				return true;
				
			}
		} else {
			if(!this.addInChildWhenPossible(data)){
				this.getData().add(data);
			}
			return true;
		}
	}
	
	private boolean addInChildWhenPossible(D data) {
		Shape2f<?> dataShape = data.getShape();
		QuadTreeNode<D> selectedChild = null;
		for (QuadTreeNode<D> child : this.children) {
			if (child.getBounds().intersects(dataShape)) {
				if (selectedChild != null) {
					// The object is intersecting two or more children
					return false;
				}
				selectedChild = child;
			}
		}
		if (selectedChild != null) {
			return selectedChild.addData(data);
		}
		// No intersection between the children and the data
		return false;
	}
	
	public void createChildren() {
		// TODO Auto-generated method stub
		Rectangle2f bounds = getBounds();
		Point2f lower = bounds.getLower();
		Point2f center = bounds.getCenter();
		Point2f upper = bounds.getUpper();
		
		if(children == null){
			this.children = new QuadTreeNode[4];
		}
		
		if (this.children[0] == null) {
			this.children[0] = new QuadTreeNode<D>(new Rectangle2f(lower, center));
			this.children[0].setRoot(this);
		}
		
		if (this.children[1] == null) {
			this.children[1] = new QuadTreeNode<D>(
					new Rectangle2f(
							lower.getX(), center.getY(),
							center.getX(), upper.getY()));
			this.children[0].setRoot(this);
		}
		
		if (this.children[2] == null) {
			this.children[2] = new QuadTreeNode<D>(
					new Rectangle2f(
							center.getX(), lower.getY(),
							upper.getX(), center.getY()));
			this.children[0].setRoot(this);
		}
		
		if (this.children[3] == null) {
			this.children[3] = new QuadTreeNode<D>(new Rectangle2f(center, upper));
			this.children[0].setRoot(this);
		}
	
		
	}
	
	
	/*private static class iteratorObjetcs<D extends ShapedObject>  implements Iterator<D>{

		private QuadTreeNode<D> quadTreeNode;
		private int cm = 0;
		
		public iteratorObjetcs(QuadTreeNode<D> quadTreeNode) {
			this.quadTreeNode = quadTreeNode;
		}

		@Override
		public boolean hasNext() {
			if(cm >= quadTreeNode.getObjets().size()){
				return true;
			}else{
				return false;
			}
		}

		@Override
		public D next() {
			cm++;		
			return quadTreeNode.getObjets().get(cm);
		}
		
	}


	@Override
	public Iterator<D> iterator() {
		return new iteratorObjetcs<D>(this);
	}*/
	

}
