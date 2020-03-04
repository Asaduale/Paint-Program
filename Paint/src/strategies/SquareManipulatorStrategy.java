package strategies;

import java.awt.event.MouseEvent;

import shapes.Point;
import shapes.Square;
import commands.DrawSquareCommand;
import commands.DrawingCommand;

/**
 * Square Manipulator Strategy inherits from Shape Manipulator Strategy. Here is where the user's MouseEvents are being
 * handled in the Paint Program.
 *
 */
public class SquareManipulatorStrategy extends ShapeManipulatorStrategy {

	private Square square;

	/**
	 * Calls makeShape, adds the square drawn to the DrawSquareCommand which then executes the Square drawing.
	 */
	@Override
	public DrawingCommand makeDrawShapeCommand(MouseEvent e, boolean filled) {
		this.makeShape(e,filled);
		return new DrawSquareCommand(this.square);
	}

	/**
	 * MouseEvent for Square.
	 * @param e - MouseEvent
	 * @param filled - boolean, true if filled and false otherwise.
	 */
	private void makeShape(MouseEvent e, boolean filled) {
		if (e.getID() == MouseEvent.MOUSE_PRESSED) {
			Point corner = new Point(e.getX(), e.getY());
			this.square = new Square(corner, e.getX(), e.getY());
			this.square.setInitial(corner);
		} 
		this.square.setX1(e.getX());
		this.square.setY1(e.getY());
		super.setAttributes(this.square, filled);

	}

}
