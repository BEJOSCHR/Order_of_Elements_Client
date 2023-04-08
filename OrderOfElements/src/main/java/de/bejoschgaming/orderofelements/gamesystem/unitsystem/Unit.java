package de.bejoschgaming.orderofelements.gamesystem.unitsystem;

import java.awt.Image;
import java.util.Random;

import de.bejoschgaming.orderofelements.gamesystem.map.MapData;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_4Deckbuilder;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;

public class Unit {
	
	private int hashID = new Random().nextInt(10000, 100000);
	
	private String rawUnitData;
	
	private int id;
	private String name;
	private int cost;
	private UnitCategory category;
	private int health, armor, shield, damage;
	private UnitTargetPattern type_attack, type_move, type_aura;
	private Image icon, small_icon;
	
	private int startHealth;
	private int x = 0, y = 0;
	
	public Unit(String unitData) {
		
		this.rawUnitData = unitData;
		
		String[] splitData = unitData.split(";");
		
		this.id = Integer.parseInt(splitData[0]);
		this.name = splitData[1];
		this.cost = Integer.parseInt(splitData[2]);
		this.category = UnitHandler.getCategoryByName(splitData[3]);
		this.health = Integer.parseInt(splitData[4]);
		this.armor = Integer.parseInt(splitData[5]);
		this.shield = Integer.parseInt(splitData[6]);
		this.damage = Integer.parseInt(splitData[7]);
		this.type_attack = UnitHandler.getTargetPatternByName(splitData[8]);
		this.type_move = UnitHandler.getTargetPatternByName(splitData[9]);
		this.type_aura = UnitHandler.getTargetPatternByName(splitData[10]);
		
		this.x = 0;
		this.y = 0;
		this.startHealth = this.health;
		
		this.reloadImage();
		
	}
	
	//ONLY CALLED TO GET A RAW COPY OF THIS UNIT WITH NO CHANGED VARIABLES - USED AS UNITTEMPLATE CLONE
	@Override
	public Unit clone() {
		return new Unit(this.rawUnitData);
	}
	@Override
	public boolean equals(Object unit) {
		if(unit instanceof Unit) {
			return ((Unit)unit).getHashID() == this.hashID;
		}else {
			return false;
		}
	}
	
	public void reloadImage() {
		this.icon = ImageHandler.loadImageFromName("game/units/"+this.name+".png", MapData.FIELD_SIZE, MapData.FIELD_SIZE);
		this.small_icon = ImageHandler.loadImageFromName("game/units/"+this.name+".png", Draw_4Deckbuilder.iconSize, Draw_4Deckbuilder.iconSize);
	}
	
	//GETTER
	
	public int getHashID() {
		return hashID;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getCost() {
		return cost;
	}
	public UnitCategory getCategory() {
		return category;
	}
	public int getStartHealth() {
		return startHealth;
	}
	public int getHealth() {
		return health;
	}
	public int getArmor() {
		return armor;
	}
	public int getShield() {
		return shield;
	}
	public int getDamage() {
		return damage;
	}
	public UnitTargetPattern getType_attack() {
		return type_attack;
	}
	public UnitTargetPattern getType_move() {
		return type_move;
	}
	public UnitTargetPattern getType_aura() {
		return type_aura;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public Image getIcon() {
		return this.icon;
	}
	public Image getSmall_icon() {
		return small_icon;
	}

	//SETTER

	public void setHealth(int health) {
		this.health = health;
	}
	public void setShield(int shield) {
		this.shield = shield;
	}
	public void addHealth(int health) {
		this.health += health;
	}
	public void addShield(int shield) {
		this.shield += shield;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void addX(int x) {
		this.x += x;
	}
	public void addY(int y) {
		this.y += y;
	}
	
}
