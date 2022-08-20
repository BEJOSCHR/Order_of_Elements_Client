package de.bejoschgaming.orderofelements.gamesystem.unitsystem;

public class Unit {

	private int id;
	private String name;
	private int cost;
	private UnitCategory category;
	private int health, armor, shield, damage;
	private UnitTargetPattern type_attack, type_move, type_aura;
	
	private int startHealth;
	private int x = 0, y = 0;
	
	public Unit(String unitData) {
		
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
		
	}

	//GETTER
	
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
