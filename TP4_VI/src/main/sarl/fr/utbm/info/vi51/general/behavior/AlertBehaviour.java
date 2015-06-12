package fr.utbm.info.vi51.general.behavior;

import java.util.List;
import java.util.Random;

import fr.utbm.info.vi51.framework.environment.Percept;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.project.environment.Semantics;
import fr.utbm.info.vi51.project.environment.State;

public class AlertBehaviour {

	private float RADIUS_ALERT;
	private Random rand = new Random();

	public AlertBehaviour(float radius_ALERT) {
		this.RADIUS_ALERT = radius_ALERT;
	}

	public State runAlert(Percept body, List<Percept> perceptions) {
		Point2f position = body.getPosition();

		if (!body.getType().equals(State.ALERTED_OUT)) {
			if (!perceptions.isEmpty()) {

				for (Percept p : perceptions) {
					// Change the state of the agents present in the alert
					// circle
					if (position.distance(p.getPosition()) < RADIUS_ALERT) {
						// System.out.println("getName" + p.getName());
						// System.out.println("getType" + p.getType());
						if (p.getType().equals(State.ALERTED_OUT)
								&& p.getName().equals(Semantics.SECURITY_AGENT)) {
							// System.out.println("tu passes en alerted_out wesh");
							return State.ALERTED_OUT;
						}

						if (body.getType().equals(State.ALERTED)) {

							return State.ALERTED;

						}

						if (p.getType().equals(State.ALERTED)
								|| p.getType().equals(State.ALERTED_OUT)
								&& p.getName().equals(Semantics.SPECTATOR)) {

							if (rand.nextInt(100) <= 23) {
								return State.ALERTED;
							} else {
								return State.ALERTED_OUT;
							}
						}

						// return State.ALERTED;

					}
				}
			}

		} else {
			return State.ALERTED_OUT;
		}

		/*
		 * if(!perceptions.isEmpty() && perceptions.size() < 2 &&
		 * perceptions.get(0).getName().equals(Semantics.BOMB)){ return true; }
		 */

		return null;
	}

	public Percept searchTarget(Percept body, List<Percept> perceptions) {

		State state = (State) body.getType();
		String target = null;
		switch (state) {
		case EATING:
		case HUNGRY:
			target = Semantics.STAND_MIAM;
			break;

		case ALERTED:
			target = Semantics.BOMB;
			break;
		case ALERTED_OUT:
			if (target != null) {
				for (Percept p : perceptions) {
					if (!p.getName().equals(Semantics.SPECTATOR) && !p.getName().equals(Semantics.SECURITY_AGENT) ) {
						return p;
					}
				}
			}
			break;

		case SEARCH_WATCHING:
		case WATCHING:
			target = body.getWantToWatch();
			break;
		}

		if (target != null) {
			for (Percept p : perceptions) {
				if (p.getName().equals(target)) {
					return p;
				}
			}
		}
		return null;
	}
}