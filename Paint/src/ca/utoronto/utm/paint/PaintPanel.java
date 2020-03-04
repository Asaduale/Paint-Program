package ca.utoronto.utm.paint;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import shapes.Point;
import strategies.SelectionPasteStrategy;
import strategies.ShapeManipulatorStrategy;
import strategies.ShapeStrategyFactory;
import strategies.SquiggleManipulatorStrategy;
import commands.DrawSelectionCommand;
import commands.DrawingCommand;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

/**
 * The PaintPanel class creates the entire GUI along with its respective
 * components.
 */
class PaintPanel extends JPanel implements Observer, MouseMotionListener,
		MouseListener {

	private PaintModel model; // slight departure from MVC, because of the way
								// painting works
	private View view; // So we can talk to our parent or other components of
						// the view

	private ShapeManipulatorStrategy strategy = new SquiggleManipulatorStrategy();
	private DrawingCommand command;
	public static int x = 0;
	public static Graphics2D g2d;
	private int red, green, blue;
	private boolean alreadySelected;
	private boolean isCopy;
	private boolean isCut;
	private static boolean isPaste;
	private Point cursorLocation;
	private String mode;
	/**
	 * Constructs the PaintPanel with the given PaintModel and View.
	 * 
	 * @param model
	 *            (PaintModel)
	 * @param view
	 *            (View)
	 */
	public PaintPanel(PaintModel model, View view) {
		if (View.img == null) {
			this.setBackground(Color.white);
		}
		this.setPreferredSize(new Dimension(300, 300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		this.model = model;
		this.model.addObserver(this);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		this.view = view;
	}

	/**
	 * This is the view aspect of whole program.
	 * 
	 * @param g
	 *            (Graphics)
	 */
	public void paintComponent(Graphics g) {
		// Use g to draw on the JPanel, lookup java.awt.Graphics in
		// the javadoc to see more of what this can do for you!!

		super.paintComponent(g); // paint background

		g2d = (Graphics2D) g; // lets use the advanced api
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// Origin is at the top left of the window 50 over, 75 down
		g2d.setColor(Color.black);

		if (View.img != null) {
			g2d.drawImage(View.img, 0, 0, getWidth(), getHeight(), null);
		}
		red = ColorChooserPanel.getRed();
		green = ColorChooserPanel.getGreen();
		blue = ColorChooserPanel.getBlue();
		g2d.setColor(new Color(red, green, blue));

		ArrayList<DrawingCommand> command = this.model.getCommand();
		for (DrawingCommand c : command) {
			c.execute(g2d);
		}
		ArrayList<DrawingCommand> temp_commands = this.model.getTempCommand();
		if (!temp_commands.isEmpty()) {
			temp_commands.get(0).execute(g2d);
		}
		g2d.dispose();
	}
	
	
	

	/**
	 * Clears the entire program, deleting all drawings.
	 */
	public void clear() {
		g2d.setPaint(Color.white);
		g2d.fillRect(0, 0, getSize().width, getSize().height);
		model.getCommand().clear();
		model.getRedoCommand().clear();
		View.img = null;
		this.repaint();
	}
	/**
	 * Save system, saves whatever has been drawn on the PaintProgram.
	 */
	public void save() {
		final JFileChooser fc = new JFileChooser();
		int result = fc.showSaveDialog(this);
		BufferedImage image = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_RGB);
		if (result != JFileChooser.CANCEL_OPTION) {
			Graphics2D graphics2D = image.createGraphics();
			this.paint(graphics2D);
			String directory;
			if (System.getProperty("os.name").contains("Windows")) {
				directory = "\\";
			} else {
				directory = "/";
			}
			try {
				ImageIO.write(image, "PNG", new File(fc.getCurrentDirectory()
						+ directory + fc.getSelectedFile().getName() + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * Copy the shape.
	 */
	public void copy() {
		isCopy = true;
		isCut = false;
	}
	
	/**
	 * Cuts the shape.
	 */
	public void cut() {
		isCut = true;
		isCopy = false;
	}
	
	/**
	 * Pastes the shape.
	 */
	public void paste() {
		if (this.model.getCommand().size() >= 1) {
			DrawingCommand lastCommand = this.model.getCommand().get(this.model.getCommand().size()-1);
		
			if (lastCommand.getClass() == DrawSelectionCommand.class && (isCopy ||isCut) ) {
				SelectionPasteStrategy selectFact = new SelectionPasteStrategy();
				DrawSelectionCommand dsc = (DrawSelectionCommand) lastCommand;
				this.command = selectFact.paste(cursorLocation, this.model.getFillState(), dsc, isCopy, isCut);
				this.model.addCommand(this.command, true);
				if (alreadySelected) {
					this.undo();
				}
				alreadySelected = false;
				isCopy = false;
				isCut = false;
			}
		}
		
	}

	/**
	 * An undo function, which deletes the most recently drawn Shape.
	 */
	public void undo() {
		if (this.model.getCommand().size() >= 1) {
			this.model.getRedoCommand().add(
					this.model.getCommand().get(
							this.model.getCommand().size() - 1));
			this.model.getCommand().remove(this.model.getCommand().size() - 1);
			this.repaint();
		}

	}
	/**
	 * Redo function, returns the recently deleted shape.
	 */
	public void redo() {
		if (this.model.getRedoCommand().size() >= 1) {
			this.model.getCommand().add(
					this.model.getRedoCommand().get(
							this.model.getRedoCommand().size() - 1));
			this.model.getRedoCommand().remove(
					this.model.getRedoCommand().size() - 1);
			this.repaint();

		}
	}

	/*
	 * Repaints the paintComponent.
	 */
	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint(); // Schedule a call to paintComponent
	}

	/**
	 * Controller aspect of the Paint program.
	 * 
	 * @param mode
	 *            (String)
	 */
	public void setMode(String mode) {
		ShapeStrategyFactory stratFact = new ShapeStrategyFactory();
		this.mode = mode;
		this.strategy = stratFact.buildStrategy(mode);
	}

	/*
	 * The MouseMotionListener for when the mouse is moved by the user.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		this.cursorLocation = new Point(x,y);
	}

	/*
	 * The MouseMotionListener for when the mouse is dragged. Meaning: the mouse
	 * button is held down.
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		boolean isFinal = false;
		this.command = this.strategy.makeDrawShapeCommand(e,
				this.model.getFillState());
		this.model.addCommand(this.command, isFinal);
	}

	/*
	 * The MouseMotionListener for when the mouse is clicked. Meaning: Mouse
	 * button is pushed and released.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	/*
	 * The MouseMotionListener for when the mouse is pressed.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (this.mode == "Select" && alreadySelected) {
			this.undo();
		}else if (this.mode == "Select") {
			alreadySelected = true;
		}else if (alreadySelected) {
			this.undo();
			alreadySelected = false;
		}else {
			alreadySelected = false;
		}
		this.model.getRedoCommand().clear();
		this.command = this.strategy.makeDrawShapeCommand(e,
		this.model.getFillState());
	}

	/*
	 * The MouseMotionListener for when the mouse is released.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		boolean isFinal = true;

		this.command = this.strategy.makeDrawShapeCommand(e,
				this.model.getFillState());
		this.model.addCommand(this.command, isFinal);
	}

	/*
	 * The MouseMotionListener for when the mouse enters the component.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/*
	 * The MouseMotionListener for when the mouse exits the component.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * Returns the thickness of the stroke.
	 * 
	 * @return The BasicStroke to use.
	 */
	public BasicStroke getStroke() {
		return new BasicStroke(ShapeChooserPanel.getStrokeValue());
	}
	/**
	 * Checks if user has used the paste functionality on the panel.
	 * @return
	 */
	public static boolean isPaste() {
		return isPaste;
	}
	/**
	 * Sets the paste function to true or false depending of the user has used the paste functionality on the panel.
	 * @param isPaste
	 */
	public void setPaste(boolean isPaste) {
		this.isPaste = isPaste;
	}

}
