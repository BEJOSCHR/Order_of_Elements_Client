package de.bejoschgaming.orderofelements.gamesystem.unitsystem;

import java.util.ArrayList;
import java.util.List;

public class UnitHandler {

	private static List<UnitCategory> unitCategories = new ArrayList<UnitCategory>();
	private static List<UnitTargetPattern> unitTargetPattern = new ArrayList<UnitTargetPattern>();
	private static List<Unit> units = new ArrayList<Unit>();
	
	//INIT
	
	public static void register(UnitCategory category) {
		unitCategories.add(category);
	}
	public static void register(UnitTargetPattern pattern) {
		unitTargetPattern.add(pattern);
	}
	public static void register(Unit unit) {
		units.add(unit);
	}
	public static boolean isUnitDataLoaded() {
		return (!unitCategories.isEmpty() && !unitTargetPattern.isEmpty() && !units.isEmpty());
	}

	//SPEZIFIC GETTER
	
	public static UnitCategory getCategoryByName(String name) {
		
		for(UnitCategory category : unitCategories) {
			if(category.getCategory().equals(name)) {
				return category;
			}
		}
		return null;
		
	}
	public static UnitTargetPattern getTargetPatternByName(String name) {
		
		for(UnitTargetPattern targetPattern : unitTargetPattern) {
			if(targetPattern.getPattern().equals(name)) {
				return targetPattern;
			}
		}
		return null;
		
	}
	public static Unit getUnit(int ID) {
		
		for(Unit unit : units) {
			if(unit.getId() == ID) {
				return unit;
			}
		}
		return null;
		
	}
	public static Unit getUnit(String name) {
		
		for(Unit unit : units) {
			if(unit.getName().equalsIgnoreCase(name)) {
				return unit;
			}
		}
		return null;
		
	}
	
	//GETTER
	
	public static List<UnitCategory> getUnitCategories() {
		return unitCategories;
	}
	public static List<UnitTargetPattern> getUnitTargetPattern() {
		return unitTargetPattern;
	}
	public static List<Unit> getUnits() {
		return units;
	}
	
}
