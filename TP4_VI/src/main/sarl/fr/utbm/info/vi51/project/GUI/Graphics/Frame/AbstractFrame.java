package fr.utbm.info.vi51.project.GUI.Graphics.Frame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.utbm.info.vi51.framework.environment.EnvironmentListener;
import fr.utbm.info.vi51.project.GUI.Graphics.AbstractComponent;
import fr.utbm.info.vi51.project.GUI.Graphics.GraphicAgent.AbstractGraphicAgent;
import fr.utbm.info.vi51.project.GUI.Graphics.GraphicBuilding.AbstractGraphicBuilding;
import fr.utbm.info.vi51.project.GUI.Graphics.Layout.AbstractLayout;
import fr.utbm.info.vi51.project.GUI.Graphics.Layout.LayoutAgent;
import fr.utbm.info.vi51.project.GUI.Graphics.Layout.MouseManager;


public abstract class AbstractFrame extends JFrame implements IFrame, EnvironmentListener {
	private static final long serialVersionUID = 1L;
	protected AbstractLayout<AbstractComponent> gui;
	protected AbstractLayout<AbstractGraphicBuilding> map;
	protected LayoutAgent<AbstractGraphicAgent> agentLayout;
	protected JLayeredPane jlp;
	protected JPanel mainPanel;
	protected MouseManager mouse;
//	private int x, y;
	private int locationX=0, locationY=0;
	protected AbstractFrame(String title, int h,  int w) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(new Point(this.locationX,this.locationY));
		setSize(h,w);
		setVisible(true);
		this.mainPanel = new JPanel();

		this.jlp = new JLayeredPane();
		mouse = new MouseManager(0,0);
		
    	
		//Centrage de la frame
    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    	this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

	}
	public AbstractLayout<AbstractGraphicAgent> getNetwork()
	{
		return this.agentLayout;
	}
	public AbstractLayout<AbstractGraphicBuilding> getMap()
	{
		return this.map;
	}
	public AbstractLayout<AbstractComponent> getGUI()
	{
		return this.gui;
	}
	public void addBuilding(AbstractGraphicBuilding c)
	{
		this.map.addComponent(c);
	}
	public void addAgent(AbstractGraphicAgent c)
	{
		this.agentLayout.addComponent(c);
	}
	public void addGUI(AbstractComponent c)
	{
		this.gui.addComponent(c);
	}
}