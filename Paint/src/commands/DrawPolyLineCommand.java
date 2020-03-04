package commands;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import shapes.PolyLine;

/**
 * DrawPolylineCommand is a class that implements the Command Design Pattern by implementing the DrawingCommand interface.
 *
 */
public class DrawPolyLineCommand implements DrawingCommand{
	
	private PolyLine polyLine;
	
	/**
	 * Constructor.
	 * @param p - Polyline.
	 */
	public DrawPolyLineCommand(PolyLine p){
		this.polyLine = p;
	}
	
	/**
	 * Overrides the execute method in DrawingCommand, executes the drawing of Polyline.
	 */
	@Override
	public void execute(Graphics2D g2d) {
		g2d.setColor(this.polyLine.getColor());
		BasicStroke new_stroke = new BasicStroke(this.polyLine.getStroke().getLineWidth(), 1, 1);
		g2d.setStroke(new_stroke);
		int edge1X = this.polyLine.getInitial().getX();
		int edge1Y = this.polyLine.getInitial().getY();
		int edge2X = this.polyLine.getEdge2().getX();
		int edge2Y = this.polyLine.getEdge2().getY();
		g2d.drawLine(edge1X, edge1Y, edge2X, edge2Y);
		
	}

}
