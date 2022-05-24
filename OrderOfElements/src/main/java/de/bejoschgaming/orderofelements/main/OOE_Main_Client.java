package de.bejoschgaming.orderofelements.main;

import java.util.Timer;
import java.util.TimerTask;

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
		
		ImageHandler.loadPreUsedImages();
		
		MouseActionAreaHandler.initMAAs();
		
		GraphicsHandler.switchTo(DrawState.LOADINGSCREEN);
		
		ImageHandler.loadImages();
		
		//THIS FREEZES THE THREAD SO NO LOADING BEHIND THIS
		ServerConnection.connectToServer();
		
		ConsoleHandler.printMessageInConsole("Startup finished!", true);
		
	}

	public static void terminateProgramm() {
		
		ConsoleHandler.printMessageInConsole("Stopping OrderOfElements_Client [OOE_C]...", true);
		
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
