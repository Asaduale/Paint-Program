package strategies;

import java.awt.BasicStroke;
import java.awt.event.MouseEvent;

import shapes.Shapes;
import ca.utoronto.utm.paint.ColorChooserPanel;
import ca.utoronto.utm.paint.ShapeChooserPanel;
import commands.DrawingCommand;

/**
 * Shape Manipulator Strategy is an abstract class, has a setAttribute method which all subclasses use.
 *
 */
public abstract class ShapeManipulatorStrategy {
	
	public abstract DrawingCommand makeDrawShapeCommand(MouseEvent e, boolean filled);
	
	/**
	 * Set the attributes of the shape; filled, stroke, color.
	 * @param s - Shapes
	 * @param filled - boolean, true if filled and false otherwise.
	 */
	protected void setAttributes(Shapes s, boolean filled) {
		s.setFilled(filled);
		s.setStroke(new BasicStroke(ShapeChooserPanel.getStrokeValue()));
		if (ColorChooserPanel.getSelected() == 1) {
			s.setColor(ColorChooserPanel.getBlank().getBackground());
			s.setFillColor(ColorChooserPanel.getBlank1().getBackground());
		} else {
			s.setColor(ColorChooserPanel.getBlank1().getBackground());
			s.setFillColor(ColorChooserPanel.getBlank().getBackground());

		}
	}
}
