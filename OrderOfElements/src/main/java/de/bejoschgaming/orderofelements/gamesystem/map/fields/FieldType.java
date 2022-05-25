package de.bejoschgaming.orderofelements.gamesystem.map.fields;

import java.awt.Color;

public enum FieldType {

	DEFAULT,
	START,
	BORDER,
	OBSTRUCTION,
	
	FIRE,
	WATER,
	EARTH,
	WIND,
	POISON;
	
	public static Color getColor(FieldType type) {
		
		switch(type) {
		case BORDER:
			return Color.DARK_GRAY;
		case DEFAULT:
			return Color.LIGHT_GRAY;
		case FIRE:
			return Color.RED;
		case EARTH:
			return new Color(101, 67, 48);
		case OBSTRUCTION:
			return Color.DARK_GRAY;
		case POISON:
			return Color.GREEN;
		case START:
			return Color.YELLOW;
		case WATER:
			return Color.BLUE;
		case WIND:
			return Color.WHITE;
		}
		
		return null;
		
	}
	
}
