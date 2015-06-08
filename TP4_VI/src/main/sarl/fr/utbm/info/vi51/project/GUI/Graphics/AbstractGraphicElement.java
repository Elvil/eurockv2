package fr.utbm.info.vi51.project.GUI.Graphics;

import java.awt.Graphics;

import fr.utbm.info.vi51.framework.math.Shape2f;

/**
 * 
 */
public abstract class AbstractGraphicElement extends AbstractComponent implements IGraphic {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8029785793479438477L;
	/**
     * 
     */
	protected boolean selected = false;
	protected int posX, posY;
    public AbstractGraphicElement() {
    }

	public abstract void draw(Graphics g);
	public void select() { this.selected = true; }
	public void unselect() { this.selected = false; }
	public abstract void update();
    public abstract boolean intersect(Shape2f<?> r);
    public abstract void destroy();

}
