package fr.utbm.info.vi51.project.GUI.Graphics.GraphicAgent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import fr.utbm.info.vi51.framework.environment.AgentBody;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.project.GUI.Graphics.ShapeConverter;

public class GraphicSecurityAgent extends AbstractGraphicAgent {

	public GraphicSecurityAgent(AgentBody r) {
		super(r);
	}
	public GraphicSecurityAgent(AgentBody r, Point2f pos) {
		super(r, pos);
	}
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.fill(ShapeConverter.toShape(this.realAgent.getShape()));
	}

}
