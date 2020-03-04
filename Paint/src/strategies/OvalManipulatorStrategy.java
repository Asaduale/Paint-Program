package strategies;

import java.awt.event.MouseEvent;

import shapes.Oval;
import shapes.Point;
import commands.DrawOvalCommand;
import commands.DrawingCommand;

/**
 * Oval Manipulator Strategy inherits from Shape Manipulator Strategy. Here is where the user's MouseEvents are being
 * handled in the Paint Program.
 *
 */
public class OvalManipulatorStrategy extends ShapeManipulatorStrategy {

	private Oval oval;
	
	/**
	 * Calls makeShape, adds the oval drawn to the DrawOvalCommand which then executes the oval drawing.
	 */
	@Override
	public DrawingCommand makeDrawShapeCommand(MouseEvent e, boolean filled) {
		this.makeShape(e, filled);
		return new DrawOvalCommand(this.oval);
	}

	/**
	 * MouseEvent for Oval.
	 * @param e - MouseEvent
	 * @param filled - boolean, true if filled and false otherwise.
	 */
	private void makeShape(MouseEvent e, boolean filled) {
		if (e.getID() == MouseEvent.MOUSE_PRESSED) {
			Point corner = new Point(e.getX(), e.getY());
			this.oval = new Oval(corner, e.getX(), e.getY());
			this.oval.setInitial(corner);
		}
				
		this.oval.setX1(e.getX());
		this.oval.setY1(e.getY());
		super.setAttributes(this.oval, filled);
		
	}

}
