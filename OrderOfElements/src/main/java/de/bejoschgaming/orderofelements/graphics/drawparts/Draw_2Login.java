package de.bejoschgaming.orderofelements.graphics.drawparts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;
import de.bejoschgaming.orderofelements.animationsystem.animations.MenuBookAnimation;
import de.bejoschgaming.orderofelements.graphics.DrawState;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;
import de.bejoschgaming.orderofelements.maa.MouseActionArea;
import de.bejoschgaming.orderofelements.maa.MouseActionAreaType;

public class Draw_2Login {

	public static int demoNumber = 1;
	
	public static void initMAAs() {
		
		new MouseActionArea(0, 0, 10000, 10000, MouseActionAreaType.MAA_TEST, "", 1, Color.WHITE, Color.WHITE, false, false) {
			
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.LOGIN;
			}
			
			@Override
			public void performAction_LEFT_RELEASE() {
				AnimationHandler.startAnimation(new MenuBookAnimation(true));
				demoNumber++;
			}
			
			@Override
			public void performAction_RIGHT_RELEASE() {
				AnimationHandler.startAnimation(new MenuBookAnimation(false));
				demoNumber--;
			}
			
		};
		
	}
	
	public static void draw(Graphics g) {
		
//		g.setColor(Color.DARK_GRAY);
//		g.fillRect(0, 0, GraphicsHandler.getWidth(), GraphicsHandler.getHeight());
		
		//BACKGROUND
		if(Draw_3Menu.backGroundAnimationFrame != null) {
			int backgroundFrame = Draw_3Menu.backGroundAnimationFrame.getValue();
			g.drawImage(ImageHandler.menu_natur[backgroundFrame], 0, 0, null);
		}
		
		g.drawImage(ImageHandler.menu_book[0], 0, 0, null);
		
		g.setFont(new Font("Arial", Font.BOLD, GraphicsHandler.getRelativTextSize(26)));
		g.drawString(demoNumber+".", (int) (GraphicsHandler.getWidth()*0.20), (int) (GraphicsHandler.getHeight()*0.8));
		g.drawString((demoNumber+1)+".", (int) (GraphicsHandler.getWidth()*0.80), (int) (GraphicsHandler.getHeight()*0.8));
		
	}
	
}
