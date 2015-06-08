package fr.utbm.info.vi51.project.GUI.Graphics.Buttons;

import java.awt.event.ActionEvent;

import fr.utbm.info.vi51.project.GUI.Graphics.Layout.AbstractLayout;

public class ZoomButton extends ButtonComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2765495561532237764L;
	protected double zoomFactor;
	public ZoomButton(String text, int x, int y, int h, int w, double zoom) {
		super(text, x, y, h, w);
		this.zoomFactor = zoom;
	}

	@Override
	public void action(ActionEvent evt) {
		for (AbstractLayout<?> m : this.m)
		{
			m.zoom(this.zoomFactor);
			m.repaint();
		}
	}
}
