package fr.utbm.info.vi51.framework.environment;

import java.util.UUID;

import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Shape2f;

public class ImmobileObject extends AbstractSituatedObject {

	public ImmobileObject(UUID id, Shape2f<?> shape, Point2f position, String name) {
		super(id, shape, position);
		this.setName(name);
	}


}
