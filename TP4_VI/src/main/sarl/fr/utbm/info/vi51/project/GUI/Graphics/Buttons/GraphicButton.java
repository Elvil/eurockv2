package fr.utbm.info.vi51.project.GUI.Graphics.Buttons;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;

public class GraphicButton extends JButton  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2889193335246290667L;

	public GraphicButton(String t)
	{
		super(t);
	}


	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.clearRect(0, 0, getWidth(), getHeight());
		g2.setStroke(new BasicStroke(0));

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f)); 

		g2.setColor(new Color(34, 102, 102));
		
		if (this.getModel().isRollover())
			g2.setColor(new Color(170, 132, 57));
		if(this.getModel().isPressed())
			g2.setColor(new Color(170, 57, 57));
		if(this.getModel().isSelected())
			g2.setColor(new Color(233, 81, 29));
		
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.white);
		// Finding size of text so can position in center. 
		FontRenderContext frc = new FontRenderContext(null, false, false);
		Rectangle2D r = getFont().getStringBounds(getText(), frc); 
		float xMargin = (float)(getWidth()-r.getWidth())/2;
		float yMargin = (float)(getHeight()-getFont().getSize())/2;
		// Draw the text in the center 
		g2.drawString(getText(), xMargin, getFont().getSize() + yMargin);
		g2.dispose();
		//super.paintComponent(g);
	}

	/**
	 * @param evt Not used here, changing just colors when entering 
	 */
	public void mouseEntered(java.awt.event.MouseEvent evt) {
		this.setBackground(Color.GREEN);
	}

	/**
	 * @param evt Not used here, changing just colors when exiting
	 */
	public void mouseExited(java.awt.event.MouseEvent evt) {
		this.setBackground(Color.cyan);
	}

}
