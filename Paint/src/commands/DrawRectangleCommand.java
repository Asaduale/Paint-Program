package commands;

import java.awt.Graphics2D;

import shapes.Rectangle;

/**
 * DrawRectangleCommand is a class that implements the Command Design Pattern by implementing the DrawingCommand interface.
 *
 */
public class DrawRectangleCommand implements DrawingCommand {
	
	private Rectangle rect;
	
	/**
	 * Constructor.
	 * @param r - Rectangle.
	 */
	public DrawRectangleCommand(Rectangle r){
		this.rect = r;
	}
	
	/**
	 * Overrides execute from DrawingCommand and executes the drawing of a Rectangle.
	 */
	@Override
	public void execute(Graphics2D g2d) {
		g2d.setColor(this.rect.getColor());
		g2d.setStroke(this.rect.getStroke());
		int width = this.rect.getWidth();
		int height = this.rect.getHeight();
		int x1 = this.rect.getCornerX(); 
		int x2 = this.rect.getCornerY();
		g2d.drawRect(x1, x2, width, height);
		if (this.rect.getFilled()) {
			g2d.setColor(this.rect.getFillColor());
			g2d.fillRect(x1, x2, width, height);
	}
		
	}

}
