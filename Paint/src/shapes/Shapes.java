package shapes;

import java.awt.BasicStroke;
import java.awt.Color;

/**
 * Parent class for all Shapes (rectangle, square, circle, point, polyline). Here is where the fill function is handled as well
 * as color.
 */
public abstract class Shapes {
	private Color color;
	private Color fillColor;
	private BasicStroke stroke;
	private Point initial;
	private boolean filled;
	
	/**
	 * Initialize Shapes
	 */
	public Shapes() {
		this.filled = false;
	}
	public Color getFillColor() {
		return this.fillColor;
	}
	public void setFillColor(Color c) {
		this.fillColor = c;
	}
	/**
	 * Return the color of the shape,
	 * @return - the color 
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * Return whether the shape is filled.
	 * @return boolean true/false
	 */
	public boolean getFilled() {
		return filled;
	}
	/**
	 * Set the boolean to true or false depending on the current state.
	 * @param state - boolean true/false
	 */
	public void setFilled(boolean state) {
		filled = state;
	
	}
	/**
	 * Set the color of the shape.
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * Get the current stroke of the shape.
	 * @return stroke
	 */
	public BasicStroke getStroke() {
		return stroke;
	}
	/**
	 * Set the stroke of the shape.
	 * @param stroke
	 */
	public void setStroke(BasicStroke stroke) {
		this.stroke = stroke;
	}
	/**
	 * Getter Point
	 * @return - point
	 */
	public Point getInitial() {
		return initial;
	}
	/**
	 * Setter Point
	 * @param centre - Point
	 */
	public void setInitial(Point centre) {
		this.initial = centre;
	}
}
