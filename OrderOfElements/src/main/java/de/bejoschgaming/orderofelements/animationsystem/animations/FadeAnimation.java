package de.bejoschgaming.orderofelements.animationsystem.animations;

import java.awt.Color;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;

public class FadeAnimation extends Animation {

	protected int alpha = 0;
	protected int darkingPerStep = 10;
	protected boolean onlyFadeIn = false;
	
	/**
	 * 60 for totalsteps and 5 for darking with onlyFadeIn true as example
	 */
	public FadeAnimation(int totalSteps, int darkingPerStep, boolean onlyFadeIn) {
		super(3, totalSteps);
		
		this.darkingPerStep = darkingPerStep;
		this.onlyFadeIn = onlyFadeIn;
		
	}

	@Override
	protected void startAction() {
		
		if(onlyFadeIn == true) {
			alpha = 255;
		}else { 
			alpha = 0;
		}
		
	}
	
	@Override
	protected void stepAction() {
		
		if(this.getCurrentStep() > getTotalSteps()-(255.0/this.darkingPerStep)) {
			alpha -= darkingPerStep+1;
		}else if(onlyFadeIn == false && this.getCurrentStep() < (255.0/this.darkingPerStep)) {
			alpha += darkingPerStep+1;
		}
		
		if(alpha < 0) { this.alpha = 0; }else 
		if(alpha > 255) { this.alpha = 255; }
		
	}
	
	@Override
	protected void finishAction(boolean stepLimitReached) {
		
		alpha = 0;
		
	}
	
	@Override
	public void draw(Graphics g) {
		
		g.setColor(new Color(0, 0, 0, this.alpha));
		g.fillRect(0, 0, GraphicsHandler.getWidth(), GraphicsHandler.getHeight());
		
	}
	
}
