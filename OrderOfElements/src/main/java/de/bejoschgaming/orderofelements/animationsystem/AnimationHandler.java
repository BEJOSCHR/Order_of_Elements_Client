package de.bejoschgaming.orderofelements.animationsystem;

import java.util.ArrayList;
import java.util.List;

import de.bejoschgaming.orderofelements.animationsystem.animations.Animation;

public class AnimationHandler {

	private static List<Animation> runningAnimations = new ArrayList<Animation>();
	
	public static void startAnimation(Animation animation) {
		
		runningAnimations.add(animation);
		animation.start();
		
	}
	
	public static void stopAnimation(Animation animation) {
		
		animation.finish(false);
		runningAnimations.remove(animation);
		
	}

	public static void stopAllAnimations() {
		
		for(Animation animation : runningAnimations) {
			stopAnimation(animation);
		}
		
	}
	
	public static List<Animation> getRunningAnimations() {
		return runningAnimations;
	}
	
}
