package de.bejoschgaming.orderofelements.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;

import de.bejoschgaming.orderofelements.gamesystem.map.MapData;
import de.bejoschgaming.orderofelements.graphics.handler.KeyHandler;
import de.bejoschgaming.orderofelements.graphics.handler.MouseHandler;
import de.bejoschgaming.orderofelements.graphics.handler.WindowHandler;

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

//		try {
//		    frame.setIconImage(ImageIO.read(BomberfrauMain.class.getClassLoader()
//			    .getResourceAsStream(ImageHandler.PATH + "Bomberman_Icon.png")));
//		} catch (Exception error) {
//		    ConsoleHandler.print("Couldn't load window icon!", MessageType.BACKEND);
//		}

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		frame.setSize(1280, 720);
		
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
	 * 
	 * @param g,        das Graphics object
	 * @param color,    die Textfarbe
	 * @param textSize, die Textgr��e
	 * @param text,     der eigentliche Text
	 * @param x,        die X-Koordinate (Links-Rechts-Verschiebung) zu der der Text
	 *                  mittig dargestellt wird
	 * @param y,        die Y-Koordinate (Oben-Unten-Verschiebung) zu der der Text
	 *                  mittig dargestellt wird
	 */
	public static void drawCentralisedText(Graphics g, Color color, int relTextSize, String text, int x, int y) {
	
		g.setColor(color);
		int textSize = (int) (((double) relTextSize / 1080.0) * (double) GraphicsHandler.getHeight() + 0.5);
		g.setFont(new Font("Arial", Font.BOLD, textSize));
		int width = g.getFontMetrics().stringWidth(" "+text);
		int height = g.getFontMetrics().getHeight() * 2 / 3;
		g.drawString(" "+text, x - width / 2, y + height / 2);
	
	}
	
	public static DrawState getDrawState() {
		return drawState;
	}
	
	public static void switchTo(DrawState newDrawState) {
		
		leaveState(getDrawState());
		
		switch (newDrawState) {
		case PROGRAMMSTART:
			//WILL NEVER HAPPEN!
			break;
		case LOADINGSCREEN:
			
			break;
		case LOGIN:
			
			break;
		case MENU:
			
			break;
		case DECKBUILDER:
			
			break;
		case INGAME:
			
			break;
		case AFTERGAME:
			
			break;
		}
		drawState = newDrawState;
		
	}
	
	private static void leaveState(DrawState oldDrawState) {
		//PERFORM EXIT CALLS IF LEAVING A STATE
		
		switch (oldDrawState) {
		case PROGRAMMSTART:
			
			break;
		case LOADINGSCREEN:
			
			break;
		case LOGIN:
			
			break;
		case MENU:
			
			break;
		case DECKBUILDER:
			
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
