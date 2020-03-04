package commands;

import java.awt.Graphics2D;

import shapes.Square;

/**
 * DrawSquareCommand is a class that implements the Command Design Pattern by implementing the DrawingCommand interface.
 *
 */
public class DrawSquareCommand implements DrawingCommand{
	
	private Square square;
	
	/**
	 * Constructor
	 * @param s - Square
	 */
	public DrawSquareCommand(Square s){
		this.square = s;
	}
	
	/**
	 * Overrides execute from DrawingCommand and executes the drawing of a Square.
	 */
	@Override
	public void execute(Graphics2D g2d) {
		g2d.setColor(this.square.getColor());
		g2d.setStroke(this.square.getStroke());
		int x = this.square.upperX();
		int y = this.square.upperY();
		int side = this.square.getSide();
		g2d.drawRect(x, y, side, side);
		if (this.square.getFilled()) {
			g2d.setColor(this.square.getFillColor());
			g2d.fillRect(x, y, side, side);
	}
		
	}

}
