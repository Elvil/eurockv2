




package fr.utbm.info.vi51.project.GUI.Graphics.Layout;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {
	private int x, y;
	private List<AbstractLayout<?>> l;
	public MouseManager(int x, int y)
	{
		this.x = x;
		this.y = y;
		l = new ArrayList<AbstractLayout<?>>();
	}
	public void add(AbstractLayout<?> al)
	{
		l.add(al);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// Not used for now
	}
	@Override
	public void mousePressed(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
		for (AbstractLayout<?> al : l)
			System.out.println("Source:"+al.actionClick(this.x, this.y));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// Not used for now
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// Not used for now
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// Not used for now
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		int newX = (this.x-e.getX());
		int newY = (this.y-e.getY());
		for (AbstractLayout<?> al : l)
		{
			al.setLocation((int)al.getLocation().getX()-newX, (int) al.getLocation().getY()-newY);
			al.repaint();
		}
		this.x = e.getX();
		this.y = e.getY();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
	    if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
	    	for (AbstractLayout<?> al : l)
				al.zoom(e.getUnitsToScroll());
			
	    }
		
	}

}
