package de.bejoschgaming.orderofelements.animationsystem.animations;

import java.awt.Graphics;

import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;

public class MenuBookAnimation extends Animation {
	
	protected boolean forwards;
	
	public MenuBookAnimation(boolean forwards) {
		super(20, 6);
		
		this.forwards = forwards;
		
	}

	@Override
	public void draw(Graphics g) {
		
		if(this.forwards == true) {
			g.drawImage(ImageHandler.menu_book[this.getCurrentStep()+1], 0, 0, null);
		}else {
			g.drawImage(ImageHandler.menu_book[6-this.getCurrentStep()], 0, 0, null);
		}
		
	}
	
}
