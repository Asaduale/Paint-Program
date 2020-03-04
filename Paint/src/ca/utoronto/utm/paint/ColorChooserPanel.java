package ca.utoronto.utm.paint;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observer;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/
/**
 * This is the ColorChooserPanel, the colors and fill buttons
 * are created here. Anything related to colors and fill is 
 * completed within this class.
 *
 */
public class ColorChooserPanel extends JPanel implements ActionListener, MouseListener{
	private View view; // So we can talk to our parent or other components of the view
	public JPanel grid;
	static JSlider red;
	static JSlider green;
	static JSlider blue;
	private static int redtemp;
	private static int bluetemp;
	private static int greentemp;
	private static String prev;
	private static JButton blank;
	private static JButton blank1;
	private static int selected;
	private static int[] color1 = new int[3];
	private static int[] color2 = new int[3];
	private static JColorChooser colorChooser;
	private static boolean isEraser;
	
	public static JColorChooser getColorChooser() {
		return colorChooser;
	}
	/**
	 * This panel is used to set the 3 RGB Sliders
	 * The fill Button is also initialized in this panel.
	 * @param view
	 */
	public ColorChooserPanel(View view) {
		color1[0] = 0;
		color1[1] = 0;
		color1[2] = 0;
		color2[0] = 0;
		color2[1] = 0;
		color2[2] = 0;
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		selected = 1;
		prev = null;
		this.view = view;
		//this.setMinimumSize(getSize());
		this.setBackground(Color.white);
		this.setLayout(new GridLayout(1,1));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		blank = new JButton("");
		blank1 = new JButton ("");
		blank.setBackground(Color.black);
		blank1.setBackground(Color.black);
		
		blank.setOpaque(true);
		blank.setBorderPainted(false);
		
		blank1.setOpaque(true);
		blank1.setBorderPainted(false);
		
		blank.addActionListener(this);
		blank.addMouseListener(this);
		blank1.addMouseListener(this);
		blank1.addActionListener(this);
		blank.setActionCommand("button1");
		blank1.setActionCommand("button2");
		this.add(blank);
		this.add(blank1);
		
		ColorDisplayListener colorDisplayListener = new ColorDisplayListener(blank, blank1);
		
		red = new JSlider();
		JLabel redl = new JLabel ("Red");
		red.setMaximum(255);
		red.setValue(0);
		red.setPreferredSize(new Dimension(10,20));
		red.setBackground(Color.white);
		red.addChangeListener(colorDisplayListener);
		red.setName("red");
		this.add(red);
		this.add(redl);
		
		green = new JSlider();
		JLabel greenl = new JLabel ("Green");
		green.setMaximum(255);
		green.setValue(0);
		green.setPreferredSize(new Dimension(10,20));
		green.setBackground(Color.white);
		green.addChangeListener(colorDisplayListener);
		green.setName("green");
		this.add(green);
		this.add(greenl);

		blue = new JSlider();
		JLabel bluel = new JLabel ("Blue");
		blue.setMaximum(255);
		blue.setValue(0);
		blue.setPreferredSize(new Dimension(10,20));
		blue.setBackground(Color.white);
		blue.addChangeListener(colorDisplayListener);
		blue.setName("blue");
		this.add(blue);
		this.add(bluel);
		
		
		JButton fill = new JButton ("Fill");
		fill.setOpaque(true);
		fill.setBorderPainted(false);
		fill.setFont(new Font("Arial", Font.BOLD, 13));
		fill.setActionCommand("fill");
		fill.addActionListener(this);
		fill.setBackground(Color.gray);
		this.add(fill);
		
		
		colorChooser = new JColorChooser();
		colorChooser.setPreviewPanel(new JPanel());
		// https://stackoverflow.com/questions/9079807/jcolorchooser-hide-all-default-panels-and-show-hsb-panel-only
		AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
		for(AbstractColorChooserPanel accp: panels) {
			if (!accp.getDisplayName().equals("Swatches")) {
				colorChooser.removeChooserPanel(accp);
			}
		}
		
		grid = new JPanel();
		grid.setPreferredSize(new Dimension(50,50));
		grid.setBackground(Color.white);
		this.add(grid);
	}
	/**
	 * gets the Selected button
	 * @return selected integer (Int)
	 */
	public static int getSelected() {
		return selected;
	}
	
	/**
	 * Set the colors for the first button.
	 * @param r
	 * @param g
	 * @param b
	 */
	public static void setColor1(int r, int g, int b) {
		color1[0] = r;
		color1[1] = g;
		color1[2] = b;
	}
	
	/**
	 * Set the colors for the second button.
	 * @param r
	 * @param g
	 * @param b
	 */
	public static void setColor2(int r, int g, int b) {
		color2[0] = r;
		color2[1] = g;
		color2[2] = b;
	}
	
	/**
	 * Return the first color.
	 * @return int
	 */
	public static int[] getColor1() {
		return color1;
	}
	
	/**
	 * Return the second button
	 * @return int
	 */
	public static int[] getColor2() {
		return color2;
	}
	
