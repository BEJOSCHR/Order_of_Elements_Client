package de.bejoschgaming.orderofelements.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;
import de.bejoschgaming.orderofelements.animationsystem.animations.DynamicInteger;
import de.bejoschgaming.orderofelements.componentssystem.TextFieldHandler;
import de.bejoschgaming.orderofelements.debug.ConsoleHandler;
import de.bejoschgaming.orderofelements.deckbuildersystem.DeckBuilder_Data;
import de.bejoschgaming.orderofelements.filesystem.FileHandler;
import de.bejoschgaming.orderofelements.gamesystem.map.MapData;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_3Menu;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_7Credits;
import de.bejoschgaming.orderofelements.graphics.handler.KeyHandler;
import de.bejoschgaming.orderofelements.graphics.handler.MouseHandler;
import de.bejoschgaming.orderofelements.graphics.handler.WindowHandler;
import de.bejoschgaming.orderofelements.main.OOE_Main_Client;

public class GraphicsHandler {

	private static DrawState drawState = DrawState.LOADINGSCREEN;
	
	private static JFrame frame = null;
	private static Label label = null;
	private static int width, height;
	
	public static void initVisuals() {

		frame = new JFrame();
		
		frame.setLocationRelativeTo(null);
		frame.setLocation(0, 0);
		frame.setUndecorated(true);

		frame.setTitle("OrderOfElements - " + "0.0.1");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		frame.addKeyListener(new KeyHandler());
		frame.addMouseListener(new MouseHandler());
		frame.addMouseMotionListener(new MouseHandler());
		frame.addMouseWheelListener(new MouseHandler());
		frame.addWindowListener(new WindowHandler());
		
		try {
		    frame.setIconImage(ImageIO.read(OOE_Main_Client.class.getResourceAsStream("../imagesystem/images/loadingscreen/Order_of_Elements_3.png")));
		} catch (Exception error) {
		    ConsoleHandler.printMessageInConsole("Couldn't load window icon!", true);
		    error.printStackTrace();
		}

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		frame.setSize(1280, 720);
//		frame.setSize(2480, 1080);
		
		width = frame.getWidth();
		height = frame.getHeight();

		// TODO CALCULATE DIMENSIONS RELATIVE TO WIDTH AND HEIGHT
		MapData.FIELD_SIZE = (int) (width/MapData.MAP_WIDTH);
		label = new Label();
		label.requestFocus();

		frame.requestFocus();

//		ConsoleHandler.print("Initialised Visuals!", MessageType.BACKEND);

	}
	
	/**
	 * Allgemeine Methode um einen beliebigen Text mit Parametern relativ zu einem
	 * Punkt (x,y) mittig darzustellen
	 * EXAMPLE: GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, 50), "Order Of Elements", (GraphicsHandler.getWidth()*32)/100, (GraphicsHandler.getHeight()*3)/7);
	 *
	 * @param g,        das Graphics object
	 * @param color,    die Textfarbe
	 * @param font, 	die Font in der der Text dargestellt werden soll
	 * @param text,     der eigentliche Text
	 * @param x,        die X-Koordinate (Links-Rechts-Verschiebung) zu der der Text
	 *                  mittig dargestellt wird
	 * @param y,        die Y-Koordinate (Oben-Unten-Verschiebung) zu der der Text
	 *                  mittig dargestellt wird
	 */
	public static void drawCentralisedText(Graphics g, Color color, Font font, String text, int x, int y) {
	
		g.setColor(color);
		g.setFont(font);
		int width = g.getFontMetrics().stringWidth(" "+text);
		int height = g.getFontMetrics().getHeight() * 2 / 3;
		g.drawString(" "+text, x - width / 2, y + height / 2);
	
	}
	
	public static int getRelativX(int realX) {
		
		return (int) ((((double) realX / 1920.0) * (double) GraphicsHandler.getWidth()) + 0.5);
		
	}
	public static int getRelativY(int realY) {
		
		return (int) ((((double) realY / 1080.0) * (double) GraphicsHandler.getHeight()) + 0.5);
		
	}
	
	public static int getRelativTextSize(int textSize) {
		
		return (int) (((double) textSize / 1080.0) * (double) GraphicsHandler.getHeight() + 0.5);
		
	}
	
