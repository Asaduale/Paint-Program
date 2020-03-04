package strategies;

import java.awt.event.MouseEvent;

import shapes.Point;
import shapes.PolyLine;
import commands.DrawPolyLineCommand;
import commands.DrawingCommand;

/**
 * Polyline Manipulator Strategy inherits from Shape Manipulator Strategy. Here is where the user's MouseEvents are being
 * handled in the Paint Program.
 *
 */
public class PolyLineManipulatorStrategy extends ShapeManipulatorStrategy {

	private PolyLine polyLine;
	PolyLine polyLine2 = null;

	/**
	 * Calls makeShape, adds the polyline drawn to the DrawPolylineCommand which then executes the Polyline drawing.
	 */
	@Override
	public DrawingCommand makeDrawShapeCommand(MouseEvent e, boolean filled) {
		this.makeShape(e, filled);
		return new DrawPolyLineCommand(this.polyLine);
	}
	
	/**
	 * MouseEvent for Polyline
	 * @param e - MouseEvent
	 * @param filled - boolean, true if filled and false otherwise.
	 */
	private void makeShape(MouseEvent e, boolean filled) {
		if (this.polyLine2 != null && e.getID() == MouseEvent.MOUSE_PRESSED){
			polyLine2.setPrevious(this.polyLine);
			this.polyLine = polyLine2;
		}
		if (this.polyLine2 == null && e.getID() == MouseEvent.MOUSE_PRESSED) {
			Point edge = new Point(e.getX(), e.getY());
			this.polyLine = new PolyLine(edge);
			this.polyLine.setEdge2(edge);
		}
		Point edge2 = new Point(e.getX(), e.getY());
		this.polyLine.setEdge2(edge2);
		this.polyLine2 = new PolyLine(this.polyLine.getEdge2());
		super.setAttributes(this.polyLine, filled);
		

	}

}
