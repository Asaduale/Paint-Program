package strategies;

import java.awt.event.MouseEvent;

import shapes.Point;
import shapes.Selection;
import commands.DrawSelectionCommand;
import commands.DrawingCommand;

/**
 * Selection Paste Manipulator Strategy inherits from Shape Manipulator Strategy. Here is where the user's MouseEvents are being
 * handled in the Paint Program.
 *
 */
public class SelectionPasteStrategy extends ShapeManipulatorStrategy {
	
	
	@Override
	public DrawingCommand makeDrawShapeCommand(MouseEvent e, boolean filled) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Handle Selection Paste Manipulator Strategy
	 * @param point - Point
	 * @param fillState - boolean
	 * @param dsc - DrawSelectionCommand
	 * @param isCopy - boolean
	 * @param isCut - boolean
	 * @return
	 */
	public DrawingCommand paste(Point point, boolean fillState, DrawSelectionCommand dsc, boolean isCopy, boolean isCut) {
		Selection s = dsc.getSelection();
		s.setNewLocation(point);
		s.setIsPaste(true);
		s.setIsCopy(isCopy);
		s.setIsCut(isCut);
		
		return new DrawSelectionCommand(s);
	}

}
