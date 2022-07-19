package de.bejoschgaming.orderofelements.mwsystem.mws;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.maasystem.MouseActionArea;
import de.bejoschgaming.orderofelements.mwsystem.MultiWindowType;

public class MW_InfoWindow extends MultiWindow {

	private final int sideBorder = 40, topDownBorder = 28, spaceBetweenLines = 5;
	
	private String message[];
	private int textWidth = 0, textHeight = 0, lineHeight = 0;
	private Font font;
	
	private MouseActionArea maa = null;
	
	public MW_InfoWindow(String[] message, Font font, Color foregroundColor, Color backgroundColor) {
		super(MultiWindowType.MW_InfoWindow_, foregroundColor, backgroundColor, true, true, false);
		
		this.message = message;
		this.font = font;
		
		this.lineHeight = (int) font.getStringBounds(this.message[0], new FontRenderContext(null, true, true)).getHeight();
		for(String line : this.message) {
			Rectangle2D lineBounds = font.getStringBounds(line, new FontRenderContext(null, true, true));
			if(lineBounds.getWidth() > this.textWidth) { this.textWidth = (int) lineBounds.getWidth(); }
			this.textHeight += (int) lineBounds.getHeight()+this.spaceBetweenLines;
		}
		this.textHeight -= this.spaceBetweenLines;
		
		int totalWidth = this.textWidth+(2*this.sideBorder);
		int totalHeight = this.textHeight+(2*this.topDownBorder);
		this.setFirstPos(GraphicsHandler.getWidth()/2-totalWidth/2, GraphicsHandler.getHeight()/2-totalHeight/2, totalWidth, totalHeight);
		
	}
	public MW_InfoWindow(String[] message, Font font, Color foregroundColor, Color backgroundColor, MouseActionArea maa) {
		super(MultiWindowType.MW_InfoWindow_, foregroundColor, backgroundColor, true, true, false);
		
		this.message = message;
		this.font = font;
		this.maa = maa;
		
		this.lineHeight = (int) font.getStringBounds(this.message[0], new FontRenderContext(null, true, true)).getHeight();
		for(String line : this.message) {
			Rectangle2D lineBounds = font.getStringBounds(line, new FontRenderContext(null, true, true));
			if(lineBounds.getWidth() > this.textWidth) { this.textWidth = (int) lineBounds.getWidth(); }
			this.textHeight += (int) lineBounds.getHeight()+this.spaceBetweenLines;
		}
		this.textHeight -= this.spaceBetweenLines;
		
		int totalWidth = this.textWidth+(2*this.sideBorder);
		int totalHeight = this.textHeight+(2*this.topDownBorder)+this.maa.getHeight()+this.topDownBorder;
		this.setFirstPos(GraphicsHandler.getWidth()/2-totalWidth/2, GraphicsHandler.getHeight()/2-totalHeight/2, totalWidth, totalHeight);
		
		this.maas.add(maa);
		this.maa.setMW(this);
		this.maa.setPos(this.x+this.width/2-this.maa.getWidth()/2, this.getY()+this.getHeight()-this.topDownBorder-this.maa.getHeight());
		
	}
	
	@Override
	protected void drawContent(Graphics g) {
		
		int x = this.x+this.width/2;
		int y = this.y+this.topDownBorder+this.lineHeight/2;
		for(String line : this.message) {
			GraphicsHandler.drawCentralisedText(g, this.foregroundColor, this.font, line, x, y);
			y += this.lineHeight+this.spaceBetweenLines;
		}
		
	}

}
