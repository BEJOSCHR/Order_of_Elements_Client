package de.bejoschgaming.orderofelements.gamesystem.map;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import de.bejoschgaming.orderofelements.graphics.handler.MouseHandler;
import de.bejoschgaming.orderofelements.gamesystem.map.fields.Field;
import de.bejoschgaming.orderofelements.gamesystem.map.fields.FieldType;
import de.bejoschgaming.orderofelements.gamesystem.unitsystem.Unit;

public class Map {

	private int width, height;
	
	private List<Field> fields = new ArrayList<>();
	private List<Unit> units = new ArrayList<>();
	
	private Field hoveredField = null;
	
	
	public Map(int width, int height) {
		
		this.width = width;
		this.height = height;
		
		units.clear();
		fillMap();
		
	}
	
	private void fillMap() {
		
		fields.clear();
		
		for(int y = this.height ; y >= 0 ; y -=1 ) {
			for(int x = 0 ; x <= this.width ; x += 1) {
				if(x % 2 == y % 2) {
					if(x == 0 || x == this.width || y == 0 || y == this.height) {
						fields.add(new Field(x, y, FieldType.BORDER));
					}else {
						fields.add(new Field(x, y, FieldType.DEFAULT));
					}
				}
			}
		}
		
	}
	
	public void draw(Graphics g) {
		
		for(Field field : this.fields) {
			field.draw(g);
		}
		
		if(getHoveredField() != null) {
			for(Field surrounder : getSurroundingFields(getHoveredField())) {
				surrounder.draw(g, Color.ORANGE);
			}
			getHoveredField().draw(g, Color.RED);
//			g.setColor(Color.ORANGE);
//			g.drawLine(MouseHandler.mouseX, MouseHandler.mouseY, this.hoveredField.getCenterX(), this.hoveredField.getCenterY());
		}
		
	}
	
	public void updateHoverField() {
		
		this.hoveredField = getNextFieldToCoords(MouseHandler.getMouseX(), MouseHandler.getMouseY());
		
	}
	
	private Field getNextFieldToCoords(int x, int y) {
		
		double closestDistance = MapData.FIELD_SIZE*1.0+5.0;
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
