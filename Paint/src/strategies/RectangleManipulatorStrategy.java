package strategies;

import java.awt.event.MouseEvent;

import shapes.Point;
import shapes.Rectangle;
import commands.DrawRectangleCommand;
import commands.DrawingCommand;

/**
 * Rectangle Manipulator Strategy inherits from Shape Manipulator Strategy. Here is where the user's MouseEvents are being
 * handled in the Paint Program.
 *
 */
public class RectangleManipulatorStrategy extends ShapeManipulatorStrategy {

	private Rectangle rectangle;

	/**
	 * Calls makeShape, adds the rectangle drawn to the DrawRectangleCommand which then executes the Rectangle drawing.
	 */
	@Override
	public DrawingCommand makeDrawShapeCommand(MouseEvent e, boolean filled) {
		this.makeShape(e, filled);
		return new DrawRectangleCommand(this.rectangle);
	}
	/**
	 * MouseEvents for Rectangle
	 * @param e - MouseEvent
	 * @param filled - boolean, true if filled and false otherwise.
	 */
	private void makeShape(MouseEvent e, boolean filled) {
		if (e.getID() == MouseEvent.MOUSE_PRESSED) {
			Point corner = new Point(e.getX(), e.getY());
			this.rectangle = new Rectangle(corner, e.getX(), e.getY());
			this.rectangle.setInitial(corner);
		} 
		this.rectangle.setX1(e.getX());
		this.rectangle.setY1(e.getY());
		super.setAttributes(this.rectangle, filled);

	}
}
