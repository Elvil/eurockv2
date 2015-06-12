package fr.utbm.info.vi51.project.GUI.Graphics.GraphicObject;

import fr.utbm.info.vi51.framework.environment.SituatedObject;
import fr.utbm.info.vi51.framework.math.Rectangle2f;
import fr.utbm.info.vi51.project.environment.Semantics;

public class GraphicObjectFactory {

	public static AbstractGraphicObject getInstance(SituatedObject obj)
	{
		if (obj.getType().equals(Semantics.SCENE))
		{
			System.out.println("caca");
			return new GraphicScene(obj.getShape());
		}
		if (obj.getType().equals(Semantics.BOMB))
			return new GraphicBomb(obj);
		if (obj.getType().equals(Semantics.DEAD))
			return new GraphicCarcass(obj);
		
		return new GraphicScene(new Rectangle2f(0,0,0,0));
	}
}
