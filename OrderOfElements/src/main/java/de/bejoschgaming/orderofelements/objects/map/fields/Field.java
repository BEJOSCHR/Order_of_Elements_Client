package de.bejoschgaming.orderofelements.objects.map.fields;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import de.bejoschgaming.orderofelements.objects.map.MapData;

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
		
		//This size is the length of one part of the 6-Eck as well as the distance from the center to each point
		int masterLength = MapData.FIELD_SIZE;
		
		int centerX = 400;
		int centerY = 400;
		
		//Start Top-Center Point then to the right in order
		int p1_x = centerX, p1_y = centerY-masterLength;
		int p2_x = centerX+masterLength, p2_y = centerY-masterLength/2;
		int p3_x = centerX+masterLength, p3_y = centerY+masterLength/2;
		int p4_x = centerX, p4_y = centerY+masterLength;
		int p5_x = centerX-masterLength, p5_y = centerY-masterLength/2;
		int p6_x = centerX-masterLength, p6_y = centerY+masterLength/2;
		
		int[] listX = {p1_x, p2_x, p3_x, p4_x, p5_x, p6_x};
		int[] listY = {p1_y, p2_y, p3_y, p4_y, p5_y, p6_y};
		
		Polygon polygon = new Polygon(listX, listY, 6);
		
		g.setColor(Color.BLACK);
		g.drawPolygon(polygon);
		
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
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
