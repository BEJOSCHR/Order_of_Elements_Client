package de.bejoschgaming.orderofelements.main;

import java.util.Timer;
import java.util.TimerTask;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;
import de.bejoschgaming.orderofelements.animationsystem.TickHandler;
import de.bejoschgaming.orderofelements.animationsystem.animations.FadeAnimation;
import de.bejoschgaming.orderofelements.animationsystem.animations.FadeType;
import de.bejoschgaming.orderofelements.connection.ServerConnection;
import de.bejoschgaming.orderofelements.debug.ConsoleHandler;
import de.bejoschgaming.orderofelements.filesystem.FileHandler;
import de.bejoschgaming.orderofelements.graphics.DrawState;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;
import de.bejoschgaming.orderofelements.maa.MouseActionAreaHandler;

public class OOE_Main_Client {
	
	public static void main(String[] args) {
		
		ConsoleHandler.printMessageInConsole("Starting OrderOfElements_Client [OOE_C]", true);
		
		FileHandler.firstWrite();
		
		ConsoleHandler.startUserInputScanner();
		
		GraphicsHandler.initVisuals();
		
		TickHandler.startTickTimer();
		
		ImageHandler.loadPreUsedImages();
		
		MouseActionAreaHandler.initMAAs();
		
		//TODO remove text animation and instead show loading animation while connecting to server after init logo bild+animation
		AnimationHandler.startAnimation(new FadeAnimation(60, 5, FadeType.FADEOUT));
		GraphicsHandler.switchTo(DrawState.LOADINGSCREEN);
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				AnimationHandler.startLoadingAnimation("Connecting to server", 22, true);
				new Timer().schedule(new TimerTask() {
					@Override
					public void run() {
						ServerConnection.connectToServer();
					}
				}, (int) (1000*3.0));
			}
		}, (int) (1000*2.7));
		
		ImageHandler.loadImages();
		
		ConsoleHandler.printMessageInConsole("Startup finished!", true);
		
	}

	public static void terminateProgramm() {
		
		ConsoleHandler.printMessageInConsole("Stopping OrderOfElements_Client [OOE_C]...", true);
		
		TickHandler.stopTickTimer();
		
		ConsoleHandler.stopUserInputScanner();
		
		ServerConnection.disconnectFromServer();
		
		ConsoleHandler.printMessageInConsole("Stopping finished!", true);
		
		//CLOSE DLEAY
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				ConsoleHandler.printMessageInConsole("Terminating...", true);
				System.exit(0);
			}
		}, 1000*3);
		
	}
	
}
