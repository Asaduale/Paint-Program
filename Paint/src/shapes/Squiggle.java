package shapes;

import java.util.ArrayList;

/**
 * A Squiggle is the implementation of a free-form line.
 * It is just an ArrayList of Points. When drawn, a line is drawn
 * between each Point.
 */
public class Squiggle extends Shapes{
	private ArrayList<Point> points;
	
	
	/**
	 * Constructs a Squiggle Object with an empty ArrayList of Points.
	 */
	public Squiggle() {
		points = new ArrayList<Point>();
	}
	
	/**
	 * Adds the Point p to the ArrayList of Points.
	 * @param p (Point)
	 */
	public void addPoints(Point p){
		this.points.add(p);
	}
	
	/**
	 * Returns all the Points in the Squiggle object.
	 * @return An ArrayList of Points.
	 */
	public ArrayList<Point> getPoints() {
		return this.points;
	}
	
}