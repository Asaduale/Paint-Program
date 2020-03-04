package shapes;


/**
 * Point Class, uses X and Y points on the Paint Program. An extension of shapes, all shapes uses the point class as 
 * respectively according to shape being used.
 */
public class Point extends Shapes {
	int x, y;
	
	/**
	 * Initialize points.
	 * @param x - integer
	 * @param y - integer
	 */
	public Point(int x, int y){
		this.x=x; this.y=y;
	}
	/**
	 * Get Point X.
	 * @return - Integer Point X
	 */
	public int getX() {
		return x;
	}
	/**
	 * Get Point X
	 * @param x - Integer
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Get Point Y
	 * @return - Integer Point Y
	 */
	public int getY() {
		return y;
	}
	/**
	 * Set Point Y
	 * @param y - Integer Point Y
	 */
	public void setY(int y) {
		this.y = y;
	}
}
