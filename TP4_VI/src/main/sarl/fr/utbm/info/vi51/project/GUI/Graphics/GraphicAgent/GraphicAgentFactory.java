package fr.utbm.info.vi51.project.GUI.Graphics.GraphicAgent;

import fr.utbm.info.vi51.framework.environment.AgentBody;
import fr.utbm.info.vi51.project.environment.Semantics;

public class GraphicAgentFactory {

	public static AbstractGraphicAgent getInstance(AgentBody r)
	{
		System.out.println(""+r.getName());
		if (r.getName().equals(Semantics.ARTIST))
			return new GraphicArtist(r);
		if (r.getName().equals(Semantics.SECURITY_AGENT))
			return new GraphicSecurityAgent(r);
		if (r.getName().equals(Semantics.SPECTATOR))
			return new GraphicSpectator(r);
		return null;
		//return new GraphicSpectator(r);
	}
}
