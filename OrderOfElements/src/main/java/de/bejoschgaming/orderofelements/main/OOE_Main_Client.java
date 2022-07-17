package de.bejoschgaming.orderofelements.main;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;
import de.bejoschgaming.orderofelements.animationsystem.TickHandler;
import de.bejoschgaming.orderofelements.animationsystem.animations.FadeAnimation;
import de.bejoschgaming.orderofelements.animationsystem.animations.FadeType;
import de.bejoschgaming.orderofelements.componentssystem.TextAreaHandler;
import de.bejoschgaming.orderofelements.componentssystem.TextFieldHandler;
import de.bejoschgaming.orderofelements.connection.ServerConnection;
import de.bejoschgaming.orderofelements.debug.ConsoleHandler;
import de.bejoschgaming.orderofelements.filesystem.FileHandler;
import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.graphics.DrawState;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;
import de.bejoschgaming.orderofelements.maasystem.MouseActionArea;
import de.bejoschgaming.orderofelements.maasystem.MouseActionAreaHandler;
import de.bejoschgaming.orderofelements.maasystem.MouseActionAreaType;
import de.bejoschgaming.orderofelements.mwsystem.MultiWindowHandler;
import de.bejoschgaming.orderofelements.mwsystem.MultiWindowType;
import de.bejoschgaming.orderofelements.mwsystem.mws.MW_DesicionWindow;
import de.bejoschgaming.orderofelements.mwsystem.mws.MultiWindow;

public class OOE_Main_Client {
	
	public static void main(String[] args) {
		
		ConsoleHandler.printMessageInConsole("Starting OrderOfElements_Client [OOE_C]", true);
		
		FileHandler.firstWrite();
		
		ConsoleHandler.startUserInputScanner();
		
		GraphicsHandler.initVisuals();
		
		TickHandler.startTickTimer();
		
		ImageHandler.loadPreUsedImages();
		
		AnimationHandler.startAnimation(new FadeAnimation(60, 5, FadeType.FADEOUT));
		GraphicsHandler.switchTo(DrawState.LOADINGSCREEN);
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				AnimationHandler.startAnimation(new FadeAnimation(108, 5, FadeType.FADEINANDOUT) {
					
					@Override
					protected void halfTimeAction() {
						
						ServerConnection.connectToServer();
						GraphicsHandler.switchTo(DrawState.LOGIN);
						
					}
					
				});
			}
		}, (int) (1000*2.0));
		
		FontHandler.loadFonts();
		
		MouseActionAreaHandler.initMAAs();
		
		TextFieldHandler.loadTextFields();
		TextAreaHandler.loadTextAreas();
		
		ImageHandler.loadImages();
		
		MultiWindowHandler.openMW(new MW_DesicionWindow(
				"Do you like this multiwindow system?", 
				FontHandler.getFont(FontHandler.medievalSharp_regular, 22), 
				new MouseActionArea(-1, -1, 74, 34, MouseActionAreaType.MW_DesicionWindow_Accept_, "Yes", 22, Color.WHITE, Color.GREEN.darker(), true, null) {
					@Override
					public void performAction_LEFT_RELEASE() {
						ConsoleHandler.printMessageInConsole("NICE! I like this mw system as well", true);
						MultiWindowHandler.closeMW(this.getMW());
					}
				}, 
				new MouseActionArea(-1, -1, 74, 34, MouseActionAreaType.MW_DesicionWindow_Decline_, "No", 22, Color.WHITE, Color.RED, true, null) {
					@Override
					public void performAction_LEFT_RELEASE() {
						ConsoleHandler.printMessageInConsole("Why not? I like it!", true);
						MultiWindowHandler.closeMW(this.getMW());
					}
				},
				Color.WHITE, Color.DARK_GRAY));
		MultiWindowHandler.openMW(new MultiWindow(MultiWindowType.MW_Test, Color.WHITE, Color.DARK_GRAY, true, true));
		MultiWindowHandler.openMW(new MultiWindow(MultiWindowType.MW_Test, Color.WHITE, Color.DARK_GRAY, true, true));
		
		
		ConsoleHandler.printMessageInConsole("Startup finished!", true);
		
	}

	public static void terminateProgramm(boolean instant) {
		
		ConsoleHandler.printMessageInConsole("Stopping OrderOfElements_Client [OOE_C]...", true);
		
		TickHandler.stopTickTimer();
		
		ConsoleHandler.stopUserInputScanner();
		
		ServerConnection.disconnectFromServer();
		
		ConsoleHandler.printMessageInConsole("Stopping finished!", true);
		
		//CLOSE DLEAY
		if(instant == true) {
			ConsoleHandler.printMessageInConsole("Terminating...", true);
			System.exit(0);
		}else {
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					ConsoleHandler.printMessageInConsole("Terminating...", true);
					System.exit(0);
				}
			}, 1000*3);
		}
		
	}
	
}
