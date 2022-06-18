package de.bejoschgaming.orderofelements.graphics.drawparts;

import java.awt.Color;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;

public class Draw_1Loadingscreen {

	public static void initMAAs() {
		
		
		
	}
	
	public static void draw(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GraphicsHandler.getWidth(), GraphicsHandler.getHeight());
		
		g.drawImage(ImageHandler.loadingscreen_bgLogo, 0, (GraphicsHandler.getHeight()/3)/2, null);
		
	}
	
}
