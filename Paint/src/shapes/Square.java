package shapes;



/**
 * Square Class, inherits from Shapes. User is able to draw squares on the Paint Program.
 */
public class Square extends Shapes{
	private int x1;
	private int y1;
	private int side;
	
	/**
	 * Initialization for Square class.
	 * @param corner - Points X and Y
	 * @param x1 - Integer x
	 * @param y1 - Integer y
	 */
	public Square(Point corner, int x1, int y1) {
		super.setInitial(corner);
		this.x1 = x1;
		this.y1 = y1;
	}
	/**
	 * Setter X
	 * @param x1 - Integer
	 */
	public void setX1(int x1) {
	    this.x1 = x1;
	}
	/**
	 * Setter Y
	 * @param y1 - Integer
	 */
	public void setY1(int y1) {
	    this.y1 = y1;
	}
	/**
	 * Getter X
	 * @return - Value of X
	 */
	public int getX1() {
	    return this.x1;
	}
	/**
	 * Getter Y
	 * @return - Value of Y
	 */
	public int getY1() {
	    return this.y1;
	}
	/**
	 * Return the width of the square, absolute value is used to keep the numbers positive.
	 * @return - Integer width
	 */
	public int getWidth() {
	    return (Math.abs(super.getInitial().getX() - x1));
	}
	/**
	 * Return the height of the square, absolute value is used to keep the numbers positive.
	 * @return - Integer height
	 */
	public int getHeight() {
	    return (Math.abs(super.getInitial().getY() - y1));
	}
	/**
	 * A square has equal height and width, this function returns the max side
	 * @return - Integer of max side length 
	 */
	public int getSide() {
		return Math.max(this.getHeight(), this.getWidth());
	}
	/**
	 * Returns the larger Point X
	 * @return - Point X
	 */
	public int upperX() {
		if(this.getInitial().getX() > x1) {
			return this.getInitial().getX() - this.getSide();
		} else{
			 return this.getInitial().getX();
		}
	}
	/**
	 * Returns the larger Point Y
	 * @return - Point Y
	 */
	public int upperY() {
		if(this.getInitial().getY() > y1) {
			return this.getInitial().getY() - this.getSide();
		} else{
			return this.getInitial().getY();
		}
	}
	
}
