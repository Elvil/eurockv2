package fr.utbm.info.vi51.project.GUI.Graphics.GraphicComponent;

import javax.swing.JComponent;

import fr.utbm.info.vi51.project.GUI.Graphics.AbstractComponent;

public class LabelComponent  extends AbstractComponent {

	private LabelGraphic label;
	public LabelComponent(String message, int x, int y, int w, int h)
	{
		this.label = new LabelGraphic(message,x,y,w,h);
	}
	@Override
	public JComponent getSwingComponent() {
		return this.label;
	}

}
