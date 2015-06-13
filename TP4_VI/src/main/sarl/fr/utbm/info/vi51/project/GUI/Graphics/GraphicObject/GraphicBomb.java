package fr.utbm.info.vi51.project.GUI.Graphics.GraphicObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

import fr.utbm.info.vi51.framework.environment.SituatedObject;
import fr.utbm.info.vi51.project.GUI.Graphics.ColorInterpolation;
import fr.utbm.info.vi51.project.environment.BombObject;
import fr.utbm.info.vi51.project.environment.Semantics;

public class GraphicBomb extends AbstractGraphicObject {

	public float bombSize = 0;
	public GraphicBomb(SituatedObject bomb) {
		super(bomb);
		bombSize = BombObject.RAYON;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		BombObject bomb = (BombObject) this.object;
		if (bomb.type.equals(Semantics.BOMB))
		{
			g2d.setColor(Color.darkGray);
			g2d.fill(this.shp);
			
			g2d.setColor(ColorInterpolation.interpolation(Color.red, Color.green, bomb.lifetime));
			g2d.fillRect((int)(this.shp.getBounds().getMinX() - (4* this.shp.getBounds().getWidth())/2), (int) (this.shp.getBounds().getMinY()+this.shp.getBounds().getHeight()), 6 * (int) (this.shp.getBounds().getWidth() * bomb.lifetime), 5);
		}
		if (bomb.type.equals(Semantics.EXPLOSION))
		{
			g2d.setColor(ColorInterpolation.interpolation(Color.red, Color.yellow, bomb.lifetime));
			g2d.fillOval((int)(this.shp.getBounds().getMinX() - bombSize*bomb.lifetime), (int)(this.shp.getBounds().getMinY() - bombSize*bomb.lifetime), (int)(2*bombSize*bomb.lifetime), (int)(2*bombSize*bomb.lifetime));
		}
		if (bomb.type.equals(Semantics.BOMBEXPLOSEE))
		{

			Color w = new Color(0,0,0,0);
	        Color gr = new Color (200, 200, 200, 200);
			Paint p = new RadialGradientPaint(new Point2D.Double(
	        		this.shp.getBounds().getMinX(),
	        		this.shp.getBounds().getMinY()), bombSize * 0.75f,
	                new float[] { 0.0f, 1.0f },
	                new Color[] {gr , w});
	        g2d.setPaint(p);
//			g2d.setColor(ColorInterpolation.interpolation(Color.red, Color.yellow, bomb.lifetime));
			g2d.fillOval((int)(this.shp.getBounds().getMinX() - bombSize), (int)(this.shp.getBounds().getMinY() - bombSize), (int)(2*bombSize), (int)(2*bombSize));
		}
		
		
	}
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
