package fr.utbm.info.vi51.general.influence;

import java.util.UUID;

import fr.utbm.info.vi51.framework.environment.Influence;

public class NullInfluence extends Influence {

	public NullInfluence()
	{
		super(UUID.randomUUID());
	}
	
	public NullInfluence(UUID influencedObject) {
		super(influencedObject);
		// TODO Auto-generated constructor stub
	}

}
