package de.bejoschgaming.orderofelements.gamesystem.unitsystem;

import java.util.ArrayList;
import java.util.List;

import de.bejoschgaming.orderofelements.debug.ConsoleHandler;

public class UnitHandler {

	private static List<UnitCategory> unitCategories = new ArrayList<UnitCategory>();
	private static List<UnitTargetPattern> unitTargetPattern = new ArrayList<UnitTargetPattern>();
	private static List<Unit> unitTemplates = new ArrayList<Unit>();
	
	private static List<Unit> createdUnits = new ArrayList<Unit>();
	//INIT
	
	public static void register(UnitCategory category) {
		unitCategories.add(category);
	}
	public static void register(UnitTargetPattern pattern) {
		unitTargetPattern.add(pattern);
	}
	public static void register(Unit unit) {
		unitTemplates.add(unit);
	}
	public static boolean isUnitDataLoaded() {
		return (!unitCategories.isEmpty() && !unitTargetPattern.isEmpty() && !unitTemplates.isEmpty());
	}

	public static Unit createNewUnit(int unitID) {
		
		Unit newUnit = getUnitTemplate(unitID).clone();
		createdUnits.add(newUnit);
		return newUnit;
		
	}
	
	//SPEZIFIC GETTER
	
	public static UnitCategory getCategoryByName(String name) {
		
		for(UnitCategory category : unitCategories) {
			if(category.getCategory().equals(name)) {
				return category;
			}
		}
		ConsoleHandler.printMessageInConsole("Found no unitcategory for name "+name+"!", true);
		return null;
		
	}
	public static UnitTargetPattern getTargetPatternByName(String name) {
		
		for(UnitTargetPattern targetPattern : unitTargetPattern) {
			if(targetPattern.getPattern().equals(name)) {
				return targetPattern;
			}
		}
		ConsoleHandler.printMessageInConsole("Found no targetpattern for name "+name+"!", true);
		return null;
		
	}
	public static Unit getUnitTemplate(int ID) {
		
		for(Unit unit : unitTemplates) {
			if(unit.getId() == ID) {
				return unit;
			}
		}
		ConsoleHandler.printMessageInConsole("Found no unittemplate for id "+ID+"!", true);
		return null;
		
	}
	public static Unit getUnitTemplate(String name) {
		
		for(Unit unit : unitTemplates) {
			if(unit.getName().equalsIgnoreCase(name)) {
				return unit;
			}
		}
		ConsoleHandler.printMessageInConsole("Found no unittemplate for name "+name+"!", true);
		return null;
		
	}
	
	//GETTER
	
	public static List<UnitCategory> getUnitCategories() {
		return unitCategories;
	}
	public static List<UnitTargetPattern> getUnitTargetPattern() {
		return unitTargetPattern;
	}
	public static List<Unit> getUnitTemplates() {
		return unitTemplates;
	}
	public static List<Unit> getCreatedUnits() {
		return createdUnits;
	}
	
}
