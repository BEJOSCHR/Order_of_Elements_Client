package de.bejoschgaming.orderofelements.decksystem;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import de.bejoschgaming.orderofelements.debug.ConsoleHandler;
import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.gamesystem.unitsystem.Unit;
import de.bejoschgaming.orderofelements.gamesystem.unitsystem.UnitHandler;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_4Deckbuilder;

public class Deck {

	private int deckID;
	private int ownerID;
	private String name = "LOADING";
	private String data = "LOADING"; 
	
	private int totalCost = 0;
	
	private List<Unit> units = new ArrayList<Unit>();
	private Map<Integer, Integer> unitsAmountMap = new HashMap<Integer, Integer>();
	
	public Deck(int deckID, int ownerID, String deckName, String deckData) {
		
		this.deckID = deckID;
		this.ownerID = ownerID;
		
		this.name = deckName;
		this.data = deckData;
		
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				readOutData(deckData);
			}
		}, 1000*2);
		
	}
	
	private void readOutData(String deckData) {
		
		if(deckData == null || deckData.contains(":") == false || deckData.contains("-") == false) {
			return;
		}
		
		for(String unitData : deckData.split(":")) {
			String[] splitData = unitData.split("-");
			int unitID = Integer.parseInt(splitData[0]);
			int unitX = Integer.parseInt(splitData[1]);
			int unitY = Integer.parseInt(splitData[2]);
			Unit newUnit = UnitHandler.createNewUnit(unitID);
			newUnit.setX(unitX);
			newUnit.setY(unitY);
			this.units.add(newUnit);
			this.totalCost += newUnit.getCost();
			if(unitsAmountMap.containsKey(newUnit.getId())) {
				int current = unitsAmountMap.get(newUnit.getId());
				unitsAmountMap.put(newUnit.getId(), current+1);
			}else {
				unitsAmountMap.put(newUnit.getId(), 1);
			}
		}
		
	}
	
	public void drawDeckPreview(Graphics g, int x, int y, int width, int height, Color color) {
		
		String name = this.getName();
		int nameWidth = g.getFontMetrics(FontHandler.getFont(FontHandler.medievalSharp_regular, 22)).stringWidth(" "+name);
		GraphicsHandler.drawCentralisedText(g, color, FontHandler.getFont(FontHandler.medievalSharp_regular, GraphicsHandler.getRelativTextSize(22)), name, x+nameWidth/2+25, y+30);
		String cost = this.totalCost+" / "+DeckHandler.MAX_DECK_COST+"";
		int costWidth = g.getFontMetrics(FontHandler.getFont(FontHandler.medievalSharp_regular, 22)).stringWidth(" "+cost);
		GraphicsHandler.drawCentralisedText(g, color, FontHandler.getFont(FontHandler.medievalSharp_regular, GraphicsHandler.getRelativTextSize(22)), cost, x+width-costWidth/2-25, y+30);
		int startX = x+40;
		int startY = y+height-Draw_4Deckbuilder.iconSize/2-20;
		int maxUnitsDisplay = (int) ( ((double) width-50 ) / ((double) (Draw_4Deckbuilder.iconSize+30) ) );
		
		for(int id : this.getUnitsAmountMap().keySet()) {
			int amount = this.getUnitsAmountMap().get(id);
			Unit unit = this.getUnit(id);
			GraphicsHandler.drawCentralisedText(g, color, FontHandler.getFont(FontHandler.medievalSharp_regular, GraphicsHandler.getRelativTextSize(22)), amount+"x", startX, startY);
			g.drawImage(unit.getSmall_icon(), startX+15, startY-Draw_4Deckbuilder.iconSize/2, null);
			startX += Draw_4Deckbuilder.iconSize+30;
			maxUnitsDisplay--;
			if(maxUnitsDisplay == 0) { break; }
		}
		
	}
	
	public List<Unit> getUnits() {
		return units;
	}
	public Unit getUnit(int id) {
		for(Unit unit : this.units) {
			if(unit.getId() == id) {
				return unit;
			}
		}
		ConsoleHandler.printMessageInConsole("Cant find unit "+id+" in deck "+deckID, true);
		return null;
	}
	public Map<Integer, Integer> getUnitsAmountMap() {
		return unitsAmountMap;
	}
	
	@Override
	public String toString() {
		return this.deckID+"#"+this.ownerID+"#"+this.name+"#"+this.data;
	}
	
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public int getDeckID() {
		return deckID;
	}
	public int getOwnerID() {
		return ownerID;
	}
	public String getName() {
		return name;
	}
	public String getData() {
		return data;
	}
	
}
