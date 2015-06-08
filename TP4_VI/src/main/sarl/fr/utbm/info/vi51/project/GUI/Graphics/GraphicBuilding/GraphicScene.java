package fr.utbm.info.vi51.project.GUI.Graphics.GraphicBuilding;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import fr.utbm.info.vi51.framework.math.Shape2f;

public class GraphicScene extends AbstractGraphicBuilding {


	public GraphicScene(int x, int y, int radius) {
		super(x, y, radius);
	}
	public GraphicScene(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
	public GraphicScene(int[] px, int[] py)
	{
		super(px, py);
	}

	public GraphicScene(Shape2f shape) {
		// TODO Auto-generated constructor stub
		super(shape);
	}
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.cyan.brighter());
		g2d.fill(shp);
		g2d.setColor(Color.black);
		g2d.draw(shp);		
	}
	public void destroy()
	{
		
	}
}
