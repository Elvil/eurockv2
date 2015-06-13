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
			if (position.distance(p.getPosition()) < BombObject.RAYON) {
				if (p.getName().equals(Semantics.EXPLOSION)) {
					return false;
				}
			}
		}
		return true;
	}
	public Point2f fleeBomb(Point2f position, List<Percept> perceptions)
	{
		for (Percept p : perceptions) {
			if (position.distance(p.getPosition()) < BombObject.RAYON+10.0f) {
				if (p.getName().equals(Semantics.EXPLOSION) || p.getName().equals(Semantics.BOMB)) {
					return p.getPosition();
				}
			}
		}
		return new Point2f();
	}
	public boolean isGone(Point2f position, List<Percept>  perceptions)
	{
		for (Percept p : perceptions) {
			Point2f center = p.getShape().getBounds().getCenter();
			if (position.distance(center) < (p.getShape().getMaxDemiSize()*2)) {
				if (p.getName().equals(Semantics.EXIT)) {
					return true;
				}
			}
		}
		return false;		
	}
}
