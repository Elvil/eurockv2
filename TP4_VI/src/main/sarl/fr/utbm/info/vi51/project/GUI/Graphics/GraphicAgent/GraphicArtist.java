package fr.utbm.info.vi51.project.GUI.Graphics.GraphicAgent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import fr.utbm.info.vi51.framework.environment.AgentBody;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.project.GUI.Graphics.ShapeConverter;

public class GraphicArtist extends AbstractGraphicAgent {

	public GraphicArtist(AgentBody r) {
		super(r);
	}

	public GraphicArtist(AgentBody r, Point2f pos) {
		super(r, pos);
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.green.darker());
		g2d.fill(ShapeConverter.toShape(this.realAgent.getShape()));
		//g2d.fillOval((int)pos.getX(), (int)pos.getY(), 8, 8);
	}
}
