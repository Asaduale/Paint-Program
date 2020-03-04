package commands;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.ArrayList;

import shapes.Point;
import shapes.Squiggle;

/**
 * DrawSquiggleCommand is a class that implements the Command Design Pattern by implementing the DrawingCommand interface.
 *
 */
public class DrawSquiggleCommand implements DrawingCommand{

	private Squiggle squiggle;
	
	/**
	 * Constructor
	 * @param s - Squiggle
	 */
	public DrawSquiggleCommand(Squiggle s){
		this.squiggle = s;
	}
	
	/**
	 * Overrides execute from DrawingCommand and executes the drawing of a Squiggle..
	 */
	@Override
	public void execute(Graphics2D g2d) {
		g2d.setColor(this.squiggle.getColor());
		BasicStroke new_stroke = new BasicStroke(this.squiggle.getStroke().getLineWidth(), 1, 1);
		g2d.setStroke(new_stroke);
		ArrayList<Point> points = this.squiggle.getPoints();
		for(int i=0;i<points.size()-1; i++){
			Point p1=points.get(i);
			Point p2=points.get(i+1);
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}
	}

}
