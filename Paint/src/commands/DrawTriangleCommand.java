package commands;

import java.awt.Graphics2D;

import shapes.Triangle;

/**
 * DrawTriangleCommand is a class that implements the Command Design Pattern by implementing the DrawingCommand interface.
 *
 */
public class DrawTriangleCommand implements DrawingCommand {

	private Triangle tri;

	/**
	 * Constructor
	 * @param t - Triangle
	 */
	public DrawTriangleCommand(Triangle t) {
		this.tri = t;
	}
	
	/**
	 * Overrides execute from DrawingCommand and executes the drawing of a Triangle.
	 */
	@Override
	public void execute(Graphics2D g2d) {
		g2d.setColor(this.tri.getColor());
		g2d.setStroke(this.tri.getStroke());
		int width = this.tri.getWidth();
		int height = this.tri.getHeight();
		int x11 = this.tri.getInitial().getX();
		int x22 = this.tri.getCornerY();

		int[] x0 = { x11, x11 + width / 2, x11 - width / 2 };
		int[] y0 = { x22, x22 + height, height + x22 };
		g2d.drawPolygon(x0, y0, 3);
		if (this.tri.getFilled()) {
			g2d.setColor(this.tri.getFillColor());
			g2d.fillPolygon(x0, y0, 3);

		}

	}

}
