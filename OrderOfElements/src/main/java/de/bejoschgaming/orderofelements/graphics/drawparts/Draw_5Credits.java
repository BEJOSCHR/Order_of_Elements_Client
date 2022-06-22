package de.bejoschgaming.orderofelements.graphics.drawparts;

import java.awt.Color;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;
import de.bejoschgaming.orderofelements.animationsystem.animations.DynamicInteger;
import de.bejoschgaming.orderofelements.animationsystem.animations.FadeAnimation;
import de.bejoschgaming.orderofelements.animationsystem.animations.FadeType;
import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.graphics.DrawState;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;
import de.bejoschgaming.orderofelements.maa.MouseActionArea;
import de.bejoschgaming.orderofelements.maa.MouseActionAreaType;

public class Draw_5Credits {

	public static int scrollSpeed = 3;
	public static DynamicInteger creditsScroll = null;
	
	public static void initMAAs() {
		
		new MouseActionArea(0, 0, 1920, 1080, MouseActionAreaType.CREDITS_Back, "", 22, Color.BLACK, Color.ORANGE, false, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.CREDITS;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				AnimationHandler.startAnimation(new FadeAnimation(95, 6, FadeType.FADEINANDOUT) {
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
		
		g.setColor(new Color(155, 155, 155, 130));
		g.fillRect(0, 0, (GraphicsHandler.getWidth()*3)/8, GraphicsHandler.getHeight());
		
		GraphicsHandler.drawCentralisedText(g, Color.WHITE, FontHandler.getFont(FontHandler.medievalSharp_regular, 28), "Click anywhere to exit", (GraphicsHandler.getWidth()*83)/100, (GraphicsHandler.getHeight()*97)/100);
		
		//CREDITS
		if(creditsScroll != null) {
			scrollSpeed = 2;
			int x = (GraphicsHandler.getWidth()*3)/16;
			int y = (GraphicsHandler.getHeight()*50)/100;
			int textSizeHeader = 42;
			int textSizeLines = 25;
			int distanceBetweenCategories = 130;
			int distanceBetweenLines = 45;
			int distanceBetweenHeader = 62;
			int category = 0, headers = 0, lines = 0;
			int scroll = creditsScroll.getValue();
			
			//HEADER
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, textSizeHeader), "OrderOfElements", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			headers++;
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, textSizeLines), "By BejoschGaming", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			category++;
			//PROGRAMMER
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, textSizeHeader), "Developer", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			headers++;
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, textSizeLines), "Benjamin Schranner aka BEJOSCH", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			lines++;
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, textSizeLines), "Tim aka Thrax", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			lines++;
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, textSizeLines), "Christopher aka Kerplunk1992", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			category++;
			//IMAGES
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, textSizeHeader), "Images", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			headers++;
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, textSizeLines), "From acegif.com", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			lines++;
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, textSizeLines), "From gifer.com", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			lines++;
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, textSizeLines), "Made by Darkness", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			category++;
			//SOUNDS
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, textSizeHeader), "Sounds", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			headers++;
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, textSizeLines), "From xxx", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			lines++;
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, textSizeLines), "From yyy", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			category++;
			//FONTS
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, textSizeHeader), "Fonts", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			headers++;
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, textSizeLines), "Uses Bridgnorth-Bold", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			lines++;
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, textSizeLines), "Uses Bridgnorth-Regular", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			lines++;
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, textSizeLines), "Uses MedievalSharp-Regular", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			category++;
			//NEW CATEGORY HERE
			
			//PLAY TESTER
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, textSizeHeader), "Thanks for testing", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			headers++;
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, textSizeLines), "xxx", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			category++;
			//SPECIAL THANKS
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, textSizeHeader), "Special thanks to", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			headers++;
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, textSizeLines), "xxx", x, y+(category*distanceBetweenCategories)+(lines*distanceBetweenLines)+(headers*distanceBetweenHeader)-scroll);
			category++;
			
		}
		
	}
	
}
