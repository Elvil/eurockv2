package fr.utbm.info.vi51.project.GUI.Graphics.GraphicObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import fr.utbm.info.vi51.framework.math.Shape2f;
import fr.utbm.info.vi51.project.GUI.Graphics.ColorInterpolation;

public class GraphicExit extends AbstractGraphicObject {

	private float lifetime = 0.0f;
	private boolean b = false;
	public GraphicExit(Shape2f shape) {
		super(shape);
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		if (b)
			lifetime += 0.01f;
		else
			lifetime -= 0.01f;
		if (lifetime > 1 || lifetime < 0)
			b = !b;
		Color c = ColorInterpolation.interpolation(Color.pink, new Color(128,0,128), lifetime);
		g2d.setColor(c);
		g2d.fill(shp);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
