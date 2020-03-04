package commands;

import java.awt.Graphics2D;

import shapes.Circle;

/**
 * DrawCircleCommand is a class that implements the Command Design Pattern by implementing the DrawingCommand interface.
 *
 */
public class DrawCircleCommand implements DrawingCommand {
	private Circle circle;
	
	/**
	 * Constructor
	 * @param c - Circle.
	 */
	public DrawCircleCommand(Circle c) {
		this.circle = c;
	}

	/**
	 * Overrides execute from DrawingCommand interface, executes the drawing of a Circle.
	 */
	@Override
	public void execute(Graphics2D g2d) {
		g2d.setColor(this.circle.getColor());
		g2d.setStroke(this.circle.getStroke());
		int x = this.circle.getInitial().getX();
		int y = this.circle.getInitial().getY();
		int radius = this.circle.getRadius();

		g2d.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
		if (this.circle.getFilled()) {
			g2d.setColor(this.circle.getFillColor());
			g2d.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
		}
	}

}
