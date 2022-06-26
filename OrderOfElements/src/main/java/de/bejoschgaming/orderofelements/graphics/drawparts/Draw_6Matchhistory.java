package de.bejoschgaming.orderofelements.graphics.drawparts;

import java.awt.Color;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;
import de.bejoschgaming.orderofelements.animationsystem.animations.MenuBookAnimation;
import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.graphics.DrawState;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;
import de.bejoschgaming.orderofelements.maa.MouseActionArea;
import de.bejoschgaming.orderofelements.maa.MouseActionAreaType;

public class Draw_6Matchhistory {

	public static void initMAAs() {
		
		new MouseActionArea(540, 730, 160, 60, MouseActionAreaType.MATCHHISTORY_Back, "Back", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.MATCHHISTORY;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				AnimationHandler.startAnimation(new MenuBookAnimation(false) {
					@Override
					protected void halfTimeAction() {
						GraphicsHandler.switchTo(DrawState.MENU);
					}
				});
			}
		};
		
	}
	
	public static void draw(Graphics g) {
		
		//BACKGROUND
		if(Draw_3Menu.backGroundAnimationFrame != null) {
			int backgroundFrame = Draw_3Menu.backGroundAnimationFrame.getValue();
			g.drawImage(ImageHandler.menu_natur[backgroundFrame], 0, 0, null);
		}
		
		g.drawImage(ImageHandler.menu_book[0], 0, 0, null);
		
		//DRAW LEFT PAGE
		GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, 50), "Matchhistory", (GraphicsHandler.getWidth()*32)/100, (GraphicsHandler.getHeight()*30)/100);
		GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, 28), "You won X out of Y games", (GraphicsHandler.getWidth()*32)/100, (GraphicsHandler.getHeight()*37)/100);
		
		
		//DRAW RIGHT PAGE
		
		
	}
	
}
