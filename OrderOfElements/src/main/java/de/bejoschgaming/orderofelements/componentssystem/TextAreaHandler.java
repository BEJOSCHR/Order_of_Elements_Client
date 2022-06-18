package de.bejoschgaming.orderofelements.componentssystem;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;

public class TextAreaHandler {

	public static JTextArea xxx;
	
	public static void loadTextAreas() {
		
		xxx = createTextArea((GraphicsHandler.getWidth()*53)/100, (GraphicsHandler.getHeight()*40)/100, 500, 70, FontHandler.getFont(FontHandler.bridgnorth_regular, 22));
		
	}
	
	private static JTextArea createTextArea(int x, int y, int width, int height, Font font) {
		
		JTextArea textArea = new JTextArea();
		textArea.setVisible(false);
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setForeground(Color.BLACK);
		textArea.setVisible(false);
		
		textArea.setAlignmentX(x);
		textArea.setAlignmentY(y);
		textArea.setSize(width, height);
		textArea.setFont(font);
		
		GraphicsHandler.getLabel().add(textArea);
		
		return textArea;
	}

	public static void showTextArea(JTextArea textArea) {
		
		textArea.setVisible(true);
		
	}
	public static void hideTextArea(JTextArea textArea) {
		
		textArea.setVisible(false);
		
	}
	
}
