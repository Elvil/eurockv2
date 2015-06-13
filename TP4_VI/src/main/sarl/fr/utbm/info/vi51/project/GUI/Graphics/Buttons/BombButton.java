package fr.utbm.info.vi51.project.GUI.Graphics.Buttons;

import java.awt.event.ActionEvent;

import fr.utbm.info.vi51.project.GUI.Graphics.Layout.AbstractLayout;
import fr.utbm.info.vi51.project.environment.BombObject;

public class BombButton extends ButtonComponent {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2765495561532237764L;
		protected double bombSize;
		public BombButton(String text, int x, int y, int h, int w, double size) {
			super(text, x, y, h, w);
			this.bombSize = size;
		}

		@Override
		public void action(ActionEvent evt) {
				//m.zoom(this.zoomFactor);
			BombObject.RAYON += this.bombSize;
			if (BombObject.RAYON <= 10)
				BombObject.RAYON = 10;
		}
	}
