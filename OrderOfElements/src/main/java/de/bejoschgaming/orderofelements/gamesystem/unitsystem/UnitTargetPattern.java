package de.bejoschgaming.orderofelements.gamesystem.unitsystem;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import de.bejoschgaming.orderofelements.debug.ConsoleHandler;

public class UnitTargetPattern {

	private String pattern;
	String rawTargetSyntax;
	private List<Point> targetRelatives = new ArrayList<Point>();
	
	public UnitTargetPattern(String unitTargetPatternData) {
		
		String[] splitData = unitTargetPatternData.split(";");
		this.pattern = splitData[0];
		this.rawTargetSyntax = splitData[1];
		
		for(String field : this.rawTargetSyntax.split("_")) {
			
			int x = Integer.parseInt(field.split(":")[0]);
			int y = Integer.parseInt(field.split(":")[1]);
			this.targetRelatives.add(new Point(x, y));
			
		}
		
		if(this.targetRelatives.isEmpty()) {
			ConsoleHandler.printMessageInConsole("TargetPattern '"+this.pattern+"' init with no relativePoints!", true);
		}
		
	}

	public String getPattern() {
		return pattern;
	}
	public String getRawTargetSyntax() {
		return rawTargetSyntax;
	}
	public List<Point> getTargetRelatives() {
		return targetRelatives;
	}
	
}
