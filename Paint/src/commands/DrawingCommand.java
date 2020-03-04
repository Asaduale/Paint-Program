package commands;


import java.awt.Graphics2D;

/**
 * Command Design Pattern, encapsulates information needed to perform an action or trigger an event at a later time. 
 * DrawingCommand interface contains a single method that is overridden by every class that implements this 
 * interface, DrawingCommand. Used as a list/stack inside of PaintModel.
 * @author asadali
 *
 */
public interface DrawingCommand {
	void execute(Graphics2D g2d);
}
