package de.bejoschgaming.orderofelements.animationsystem.animations;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;

public class DynamicInteger extends Animation {

	protected int startValue, endValue;
	protected int addPerStep;
	protected int value = 0;
	
	public DynamicInteger(int ticksPerStep, int addPerStep, int startValue, int endValue) {
		super(ticksPerStep, -1);
		
		this.startValue = startValue;
		this.endValue = endValue;
		this.addPerStep = addPerStep;
		
		AnimationHandler.startAnimation(this);
		
	}

	@Override
	protected void startAction() {
		
		this.value = this.startValue;
		
	}
	
	@Override
	protected void stepAction() {
		
		this.value += this.addPerStep;
		
		if(this.startValue <= this.endValue) {
			//POS STEPS (INCREASE)
			if(this.value >= this.endValue) {
				this.finish(false);
			}
		}else {
			//NEG STEPS (DECREASE)
			if(this.value <= this.endValue) {
				this.finish(false);
			}
		}
		
	}
	
	@Override
	protected void finishAction(boolean stepLimitReached) {
		
		this.value = this.endValue;
		
	}
	
	public int getValue() {
		if(this.startValue <= this.endValue) {
			//POS STEPS (INCREASE)
			if(this.value >= this.endValue) {
				return this.endValue;
			}
		}else {
			//NEG STEPS (DECREASE)
			if(this.value <= this.endValue) {
				return this.endValue;
			}
		}
		return this.value;
	}
	
}
