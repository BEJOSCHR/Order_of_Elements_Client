package de.bejoschgaming.orderofelements.animationsystem;

import java.util.ArrayList;
import java.util.List;

import de.bejoschgaming.orderofelements.animationsystem.animations.Animation;
import de.bejoschgaming.orderofelements.animationsystem.animations.FadeAnimation;
import de.bejoschgaming.orderofelements.animationsystem.animations.FadeType;
import de.bejoschgaming.orderofelements.animationsystem.animations.LoadingAnimation;

public class AnimationHandler {

	private static List<Animation> runningAnimations = new ArrayList<Animation>();

	private static LoadingAnimation loadingAnimation = null;
	
	public static void startAnimation(Animation animation) {
		
		runningAnimations.add(animation);
		animation.start();
		
	}
	
	public static void stopAnimation(Animation animation) { stopAnimation(animation, false); } //STOPPED FROM EXTERN SO CALL ANIMATION STOP
	public static void stopAnimation(Animation animation, boolean fromAnimation) { //CALLED FROM ANIMATION SO DONT CALL ANIMATION TO PREVENT INFINIT-LOOPS
		
		if(fromAnimation == false) {
			animation.finish(false);
		}else {
			runningAnimations.remove(animation);
		}
		
	}

	public static void stopAllAnimations() {
		
		for(Animation animation : runningAnimations) {
			stopAnimation(animation);
		}
		
	}
	
	public static void startLoadingAnimation(String messageToDisplay, int textSize, boolean darkBackground) { startLoadingAnimation(-1, messageToDisplay, textSize, darkBackground); } //INFINIT LOADING UNTIL STOP IS CALLED
	public static void startLoadingAnimation(int autoFinishAfterThisSteps, String messageToDisplay, int textSize, boolean darkBackground) { //END AFTER THIS STEPS
		
		if(loadingAnimation != null) { return; }
		
		if(darkBackground == true) {
			//WITH FADE
			FadeAnimation fadeAnimation = new FadeAnimation(55, 10, FadeType.FADEIN) {
				@Override
				protected void halfTimeAction() {
					loadingAnimation = new LoadingAnimation(autoFinishAfterThisSteps, messageToDisplay, textSize, darkBackground);
					AnimationHandler.startAnimation(loadingAnimation);
				}
			};
			AnimationHandler.startAnimation(fadeAnimation);
		}else {
			//NO FADE
			loadingAnimation = new LoadingAnimation(autoFinishAfterThisSteps, messageToDisplay, textSize, darkBackground);
			AnimationHandler.startAnimation(loadingAnimation);
		}
		
	}
	public static void updateLoadingMessage(String newMessage) {
		
		if(loadingAnimation == null) { return; }
		
		loadingAnimation.updateLoadingMessage(newMessage);
		
	}
	public static void freezeLoadingAnimation() {
		
		if(loadingAnimation == null) { return; }
		
		loadingAnimation.freezeAnimation();
		
	}
	public static void stopLoadingAnimation() {
		
		if(loadingAnimation == null) { return; };
		
		if(loadingAnimation.isDarkBackground() == true) {
			//WITH FADE
			AnimationHandler.startAnimation(new FadeAnimation(30, 10, FadeType.FADEOUT));
		}
		AnimationHandler.stopAnimation(loadingAnimation);
		loadingAnimation = null;
		
	}
	
	public static List<Animation> getRunningAnimations() {
		return runningAnimations;
	}
	
}
