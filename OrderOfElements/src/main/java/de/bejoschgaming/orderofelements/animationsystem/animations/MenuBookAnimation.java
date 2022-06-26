package de.bejoschgaming.orderofelements.animationsystem.animations;

import java.awt.Graphics;

import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;

public class MenuBookAnimation extends Animation {
	
	protected boolean forwards;
	
	public MenuBookAnimation(boolean forwards) {
		super(20, 5);
		
		this.forwards = forwards;
		
	}

	@Override
	public void draw(Graphics g) {
		
		int frame;
		
		if(this.forwards == true) {
			frame = this.getCurrentStep()+1;
		}else {
			frame = 6-this.getCurrentStep();
		}
		
		if(frame < 0) { frame = 0; }
		else if(frame > 6) { frame = 6; }
		
		g.drawImage(ImageHandler.menu_book[frame], 0, 0, null);
		
	}
	
}
