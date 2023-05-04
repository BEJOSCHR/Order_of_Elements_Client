package de.bejoschgaming.orderofelements.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;


public class OOE_Main_Client {
	
	public static Frame frame = null;
	
	public static Map map = null;
	
	public static void main(String[] args) {
		
		ConsoleHandler.printMessageInConsole("Starting OrderOfElements_Client [OOE_C]", true);
		
		OOE_Main_Client.frame = new Frame();
		
		map = new Map();
		
		ConsoleHandler.printMessageInConsole("Startup finished!", true);
		
	}
	
	public static void terminateProgramm(boolean instant) {
		
		ConsoleHandler.printMessageInConsole("Stopping OrderOfElements_Client [OOE_C]...", true);
		
		
		
		ConsoleHandler.printMessageInConsole("Stopping finished!", true);
		
		//CLOSE DLEAY
		if(instant == true) {
			ConsoleHandler.printMessageInConsole("Terminating...", true);
			System.exit(0);
		}else {
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					ConsoleHandler.printMessageInConsole("Terminating...", true);
					System.exit(0);
				}
			}, 1000*3);
		}
		
	}
	
	/**
	 * Allgemeine Methode um einen beliebigen Text mit Parametern relativ zu einem
	 * Punkt (x,y) mittig darzustellen
	 * EXAMPLE: GraphicsHandler.drawCentralisedText(g, Color.BLACK, 12, "Order Of Elements", (GraphicsHandler.getWidth()*32)/100, (GraphicsHandler.getHeight()*3)/7);
	 *
	 * @param g,        das Graphics object
	 * @param color,    die Textfarbe
	 * @param textSize, die TextSize in der der Text dargestellt werden soll
	 * @param text,     der eigentliche Text
	 * @param x,        die X-Koordinate (Links-Rechts-Verschiebung) zu der der Text
	 *                  mittig dargestellt wird
	 * @param y,        die Y-Koordinate (Oben-Unten-Verschiebung) zu der der Text
	 *                  mittig dargestellt wird
	 */
	public static void drawCentralisedText(Graphics g, Color color, int textSize, String text, int x, int y) {
	
		g.setColor(color);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, textSize));
		int width = g.getFontMetrics().stringWidth(" "+text);
		int height = g.getFontMetrics().getHeight() * 2 / 3;
		g.drawString(" "+text, x - width / 2, y + height / 2);
	
	}
	
}
