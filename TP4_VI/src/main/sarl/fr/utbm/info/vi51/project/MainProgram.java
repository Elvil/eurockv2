package fr.utbm.info.vi51.project;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.sarl.lang.core.Agent;
import fr.utbm.info.vi51.framework.FrameworkLauncher;
import fr.utbm.info.vi51.framework.environment.AgentBody;
import fr.utbm.info.vi51.framework.environment.DynamicType;
import fr.utbm.info.vi51.framework.environment.ImmobileObject;
import fr.utbm.info.vi51.framework.gui.BehaviorTypeSelector;
import fr.utbm.info.vi51.framework.gui.FrameworkGUI;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Rectangle2f;
import fr.utbm.info.vi51.framework.util.LocalizedString;
import fr.utbm.info.vi51.framework.util.SpawnMapping;
import fr.utbm.info.vi51.project.GUI.Graphics.Frame.Window;
import fr.utbm.info.vi51.project.agent.Artist;
import fr.utbm.info.vi51.project.agent.Rabbit;
import fr.utbm.info.vi51.project.agent.spectator;
import fr.utbm.info.vi51.project.agent.SecurityAgent;
import fr.utbm.info.vi51.project.environment.WorldModel;
import fr.utbm.info.vi51.project.guiOld.GUI;
import fr.utbm.info.vi51.project.environment.Semantics;

public class MainProgram {

	private static float WORLD_SIZE_X = 500;
	private static float WORLD_SIZE_Y = 500;
	private static int NUMBER_ARTIST = 0;
	private static int NUMBER_SPECTATOR = 1;
	private static int NUMBER_SECURITYAGENT = 0;

	/** Main program.
	 * 
	 * @param argv are the command line arguments.
	 * @throws Exception 
	 */	
	public static void main(String[] argv) throws Exception {

		System.out.println(LocalizedString.getString(MainProgram.class, "INTRO_MESSAGE")); //$NON-NLS-1$

		/*DynamicType type = BehaviorTypeSelector.open();
		
		if(type == null){
			System.exit(0);
		}*/
		
		WorldModel environment = new WorldModel(WORLD_SIZE_X, WORLD_SIZE_Y);

		
		List<ImmobileObject> listIm = new ArrayList<ImmobileObject>();
		
		//listIm.add(new ImmobileObject(UUID.randomUUID(), new Rectangle2f(new Point2f(3, 2), new Point2f(100, 100)), new Point2f(100,100), Semantics.SCENE));
		listIm.add(new ImmobileObject(UUID.randomUUID(), new Rectangle2f(new Point2f(3, 2), new Point2f(10, 10)), new Point2f(100,100), Semantics.STAND_MIAM));
		//listIm.add(new ImmobileObject(UUID.randomUUID(), new Rectangle2f(new Point2f(3, 2), new Point2f(100, 100)), new Point2f(400,100), Semantics.STAND_MIAM));
		//listIm.add(new ImmobileObject(UUID.randomUUID(), new Rectangle2f(new Point2f(3, 2), new Point2f(100, 100)), new Point2f(150,150), Semantics.STAND_MIAM));
		/*listIm.add(new ImmobileObject(UUID.randomUUID(), new Rectangle2f(new Point2f(3, 2), new Point2f(100, 100)), new Point2f(400,234), "Scene"));
		*/
		environment.setImmobileObject(listIm);
		
		
		for (int i = 0; i < NUMBER_ARTIST; ++i) {
			environment.createArtist();
		}
		for (int i = 0; i < NUMBER_SPECTATOR; ++i) {
			environment.createSpectator();
		}
		for (int i = 0; i < NUMBER_SECURITYAGENT; ++i) {
			environment.createSecurityAgent();
		}
		
		
		Window w = Window.getInstance();
		w.setEnvironment(environment);
		w.run();
		
		
		//FrameworkGUI gui = new GUI(WORLD_SIZE_X, WORLD_SIZE_Y,environment.getTimeManager());
		
		FrameworkLauncher.launchSimulation(
				environment,
				new ApplicationMapping(),
				DynamicType.STEERING,
				w);
		
				System.out.println("je suis à la fin)");
				
	}

	/**
	 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
	 * @version $Name$ $Revision$ $Date$
	 */
	private static class ApplicationMapping extends SpawnMapping {

		@Override
		public Class<? extends Agent> getAgentTypeForBody(AgentBody body) {

			System.out.print("création mapping");
			if(body.getName() == "ARTIST") {
				return Artist.class;
			} else if (body.getName() == "SPECTATOR") {
				return spectator.class;
			} else if (body.getName() == "SECURITYAGENT") {
				return SecurityAgent.class;
			}
			return null;
			//return Artist.class;
		}

	}

}
