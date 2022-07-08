package de.bejoschgaming.orderofelements.componentssystem;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;

public class TextFieldHandler {

	public static JTextField LOGIN_Name;
	public static JTextField LOGIN_Password;
	public static JTextField FRIENDLIST_RequestName;
	
	public static void loadTextFields() {
		
		LOGIN_Name = createTextField(FontHandler.getFont(FontHandler.medievalSharp_regular, 22));
		LOGIN_Password = createTextField(FontHandler.getFont(FontHandler.medievalSharp_regular, 22));
		FRIENDLIST_RequestName = createTextField(FontHandler.getFont(FontHandler.medievalSharp_regular, 22));
		updateAllPositions();
		
	}
	
	public static void updateAllPositions() {
		
		int width = 384, height = 43;
		updatePosition(LOGIN_Name, 1305, 454, width, height, true);
		updatePosition(LOGIN_Password, 1305, 540, width, height, true);
		updatePosition(FRIENDLIST_RequestName, 365, 745, width, height, false);
		
	}
	
	private static JTextField createTextField(Font font) {
		
		JTextField textField = new JTextField();
		textField.setVisible(false);
		textField.setBackground(Color.WHITE);
		textField.setForeground(Color.BLACK);
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setVisible(false);
		textField.setFont(font);
		
		GraphicsHandler.getLabel().add(textField);
		
		return textField;
	}
	private static void updatePosition(JTextField textField, int relX, int relY, int relWidth, int relHeight, boolean centerCords) {
		
		int x = (int) ((((double) relX / 1920.0) * (double) GraphicsHandler.getWidth()) + 0.5);
		int y = (int) ((((double) relY / 1080.0) * (double) GraphicsHandler.getHeight()) + 0.5);
		int width = (int) ((((double) relWidth / 1920.0) * (double) GraphicsHandler.getWidth()) + 0.5);
		int height = (int) ((((double) relHeight / 1080.0) * (double) GraphicsHandler.getHeight()) + 0.5);
		if(centerCords == true) {
			textField.setLocation(x-(width/2), y-(height/2));
		}else {
			textField.setLocation(x, y);
		}
		textField.setSize(width, height);
		
	}

	public static void showTextField(JTextField textfield) {
		
		textfield.setVisible(true);
		
	}
	public static void hideTextField(JTextField textfield) {
		
		textfield.setVisible(false);
		
	}
	
}
