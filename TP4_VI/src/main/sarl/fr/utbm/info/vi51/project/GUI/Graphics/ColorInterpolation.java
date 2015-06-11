package fr.utbm.info.vi51.project.GUI.Graphics;

import java.awt.Color;

public class ColorInterpolation {
	public static Color interpolation (Color x, Color y, float blending)
	{
		if (blending < 1) {
			float inverse_blending = 1 - blending;

			float red =   x.getRed()   * blending   +   y.getRed()   * inverse_blending;
			float green = x.getGreen() * blending   +   y.getGreen() * inverse_blending;
			float blue =  x.getBlue()  * blending   +   y.getBlue()  * inverse_blending;

			//note that if i pass float values they have to be in the range of 0.0-1.0 
			//and not in 0-255 like the ones i get returned by the getters.
			return new Color (red / 255, green / 255, blue / 255);
		}
		else
			return x;
	}
}
