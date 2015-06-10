package fr.utbm.info.vi51.project.GUI.Graphics.Layout;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import fr.utbm.info.vi51.framework.math.Circle2f;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Shape2f;
import fr.utbm.info.vi51.project.GUI.Graphics.AbstractGraphicElement;

/**
 * 
 */
public class LayoutAgent<C extends AbstractGraphicElement> extends AbstractLayout<C> implements Runnable {

	private static final long serialVersionUID = -8388992835805072885L;
	private Thread t;
	private int x, y;
	private AbstractGraphicElement selected = null;
	private int clicx=0, clicy=0;
	
	public LayoutAgent(int h, int w) {
		super(h, w);
		listComponents = Collections.synchronizedList(new ArrayList());
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (true)
		{
			try {
				Thread.sleep(50);
				synchronized(listComponents) {
					Iterator i = listComponents.iterator(); 
					while (i.hasNext())
					{
						AbstractGraphicElement b = (AbstractGraphicElement) i.next();
						b.update();
					}
				}
				this.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
	public synchronized void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.scale(this.zoom/100, this.zoom/100);
		g2d.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);

		for(C c: this.listComponents)
			c.draw(g);

		g2d.dispose();
		super.paintComponent(g);
	}
	public AbstractGraphicElement actionClick(int x, int y)
	{
		this.clicx = (int) ((100/zoom) * (x - this.getLocation().getX()));
		this.clicy = (int) ((100/zoom) * (y - this.getLocation().getY()));
		Point2f pt = new Point2f(this.clicx, this.clicy);
		Shape2f<?> ellipse = new Circle2f(pt, 8);
		for(AbstractGraphicElement e: this.listComponents)
		{
			if (e.intersect(ellipse)) {
				if (this.selected != null)
					this.selected.unselect();
				e.select();
				this.selected = e;
				return e;
			}		
		}
		if (this.selected != null)
			this.selected.unselect();
		this.selected = null;
		return null;
	}
}
