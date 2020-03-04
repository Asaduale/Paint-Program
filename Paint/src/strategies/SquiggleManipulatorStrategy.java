package strategies;


import java.awt.event.MouseEvent;

import shapes.Point;
import shapes.Squiggle;
import commands.DrawSquiggleCommand;
import commands.DrawingCommand;

/**
 * Squiggle Manipulator Strategy inherits from Shape Manipulator Strategy. Here is where the user's MouseEvents are being
 * handled in the Paint Program.
 *
 */
public class SquiggleManipulatorStrategy extends ShapeManipulatorStrategy{
	
	private Squiggle squiggle;
	
	/**
	 * Calls makeShape, adds the squiggle drawn to the DrawSquiggleCommand which then executes the squiggle drawing.
	 */
	@Override
	public DrawingCommand makeDrawShapeCommand(MouseEvent e, boolean filled) {
		this.makeShape(e, filled);
		return new DrawSquiggleCommand(this.squiggle);
	}

	/**
	 * MouseEvent for Squiggle
	 * @param e - MouseEvent
	 * @param filled - boolean, true if filled and false otherwise.
	 */
	private void makeShape(MouseEvent e, boolean filled) {
		if (e.getID() == MouseEvent.MOUSE_PRESSED) {
			Point p = new Point(e.getX(), e.getY());
			this.squiggle = new Squiggle();
			this.squiggle.addPoints(p);	
		} 
		Point p = new Point(e.getX(), e.getY());
		this.squiggle.addPoints(p);
		super.setAttributes(this.squiggle, filled);
		
	}


}
