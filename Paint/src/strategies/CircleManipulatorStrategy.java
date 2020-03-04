package strategies;

import java.awt.event.MouseEvent;

import shapes.Circle;
import shapes.Point;
import commands.DrawCircleCommand;
import commands.DrawingCommand;

/**
 * Circle Manipulator Strategy inherits from Shape Manipulator Strategy. Here is where the user's MouseEvents are being
 * handled in the Paint Program.
 *
 */
public class CircleManipulatorStrategy extends ShapeManipulatorStrategy {

	private Circle circle;
	
	/**
	 * Calls makeShape, adds the circle drawn to the DrawCircleCommand which then executes the circle drawing.
	 */
	@Override
	public DrawingCommand makeDrawShapeCommand(MouseEvent e, boolean filled) {
		this.makeShape(e, filled);
		return new DrawCircleCommand(this.circle);
	}
	
	/**
	 * MouseEvent for Circle.
	 * @param e - MouseEvent
	 * @param filled - boolean, true if filled, false otherwise.
	 */
	private void makeShape(MouseEvent e, boolean filled) {
		if (e.getID() == MouseEvent.MOUSE_PRESSED) {
			Point centre = new Point(e.getX(), e.getY());
			this.circle=new Circle(centre, 0);
			this.circle.setInitial(centre);
		} 
		int radiusX = this.circle.getInitial().getX()-e.getX();
		int radiusY = this.circle.getInitial().getY()-e.getY();
		int radius = (int) Math.round(Math.sqrt(radiusX*radiusX + radiusY*radiusY));
		this.circle.setRadius(radius);
		super.setAttributes(this.circle, filled);

	}

}
