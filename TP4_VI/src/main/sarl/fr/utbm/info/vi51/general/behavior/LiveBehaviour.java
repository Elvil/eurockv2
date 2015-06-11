package fr.utbm.info.vi51.general.behavior;

import java.util.List;

import fr.utbm.info.vi51.framework.environment.Percept;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.project.environment.BombObject;
import fr.utbm.info.vi51.project.environment.Semantics;

public class LiveBehaviour {

	public boolean isAlive(Point2f position, List<Percept> perceptions)
	{
		for (Percept p : perceptions) {
			// Change the state of the agents present in the alert
			// circle
			if (position.distance(p.getPosition()) < BombObject.RAYON) {
			//	System.out.println(""+p.getName());
				if (p.getName().equals(Semantics.EXPLOSION)) {
					return false;
				}
			}
		}
		return true;
	}
}
