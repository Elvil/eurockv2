package fr.utbm.info.vi51.project.GUI.Graphics;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import fr.utbm.info.vi51.framework.math.Circle2f;
import fr.utbm.info.vi51.framework.math.Rectangle2f;
import fr.utbm.info.vi51.framework.math.Shape2f;

public class ShapeConverter {

	public static Shape2f toShape2f(Shape shape)
	{
		Shape2f s = null;
		if (shape instanceof Rectangle2D)
		{
			s = toRectangle2f((Rectangle2D) shape);
		}
		else if (shape instanceof Ellipse2D)
		{
			s = toCircle2f((Ellipse2D) shape);
		}		
		return s;
	}
	
	public static Shape toShape(Shape2f shape)
	{
		Shape s = null;
		if (shape instanceof Rectangle2f)
		{
			s = toRectangle((Rectangle2f) shape);
		}
		else if (shape instanceof Circle2f)
		{
			s = toEllipse((Circle2f) shape);
		}		
		return s;
	}
	public static Rectangle2D toRectangle(Rectangle2f rect)
	{
		return new Rectangle2D.Double(rect.getUpper().getX(), rect.getUpper().getY(), rect.getWidth(), rect.getHeight());
	}
	public static Ellipse2D toEllipse(Circle2f circle)
	{
		return new Ellipse2D.Double(circle.getCenter().getX(), circle.getCenter().getY(), circle.getRadius(), circle.getRadius());
	}
	public static Rectangle2f toRectangle2f(Rectangle2D rect)
	{
		return new Rectangle2f((float)rect.getX(), (float)rect.getY(), (float)rect.getWidth(), (float)rect.getHeight());
	}
	public static Circle2f toCircle2f(Ellipse2D circle)
	{
		return new Circle2f((float)circle.getX(), (float)circle.getY(), (float)circle.getWidth());
	}
}
