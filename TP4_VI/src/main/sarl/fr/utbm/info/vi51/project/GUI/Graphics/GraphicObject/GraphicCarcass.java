package fr.utbm.info.vi51.project.GUI.Graphics.GraphicObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import fr.utbm.info.vi51.framework.environment.SituatedObject;

public class GraphicCarcass extends AbstractGraphicObject {


	public GraphicCarcass(SituatedObject obj) {
		super(obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.cyan);
		g2d.fill(shp);
		/*g2d.setColor(Color.black);
		g2d.draw(shp);		*/
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

