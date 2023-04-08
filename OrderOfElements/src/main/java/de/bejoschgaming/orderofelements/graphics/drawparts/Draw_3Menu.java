package de.bejoschgaming.orderofelements.graphics.drawparts;

import java.awt.Color;
import java.awt.Font;
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
import de.bejoschgaming.orderofelements.maasystem.MouseActionArea;
import de.bejoschgaming.orderofelements.maasystem.MouseActionAreaType;
import de.bejoschgaming.orderofelements.main.OOE_Main_Client;

public class Draw_3Menu {

	private static int backgroundAnimationTempo = 15;
	public static DynamicInteger backGroundAnimationFrame = null;
	
	private static int patchnotesX = (GraphicsHandler.getWidth()*55)/100;
	private static int patchnotesY = (GraphicsHandler.getHeight()*22)/100;
	private static int patchnotesWidth = (GraphicsHandler.getWidth()*30)/100;
	private static int patchnotesHeight = (GraphicsHandler.getHeight()*55)/100;
	public static int patchnotesScroll = 0;
	public static String patchnotesData = null;
	private static boolean patchnotesDrawsSth = false;
	
	public static void initMAAs() {
		
		int x = 540, y = 380;
		int width = 160, height = 60;
		int distanceBetween = 22; 
		
		int extraButton_width = (GraphicsHandler.getWidth()*8)/100;
		int extraButton_height = (GraphicsHandler.getHeight()*5)/100;
		
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
		
		new MouseActionArea(1135, 790, extraButton_width, extraButton_height, MouseActionAreaType.MENU_Friendlist, "Friendlist", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
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
		new MouseActionArea(1135+extraButton_width+50, 790, extraButton_width, extraButton_height, MouseActionAreaType.MENU_Matchhistory, "Matchhistory", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
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
		new MouseActionArea(1036, 207, 536, 534, MouseActionAreaType.MENU_PatchnotesScroll, "", -1, Color.DARK_GRAY, Color.DARK_GRAY, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.MENU;
			}
			@Override
			public void performAction_MOUSEWHEEL_TURN(int turns) {
				if(patchnotesDrawsSth == true || turns < 0) { //NOT ON THE END OF SCROLL OR GOING BACK UP
					patchnotesScroll -= turns*10; //turns is -1 or 1
					if(patchnotesScroll > 0) { patchnotesScroll = 0; }
				}
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
		
		patchnotesDrawsSth = false;
		int startX = patchnotesX;
		int y = patchnotesY+patchnotesScroll;
		
		if(patchnotesData != null) {
			
			String dateSplit[] = patchnotesData.split("---");
			
			drawPatchnote(g, "Patchnotes and Infos", startX, y+10, Color.DARK_GRAY, FontHandler.bridgnorth_bold, 25);
			y += 25+g.getFontMetrics().getHeight() * 2 / 3;
			
			for(String date : dateSplit) {
				
				String newsSplit[] = date.split(";");
				
				int number = newsSplit.length-1;
				int x = startX;
				
				//FIRST IS THE DATE
				for(int i = 1 ; i <= number ; i+=2) {
					
					String type = newsSplit[i];
					String message = newsSplit[i+1];
					
					Color highlightColor = Color.YELLOW;
					
					switch (type) {
					case "BUFF":
						highlightColor = new Color(10, 105, 29); //GREEN
						break;
					case "NERF":
						highlightColor = new Color(152, 8, 8); //RED
						break;
					case "INFO":
						highlightColor = new Color(20, 25, 168); //BLUE
						break;
					case "UPDATE":
						highlightColor = new Color(166, 21, 147); //DARK PURPLE
						break;
					}
					drawPatchnote(g, type+" - "+newsSplit[0], x, y+10, highlightColor, FontHandler.medievalSharp_regular, 16);
					y += 21+g.getFontMetrics().getHeight() * 2 / 3;
					
					String lineSplit[] = message.split("#");
					
					for(String line : lineSplit) {
						
						String wordSplit[] = line.split(" ");
						
						int rowX = x;
						for(String word : wordSplit) {
							
							try {
								//NUMBER
								Integer.parseInt(word);
								drawPatchnote(g, word, rowX, y, highlightColor, FontHandler.medievalSharp_regular, 22);
							}catch(NumberFormatException error) {
								//NO NUMBER
								drawPatchnote(g, word, rowX, y, Color.DARK_GRAY, FontHandler.medievalSharp_regular, 20);
							}
							rowX += 0+g.getFontMetrics().stringWidth(" "+word);
							
						}
						
						y += 6+g.getFontMetrics().getHeight() * 2 / 3;
						
					}
					
				}
				
			}
			  
		}else {
			
			
			drawPatchnote(g, "Patchnotes and Infos", startX, y+10, Color.DARK_GRAY, FontHandler.bridgnorth_bold, 25);
			y += 25+g.getFontMetrics().getHeight() * 2 / 3;
			drawPatchnote(g, "No infos or changes to display!", startX, y+10, Color.DARK_GRAY, FontHandler.medievalSharp_regular, 16);
			
		}
		
	}
	
	private static void drawPatchnote(Graphics g, String text, int x, int y, Color color, Font font, int textSize) {
		
		g.setColor(color);
		g.setFont(FontHandler.getFont(font, textSize));
		
		if(x < patchnotesX || x > patchnotesX+patchnotesWidth || y < patchnotesY || y > patchnotesY+patchnotesHeight-100) {
			return;
		}
		
		patchnotesDrawsSth = true;
		g.drawString(text, x, y);
		
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
