package de.bejoschgaming.orderofelements.graphics.drawparts;

import java.awt.Color;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;
import de.bejoschgaming.orderofelements.animationsystem.animations.DynamicInteger;
import de.bejoschgaming.orderofelements.animationsystem.animations.FadeAnimation;
import de.bejoschgaming.orderofelements.animationsystem.animations.FadeType;
import de.bejoschgaming.orderofelements.animationsystem.animations.MenuBookAnimation;
import de.bejoschgaming.orderofelements.connection.ServerConnection;
import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.graphics.DrawState;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;
import de.bejoschgaming.orderofelements.maa.MouseActionArea;
import de.bejoschgaming.orderofelements.maa.MouseActionAreaType;
import de.bejoschgaming.orderofelements.main.OOE_Main_Client;

public class Draw_3Menu {

	public static DynamicInteger backGroundAnimationFrame = null;
	
	public static void initMAAs() {
		
		new MouseActionArea(2740, 3580, 1000, 530, MouseActionAreaType.MENU_Play, "Play", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.MENU;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				//TODO
			}
		};
		new MouseActionArea(2740, 4260, 1000, 530, MouseActionAreaType.MENU_Deckbuilder, "Deckbuilder", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.MENU;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				//TODO
			}
		};
		new MouseActionArea(2740, 4940, 1000, 530, MouseActionAreaType.MENU_Settings, "Settings", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.MENU;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				//TODO
			}
		};
		new MouseActionArea(2740, 5620, 1000, 530, MouseActionAreaType.MENU_Credits, "Credits", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.MENU;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				AnimationHandler.startAnimation(new FadeAnimation(95, 6, FadeType.FADEINANDOUT) {
					@Override
					protected void halfTimeAction() {
						GraphicsHandler.switchTo(DrawState.CREDITS);
					}
				});
			}
		};
		new MouseActionArea(2740, 6300, 1000, 530, MouseActionAreaType.MENU_Cancle, "Exit", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.MENU;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				OOE_Main_Client.terminateProgramm(true);
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
		GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, 50), "Order Of Elements", (GraphicsHandler.getWidth()*32)/100, (GraphicsHandler.getHeight()*3)/12);
		
		//DRAW RIGHT PAGE
		
		
	}
	
}
