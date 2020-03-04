package strategies;

import java.awt.event.MouseEvent;

import shapes.Point;
import shapes.Selection;
import commands.DrawSelectionCommand;
import commands.DrawingCommand;

/**
 * Selection Manipulator Strategy inherits from Shape Manipulator Strategy. Here is where the user's MouseEvents are being
 * handled in the Paint Program.
 *
 */
public class SelectionManipulatorStrategy extends ShapeManipulatorStrategy {

	private Selection selection;
	
	/**
	 * Calls makeShape, adds the selection drawn to the DrawSelectionCommand which then executes the Selection drawing.
	 */
	@Override
	public DrawingCommand makeDrawShapeCommand(MouseEvent e, boolean filled) {
		this.makeShape(e, filled);		
		return new DrawSelectionCommand(this.selection);
	}

	/**
	 * MouseEvents for Selection.
	 * @param e - MouseEvent
	 * @param filled - boolean, true if filled and false otherwise.
	 */
	private void makeShape(MouseEvent e, boolean filled) {
		if (e.getID() == MouseEvent.MOUSE_PRESSED) {
			Point corner = new Point(e.getX(), e.getY());
			this.selection = new Selection(corner, e.getX(), e.getY());
			this.selection.setInitial(corner);
		}
		this.selection.setX1(e.getX());
		this.selection.setY1(e.getY());
		super.setAttributes(this.selection, filled);
	}

}
