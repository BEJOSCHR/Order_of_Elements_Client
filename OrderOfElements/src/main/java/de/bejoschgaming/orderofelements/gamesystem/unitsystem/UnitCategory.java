package de.bejoschgaming.orderofelements.gamesystem.unitsystem;

public class UnitCategory {

	private String category, description;
	
	public UnitCategory(String unitCategoryData) {
		
		String[] splitData = unitCategoryData.split(";");
		
		this.category = splitData[0];
		this.description = splitData[1];
		
	}

	public String getCategory() {
		return category;
	}
	public String getDescription() {
		return description;
	}
	
}
