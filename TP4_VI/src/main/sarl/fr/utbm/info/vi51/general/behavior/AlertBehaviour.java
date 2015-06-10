package fr.utbm.info.vi51.general.behavior;

import java.util.List;

import fr.utbm.info.vi51.framework.environment.Percept;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.project.environment.Semantics;
import fr.utbm.info.vi51.project.environment.State;

public class AlertBehaviour {

	private float RADIUS_ALERT;

	public AlertBehaviour(float radius_ALERT) {
		this.RADIUS_ALERT = radius_ALERT;
	}

	public boolean runAlert(Percept body, List<Percept> perceptions) {
		Point2f position = body.getPosition();

		if (!body.getType().equals(State.ALERTED)) {
			if (!perceptions.isEmpty()) {

				for (Percept p : perceptions) {
					// Change the state of the agents present in the alert
					// circle
					if (position.distance(p.getPosition()) < RADIUS_ALERT) {
						// System.out.println("getName" + p.getName());
						// System.out.println("getType" + p.getType());
						if (p.getType().equals(State.ALERTED)) {

							return true;
						}

					}
				}
			}

		}

		/*
		 * if(!perceptions.isEmpty() && perceptions.size() < 2 &&
		 * perceptions.get(0).getName().equals(Semantics.BOMB)){ return true; }
		 */

		return false;
	}

	public Point2f searchTarget(String target, List<Percept> perceptions) {
		for (Percept p : perceptions) {
			if (p.getName().equals(target)) {
				return p.getPosition();
			}
		}
		return new Point2f();
	}
}