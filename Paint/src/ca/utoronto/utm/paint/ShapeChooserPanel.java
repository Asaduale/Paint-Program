package ca.utoronto.utm.paint;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observer;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

/**
 * The Panel with buttons to select the Shapes to draw, thickness, etc.
 */
public class ShapeChooserPanel extends JPanel implements ActionListener {
	private View view; // So we can talk to our parent or other components of the view
	public static JSlider thicness;
	public static JTextField strokeTF;
	private JButton[] buttons;
	
	/**
	 * Constructs the ShapeChooserPanel with the given View.
	 * @param view (View)
	 */
	public ShapeChooserPanel(View view) {	
		this.view=view;
		int counter = 0;
		String[] buttonLabels = { "Select", "Circle","Oval", "Rectangle", "Square", "Squiggle", "Polyline", "Triangle", "eraser"};
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,Color.BLACK));
		this.setBackground(Color.white);
		// GridBag Constraint information
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttons = new JButton[buttonLabels.length];
		for (String label : buttonLabels) {
			
			// With the help of
			// https://stackoverflow.com/questions/2856480/resizing-a-imageicon-in-a-jbutton
			ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(label + ".png"));
			Image img = icon.getImage() ;  
			Image newimg = img.getScaledInstance( 40, 40,  java.awt.Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon( newimg );
			JButton button = new JButton(icon);
			button.setBackground(Color.WHITE);
			button.setFocusable(false);
			button.setToolTipText(label);
			button.setActionCommand(label);
			if (label == "Squiggle") {
				button.setEnabled(false);
			}
			buttons[counter] = button;
			counter ++;
			this.add(button, c);
			button.addActionListener(this);
			c.gridy += 1;
		}

		thicness = new JSlider(JSlider.VERTICAL, 1,50,5);
		thicness.setInverted(true);
		thicness.setBackground(Color.white);
		thicness.setToolTipText("Stroke");

		thicness.setPreferredSize(new Dimension(20, 80));
		
		strokeTF = new JTextField(4);
		strokeTF.setText(thicness.getValue()+"");
		strokeTF.setToolTipText("Stroke");
		strokeTF.setHorizontalAlignment(JTextField.CENTER);

		
		// The following two listeners were inspired from:
		// https://stackoverflow.com/questions/1548606/java-link-jslider-and-jtextfield-for-float-value
		
		thicness.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				strokeTF.setText("" +thicness.getValue());
				
			}
		});
		strokeTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ke) {
				try {
					int strokeVal = Integer.parseInt(strokeTF.getText());
					if (strokeVal >= 1 && strokeVal <= 50) {
						thicness.setValue(strokeVal);
					}else if (strokeVal <1) {
						strokeTF.setText(""+1);
						thicness.setValue(1);
					}else if (strokeVal <100) {
						thicness.setValue(50);
						strokeTF.setText("" + strokeVal);
					}else {
						thicness.setValue(50);
						strokeTF.setText("" + 100);
					}
				}catch (Exception e) {
					strokeTF.setText("" + thicness.getValue());
				}
				
			}
		});
		c.gridx = 0;
		c.gridy += 1;
		c.fill = GridBagConstraints.BOTH;
		this.add(thicness, c);
		c.gridy += 1;
		this.add(strokeTF, c);
	}
	
	/**
	 * Controller aspect of the ShapeChooserPanel.
	 * Changes the thickness, color of the Shapes, and whether
	 * or not to enable the eraser.
	 * @param e (ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand()=="eraser") {
			
			ColorChooserPanel.setTemp(ColorChooserPanel.getBlue(), ColorChooserPanel.getRed(), ColorChooserPanel.getGreen());
			ColorChooserPanel.gBlue().setValue(255);
			ColorChooserPanel.gBlue().setEnabled(false);
			ColorChooserPanel.gRed().setValue(255);
			ColorChooserPanel.gRed().setEnabled(false);
			ColorChooserPanel.gGreen().setValue(255);
			ColorChooserPanel.gGreen().setEnabled(false);
			ColorChooserPanel.getBlank().setEnabled(false);
			ColorChooserPanel.getBlank1().setEnabled(false);
			ColorChooserPanel.setEraser(true);
			ColorChooserPanel.count(e.getActionCommand());
			this.view.getPaintPanel().setMode("Squiggle");
		}
		else {
			
			if (ColorChooserPanel.getCount()=="eraser") {
				ColorChooserPanel.setEraser(false);
				ColorChooserPanel.gBlue().setValue(ColorChooserPanel.getBluetemp());
				ColorChooserPanel.gBlue().setEnabled(true);
				ColorChooserPanel.gRed().setValue(ColorChooserPanel.getRedtemp());
				ColorChooserPanel.gRed().setEnabled(true);
				ColorChooserPanel.gGreen().setValue(ColorChooserPanel.getGreentemp());
				ColorChooserPanel.gGreen().setEnabled(true);
				ColorChooserPanel.getBlank().setEnabled(true);
				ColorChooserPanel.getBlank1().setEnabled(true);
			}

			ColorChooserPanel.count(e.getActionCommand());
			this.view.getPaintPanel().setMode(e.getActionCommand());
		}
		
		PaintPanel.x=thicness.getValue();
		// Changing the display of the button if it is selected.
		for (JButton jb : buttons) {
			if(e.getActionCommand().equals(jb.getActionCommand())) {			
				jb.setEnabled(false);
			}else {  
				jb.setEnabled(true);
			}
		}
	}
	/**
	 * Returns the JSlider which chooses the thickness of the brush.
	 * @return A JSlider.
	 */
	public static JSlider getThicness() {
		return thicness;
	}
	/**
	 * Returns the stroke value.
	 * @return
	 */
	public static float getStrokeValue() {
		// TODO Auto-generated method stub
		return Integer.parseInt(strokeTF.getText());
	}

	
}

