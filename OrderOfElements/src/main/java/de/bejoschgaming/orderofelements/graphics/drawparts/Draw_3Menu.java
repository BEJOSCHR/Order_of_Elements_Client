package de.bejoschgaming.orderofelements.graphics.drawparts;

import java.awt.Graphics;

import de.bejoschgaming.orderofelements.animationsystem.animations.DynamicInteger;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;

public class Draw_3Menu {

	public static DynamicInteger backGroundAnimationFrame = null;
	
	public static void initMAAs() {
		
		
		
	}
	
	public static void draw(Graphics g) {
		
		//BACKGROUND
		if(Draw_3Menu.backGroundAnimationFrame != null) {
			int backgroundFrame = Draw_3Menu.backGroundAnimationFrame.getValue();
			g.drawImage(ImageHandler.menu_natur[backgroundFrame], 0, 0, null);
		}
		
		g.drawImage(ImageHandler.menu_book[0], 0, 0, null);
		
	}
	
}
