package fr.utbm.info.vi51.project.GUI.Graphics.GraphicObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import fr.utbm.info.vi51.framework.environment.AbstractMobileObject;
import fr.utbm.info.vi51.framework.environment.SituatedObject;
import fr.utbm.info.vi51.project.environment.Semantics;

public class StandNourriture extends AbstractGraphicObject {

	public StandNourriture(SituatedObject obj) {
		super(obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Color c = Color.green.darker();
		g2d.setColor(c);
		g2d.fill(shp);
		g2d.setColor(Color.black);
		g2d.draw(shp);		

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
