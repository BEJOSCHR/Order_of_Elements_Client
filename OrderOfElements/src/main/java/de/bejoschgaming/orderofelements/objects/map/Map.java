package de.bejoschgaming.orderofelements.objects.map;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import de.bejoschgaming.orderofelements.graphics.handler.MouseHandler;
import de.bejoschgaming.orderofelements.objects.map.fields.Field;
import de.bejoschgaming.orderofelements.objects.map.fields.FieldType;

public class Map {

	private List<Field> fields = new ArrayList<>();
	
	private Field hoveredField = null;
	
	
	public Map() {
		
		fillMap();
		
	}
	
	private void fillMap() {
		
		for(int x = 0 ; x <= MapData.MAP_WIDTH ; x += 1) {
			for(int y = 0 ; y <= MapData.MAP_HEIGHT ; y +=1 ) {
				if(x % 2 == y % 2) {
					if(x == 0 || x == MapData.MAP_WIDTH || y == 0 || y == MapData.MAP_HEIGHT) {
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
		
		this.hoveredField = getNextFieldToCoords(MouseHandler.mouseX, MouseHandler.mouseY);
		
	}
	
	private Field getNextFieldToCoords(int x, int y) {
		
		double closestDistance = MapData.FIELD_SIZE*2.0+10.0;
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
	
}
