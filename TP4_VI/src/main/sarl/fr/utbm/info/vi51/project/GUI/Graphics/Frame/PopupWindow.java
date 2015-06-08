package fr.utbm.info.vi51.project.GUI.Graphics.Frame;

import fr.utbm.info.vi51.framework.environment.EnvironmentEvent;
import fr.utbm.info.vi51.project.GUI.Graphics.GraphicComponent.LabelComponent;
import fr.utbm.info.vi51.project.GUI.Graphics.Layout.LayoutGUI;

public class PopupWindow extends AbstractFrame {

	public PopupWindow(String message)
	{
		super("Popup", 400, 150);
		
		int h = 400;
		int w = 150;
    	this.gui = new LayoutGUI<>(h, w);
        this.jlp.add(this.gui, new Integer(1));
        
        LabelComponent label = new LabelComponent(message, 10, 10, 390, 70);
        this.addGUI(label);
        
    	this.setContentPane(this.jlp);
    	this.setResizable(false);    	repaint();
	}

	@Override
	public void environmentChanged(EnvironmentEvent event) {
		// TODO Auto-generated method stub
		
	}


}
