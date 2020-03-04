package strategies;

/**
 * Shape Strategy Factory; checks the string of the button that was clicked on the Paint Panel and returns a 
 * strategy pertaining to that specific button. 
 * 
 * Ex; Clicking on the circle button would then create a circle manipulator strategy, inside of that strategy
 * it would handle the MouseEvent and draw the circle as well as add it to the command stack.
 *
 */
public class ShapeStrategyFactory {
	public ShapeManipulatorStrategy buildStrategy(String s){
		ShapeManipulatorStrategy strategy = null;
		if (s.equals("Circle")){
			strategy = new CircleManipulatorStrategy();
		}
		else if (s.equals("Square")){
			strategy = new SquareManipulatorStrategy();
		}
		else if (s.equals("Rectangle")){
			strategy = new RectangleManipulatorStrategy();
		}
		else if (s.equals("Squiggle")){
			strategy = new SquiggleManipulatorStrategy();
		}
		else if (s.equals("Polyline")){
			strategy = new PolyLineManipulatorStrategy();
		}
		else if (s.equals("Triangle")){
			strategy = new TriangleManipulatorStrategy();
		}
		else if (s.equals("Oval")){
			strategy = new OvalManipulatorStrategy();
		}else if(s.equals("Select")) {
			strategy = new SelectionManipulatorStrategy();
		}else if(s.equals("SelectionPaste")) {
			strategy = new SelectionPasteStrategy();
		}
		return strategy;
	}
}
