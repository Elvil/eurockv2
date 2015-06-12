package fr.utbm.info.vi51.project.GUI.Graphics.GraphicObject;

import fr.utbm.info.vi51.framework.environment.SituatedObject;
import fr.utbm.info.vi51.project.environment.Semantics;

public class GraphicObjectFactory {
	public static AbstractGraphicObject getInstance(SituatedObject obj)
	{
		System.out.println(""+obj.getName() + " / "+ obj.getType());
		if (obj.getName().equals(Semantics.SCENE_PLAGE) || obj.getName().equals(Semantics.SCENE_GRAND) || obj.getName().equals(Semantics.SCENE_LOGGIA))
			return new GraphicScene(obj);
		if (obj.getName().equals(Semantics.BOMB))
			return new GraphicBomb(obj);
		if (obj.getName().equals(Semantics.STAND_MIAM))
			return new StandNourriture(obj);
		if (obj.getName().equals(Semantics.EXIT))
			return new GraphicExit(obj);		
		return null;
	}
}
