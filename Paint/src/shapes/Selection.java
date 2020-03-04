package shapes;

import java.awt.BasicStroke;
import java.awt.Color;

/**
 * 
 * Selection is a rectangle, it's a draggable option where the user can then copy, cut or paste whatever is held within that
 * rectangle.
 *
 */
public class Selection extends Rectangle{

	private Point newLocation;
	private boolean isPaste;
	private boolean isCut;
	private boolean isCopy;
	
	/**
	 * Constructor
	 * @param corner - Point
	 * @param x1 - Integer
	 * @param y1 - Integer
	 */
	public Selection(Point corner, int x1, int y1) {
		super(corner, x1, y1);
	}
	
	/**
	 * Set Filled is set to false since selection must be clear, everything contained within the rectangle can be
	 * cut, copy or pasted. The user also wants to be able to see what they want to copy/paste/cut.
	 */
	@Override
	public void setFilled(boolean state) {
		super.setFilled(false);
	}
	
	/**
	 * Sets the border for the selection rectangle.
	 */
	@Override
	public void setColor(Color c) {
		super.setColor(Color.BLUE);
	}
	/**
	 * Sets the stroke for the selection rectangle, a dashed line rectangle.
	 */
	@Override
	public void setStroke(BasicStroke stroke) {
		//https://stackoverflow.com/questions/21989082/drawing-dashed-line-in-java
		 BasicStroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	     super.setStroke(dashed);;
	}
	
	/**
	 * Sets the location
	 * @param p - Point
	 */
	public void setNewLocation(Point p) {
		this.newLocation = p;
	}
	
	/**
	 * Gets the location
	 * @return Point
	 */
	public Point getNewLocation() {
		return this.newLocation;
	}
	/**
	 * Return a boolean, whether the user has pasted or not. 
	 * @return boolean
	 */
	public boolean getIsPaste() {
		// TODO Auto-generated method stub
		return isPaste;
	}
	
	/**
	 * Set the paste, true if pasted and false otherwise.
	 * @param paste - boolean.
	 */
	public void setIsPaste(boolean paste) {
		this.isPaste = paste;
	}
	/**
	 * Set the copy, true if copied and false otherwise.
	 * @param isCopy - boolean
	 */
	public void setIsCopy(boolean isCopy) {
		this.isCopy =isCopy;
	}
	/**
	 * Return a boolean, whether the user has copied or not.
	 * @return boolean
	 */
	public boolean getIsCopy() {
		return this.isCopy;
	}
	
	/**
	 * Set the cut, true if cut and false otherwise.
	 * @param isCut - boolean
	 */
	public void setIsCut(boolean isCut) {
		this.isCut = isCut;
	}
	/**
	 * Return a boolean, whether the user has cut or not.
	 * @return boolean
	 */
	public boolean getIsCut() {
		return this.isCut;
	}

}
