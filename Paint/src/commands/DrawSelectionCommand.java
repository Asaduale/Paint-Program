package commands;

import java.awt.Graphics2D;

import shapes.Selection;

/**
 * DrawSelectionCommand is a class that implements the Command Design Pattern by implementing the DrawingCommand interface.
 *
 */
public class DrawSelectionCommand implements DrawingCommand {

	private Selection selection;
	
	/**
	 * Constructor.
	 * @param s - Selection
	 */
	public DrawSelectionCommand(Selection s){
		this.selection = s;
	}
	
	/**
	 * Overrides execute from DrawingCommand interface, executes the drawing of a Selection Rectangle.
	 */
	@Override
	public void execute(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(this.selection.getColor());
		g2d.setStroke(this.selection.getStroke());
		int width = this.selection.getWidth();
		int height = this.selection.getHeight();
		int x = this.selection.getCornerX();
		int y = this.selection.getCornerY();
//		g2d.clearRect(x, y, width, height);
		if(this.selection.getIsPaste()) {
			int newX = this.selection.getNewLocation().getX();
			int newY = this.selection.getNewLocation().getY();
			g2d.copyArea(x, y, width, height, (newX-(x)), (newY-(y)));
			if (this.selection.getIsCut()) {
				g2d.clearRect(x, y, width, height);
			}
		}else {
			g2d.drawRect(x, y, width, height);
		}
		
	}
	
	/**
	 * Return the selection command
	 * @return - Selection Command.
	 */
	public Selection getSelection() {
		return this.selection;
	}

}
