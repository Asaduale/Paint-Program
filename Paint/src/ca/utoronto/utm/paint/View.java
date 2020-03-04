package ca.utoronto.utm.paint;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.FileFilter;

/**
 * This is the top level View+Controller, it contains other aspects of the
 * View+Controller.
 * 
 * @author arnold
 *
 */

public class View extends JFrame implements ActionListener {

	public static Image img = null;
	private static final long serialVersionUID = 1L;
	private PaintModel model;
	// The components that make this up
	public PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	private ColorChooserPanel colorChooserPanel;


	/**
	 * Initialize the entire frame. Initialize the color chooser panel and the paint
	 * panel.
	 * 
	 * @param model
	 */
	public View(PaintModel model) {
		super("Paint"); // set the title and do other JFrame init
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setJMenuBar(createMenuBar());
		this.setBackground(Color.WHITE);
		//Container c = this.getContentPane();
		Container c = this.getContentPane();
		c.setBackground(Color.white);
		// c.add(new JButton("South"),BorderLayout.SOUTH);
		// c.add(new JButton("East"),BorderLayout.EAST);
		this.setPreferredSize(new Dimension(1000, 750));
		this.shapeChooserPanel = new ShapeChooserPanel(this);
		c.add(this.shapeChooserPanel, BorderLayout.WEST);

		this.model = model;

		this.paintPanel = new PaintPanel(model, this);
		c.add(this.paintPanel, BorderLayout.CENTER);

		this.colorChooserPanel = new ColorChooserPanel(this);
		c.add(this.colorChooserPanel, BorderLayout.SOUTH);

		WindowListener listener = new WindowAdapter() {

            public void windowClosing(WindowEvent w) {
                String[] options = new String[] {"Yes", "No", "Cancel"};
                int response = JOptionPane.showOptionDialog(null, "Do you want to save your work?", "Exiting Project",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
                if (response == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }    
                
                else if (response == JOptionPane.YES_OPTION) {
                	paintPanel.save();
                	System.exit(0);
                }
            }
        };
        
     
        this.addWindowListener(listener);
		
        
		
		this.pack();
		this.setVisible(true);
	}

	/**
	 * Gets the PaintPanel object
	 * 
	 * @return PaintPanel object is returned
	 */
	public PaintPanel getPaintPanel() {
		return paintPanel;
	}

	/**
	 * Gets the ColorChooserPanel
	 * 
	 * @return ColorChooserPanel is returned
	 */
	public ColorChooserPanel getColorChooserPanel() {
		return colorChooserPanel;
	}

	/**
	 * Gets the ShapesChooserPanel
	 * 
	 * @return ShapeChooserPanel is returned
	 */
	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}

	/**
	 * The Menu Bar is created which appears at the top of the frame.
	 * 
	 * @return returns the created JMenuBar
	 */
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;
		
		menu = new JMenu("File");

		// a group of JMenuItems
		menuItem = new JMenuItem("New");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("Edit");

		// a group of JMenuItems
		menuItem = new JMenuItem("Cut");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Copy");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Paste");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Undo");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Redo");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);
		
		menu = new JMenu ("Rotate");
		menuItem = new JMenuItem("Rotate 90 Degrees");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuBar.add(menu);

		return menuBar;
	}

	/**
	 * gets the Model of Paint
	 * 
	 * @return PaintModel is returned
	 */
	public PaintModel returnModel() {
		return model;
	}
	

	/**
	 * Action Performed of the View Panel
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Copy") {
			paintPanel.copy();
		}
		if (e.getActionCommand() == "Cut") {
			paintPanel.cut();
		}

		if (e.getActionCommand() == "Paste") {
			paintPanel.paste();
		}
		
		if (e.getActionCommand() == "New") {
			paintPanel.clear();
		}
		if (e.getActionCommand() == "Undo") {
			paintPanel.undo();
		}

		if (e.getActionCommand() == "Redo") {
			paintPanel.redo();
		}
		if (e.getActionCommand() == "Open") {
			paintPanel.clear();

			final JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg", "gif", "png");
			fc.setFileFilter(filter);
			int response = fc.showOpenDialog(this);
			String directory;
			if (System.getProperty("os.name").contains("Windows")) {
				directory = "\\";
			}
			else {
				directory = "/";
			}
			if (response == JFileChooser.APPROVE_OPTION) {
				img = Toolkit.getDefaultToolkit()
						.createImage(fc.getCurrentDirectory() + directory + fc.getSelectedFile().getName());
				paintPanel.repaint();
			}

		}

		if (e.getActionCommand() == "Exit") {
			String[] options = new String[] {"Yes", "No", "Cancel"};
            int response = JOptionPane.showOptionDialog(null, "Do you want to save your work?", "Exiting Project",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
            if (response == JOptionPane.NO_OPTION) {
                this.dispose();
            }    
            
            else if (response == JOptionPane.YES_OPTION) {
            	paintPanel.save();
            	this.dispose();
            }
			
		}
		
		if (e.getActionCommand() == "Save") {
			paintPanel.save();
		}
		
		if (e.getActionCommand() == "Rotate 90 Degrees") {
			int x = this.getWidth();
			int y = this.getHeight();
			this.setSize(new Dimension(y, x));
		}
		
	}
}
