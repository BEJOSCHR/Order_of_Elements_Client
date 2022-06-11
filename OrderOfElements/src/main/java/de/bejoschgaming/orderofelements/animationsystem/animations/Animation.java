package de.bejoschgaming.orderofelements.animationsystem.animations;

import java.awt.Graphics;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;

public class Animation {

	private int ticksPerStep;
	private int totalSteps;
	private int currentTick = 0;
	private int currentStep = 0;
	
	public Animation(int ticksPerStep, int totalSteps) {
		
		this.ticksPerStep = ticksPerStep;
		this.totalSteps = totalSteps;
		
	}
	
	public void start() {
		
		this.startAction();
		
	}
	
	protected void startAction() {}
	
	public void tick() {
		
		this.currentTick++;
		
		if(this.currentTick == this.ticksPerStep) {
			this.step();
			this.currentTick = 0;
		}
		
	}
	
	private void step() {
		
		this.currentStep++;
		
		if(this.totalSteps != -1 && this.currentStep == this.totalSteps) {
			this.finish(true);
		}else {
			this.stepAction();
		}
		
		if(this.totalSteps != -1 && this.currentStep == (int) (this.totalSteps/2.0) ) {
			this.halfTimeAction();
		}
		
	}
	
	protected void stepAction() {}
	
	protected void halfTimeAction() {}
	
	public void finish(boolean stepLimitReached) {
		
		AnimationHandler.stopAnimation(this, true);
		this.finishAction(stepLimitReached);
		
	}
	
	protected void finishAction(boolean stepLimitReached) {}
	
	public void draw(Graphics g) {}
	
	public int getTicksPerStep() {
		return ticksPerStep;
	}
	public int getTotalSteps() {
		return totalSteps;
	}
	public int getCurrentTick() {
		return currentTick;
	}
	public int getCurrentStep() {
		return currentStep;
	}
	
}
