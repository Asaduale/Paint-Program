package commands;

import java.awt.Graphics2D;

import shapes.Oval;

/**
 * DrawOvalCommand is a class that implements the Command Design Pattern, implementing the DrawingCommand interface.
 *
 */
public class DrawOvalCommand implements DrawingCommand {

	private Oval oval;
	/**
	 * Constructor
	 * @param o - Oval
	 */
	public DrawOvalCommand(Oval o) {
		this.oval = o;
	}
	
	/**
	 * Overrides execute from DrawingCommand, executes drawing of an Oval.
	 */
	@Override
	public void execute(Graphics2D g2d) {
		g2d.setColor(this.oval.getColor());
		g2d.setStroke(this.oval.getStroke());
		int width = this.oval.getWidth();
		int height = this.oval.getHeight();
		int x1 = this.oval.getCornerX();
		int x2 = this.oval.getCornerY();

		g2d.drawOval(x1, x2, width, height);
		if (this.oval.getFilled()) {
			g2d.setColor(this.oval.getFillColor());
			g2d.fillOval(x1, x2, width, height);
		}
	}

}
