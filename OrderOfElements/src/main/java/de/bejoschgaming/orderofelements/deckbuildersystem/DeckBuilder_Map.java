package de.bejoschgaming.orderofelements.deckbuildersystem;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import de.bejoschgaming.orderofelements.gamesystem.unitsystem.Unit;
import de.bejoschgaming.orderofelements.gamesystem.unitsystem.UnitHandler;
import de.bejoschgaming.orderofelements.graphics.handler.MouseHandler;

public class DeckBuilder_Map {

	private int width, height;
	
	private List<DeckBuilder_Field> fields = new ArrayList<>();
	private List<Unit> units = new ArrayList<>();
	
	private DeckBuilder_Field hoveredField = null;
	
	
	public DeckBuilder_Map(List<Unit> units, int width, int height) {
		
		//CLONE FOR UNLINKED COPY OF LIST
		for(Unit u : units) {
			Unit newUnit = u.clone();
			newUnit.setX(u.getX());
			newUnit.setY(u.getY());
			this.units.add(newUnit);
		}
		this.width = width;
		this.height = height;
		
		fillMap();
		
	}
	
	public String getUnitDataAsString() {
		
		String output = null;
		for(Unit unit : this.units) {
			if(output == null) {
				output = unit.getId()+DeckBuilder_Data.smallSplit+unit.getX()+DeckBuilder_Data.smallSplit+unit.getY();
			}else {
				output += DeckBuilder_Data.bigSplit+unit.getId()+DeckBuilder_Data.smallSplit+unit.getX()+DeckBuilder_Data.smallSplit+unit.getY();
			}
		}
		return output;
		
	}
	
	public void unregisterSavedUnits() {
		
		for(Unit unit : this.units) {
			UnitHandler.removeNewUnit(unit);
		}
		this.units.clear();
		
	}
	
	private void fillMap() {
		
		fields.clear();
		
		for(int y = this.height ; y >= 0 ; y -=1 ) {
			for(int x = 0 ; x <= this.width ; x += 1) {
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
			if(getHoveredField().isUnplaceable()) {
				getHoveredField().draw(g, Color.RED);
			}else {
				getHoveredField().draw(g, Color.GREEN.darker());
			}
//			g.setColor(Color.ORANGE);
//			g.drawLine(MouseHandler.mouseX, MouseHandler.mouseY, this.hoveredField.getCenterX(), this.hoveredField.getCenterY());
		}
		
	}
	
	public void handleLeftPress() {
		
		
		
	}
	public void handleLeftRelease() {
		
		if(DeckBuilder_Data.draggedUnit != null && getHoveredField() != null && getHoveredField().isUnplaceable() == false && getUnitAtPos(getHoveredField()) == null) {
			//HAS DRAGGED UNIT - HAS HOVERFIELD WHICH IS PLACABLE AND HAS NOT A UNIT ALREADY
			
			Unit newUnit = DeckBuilder_Data.draggedUnit.clone();
			newUnit.setX(getHoveredField().getX());
			newUnit.setY(getHoveredField().getY());
			this.units.add(newUnit);
			if(newUnit.getCost() != -1) {
				DeckBuilder_Data.deckCost += newUnit.getCost();
			}
				
		}
		
		DeckBuilder_Data.draggedUnit = null;
		
	}
	public void handleRightPress() {
		
		
		
	}
	public void handleRightRelease() {
		
		if(DeckBuilder_Data.draggedUnit == null && getHoveredField() != null && getHoveredField().isUnplaceable() == false) {
			
			Unit unit = getUnitAtPos(getHoveredField());
			if(unit != null) {
				this.units.remove(unit);
				if(unit.getCost() != -1) {
					DeckBuilder_Data.deckCost -= unit.getCost();
				}
				UnitHandler.removeNewUnit(unit);
			}
			
		}
		
		DeckBuilder_Data.draggedUnit = null;
		
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
	
	public Unit getUnitAtPos(DeckBuilder_Field field) { return getUnitAtPos(field.getX(), field.getY()); }
	public Unit getUnitAtPos(int x, int y) {
		
		for(Unit unit : this.units) {
			if(unit.getX() == x && unit.getY() == y) {
				return unit;
			}
		}
		return null;
		
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
