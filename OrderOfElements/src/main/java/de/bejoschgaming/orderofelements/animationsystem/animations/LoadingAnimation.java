package de.bejoschgaming.orderofelements.animationsystem.animations;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;

public class LoadingAnimation extends Animation {

	protected boolean freezeAnimation = false;
	protected String letters[] = {"O","r","d","e","r"," ","O","f"," ","E","l","e","m","e","n","t","s"}; //17 letters
	protected int shouldWaitSteps = 10;
	protected int waitedSteps;
	protected boolean fromLeft;
	protected int currentPos = 0;

	protected int alpha = 0;
	protected int alphaSteps = 13;
	
	protected boolean textShown = true;
//	protected int textBlinkShouldWait = 10;
//	protected int textBlinkWait;
	
	protected String messageToDisplay;
	protected int textSize;
	protected boolean darkBackground;
	
	public LoadingAnimation(int totalSteps, String messageToDisplay, int textSize, boolean darkBackground) {
		super(9, totalSteps);
		
		this.messageToDisplay = messageToDisplay;
		this.textSize = textSize;
		this.darkBackground = darkBackground;
		
	}

	@Override
	protected void startAction() {
		
		this.alpha = 0;
		this.fromLeft = false;
		this.waitedSteps = this.shouldWaitSteps;
//		this.textBlinkWait = this.textBlinkShouldWait/2;
		
	}
	
	@Override
	protected void stepAction() {
		
		if(this.freezeAnimation == true) {
			waitedSteps = shouldWaitSteps;
			fromLeft = true;
			this.currentPos = 0;
		}else if(fromLeft) {
			
			if(this.currentPos == 17) {
				//END
				if(waitedSteps == 0) {
					waitedSteps = shouldWaitSteps;
					fromLeft = !fromLeft;
				}else {
					waitedSteps--;
				}
			}else {
				//GO UP
				this.currentPos++;
				if(this.currentPos == 6 || this.currentPos == 9) {
					this.currentPos++;
				}
			}
			
		}else {
			
			if(this.currentPos == 0) {
				//END
				if(waitedSteps == 0) {
					waitedSteps = shouldWaitSteps;
					fromLeft = !fromLeft;
				}else {
					waitedSteps--;
				}
			}else {
				//GO DOWN
				this.currentPos--;
				if(this.currentPos == 6 || this.currentPos == 9) {
					this.currentPos--;
				}
			}
			
		}

		//TEXTBLINK
//		if(textBlinkWait == 0) {
//			textBlinkWait = textBlinkShouldWait;
//			textShown = !textShown;
//		}else {
//			textBlinkWait--;
//		}
		
		//ALPHA
		if(this.getCurrentStep() >= 6 && this.alpha < 255) {
			this.alpha += this.alphaSteps;
			if(this.alpha >= 255) {
				this.alpha = 255;
			}
		}
		
	}
	
	@Override
	public void draw(Graphics g) {
		
		if(this.darkBackground == true) {
			g.setColor(new Color(0, 0, 0, 255));
			g.fillRect(0, 0, GraphicsHandler.getWidth(), GraphicsHandler.getHeight());
		}
		
		GraphicsHandler.drawCentralisedText(g, new Color(255, 255, 255, this.alpha), new Font("Arial", Font.BOLD, GraphicsHandler.getRelativTextSize(62)), this.getAnimatedText(), GraphicsHandler.getWidth()/2, (GraphicsHandler.getHeight()/2)*1);
		
		if(this.textShown == true) {
			GraphicsHandler.drawCentralisedText(g, new Color(255, 255, 255, this.alpha), new Font("Arial", Font.BOLD, GraphicsHandler.getRelativTextSize(this.textSize)), this.messageToDisplay, GraphicsHandler.getWidth()/2, (GraphicsHandler.getHeight()/3)*2);	
		}
		
	}
	
	public String getAnimatedText() {
		String output = "";
		for(int i = 0 ; i < 17 ; i++) {
			if(i == 0 || i == 6 || i == 9 || i > this.currentPos) {
				output += this.letters[i];
			}
		}
		return output;
	}
	
	public void freezeAnimation() {
		freezeAnimation = !freezeAnimation;
	}
	public void updateLoadingMessage(String newMessage) {
		this.messageToDisplay = newMessage;
	}
	public String getMessageToDisplay() {
		return messageToDisplay;
	}
	public boolean isDarkBackground() {
		return darkBackground;
	}
	public boolean isFrozen() {
		return freezeAnimation;
	}
	
}