	public static void setSelected(int num) {
		selected = num;
	}
	/**
	 * Sets the temporary colour values.
	 * @param b blue temp.
	 * @param r red temp
	 * @param g green temp
	 */
	public static void setTemp(int b, int r, int g) {
		redtemp = r;
		bluetemp = b;
		greentemp = g;
	}
	/**
	 * returns the red temp value
	 * @return red temp value
	 */
	public static int getRedtemp() {
		return redtemp;
	}
	
	/**
	 * returns the green temp value
	 * @return green temp value
	 */
	public static int getGreentemp() {
		return greentemp;
	}
	
	/**
	 * returns the blue temp value
	 * @return blue temp value
	 */
	public static int getBluetemp() {
		return bluetemp;
	}
	
	/**
	 * returns the Red Slider.
	 * @return red JSlider
	 */
	public static JSlider gRed() {
		return red;
	}
	
	/**
	 * returns the Blue Slider.
	 * @return blue JSlider
	 */
	public static JSlider gBlue() {
		return blue;
	}
	
	/**
	 * returns the Green Slider.
	 * @return green JSlider
	 */
	public static JSlider gGreen() {
		return green;
	}
	
	/**
	 * returns the value on the red slider
	 * @return integer of red slider value.
	 */
	public static int getRed() {
		return red.getValue();
	}
	
	/**
	 * returns the value on the green slider
	 * @return integer of green slider value.
	 */
	public static int getGreen() {
		return green.getValue();
	}
	
	/**
	 * returns the value on the blue slider
	 * @return integer of blue slider value.
	 */
	public static int getBlue() {
		return blue.getValue();
	}
	
	/**
	 * sets the last object selected.
	 * @param s String of last button pressed
	 */
	public static void count(String s) {
		prev = s;
	}
	
	/**
	 * returns the last object pressed
	 * @return String last button pressed.
	 */
	public static String getCount() {
		return prev;
	}
	/**
	 * Get first JButton for colors.
	 * @return
	 */
	public static JButton getBlank() {
		return blank;
	}
	/**
	 * Get second JButton for colors.
	 * @return
	 */
	public static JButton getBlank1() {
		return blank1;
	}
	
	
	/**
	 * Controller aspect of this
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "fill") {
			JButton b = (JButton)e.getSource();
			if (b.getText() == "Fill") {
				b.setText("Don't Fill");
				b.setBackground(Color.white);
				this.view.returnModel().FillState();
			}
			else {
				b.setText("Fill");
				b.setBackground(Color.gray);
				this.view.returnModel().FillState();
				
			}
			
		}
		
		if (e.getActionCommand() == "button1") {
			
			ColorChooserPanel.blank1.setEnabled(true);
			
			JButton button = (JButton) e.getSource();
			button.setEnabled(false);
			ColorChooserPanel.setSelected(1);
			ColorChooserPanel.setColor2(ColorChooserPanel.getRed(), ColorChooserPanel.getGreen(), ColorChooserPanel.getBlue());
			int[] colors = ColorChooserPanel.getColor1();
			
			ColorChooserPanel.gRed().setValue(colors[0]);
			ColorChooserPanel.gGreen().setValue(colors[1]);
			ColorChooserPanel.gBlue().setValue(colors[2]);
			
		}
		
		if (e.getActionCommand() == "button2") {
			
			ColorChooserPanel.blank.setEnabled(true);
			JButton button = (JButton) e.getSource();
			button.setEnabled(false);
			ColorChooserPanel.setSelected(2);
			ColorChooserPanel.setColor1(ColorChooserPanel.getRed(), ColorChooserPanel.getGreen(), ColorChooserPanel.getBlue());
			int[] colors = ColorChooserPanel.getColor2();
			
			ColorChooserPanel.gRed().setValue(colors[0]);
			ColorChooserPanel.gGreen().setValue(colors[1]);
			ColorChooserPanel.gBlue().setValue(colors[2]);
		}
		
	}
	
	/**
	 * Gets and sets the new color chosen by the user.
	 * @param chosen
	 */
	public static void newColorSelected(Color chosen) {
		int chosenR = chosen.getRed();
		int chosenG = chosen.getGreen();
		int chosenB = chosen.getBlue();
		red.setValue(chosenR);
		green.setValue(chosenG);
		blue.setValue(chosenB);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			ActionListener okNewColor = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				newColorSelected(colorChooser.getColor());
				
			}
		};
		if(!isEraser) {
			JDialog colorDialog = JColorChooser.createDialog(null, "Pick a Color", true, 
					colorChooser, okNewColor, null);
			colorDialog.setVisible(true);
		}
		
	  }
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Checks if the eraser has been clicked.
	 * @return boolean
	 */
	public boolean isEraser() {
		return isEraser;
	}
	/**
	 * Sets the eraser if it has been clicked.
	 * @param boolean 
	 */
	public static void setEraser(boolean eraser) {
		isEraser = eraser;
	}

	
}

