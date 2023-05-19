package de.bejoschgaming.orderofelements.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public class Field {

	public static final int FIELD_SIZE = 60;
	public static final Color DEFAULT_BACKROUND = Color.LIGHT_GRAY;
	public static final Color DEFAULT_BORDER = Color.BLACK;
	
	private int x, y;
//	private Image img;
	
	public Field(int x_, int y_) {
		
		this.x = x_;
		this.y = y_;
		
	}
	
	public void draw(Graphics g) {
		this.draw(g, Field.DEFAULT_BORDER);
	}
	public void draw(Graphics g, Color color) {
		
		//This size is the length of one part of the 6-Eck as well as the distance from the center to each point
		int masterLength = Field.FIELD_SIZE;
		
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
		
		if(FrameEvents.isFillMap() == true) { 
			g.setColor(Field.DEFAULT_BACKROUND);
			g.fillPolygon(polygon);
		}
		
		g.setColor(color);
		g.drawPolygon(polygon);
		
		//MITTELPUNKT
		if(FrameEvents.isShowMapDetails() == true) { 
			g.drawRoundRect(centerX, centerY, 1, 1, 1, 1); 
		}
		
		//CORDS
		OOE_Main_Client.drawCentralisedText(g, Color.WHITE, 10, this.x+":"+this.y, p4_x, p4_y-12);
		
		
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getCenterX() {
		return this.x * Field.FIELD_SIZE;
	}
	public int getCenterY() {
		return this.y * (int) (Field.FIELD_SIZE*3.0/2.0);
	}
	
}