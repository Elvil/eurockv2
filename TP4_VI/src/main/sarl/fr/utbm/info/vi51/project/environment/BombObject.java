package fr.utbm.info.vi51.project.environment;

import java.util.UUID;

import fr.utbm.info.vi51.framework.environment.AbstractMobileObject;
import fr.utbm.info.vi51.framework.environment.ImmobileObject;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Shape2f;


public class BombObject extends ImmobileObject {


	public BombObject(UUID id, Shape2f<?> shape, Point2f position, String name) {
		super(id, shape, position, name);
		// TODO Auto-generated constructor stub

		lifetime = 0;
		type = Semantics.BOMB;
	}
	public float lifetime = 0;
	public String type;
	public void update()
	{

		if (type.equals(Semantics.BOMB))
		{
			lifetime += 0.05f;
			if (lifetime >= 1)
			{
				lifetime = 0;
				type = Semantics.EXPLOSION;
			}
		}
		if (type.equals(Semantics.EXPLOSION))
		{
			lifetime += 0.1f;
			if (lifetime >= 1)
			{
				lifetime = 0;
				type = Semantics.BOMBEXPLOSEE;
			}
		}
	}
}
