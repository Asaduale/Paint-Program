package ca.utoronto.utm.paint;

import java.awt.List;
import java.util.ArrayList;
import java.util.Observable;

import commands.DrawingCommand;

/**
 *The Model of the entire Paint program, uses the Command Design Pattern. A single array which holds commands of each
 *shape.
 */
public class PaintModel extends Observable {	
	private ArrayList<DrawingCommand> command = new ArrayList<DrawingCommand>();
	private ArrayList<DrawingCommand> temp_command = new ArrayList<DrawingCommand>();
	
	private ArrayList<DrawingCommand> redo_command = new ArrayList<DrawingCommand>();
	
	private boolean fillshapes;
	
	/**
	 * Constructs the PaintModel, with the fill setting set to False.
	 */
	public PaintModel() {
		this.fillshapes = false;
	}
	
	/**
	 * Returns whether or not to fill in Shapes.
	 * @return True if to draw Shapes filled in, False if to not.
	 */
	public boolean getFillState() {
		return fillshapes;
	}
	
	/**
	 * Changes the fill setting. In essence, if Shapes were
	 * drawn filled in before, now they will not be and vice versa.
	 */
	public void FillState() {
		if (fillshapes == false) {
			fillshapes = true;
		}
		
		else {
			fillshapes = false;
		}
		
	}
	/**
	 * Returns the array list command, a list of commands.
	 * @return command (ArrayList<DrawingCommand>)
	 */
	public ArrayList<DrawingCommand> getCommand(){
		return command;
	}
	/**
	 * Returns the temporary array list command, a list of commands.
	 * @return command (ArrayList<DrawingCommand>)
	 */
	public ArrayList<DrawingCommand> getTempCommand(){
		return temp_command;
	}
	
	/**
	 * Returns the redo array list command, a list of commands.
	 * @return command (ArrayList<DrawingCommand>)
	 */
	public ArrayList<DrawingCommand> getRedoCommand(){
		return redo_command;
	}
	
	/**
	 * Adds the commands to the list
	 * @param c - DrawingCommand
	 * @param isFinal - boolean (checks if the user has released the click true = yes, false = no )
	 */
	public void addCommand(DrawingCommand c, boolean isFinal) {
		if (isFinal) {
			command.add(c);
			temp_command.clear();
		}else {
			temp_command.clear();
			temp_command.add(c);
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Clears the array list.
	 */
	public void clearCommand() {
		command.clear();
	}

}
