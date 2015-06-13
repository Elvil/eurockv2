package fr.utbm.info.vi51.project.GUI.Graphics.GraphicAgent;

import java.awt.Graphics;

import javax.swing.JComponent;

import fr.utbm.info.vi51.framework.environment.AgentBody;
import fr.utbm.info.vi51.framework.math.Circle2f;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Shape2f;
import fr.utbm.info.vi51.project.GUI.Graphics.AbstractGraphicElement;

public abstract class AbstractGraphicAgent extends AbstractGraphicElement {

	protected AgentBody realAgent = null;
	protected Point2f pos = new Point2f(0,0);
	public AbstractGraphicAgent(AgentBody r)
	{
		this.realAgent = r;
		this.pos = this.realAgent.getPosition();
	}
	
	public AbstractGraphicAgent(AgentBody r, Point2f pos)
	{
		this.realAgent = r;
		this.pos = pos;
	}
	@Override
	public abstract void draw(Graphics g);

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (this.realAgent != null)
			this.pos = this.realAgent.getPosition();
		repaint();
	}

	@Override
	public JComponent getSwingComponent() {
		return null;
	}

    public boolean intersect(Shape2f<?> r)
    {
    	if (this.realAgent != null)
    		return r.intersects(this.realAgent.getShape());
    	else
    		return (new Circle2f(pos, 8)).intersects(r);
    }
    public void destroy()
    {

    }
}
