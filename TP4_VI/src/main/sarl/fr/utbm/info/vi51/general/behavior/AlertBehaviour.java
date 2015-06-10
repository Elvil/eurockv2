package fr.utbm.info.vi51.general.behavior;

import java.util.List;

import fr.utbm.info.vi51.framework.environment.Percept;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.project.environment.Semantics;
import fr.utbm.info.vi51.project.environment.State;

public class AlertBehaviour {
	
	private float RADIUS_ALERT;
	
	public AlertBehaviour( float radius_ALERT){
		this.RADIUS_ALERT = radius_ALERT;
	}
	
	public boolean runAlert(Point2f position ,List<Percept> perceptions) {
		
		if(!perceptions.isEmpty()){
			
			for (Percept p : perceptions) {
				// Change the state of the agents present in the alert circle 
				if(position.distance(p.getPosition()) < RADIUS_ALERT){
					if(p.getType() == State.ALERTED){
						return true;
					}
				}
			}
			
		}
		
		if(!perceptions.isEmpty() && perceptions.size() < 4 && perceptions.get(0).getName().equals(Semantics.BOMB)){
			return true;
		}
		
		return false;
	}
}