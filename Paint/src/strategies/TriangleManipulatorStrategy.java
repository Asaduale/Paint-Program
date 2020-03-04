package strategies;

import java.awt.event.MouseEvent;

import shapes.Point;
import shapes.Triangle;
import commands.DrawTriangleCommand;
import commands.DrawingCommand;

/**
 * Triangle Manipulator Strategy inherits from Shape Manipulator Strategy. Here is where the user's MouseEvents are being
 * handled in the Paint Program.
 * @author asadali
 *
 */
public class TriangleManipulatorStrategy extends ShapeManipulatorStrategy {
	
	private Triangle triangle;
	
	/**
	 * Calls makeShape, adds the Triangle drawn to the DrawTriangleCommand which then executes the Triangle drawing.
	 */
	@Override
	public DrawingCommand makeDrawShapeCommand(MouseEvent e, boolean filled) {
		this.makeShape(e, filled);
		return new DrawTriangleCommand(this.triangle);
	}
	
	/**
	 * MouseEvent for Triangle
	 * @param e - MouseEvent
	 * @param filled - boolean, true if filled and false otherwise.
	 */
	private void makeShape(MouseEvent e, boolean filled) {
		if (e.getID() == MouseEvent.MOUSE_PRESSED) {
			Point corner = new Point(e.getX(), e.getY());
			this.triangle = new Triangle(corner, e.getX(), e.getY());
			this.triangle.setInitial(corner);
		}
		this.triangle.setX1(e.getX());
		this.triangle.setY1(e.getY());
		super.setAttributes(this.triangle, filled);

		
	}

}
