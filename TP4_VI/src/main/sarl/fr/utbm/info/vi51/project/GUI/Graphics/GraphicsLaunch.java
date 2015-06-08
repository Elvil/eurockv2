package fr.utbm.info.vi51.project.GUI.Graphics;


import java.util.Random;

import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.project.GUI.Graphics.Buttons.AddAgentButton;
import fr.utbm.info.vi51.project.GUI.Graphics.Buttons.CenterButton;
import fr.utbm.info.vi51.project.GUI.Graphics.Buttons.ZoomButton;
import fr.utbm.info.vi51.project.GUI.Graphics.Frame.Window;
import fr.utbm.info.vi51.project.GUI.Graphics.GraphicAgent.AbstractGraphicAgent;
import fr.utbm.info.vi51.project.GUI.Graphics.GraphicAgent.GraphicSpectator;
import fr.utbm.info.vi51.project.GUI.Graphics.GraphicBuilding.GraphicScene;

@SuppressWarnings("deprecation")
public class GraphicsLaunch {

	public static Window w;

	public static void main(String[] args) {
		Window w = Window.getInstance();
		w.setVisible(true);
		ZoomButton zplus = new ZoomButton("+", 0, 200, 40, 40, 10);
    	ZoomButton zminus = new ZoomButton("-", 0, 240, 40, 40, -10);
    	AddAgentButton busButt = new AddAgentButton("A", 0, 280, 40, 40);
    	
    	AbstractGraphicAgent agentTemp;
    	for (int i = 0; i < 10; i++)
    	{
    		Random rand = new Random();
    		int x = rand.nextInt((800 - 0) + 1) + 0;
    		int y = rand.nextInt((800 - 0) + 1) + 0;
    		agentTemp = new GraphicSpectator(null, new Point2f(x, y));
    		w.addAgent(agentTemp);
    		//w.addNetworkElement(new YellowBus(x,y) );
    	}
    	
    	int[] px = { 250, 200, 550, 500 };
    	int[] py = { 100, 200, 200, 100 };
    	w.addBuilding(new GraphicScene(px, py));
    	
    	zplus.setLayout(w.getMap());
    	zminus.setLayout(w.getMap());
    	
    	zplus.setLayout(w.getNetwork());
    	zminus.setLayout(w.getNetwork());
    	
    	busButt.setLayout(w.getMap());

    	w.addGUI(zplus);
    	w.addGUI(zminus);
    	w.addGUI(busButt);
    	w.repaint();
	}
	
}
