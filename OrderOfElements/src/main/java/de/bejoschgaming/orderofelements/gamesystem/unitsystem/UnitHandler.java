package de.bejoschgaming.orderofelements.gamesystem.unitsystem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.bejoschgaming.orderofelements.debug.ConsoleHandler;

public class UnitHandler {

	private static List<UnitCategory> unitCategories = new ArrayList<UnitCategory>();
	private static List<UnitTargetPattern> unitTargetPattern = new ArrayList<UnitTargetPattern>();
	private static LinkedList<Unit> unitTemplates = new LinkedList<Unit>();
	private static boolean hasBeenSorted = false;
	
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
		hasBeenSorted = false;
	}
	public static boolean isUnitDataLoaded() {
		return (!unitCategories.isEmpty() && !unitTargetPattern.isEmpty() && !unitTemplates.isEmpty());
	}

	public static Unit createNewUnit(int unitID) {
		
		Unit newUnit = getUnitTemplate(unitID).clone();
		createdUnits.add(newUnit);
		return newUnit;
		
	}
	public static void removeNewUnit(Unit unit) {
		
		for(Unit u : createdUnits) {
			if(u.equals(unit)) { //EQUALS VIA OWN HASH ID
				createdUnits.remove(unit);
				return;
			}
//			if(u == unit) { //== VIA POINTER/DISK LOCATION
//				createdUnits.remove(unit);
//				return;
//			}
		}
		
	}
	
	public static void sortUnits() {
		
		if(hasBeenSorted == true) { return; } //ALREADY SORTED
		
		LinkedList<Unit> unsortedList = new LinkedList<Unit>();
		LinkedList<Unit> sortedList = new LinkedList<Unit>();
		
		for(Unit u : unitTemplates) {
			unsortedList.add(u);
		}
		
		while(unsortedList.isEmpty() == false) {
			
			Unit selected = unsortedList.getFirst();
			for(Unit u : unsortedList) {
				//COST 0 PRIO
				if(u.getCost() == -1 || selected.getCost() == -1) {
					if(selected.getCost() == -1) {
						//0 COST ALREADY SELECTED
						int nameCompare = u.getName().compareToIgnoreCase(selected.getName());
						//Same name cant be!
						if(nameCompare < 0) {
							//BETTER
							selected = u;
						}
						continue;
					}else if(u.getCost() == -1) {
						//NO 0 COST SELECTED AND u -1 COST
						selected = u;
						continue;
					}else {
						//0 COST SELECTED BUT u NOT -1 SO IGNORE
						continue;
					}
				}
				//NOT A 0 COST UNIT
				int categoryCompare = u.getCategory().getCategory().compareToIgnoreCase(selected.getCategory().getCategory());
				if(categoryCompare == 0) {
					//EQUALS
					if(u.getCost() == selected.getCost()) {
						//EQUALS
						int nameCompare = u.getName().compareToIgnoreCase(selected.getName());
						//Same name cant be!
						if(nameCompare < 0) {
							//BETTER
							selected = u;
						}
					}else if(u.getCost() > selected.getCost()) {
						//BETTER
						selected = u;
					}
				}else if(categoryCompare < 0) {
					//BETTER
					selected = u;
				}
				
			}
			
			unsortedList.remove(selected);
			sortedList.add(selected);
			
		}
		
		unitTemplates = sortedList;
		hasBeenSorted = true;
		
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
	public static Unit getUnitTemplateByIndex(int index) {
		
		if(unitTemplates.size() > index) {
			return unitTemplates.get(index);
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
	public static List<Unit> getUnitTemplates() {
		return unitTemplates;
	}
	public static List<Unit> getCreatedUnits() {
		return createdUnits;
	}
	
}
