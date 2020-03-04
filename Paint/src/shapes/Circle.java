package shapes;


/**
 * Circle Class, inherits from Shapes and draws circles on the Paint program.
 *
 */
public class Circle extends Shapes{
	private int radius;
	
	/**
	 * Initialize Circle
	 * @param centre
	 * @param radius
	 */
	public Circle(Point centre, int radius){
		super.setInitial(centre);
		this.radius = radius;
	}
	/**
	 * Getter radius.
	 * @return - radius
	 */
	public int getRadius() {
		return radius;
	}
	/**
	 * Setter radius.
	 * @param radius
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
}
