package fr.utbm.info.vi51.project.GUI.Graphics.GraphicBuilding;

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

public class GraphicBomb extends AbstractGraphicBuilding {

	public GraphicBomb(SituatedObject bomb) {
		super(bomb);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		BombObject bomb = (BombObject) this.object;
		bomb.update();
		if (bomb.type.equals(Semantics.BOMB))
		{
			g2d.setColor(Color.red);
			g2d.fill(this.shp);
			
			g2d.setColor(ColorInterpolation.interpolation(Color.red, Color.green, bomb.lifetime));
			g2d.fillRect((int)this.shp.getBounds().getMinX()+10, (int) this.shp.getBounds().getMinY()-4, 3 , (int) (this.shp.getBounds().getHeight()+4 * bomb.lifetime));
		}
		if (bomb.type.equals(Semantics.EXPLOSION))
		{
			g2d.setColor(ColorInterpolation.interpolation(Color.red, Color.yellow, bomb.lifetime));
			g2d.fillOval((int)(this.shp.getBounds().getMinX() - 50*bomb.lifetime), (int)(this.shp.getBounds().getMinY() - 50*bomb.lifetime), (int)(100*bomb.lifetime), (int)(100*bomb.lifetime));
		}
		if (bomb.type.equals(Semantics.BOMBEXPLOSEE))
		{

			Color w = new Color(0,0,0,0);
	        Paint p = new RadialGradientPaint(new Point2D.Double(
	        		this.shp.getBounds().getMinX() - 50*bomb.lifetime,
	        		this.shp.getBounds().getMinY() - 50*bomb.lifetime), 50,
	                new float[] { 0.0f, 1.0f },
	                new Color[] { Color.darkGray, w});
	        g2d.setPaint(p);
//			g2d.setColor(ColorInterpolation.interpolation(Color.red, Color.yellow, bomb.lifetime));
			g2d.fillOval((int)(this.shp.getBounds().getMinX() - 50), (int)(this.shp.getBounds().getMinY() - 50), 100, 100);
		}
		
		
	}
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
