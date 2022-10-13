package de.bejoschgaming.orderofelements.deckbuildersystem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import de.bejoschgaming.orderofelements.gamesystem.unitsystem.Unit;
import de.bejoschgaming.orderofelements.graphics.handler.MouseHandler;

public class DeckBuilder_Map {

	private int width, height;
	
	private List<DeckBuilder_Field> fields = new ArrayList<>();
	private List<Unit> units = new ArrayList<>();
	
	private DeckBuilder_Field hoveredField = null;
	
	
	public DeckBuilder_Map(int width, int height) {
		
		this.width = width;
		this.height = height;
		
		units.clear();
		fillMap();
		
	}
	
	private void fillMap() {
		
		fields.clear();
		
		for(int x = 0 ; x <= this.width ; x += 1) {
			for(int y = 0 ; y <= this.height ; y +=1 ) {
				if(x % 2 == y % 2) {
					if(x == 0 || x == 1 || x == this.width || x == this.width-1 || y == 0 || y == 1 || y == this.height || y == this.height-1) {
						fields.add(new DeckBuilder_Field(x, y, true));
					}else {
						fields.add(new DeckBuilder_Field(x, y, false));
					}
				}
			}
		}
		
	}
	
	public void draw(Graphics g) {
		
		for(DeckBuilder_Field field : this.fields) {
			field.draw(g);
		}
		
		if(getHoveredField() != null) {
//			for(Field surrounder : getSurroundingFields(getHoveredField())) {
//				surrounder.draw(g, Color.ORANGE);
//			}
			getHoveredField().draw(g, Color.RED);
//			g.setColor(Color.ORANGE);
//			g.drawLine(MouseHandler.mouseX, MouseHandler.mouseY, this.hoveredField.getCenterX(), this.hoveredField.getCenterY());
		}
		
		if(DeckBuilder_Data.startDragPoint != null) {
			g.setColor(Color.BLACK);
			g.drawLine((int) DeckBuilder_Data.startDragPoint.getX(), (int) DeckBuilder_Data.startDragPoint.getY(), MouseHandler.getMouseX(), MouseHandler.getMouseY());
		}
		
	}
	
	public void handleLeftPress() {
		
		DeckBuilder_Data.startDragPoint = new Point(MouseHandler.getMouseX(), MouseHandler.getMouseY());
		
	}
	public void handleLeftRelease() {
		
		//DO STH
		DeckBuilder_Data.startDragPoint = null;
		
	}
	public void handleRightPress() {
		
		
		
	}
	public void handleRightRelease() {
		
		
		
	}
	
	public void updateHoverField() {
		
		this.hoveredField = getNextFieldToCoords(MouseHandler.getMouseX(), MouseHandler.getMouseY());
		
	}
	
	private DeckBuilder_Field getNextFieldToCoords(int x, int y) {
		
		double closestDistance = DeckBuilder_Data.displayFieldSize*1.0+5.0;
		DeckBuilder_Field closestField = null;
		
		for(DeckBuilder_Field field : this.fields) {
			double distanceToCoords = Math.sqrt( Math.pow(field.getCenterX()-x, 2) + Math.pow(field.getCenterY()-y, 2) );
			if(distanceToCoords < closestDistance) {
				closestDistance = distanceToCoords;
				closestField = field;
			}
		}
		
		return closestField;
		
	}
	
	public List<DeckBuilder_Field> getSurroundingFields(DeckBuilder_Field center) {
		
		List<DeckBuilder_Field> neighbours = new ArrayList<>();
		int x = center.getX(), y = center.getY();
		
		for(DeckBuilder_Field field : this.fields) {
			if(field.getX() == x-2 && field.getY() == y) {
				neighbours.add(field);
			}else if(field.getX() == x+2 && field.getY() == y) {
				neighbours.add(field);
			}else if(field.getX() == x-1 && field.getY() == y+1) {
				neighbours.add(field);
			}else if(field.getX() == x+1 && field.getY() == y+1) {
				neighbours.add(field);
			}else if(field.getX() == x+1 && field.getY() == y-1) {
				neighbours.add(field);
			}else if(field.getX() == x-1 && field.getY() == y-1) {
				neighbours.add(field);
			}
		}
		
		return neighbours;
		
	}
	
	public List<DeckBuilder_Field> getFields() {
		return fields;
	}
	public DeckBuilder_Field getHoveredField() {
		return hoveredField;
	}
	public List<Unit> getUnits() {
		return units;
	}
	
}
