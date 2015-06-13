package fr.utbm.info.vi51.project;


import io.sarl.lang.core.Agent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.utbm.info.vi51.framework.FrameworkLauncher;
import fr.utbm.info.vi51.framework.environment.AgentBody;
import fr.utbm.info.vi51.framework.environment.DynamicType;
import fr.utbm.info.vi51.framework.environment.ImmobileObject;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Rectangle2f;
import fr.utbm.info.vi51.framework.util.LocalizedString;
import fr.utbm.info.vi51.framework.util.SpawnMapping;
import fr.utbm.info.vi51.project.GUI.Graphics.Frame.Window;
import fr.utbm.info.vi51.project.agent.Artist;
import fr.utbm.info.vi51.project.agent.SecurityAgent;
import fr.utbm.info.vi51.project.agent.spectator;
import fr.utbm.info.vi51.project.environment.Semantics;
import fr.utbm.info.vi51.project.environment.WorldModel;

public class MainProgram {

	public static float WORLD_SIZE_X = 800;
	public static float WORLD_SIZE_Y = 800;
	private static int NUMBER_ARTIST = 0;
	private static int NUMBER_SPECTATOR = 100;
	private static int NUMBER_SECURITYAGENT = 30;

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
		
		listIm.add(new ImmobileObject(UUID.randomUUID(), new Rectangle2f(new Point2f(3, 2), new Point2f(100, 100)), new Point2f(50,50), Semantics.SCENE_GRAND));
		listIm.add(new ImmobileObject(UUID.randomUUID(), new Rectangle2f(new Point2f(5, 5), new Point2f(100, 100)), new Point2f(50,550), Semantics.SCENE_LOGGIA));
		listIm.add(new ImmobileObject(UUID.randomUUID(), new Rectangle2f(new Point2f(3, 2), new Point2f(100, 100)), new Point2f(550,50), Semantics.SCENE_PLAGE));
		listIm.add(new ImmobileObject(UUID.randomUUID(), new Rectangle2f(new Point2f(3, 2), new Point2f(100, 100)), new Point2f(550,550), Semantics.STAND_MIAM));
//		listIm.add(new ImmobileObject(UUID.randomUUID(), new Rectangle2f(new Point2f(3, 2), new Point2f(10, 100)), new Point2f(790,550), Semantics.EXIT));
		//listIm.add(new ImmobileObject(UUID.randomUUID(), new Circle2f(400,400,20), new Point2f(0,0), Semantics.EXIT));
		listIm.add(new ImmobileObject(UUID.randomUUID(), new Rectangle2f(new Point2f(0, 0), new Point2f(50, 50)), new Point2f(0,400), Semantics.EXIT));
//			
		listIm.add(new ImmobileObject(UUID.randomUUID(), new Rectangle2f(new Point2f(0, 0), new Point2f(50, 50)), new Point2f(750,400), Semantics.EXIT));
		//listIm.add(new ImmobileObject(UUID.randomUUID(), new Rectangle2f(new Point2f(3, 2), new Point2f(100, 100)), new Point2f(400,400), Semantics.SCENE));
		environment.setImmobileObject(listIm);
		
		Window w = Window.getInstance();
		
		for (int i = 0; i < NUMBER_ARTIST; ++i) {
			environment.createArtist();
		}
		for (int i = 0; i < NUMBER_SPECTATOR; ++i) {
			environment.createSpectator(new Point2f(MainProgram.WORLD_SIZE_X/2,MainProgram.WORLD_SIZE_Y/2));
		}
		for (int i = 0; i < NUMBER_SECURITYAGENT; ++i) {
			environment.createSecurityAgent();
		}
		Thread.sleep(1);

		w.setEnvironment(environment);
		w.run();
		
		
		
		//FrameworkGUI gui = new GUI(WORLD_SIZE_X, WORLD_SIZE_Y,environment.getTimeManager());
		
		FrameworkLauncher.launchSimulation(
				environment,
				new ApplicationMapping(),
				DynamicType.STEERING,
				w);
		
	//	environment.displayTree();
				
	}

	/**
	 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
	 * @version $Name$ $Revision$ $Date$
	 */
	private static class ApplicationMapping extends SpawnMapping {

		@Override
		public Class<? extends Agent> getAgentTypeForBody(AgentBody body) {

			//System.out.println("création de l'agent := " + body.getName());
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
