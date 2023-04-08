package de.bejoschgaming.orderofelements.deckbuildersystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;

import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.gamesystem.unitsystem.Unit;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_4Deckbuilder;

public class DeckBuilder_Field {

	private int x, y;
	private boolean unplaceable = false;
	
	public DeckBuilder_Field(int x, int y, boolean unplaceable) {
		
		this.x = x;
		this.y = y;
		this.unplaceable = unplaceable;
		
	}
	
	public void draw(Graphics g) {
		this.draw(g, Color.BLACK);
	}
	public void draw(Graphics g, Color color) {
		
		if(DeckBuilder_Data.layoutMap == null) { return; }
		
		//This size is the length of one part of the 6-Eck as well as the distance from the center to each point
		int masterLength = DeckBuilder_Data.displayFieldSize;
		
		int centerX = getCenterX();
		int centerY = getCenterY();
		
		//Start Top-Center Point then to the right in order
		int p1_x = centerX, p1_y = centerY-masterLength;
		int p2_x = centerX+masterLength, p2_y = centerY-masterLength/2;
		int p3_x = centerX+masterLength, p3_y = centerY+masterLength/2;
		int p4_x = centerX, p4_y = centerY+masterLength;
		int p5_x = centerX-masterLength, p5_y = centerY+masterLength/2;
		int p6_x = centerX-masterLength, p6_y = centerY-masterLength/2;
		
		int[] listX = {p1_x, p2_x, p3_x, p4_x, p5_x, p6_x};
		int[] listY = {p1_y, p2_y, p3_y, p4_y, p5_y, p6_y};
		
		Polygon polygon = new Polygon(listX, listY, 6);
		
		if(this.unplaceable == true) {
			g.setColor(Color.DARK_GRAY);
		}else {
			boolean hasUnit = false;
			if(DeckBuilder_Data.layoutMap != null) {
				for(Unit unit : DeckBuilder_Data.layoutMap.getUnits()) {
					if(unit.getX() == this.getX() && unit.getY() == this.getY()) {
						hasUnit = true;
						break;
					}
				}
			}
			if(hasUnit) {
				g.setColor(Color.WHITE);
			}else {
				g.setColor(Color.LIGHT_GRAY);
			}
		}
		g.fillPolygon(polygon);
		
		g.setColor(color);
		g.drawPolygon(polygon);
		
		//MITTELPUNKT
		if(DeckBuilder_Data.showFieldCenterPoints == true) {
			g.drawRoundRect(centerX, centerY, 1, 1, 1, 1);
		}
		
		boolean hasUnit = false;
		if(DeckBuilder_Data.layoutMap != null) {
			for(Unit unit : DeckBuilder_Data.layoutMap.getUnits()) {
				if(unit.getX() == this.getX() && unit.getY() == this.getY()) {
					g.drawImage(unit.getSmall_icon(), this.getCenterX()-Draw_4Deckbuilder.iconSize/2, this.getCenterY()-Draw_4Deckbuilder.iconSize/2, null);
					int textSize = 8;
					if(DeckBuilder_Data.layoutMap.getHoveredField() != null && this.getX() == DeckBuilder_Data.layoutMap.getHoveredField().getX() && this.getY() == DeckBuilder_Data.layoutMap.getHoveredField().getY()) {
						//IS HOVER FIELD
						textSize = 13;
					}
					GraphicsHandler.drawCentralisedText(g, Color.WHITE, FontHandler.getFont(FontHandler.medievalSharp_regular, textSize), unit.getName(), this.getCenterX()+1, this.getCenterY()+(DeckBuilder_Data.displayFieldSize*5)/7+1);
					GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, textSize), unit.getName(), this.getCenterX(), this.getCenterY()+(DeckBuilder_Data.displayFieldSize*5)/7);
					hasUnit = true;
					break;
				}
			}
		}
		if(hasUnit == false && DeckBuilder_Data.showFieldCords == true) {
			GraphicsHandler.drawCentralisedText(g, Color.DARK_GRAY, new Font("Arial", Font.BOLD, GraphicsHandler.getRelativTextSize(10)), this.x+":"+this.y, p4_x, p4_y-12);
		}
		
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getCenterX() {
		return this.x * DeckBuilder_Data.displayFieldSize + DeckBuilder_Data.map_offset_X;
	}
	public int getCenterY() {
		return this.y * (int) (DeckBuilder_Data.displayFieldSize*3.0/2.0) + DeckBuilder_Data.map_offset_Y;
	}
	public boolean isUnplaceable() {
		return unplaceable;
	}
	
}
