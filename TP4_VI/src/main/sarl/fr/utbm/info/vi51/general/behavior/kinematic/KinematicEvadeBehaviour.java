/* 
 * $Id$
 * 
 * Copyright (c) 2011-15 Stephane GALLAND <stephane.galland@utbm.fr>.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * This program is free software; you can redistribute it and/or modify
 */
package fr.utbm.info.vi51.general.behavior.kinematic;

import fr.utbm.info.vi51.framework.agent.BehaviourOutput;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.general.behavior.EvadeBehaviour;

/**
 * Kinematic Evade Behaviour.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public class KinematicEvadeBehaviour implements EvadeBehaviour {

	private final KinematicFleeBehaviour flee;
	
	public KinematicEvadeBehaviour() {
		this.flee = new KinematicFleeBehaviour();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public BehaviourOutput runEvade(Point2f position, float linearSpeed, float maxLinearSpeed,
			Point2f targetPosition, Vector2f targetLinearMotion) {
		Point2f fleeTarget = targetPosition.operator_plus(targetLinearMotion);
		return this.flee.runFlee(position, linearSpeed, maxLinearSpeed, fleeTarget);
	}
	
}