package de.bejoschgaming.orderofelements.animationsystem.animations;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;

public class TextAnimation extends Animation {

	protected int alpha = 0;
	protected int darkingPerStep = 10;
	protected boolean onlyFadeIn = false;
	
	protected int x, y;
	protected String text;
	protected Color color;
	protected Font font;
	
	/**
	 * 60 for totalsteps and 5 for darking with onlyFadeIn true as example
	 */
	public TextAnimation(int totalSteps, int darkingPerStep, boolean onlyFadeIn, int x, int y, String text, Color color, Font font) {
		super(3, totalSteps);
		
		this.darkingPerStep = darkingPerStep;
		this.onlyFadeIn = onlyFadeIn;
		
		this.x = x;
		this.y = y;
		this.text = text;
		this.color = color;
		this.font = font;
		
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
		
		Color fadeColor = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), this.alpha);
		GraphicsHandler.drawCentralisedText(g, fadeColor, this.font, this.text, this.x, this.y);
		
	}
	
}
