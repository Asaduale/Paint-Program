package shapes;

/**
 * Triangle class inherits from Shapes and draws Triangles on the Paint program.
 * @author asadali
 *
 */
public class Triangle extends Shapes{
	
	private int x1;
	private int y1;
	
	public Triangle(Point corner, int x1, int y1) {
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
	 * @return - Integer X
	 */
	public int getX1() {
	    return this.x1;
	}
	/**
	 * Getter Y
	 * @return - Integer Y
	 */
	public int getY1() {
	    return this.y1;
	}
	
	/**
	 * Get width of the triangle, absolute values to keep numbers positive.
	 * @return - integer height
	 */
	public int getWidth() {
	    return (Math.abs(super.getInitial().getX() - x1));
	}
	/**
	 * Get height of the triangle, absolute values to keep numbers positive.
	 * @return - integer height
	 */
	public int getHeight() {
	    return (Math.abs(super.getInitial().getY() - y1));
	}
	
	/**
	 * Get corner point X
	 * @return - minimum point of X
	 */
	public int getCornerX() {
	    return (Math.min(super.getInitial().getX(), x1));
	}
	/**
	 * Get corner point Y
	 * @return - minimum point of Y
	 */
	public int getCornerY() {
	    return (Math.min(super.getInitial().getY(), y1));
	}

	
}
