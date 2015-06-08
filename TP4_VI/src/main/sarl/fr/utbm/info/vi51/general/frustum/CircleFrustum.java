package fr.utbm.info.vi51.general.frustum;

import java.util.UUID;

import fr.utbm.info.vi51.framework.environment.AbstractFrustum;
import fr.utbm.info.vi51.framework.math.Circle2f;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Shape2f;
import fr.utbm.info.vi51.framework.math.Vector2f;

public class CircleFrustum extends AbstractFrustum  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4958874112361905506L;

	private final float radius;

	public CircleFrustum(UUID id, float perceptionRadius) {
		super(id);
		this.radius = perceptionRadius;
	}

	@Override
	public Shape2f<?> toShape(Point2f position, Vector2f orientation) {
		return new Circle2f(position, this.radius);
	}

}
