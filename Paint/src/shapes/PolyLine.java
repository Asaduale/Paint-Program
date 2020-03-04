package shapes;


/**
 * PolyLine, when the user clicks two separate points on the paint program it connects the points together with a straight line.
 *
 */
public class PolyLine extends Shapes {
	private Point edge2;
	private PolyLine previous_polyLine;
	
	/**
	 * Initialize Polyline.
	 * @param edge - Point.
	 */
	public PolyLine(Point edge){
		super.setInitial(edge);
	}
	
	/**
	 * Getter for edge (edge is a point).
	 * @return
	 */
	public Point getEdge2() {
		return edge2;
	}
	/**
	 * Setter for edge (edge is a point).
	 * @param edge2
	 */
	public void setEdge2(Point edge2) {
		this.edge2 = edge2;
	}
	
	public void setPrevious(PolyLine pl) {
		this.previous_polyLine = pl;
	}
	public PolyLine getPrevious() {
		return this.previous_polyLine;
	}

}
