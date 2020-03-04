package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A ChangeListener that changes the color of 2 JTextArea Objects.
 */
public class ColorDisplayListener implements ChangeListener {

	//These 2 JTextArea Objects display the color the user has selected.
	private JButton colorArea1;
	private JButton colorArea2;
	
	/**
	 * Constructs the ColorDisplayListener with the 2 given JTextArea Objects.
	 * @param color1 (JButton)
	 * @param color2 (JButton)
	 */
	public ColorDisplayListener(JButton color1, JButton color2){
		this.colorArea1 = color1;
		this.colorArea2 = color2;

	}
	/* 
	 * This method changes the background of the JTextArea objects
	 * according to the color the user has selected with the JSlider Objects.
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		Color sliderColor1 = this.colorArea1.getBackground();
		int red = sliderColor1.getRed();
		int blue = sliderColor1.getBlue();
		int green = sliderColor1.getGreen();
		if (source.getName()=="red"){
			if (ColorChooserPanel.getSelected()==1) {
				this.colorArea1.setBackground(new Color(ColorChooserPanel.gRed().getValue(), ColorChooserPanel.gGreen().getValue(), ColorChooserPanel.gBlue().getValue()));
			}
			else {
				this.colorArea2.setBackground(new Color(ColorChooserPanel.gRed().getValue(), ColorChooserPanel.gGreen().getValue(), ColorChooserPanel.gBlue().getValue()));
			}
			
		}
		else if (source.getName()=="blue"){
			if (ColorChooserPanel.getSelected()==1) {
				this.colorArea1.setBackground(new Color(ColorChooserPanel.gRed().getValue(), ColorChooserPanel.gGreen().getValue(), ColorChooserPanel.gBlue().getValue()));
			}
			else {
				this.colorArea2.setBackground(new Color(ColorChooserPanel.gRed().getValue(), ColorChooserPanel.gGreen().getValue(), ColorChooserPanel.gBlue().getValue()));
			}	
			
		}
		else if (source.getName()=="green"){
			if (ColorChooserPanel.getSelected()==1) {
				this.colorArea1.setBackground(new Color(ColorChooserPanel.gRed().getValue(), ColorChooserPanel.gGreen().getValue(), ColorChooserPanel.gBlue().getValue()));
			}
			else {
				this.colorArea2.setBackground(new Color(ColorChooserPanel.gRed().getValue(), ColorChooserPanel.gGreen().getValue(), ColorChooserPanel.gBlue().getValue()));
			}
			
		}
	}

}
