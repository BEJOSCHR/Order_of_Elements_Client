package de.bejoschgaming.orderofelements.graphics.drawparts;

import java.awt.Color;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;
import de.bejoschgaming.orderofelements.animationsystem.animations.DynamicInteger;
import de.bejoschgaming.orderofelements.animationsystem.animations.FadeAnimation;
import de.bejoschgaming.orderofelements.animationsystem.animations.FadeType;
import de.bejoschgaming.orderofelements.animationsystem.animations.MenuBookAnimation;
import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.graphics.DrawState;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;
import de.bejoschgaming.orderofelements.maa.MouseActionArea;
import de.bejoschgaming.orderofelements.maa.MouseActionAreaType;
import de.bejoschgaming.orderofelements.main.OOE_Main_Client;

public class Draw_3Menu {

	private static int backgroundAnimationTempo = 15;
	public static DynamicInteger backGroundAnimationFrame = null;
	
	public static void initMAAs() {
		
		int x = 540, y = 380;
		int width = 160, height = 60;
		int distanceBetween = 22; 
		
		int extraButton_width = (GraphicsHandler.getWidth()*8)/100;
		int extraButton_height = (GraphicsHandler.getWidth()*3)/100;
		
		new MouseActionArea(x, y+0*(height+distanceBetween), width, height, MouseActionAreaType.MENU_Play, "Play", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.MENU;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				//TODO
			}
		};
		new MouseActionArea(x, y+1*(height+distanceBetween), width, height, MouseActionAreaType.MENU_Deckbuilder, "Deckbuilder", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.MENU;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				AnimationHandler.startAnimation(new MenuBookAnimation(true) {
					@Override
					protected void halfTimeAction() {
						GraphicsHandler.switchTo(DrawState.DECKBUILDER);
					}
				});
			}
		};
		new MouseActionArea(x, y+2*(height+distanceBetween), width, height, MouseActionAreaType.MENU_Settings, "Settings", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.MENU;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				//TODO
			}
		};
		new MouseActionArea(x, y+3*(height+distanceBetween), width, height, MouseActionAreaType.MENU_Credits, "Credits", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.MENU;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				AnimationHandler.startAnimation(new FadeAnimation(85, 6, FadeType.FADEINANDOUT) {
					@Override
					protected void halfTimeAction() {
						GraphicsHandler.switchTo(DrawState.CREDITS);
					}
				});
			}
		};
		new MouseActionArea(x, y+4*(height+distanceBetween), width, height, MouseActionAreaType.MENU_Cancle, "Exit", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.MENU;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				OOE_Main_Client.terminateProgramm(true);
			}
		};
		
		new MouseActionArea(1135, 710, extraButton_width, extraButton_height, MouseActionAreaType.MENU_Friendlist, "Friendlist", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.MENU;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				AnimationHandler.startAnimation(new MenuBookAnimation(true) {
					@Override
					protected void halfTimeAction() {
						GraphicsHandler.switchTo(DrawState.FRIENDLIST);
					}
				});
			}
		};
		new MouseActionArea(1135+extraButton_width+50, 710, extraButton_width, extraButton_height, MouseActionAreaType.MENU_Matchhistory, "Matchhistory", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.MENU;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				AnimationHandler.startAnimation(new MenuBookAnimation(true) {
					@Override
					protected void halfTimeAction() {
						GraphicsHandler.switchTo(DrawState.MATCHHISTORY);
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
		GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, 50), "Order Of Elements", (GraphicsHandler.getWidth()*32)/100, (GraphicsHandler.getHeight()*3)/12);
		
		//DRAW RIGHT PAGE
		//TODO PATCHNOTES
		
	}
	
	public static void startBackgroundAnimation() {
		
		if(Draw_3Menu.backGroundAnimationFrame == null) {
			Draw_3Menu.backGroundAnimationFrame = new DynamicInteger(backgroundAnimationTempo, 1, 0, 6) {
				
				@Override
				protected void stepAction() {
					
					this.value += this.addPerStep;
					
					if(this.value > this.endValue) {
						this.value = this.startValue;
					}
					
				}
				
			};
		}
		
	}
	public static void stopBackgroundAnimation() {
		
		if(Draw_3Menu.backGroundAnimationFrame != null) {
			AnimationHandler.stopAnimation(Draw_3Menu.backGroundAnimationFrame);
			Draw_3Menu.backGroundAnimationFrame = null;
		}
		
	}
	
}