	public static DrawState getDrawState() {
		return drawState;
	}
	
	public static void switchTo(DrawState newDrawState) {
		
		leaveState(getDrawState(), newDrawState);
		
		switch (newDrawState) {
		case PROGRAMMSTART:
			//WILL NEVER HAPPEN!
			break;
		case LOADINGSCREEN:
			
			break;
		case LOGIN:
			TextFieldHandler.LOGIN_Name.setText(FileHandler.readOutData(FileHandler.file_Settings, "LOGIN_Name"));
			TextFieldHandler.LOGIN_Password.setText("benno2001"); //TODO ONLY TEMP FOR TESTING!
			TextFieldHandler.showTextField(TextFieldHandler.LOGIN_Name);
			TextFieldHandler.showTextField(TextFieldHandler.LOGIN_Password);
			TextFieldHandler.LOGIN_Name.requestFocus();
			Draw_3Menu.startBackgroundAnimation();
			break;
		case MENU:
			Draw_3Menu.startBackgroundAnimation();
			break;
		case DECKBUILDER:
			Draw_3Menu.startBackgroundAnimation();
			DeckBuilder_Data.selectedDeck = null;
			break;
		case FRIENDLIST:
			Draw_3Menu.startBackgroundAnimation();
			TextFieldHandler.showTextField(TextFieldHandler.FRIENDLIST_RequestName);
			break;
		case MATCHHISTORY:
			Draw_3Menu.startBackgroundAnimation();
			break;
		case CREDITS:
			Draw_3Menu.startBackgroundAnimation();
			if(Draw_7Credits.creditsScroll == null) {
				Draw_7Credits.creditsScroll = new DynamicInteger(Draw_7Credits.scrollSpeed, 1, 0, 8000);
			}
			break;
		case INGAME:
			
			break;
		case AFTERGAME:
			
			break;
		}
		
		drawState = newDrawState;
		
	}
	
	private static void leaveState(DrawState oldDrawState, DrawState newDrawState) {
		//PERFORM EXIT CALLS IF LEAVING A STATE
		
		switch (oldDrawState) {
		case PROGRAMMSTART:
			
			break;
		case LOADINGSCREEN:
			
			break;
		case LOGIN:
			TextFieldHandler.hideTextField(TextFieldHandler.LOGIN_Name);
			TextFieldHandler.hideTextField(TextFieldHandler.LOGIN_Password);
			GraphicsHandler.getFrame().requestFocus();
			FileHandler.saveDataInFile(FileHandler.file_Settings, "LOGIN_Name", TextFieldHandler.LOGIN_Name.getText());
			if(newDrawState != DrawState.MENU) {
				Draw_3Menu.stopBackgroundAnimation();
			}
			break;
		case MENU:
			if(newDrawState != DrawState.DECKBUILDER && newDrawState != DrawState.CREDITS  && newDrawState != DrawState.FRIENDLIST && newDrawState != DrawState.MATCHHISTORY) {
				Draw_3Menu.stopBackgroundAnimation();
			}
			break;
		case DECKBUILDER:
			if(newDrawState != DrawState.MENU) {
				Draw_3Menu.stopBackgroundAnimation();
			}
			break;
		case FRIENDLIST:
			TextFieldHandler.hideTextField(TextFieldHandler.FRIENDLIST_RequestName);
			if(newDrawState != DrawState.MENU) {
				Draw_3Menu.stopBackgroundAnimation();
			}
			break;
		case MATCHHISTORY:
			if(newDrawState != DrawState.MENU) {
				Draw_3Menu.stopBackgroundAnimation();
			}
			break;
		case CREDITS:
			if(newDrawState != DrawState.MENU) {
				Draw_3Menu.stopBackgroundAnimation();
			}
			if(Draw_7Credits.creditsScroll != null) {
				AnimationHandler.stopAnimation(Draw_7Credits.creditsScroll);
				Draw_7Credits.creditsScroll = null;
			}
			break;
		case INGAME:
			
			break;
		case AFTERGAME:
			
			break;
		}
		
	}
	
	public static JFrame getFrame() {
		return frame;
	}
	public static Label getLabel() {
		return label;
	}
	public static int getWidth() {
		return width;
	}
	public static int getHeight() {
		return height;
	}
	
}
