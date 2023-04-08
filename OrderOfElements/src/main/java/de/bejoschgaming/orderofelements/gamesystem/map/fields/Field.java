package de.bejoschgaming.orderofelements.gamesystem.map.fields;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;

import de.bejoschgaming.orderofelements.gamesystem.map.MapData;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;

public class Field {

	private int x, y;
	private FieldType type;
//	private Image img;
	
	public Field(int x_, int y_, FieldType type_) {
		
		this.x = x_;
		this.y = y_;
		this.changeType(type_);
		
	}
	
	public void changeType(FieldType newType) {
		
		this.type = newType;
//		this.img = loadImage;
		
	}
	
	public void draw(Graphics g) {
		this.draw(g, Color.BLACK);
	}
	public void draw(Graphics g, Color color) {
		
		//This size is the length of one part of the 6-Eck as well as the distance from the center to each point
		int masterLength = MapData.FIELD_SIZE;
		
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
		
		g.setColor(FieldType.getColor(this.type));
		g.fillPolygon(polygon);
		
		g.setColor(color);
		g.drawPolygon(polygon);
		
		//MITTELPUNKT
		g.drawRoundRect(centerX, centerY, 1, 1, 1, 1);
		
		//CORDS
		if(this.isMoveable() == true) {
			GraphicsHandler.drawCentralisedText(g, Color.WHITE, new Font("Arial", Font.BOLD, GraphicsHandler.getRelativTextSize(11)), this.x+":"+this.y, p4_x, p4_y-12);
		}
		
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getCenterX() {
		return this.x * MapData.FIELD_SIZE;
	}
	public int getCenterY() {
		return this.y * (int) (MapData.FIELD_SIZE*3.0/2.0);
	}
	public FieldType getType() {
		return type;
	}
	public boolean isMoveable() {
		if(this.getType() != FieldType.BORDER && this.getType() != FieldType.OBSTRUCTION) {
			return true;
		}else {
			return false;
		}
	}
	
}
