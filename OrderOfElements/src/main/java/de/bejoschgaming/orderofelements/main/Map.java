package de.bejoschgaming.orderofelements.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Map {

	public static final int MAP_WIDTH = 31, MAP_HEIGHT = 12;
	
	private int width, height;
	
	private List<Field> fields = new ArrayList<>();
	private List<Unit> units = new ArrayList<>();
	
	private Field hoveredField = null;
	
	public Map() {
		
		this.width = MAP_WIDTH;
		this.height = MAP_HEIGHT;
		
		units.clear();
		fillMap();
		
	}
	
	public Map(int width, int height) {
		
		this.width = width;
		this.height = height;
		
		units.clear();
		fillMap();
		
	}
	
	private void fillMap() {
		
		fields.clear();
		
		for(int y = this.height-1 ; y >= 1 ; y -=1 ) {
			for(int x = 1 ; x <= this.width ; x += 1) {
				if(x % 2 == y % 2) {
					fields.add(new Field(x, y));
				}
			}
		}
		
	}
	
	public void draw(Graphics g) {
		
		for(Field field : this.fields) {
			field.draw(g);
		}
		
		if(getHoveredField() != null) {
			getHoveredField().draw(g, Color.WHITE);
			if(FrameEvents.isShowMapDetails() == true) {
				for(Field surrounder : getSurroundingFields(getHoveredField())) {
					surrounder.draw(g, Color.ORANGE);
				}
				g.setColor(Color.BLACK);
				g.drawLine(FrameEvents.getMX(), FrameEvents.getMY(), this.hoveredField.getCenterX(), this.hoveredField.getCenterY());
			}
		}
		
	}
	
	public void updateHoverField() {
		
		this.hoveredField = getNextFieldToCoords(FrameEvents.getMX(), FrameEvents.getMY());
		
	}
	
	private Field getNextFieldToCoords(int x, int y) {
		
		double closestDistance = Field.FIELD_SIZE*1.0+5.0;
		Field closestField = null;
		
		for(Field field : this.fields) {
			double distanceToCoords = Math.sqrt( Math.pow(field.getCenterX()-x, 2) + Math.pow(field.getCenterY()-y, 2) );
			if(distanceToCoords < closestDistance) {
				closestDistance = distanceToCoords;
				closestField = field;
			}
		}
		
		return closestField;
		
	}
	
	public List<Field> getSurroundingFields(Field center) {
		
		List<Field> neighbours = new ArrayList<>();
		int x = center.getX(), y = center.getY();
		
		for(Field field : this.fields) {
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
	
	public List<Field> getFields() {
		return fields;
	}
	public Field getHoveredField() {
		return hoveredField;
	}
	public List<Unit> getUnits() {
		return units;
	}

}