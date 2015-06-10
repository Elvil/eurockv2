package fr.utbm.info.vi51.project.environment;

import java.util.UUID;

import fr.utbm.info.vi51.framework.environment.Influence;
import fr.utbm.info.vi51.framework.math.Point2f;

public class TestInfluence extends Influence {

	private static final long serialVersionUID = 176348512638922738L;

	private final String text;
	
	/**
	 * @param influencedObject the removed object.
	 * @param position the new position of the object.
	 */
	public TestInfluence(UUID influencedObject, String message) {
		super(influencedObject);
		this.text = message;
	}
		@Override
	public String toString() {
		return "TEST TARGET @ " + text;
	}

}
