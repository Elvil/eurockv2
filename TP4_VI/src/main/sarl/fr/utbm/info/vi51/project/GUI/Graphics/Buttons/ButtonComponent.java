package fr.utbm.info.vi51.project.GUI.Graphics.Buttons;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;

import fr.utbm.info.vi51.project.GUI.Graphics.AbstractComponent;
import fr.utbm.info.vi51.project.GUI.Graphics.Layout.AbstractLayout;


/**
 * 
 */
public abstract class ButtonComponent extends AbstractComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3341644248986327368L;
	protected List<AbstractLayout<?>> m;
	protected Rectangle bounds;
	private JButton button;
	private void init() {
		this.button.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		        action(evt);
		      }
		    });
	}
	public ButtonComponent(String text, int x, int y, int h, int w) {
		this.button = new GraphicButton(text);
    	this.button.setText(text);
    	this.bounds = new Rectangle(x, y, h, w);
    	this.button.setSize(w,h);
    	this.button.setBounds(this.bounds);
    	this.m = new ArrayList<AbstractLayout<?>>();
    	init();
    }
	public void setLayout(AbstractLayout<?> l)
	{
		this.m.add(l);
	}
    public void move(int x, int y) {
		this.bounds.setLocation(x, y);
		this.button.setBounds(this.bounds);
	}

    public JComponent getSwingComponent()
    {
    	return this.button;
    }
    public abstract void action(ActionEvent evt);

}