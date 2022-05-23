package de.bejoschgaming.orderofelements.graphics.drawparts;

import java.awt.Color;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;

public class Draw_1Loadingscreen {

	public static void draw(Graphics g) {
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, GraphicsHandler.getWidth(), GraphicsHandler.getHeight());
		
		GraphicsHandler.drawCentralisedText(g, Color.WHITE, 200, "OrderOfElements", GraphicsHandler.getWidth()/2, GraphicsHandler.getHeight()/2);
		
	}
	
}
