package fr.utbm.info.vi51.general.behavior;

import java.util.List;

import fr.utbm.info.vi51.framework.environment.Percept;
import fr.utbm.info.vi51.project.environment.State;

public class AlertBehaviour {
	
	public boolean runAlert(List<Percept> perceptions) {
		
		for (Percept p : perceptions) {
			// Change the state of the agents present in the alert circle 
			//if(p.getState() == State.ALERTED){
				return true;
			//}
		}
		return false;
	}
}