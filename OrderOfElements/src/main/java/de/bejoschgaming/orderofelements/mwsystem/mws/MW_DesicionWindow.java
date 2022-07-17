package de.bejoschgaming.orderofelements.mwsystem.mws;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.maasystem.MouseActionArea;
import de.bejoschgaming.orderofelements.mwsystem.MultiWindowType;

public class MW_DesicionWindow extends MultiWindow {

	private final int sideBorder = 34, topDownBetweenBorder = 20, spaceBetweenMAAs = 40;
	
	private String question;
	private int textWidth, textHeight;
	private Font font;
	private MouseActionArea acceptMAA, declineMAA;
	
	public MW_DesicionWindow(String question, Font font, MouseActionArea acceptMAA, MouseActionArea declineMAA, Color foregroundColor, Color backgroundColor) {
		super(MultiWindowType.MW_DesicionWindow_, foregroundColor, backgroundColor, false, false);
		
		this.question = question;
		this.font = font;
		this.acceptMAA = acceptMAA;
		this.declineMAA = declineMAA;
		
		Rectangle2D stringBounds = font.getStringBounds(question, new FontRenderContext(null, true, true));
		textWidth = (int) stringBounds.getWidth();
		textHeight = (int) stringBounds.getHeight();
		
		int totalWidth = Math.max(textWidth+2*sideBorder, spaceBetweenMAAs+2*sideBorder+Math.max(this.acceptMAA.getWidth(), this.declineMAA.getWidth()));
		int totalHeight = textHeight+3*topDownBetweenBorder+Math.max(this.acceptMAA.getHeight(), this.declineMAA.getHeight());
		this.setFirstPos(GraphicsHandler.getWidth()/2-totalWidth/2, GraphicsHandler.getHeight()/2-totalHeight/2, totalWidth, totalHeight);
		
		//OVERWRITING MAA POS (Dont use initMaa because maas need these calcs to happen first)
		this.acceptMAA.setMW(this);
		this.declineMAA.setMW(this);
		this.acceptMAA.setPos(this.x+this.width/2-this.spaceBetweenMAAs/2-this.acceptMAA.getWidth(), this.y+2*topDownBetweenBorder+textHeight);
		this.declineMAA.setPos(this.x+this.width/2+this.spaceBetweenMAAs/2, this.y+2*topDownBetweenBorder+textHeight);
		
		this.maas.add(acceptMAA);
		this.maas.add(declineMAA);
		
	}

	@Override
	protected void initMAAs() {
		super.initMAAs();
	}
	
	@Override
	protected void drawContent(Graphics g) {
		
		g.setColor(this.foregroundColor);
		g.setFont(this.font);
		GraphicsHandler.drawCentralisedText(g, this.foregroundColor, this.font, question, x+(this.width/2), y+this.topDownBetweenBorder+this.textHeight/2);
		
	}

}
