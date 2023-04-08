package de.bejoschgaming.orderofelements.animationsystem.animations;

import java.awt.Color;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;

public class FadeAnimation extends Animation {

	protected int alpha = 0;
	protected int darkingPerStep = 10;
	protected FadeType fadeType = FadeType.FADEINANDOUT;
	protected boolean withIcon = true;
	
	/**
	 * 60 for totalsteps and 5 for darking with onlyFadeIn true as example
	 */
	public FadeAnimation(int totalSteps, int darkingPerStep, FadeType fadeType) {
		super(3, totalSteps);
		
		this.darkingPerStep = darkingPerStep;
		this.fadeType = fadeType;
		this.withIcon = true;
		
	}
	/**
	 * 60 for totalsteps and 5 for darking with onlyFadeIn true as example
	 */
	public FadeAnimation(int totalSteps, int darkingPerStep, FadeType fadeType, boolean withIcon) {
		super(3, totalSteps);
		
		this.darkingPerStep = darkingPerStep;
		this.fadeType = fadeType;
		this.withIcon = withIcon;
		
	}

	@Override
	protected void startAction() {
		
		switch (this.fadeType) {
		case FADEIN:
			alpha = 0;
			break;
		case FADEOUT:
			alpha = 255;
			break;
		case FADEINANDOUT:
			alpha = 0;
			break;
		}
		
	}
	
	@Override
	protected void stepAction() {
		
		if(this.fadeType != FadeType.FADEIN && this.getCurrentStep() > getTotalSteps()-(255.0/this.darkingPerStep)) {
			alpha -= darkingPerStep+1;
		}else if(this.fadeType != FadeType.FADEOUT && this.getCurrentStep() < (255.0/this.darkingPerStep)) {
			alpha += darkingPerStep+1;
		}
		
		if(alpha < 0) { this.alpha = 0; }else 
		if(alpha > 255) { this.alpha = 255; }
		
	}
	
	@Override
	protected void finishAction(boolean stepLimitReached) {
		
		switch (this.fadeType) {
		case FADEIN:
			alpha = 255;
			break;
		case FADEOUT:
			alpha = 0;
			break;
		case FADEINANDOUT:
			alpha = 0;
			break;
		}
		
	}
	
	@Override
	public void draw(Graphics g) {
		
		g.setColor(new Color(0, 0, 0, this.alpha));
		g.fillRect(0, 0, GraphicsHandler.getWidth(), GraphicsHandler.getHeight());
		
		if(this.withIcon && this.alpha > 80) {
			g.drawImage(ImageHandler.menu_ooeIcon_clean_transparent, GraphicsHandler.getWidth()/2-ImageHandler.iconSize/2, GraphicsHandler.getHeight()/2-ImageHandler.iconSize/2, null);
		}
	}
	
}
