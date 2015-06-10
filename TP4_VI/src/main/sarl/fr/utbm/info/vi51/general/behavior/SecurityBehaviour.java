package fr.utbm.info.vi51.general.behavior;

import java.util.List;
import java.util.Random;

import fr.utbm.info.vi51.framework.environment.Percept;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.project.environment.Semantics;
import fr.utbm.info.vi51.project.environment.State;

public class SecurityBehaviour {

	private float RADIUS_ALERT;
	private Point2f savePosBomb = new Point2f();;
	private boolean FollowBombeOrSpec;
	private static boolean bombeTrouver = false;

	public SecurityBehaviour(float radius_ALERT) {
		this.RADIUS_ALERT = radius_ALERT;
		FollowBombeOrSpec = false; // new Random().nextBoolean();
	}

	public boolean runAlert(Point2f position, List<Percept> perceptions) {
		if (!bombeTrouver) {
			if (!perceptions.isEmpty()) {

				for (Percept p : perceptions) {
					// Change the state of the agents present in the alert
					// circle
					if (position.distance(p.getPosition()) < RADIUS_ALERT) {
						if (p.getName().equals(Semantics.BOMB)) {
							bombeTrouver = true;
							FollowBombeOrSpec = true; 
							savePosBomb = p.getPosition();
							return true;
						}

					}
				}
			}

		}
		return false;
	}

	public Point2f followAgent(Point2f position, List<Percept> perceptions) {

		if (FollowBombeOrSpec) {
			return savePosBomb;
		} else {
			for (Percept p : perceptions) {
				if (p.getName().equals(Semantics.SPECTATOR)
						&& !p.getType().equals(State.ALERTED)) {
					return p.getPosition();
				}
			}
		}

		return new Point2f();
	}

	public boolean getBombFind() {
		return bombeTrouver;
	}

}
