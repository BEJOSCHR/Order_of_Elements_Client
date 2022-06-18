package de.bejoschgaming.orderofelements.componentssystem;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;

public class TextFieldHandler {

	public static JTextField LOGIN_Name;
	public static JTextField LOGIN_Password;
	
	public static void loadTextFields() {
		
		LOGIN_Name = createTextField((GraphicsHandler.getWidth()*68)/100, (GraphicsHandler.getHeight()*42)/100, 400, 45, FontHandler.getFont(FontHandler.medievalSharp_regular, 22));
		LOGIN_Password = createTextField((GraphicsHandler.getWidth()*68)/100, (GraphicsHandler.getHeight()*50)/100, 400, 45, FontHandler.getFont(FontHandler.medievalSharp_regular, 22));
		
	}
	
	private static JTextField createTextField(int x, int y, int width, int height, Font font) {
		
		JTextField textField = new JTextField();
		textField.setVisible(false);
		textField.setBackground(Color.WHITE);
		textField.setForeground(Color.BLACK);
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setVisible(false);
		
		textField.setLocation(x-(width/2), y-(height/2));
		textField.setSize(width, height);
		textField.setFont(font);
		
		GraphicsHandler.getLabel().add(textField);
		
		return textField;
	}

	public static void showTextField(JTextField textfield) {
		
		textfield.setVisible(true);
		
	}
	public static void hideTextField(JTextField textfield) {
		
		textfield.setVisible(false);
		
	}
	
}
