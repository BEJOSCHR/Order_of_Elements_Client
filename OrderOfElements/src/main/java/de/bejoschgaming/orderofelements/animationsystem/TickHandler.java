package de.bejoschgaming.orderofelements.animationsystem;

import java.util.Timer;
import java.util.TimerTask;

import de.bejoschgaming.orderofelements.animationsystem.animations.Animation;
import de.bejoschgaming.orderofelements.debug.ConsoleHandler;

public class TickHandler {

	private static Timer tickTimer = null;
	
	public static void startTickTimer() {
		
		if(tickTimer == null) {
			
			tickTimer = new Timer();
			tickTimer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					
					for(Animation animation : AnimationHandler.getRunningAnimations()) {
						animation.tick();
					}
					
				}
			}, 0, 10);
			ConsoleHandler.printMessageInConsole("TickTimer started!", true);
			
		}
		
	}
	
	public static void stopTickTimer() {
		
		if(tickTimer != null) {
			
			tickTimer.cancel();
			tickTimer = null;
			ConsoleHandler.printMessageInConsole("TickTimer stopped!", true);
			
		}
		
	}
	
}
