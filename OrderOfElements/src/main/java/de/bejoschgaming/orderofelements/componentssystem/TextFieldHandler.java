package de.bejoschgaming.orderofelements.componentssystem;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.mwsystem.MultiWindowHandler;

public class TextFieldHandler {

	public static JTextField LOGIN_Name;
	public static JTextField LOGIN_Password;
	public static JTextField FRIENDLIST_RequestName;
	public static JTextField DECKBUILDER_Rename;
	
	public static void loadTextFields() {
		
		LOGIN_Name = createTextField(FontHandler.getFont(FontHandler.medievalSharp_regular, 22));
		LOGIN_Password = createTextField(FontHandler.getFont(FontHandler.medievalSharp_regular, 22));
		FRIENDLIST_RequestName = createTextField(FontHandler.getFont(FontHandler.medievalSharp_regular, 22));
		DECKBUILDER_Rename = createTextField(FontHandler.getFont(FontHandler.medievalSharp_regular, 22));
		updateAllPositions();
		setNormalColorsForAll();
		
	}
	
	public static void updateAllPositions() {
		
		int width = 384, height = 43;
		updatePosition(LOGIN_Name, 1305, 454, width, height, true);
		updatePosition(LOGIN_Password, 1305, 540, width, height, true);
		updatePosition(FRIENDLIST_RequestName, 365, 745, width, height, false);
		updatePosition(DECKBUILDER_Rename, GraphicsHandler.getWidth()/2, GraphicsHandler.getHeight()/2-30, width, height-8, true);
		
	}
	
	private static JTextField createTextField(Font font) {
		
		JTextField textField = new JTextField();
		textField.setVisible(false);
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setBorder(null);
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
			textField.setBounds(x-(width/2), y-(height/2), width, height);
		}else {
			textField.setBounds(x, y, width, height);
		}
		
	}

	public static void showTextField(JTextField textfield) {
		
		textfield.setVisible(true);
		
	}
	public static void hideTextField(JTextField textfield) {
		
		textfield.setVisible(false);
		
	}
	public static void setNormalColorsForAll() {
		
		LOGIN_Name.setBackground(Color.WHITE);
		LOGIN_Name.setForeground(Color.BLACK);
		LOGIN_Password.setBackground(Color.WHITE);
		LOGIN_Password.setForeground(Color.BLACK);
		FRIENDLIST_RequestName.setBackground(Color.WHITE);
		FRIENDLIST_RequestName.setForeground(Color.BLACK);
		DECKBUILDER_Rename.setBackground(Color.WHITE);
		DECKBUILDER_Rename.setForeground(Color.BLACK);
		
	}
	public static void setCamoflageColorsForAll() {
		
		LOGIN_Name.setBackground(MultiWindowHandler.MW_BLOCKING_BACKGROUNDCOLOR);
		LOGIN_Name.setForeground(MultiWindowHandler.MW_BLOCKING_BACKGROUNDCOLOR);
		LOGIN_Password.setBackground(MultiWindowHandler.MW_BLOCKING_BACKGROUNDCOLOR);
		LOGIN_Password.setForeground(MultiWindowHandler.MW_BLOCKING_BACKGROUNDCOLOR);
		FRIENDLIST_RequestName.setBackground(MultiWindowHandler.MW_BLOCKING_BACKGROUNDCOLOR);
		FRIENDLIST_RequestName.setForeground(MultiWindowHandler.MW_BLOCKING_BACKGROUNDCOLOR);
		DECKBUILDER_Rename.setBackground(Color.WHITE);
		DECKBUILDER_Rename.setForeground(Color.BLACK);
		
	}
}
