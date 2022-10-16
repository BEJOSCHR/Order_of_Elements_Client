package de.bejoschgaming.orderofelements.gamesystem.unitsystem;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import de.bejoschgaming.orderofelements.debug.ConsoleHandler;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_4Deckbuilder;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;

public class UnitTargetPattern {

	private String pattern;
	String rawTargetSyntax;
	private List<Point> targetRelatives = new ArrayList<Point>();
	private Image icon = null;
	
	public UnitTargetPattern(String unitTargetPatternData) {
		
		String[] splitData = unitTargetPatternData.split(";");
		this.pattern = splitData[0];
		this.rawTargetSyntax = splitData[1];
		
		for(String field : this.rawTargetSyntax.split("_")) {
			
			int x = Integer.parseInt(field.split(":")[0]);
			int y = Integer.parseInt(field.split(":")[1]);
			this.targetRelatives.add(new Point(x, y));
			
		}
		
		loadIcon();
		
		if(this.targetRelatives.isEmpty()) { //EVEN NONE HAS 0:0 AS PLACEHOLDER
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
	public void loadIcon() {
		
		String rawName = this.pattern;
		if(this.pattern.contains("_")) {
			rawName = this.pattern.split("_")[0];
		}
		this.icon = ImageHandler.loadImageFromName("game/movementpattern/Icon_Movement_"+rawName+".png", Draw_4Deckbuilder.iconSize, Draw_4Deckbuilder.iconSize);
		
	}
	public Image getIcon() {
		return icon;
	}
	
}
