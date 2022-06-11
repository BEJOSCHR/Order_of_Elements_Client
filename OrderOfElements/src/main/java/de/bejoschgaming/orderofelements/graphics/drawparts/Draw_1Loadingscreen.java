package de.bejoschgaming.orderofelements.graphics.drawparts;

import java.awt.Color;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;
import de.bejoschgaming.orderofelements.connection.ServerConnection;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;
import de.bejoschgaming.orderofelements.maa.MouseActionArea;
import de.bejoschgaming.orderofelements.maa.MouseActionAreaType;
import de.bejoschgaming.orderofelements.main.OOE_Main_Client;

public class Draw_1Loadingscreen {

	public static void initMAAs() {
		
		new MouseActionArea(5000-(1100/2), 8000, 1100, 580, MouseActionAreaType.LOADINGSCREEN_SERVERCONFAILED_EXIT, 
				"Exit", 24, Color.WHITE, Color.ORANGE, true, true) {
			
			@Override
			public boolean isActiv() {
				//LOADING HAS STOPPED BUT NOT CANCLED AND NO SERVER CON IS THERE
				return AnimationHandler.isLoadingAnimationFrozen() && ServerConnection.connectedToServer == false;
			}
			
			@Override
			public void performAction_LEFT_RELEASE() {
				super.performAction_LEFT_RELEASE();
				
				OOE_Main_Client.terminateProgramm(true);
				
			}
			
		};
		
	}
	
	public static void draw(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GraphicsHandler.getWidth(), GraphicsHandler.getHeight());
		
		g.drawImage(ImageHandler.loadingscreen_bgLogo, 0, (GraphicsHandler.getHeight()/3)/2, null);
		
	}
	
}
