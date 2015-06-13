package fr.utbm.info.vi51.general.behavior;

import java.util.List;

import fr.utbm.info.vi51.framework.environment.Percept;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.project.environment.Semantics;
import fr.utbm.info.vi51.project.environment.State;

public class SecurityBehaviour {

	private float RADIUS_ALERT;
	private Point2f savePosBomb = new Point2f(-1,-1);
	private boolean FollowBombeOrSpec;
	public static boolean bombeTrouver = false;

	public SecurityBehaviour(float radius_ALERT) {
		this.RADIUS_ALERT = radius_ALERT;
		FollowBombeOrSpec = false; // new Random().nextBoolean();
	}

	public boolean runAlert(Point2f position, List<Percept> perceptions) {
		if (!bombeTrouver) {
			if (!perceptions.isEmpty()) {

				for (Percept p : perceptions) {
					if (position.distance(p.getPosition()) < RADIUS_ALERT) {
						if (p.getName().equals(Semantics.BOMB)) {
							bombeTrouver = true;
							FollowBombeOrSpec = p.getName().equals(
									Semantics.BOMB);
							savePosBomb = p.getPosition();
							return true;
						}
						if (p.getType().equals(State.DEAD)
								|| p.getType().equals(State.ALERTED_OUT)) {
							bombeTrouver = false;
							FollowBombeOrSpec = p.getName().equals(
									Semantics.BOMB);
							savePosBomb = p.getPosition();
							return true;
						}
					}
				}
			}

		} else {
			if (!perceptions.isEmpty()) {

				for (Percept p : perceptions) {
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
			return true;
		}
		return false;
	}

	public Point2f fleeAgent(Point2f position, List<Percept> perceptions) {
		for (Percept p : perceptions) {
			if (position.distance(p.getPosition()) < 10.0f) {
				if (p.getName().equals(Semantics.SECURITY_AGENT)
						&& (p.getType().equals(State.ALERTED_OUT) || p
								.getType().equals(State.ALERTED))) {
					return p.getPosition();
				}
			}
		}
		return new Point2f(-1, -1);
	}

	public Point2f followAgent(Point2f position, List<Percept> perceptions) {
		if (FollowBombeOrSpec) {
			if (position.distance(savePosBomb) < RADIUS_ALERT) {
				return savePosBomb;
			}
		} else {
			for (Percept p : perceptions) {
				if (position.distance(p.getPosition()) < RADIUS_ALERT) {
					if (p.getName().equals(Semantics.SPECTATOR)
							&& !p.getType().equals(State.ALERTED_OUT)) {
						return p.getPosition();
					}
				}
			}
		}
		return new Point2f(-1, -1);
	}

	public boolean getBombFind() {
		return bombeTrouver;
	}

}
