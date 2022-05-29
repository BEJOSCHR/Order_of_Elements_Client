package de.bejoschgaming.orderofelements.graphics.drawparts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;

public class Draw_2Login {

	public static void draw(Graphics g) {
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, GraphicsHandler.getWidth(), GraphicsHandler.getHeight());
		
		GraphicsHandler.drawCentralisedText(g, Color.WHITE, new Font("Arial", Font.BOLD, GraphicsHandler.getRelativTextSize(120)), "LOGIN", GraphicsHandler.getWidth()/2, 250);
		
	}
	
}
