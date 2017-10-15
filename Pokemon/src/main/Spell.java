package main;
/**
 * Spell class for game - perhaps one day implemented ?
 * @author Tyler Deaton
 *
 */
public class Spell {
	String name;
	int damage;
	int level;
	int mana;
	int cost;
	int damagetype;
	public Spell(String name, int damage, int mana, int cost) {
		this.name = name;
		this.damage = damage;
		this.mana = mana;
		this.cost = cost;
		this.level = 1;
	}
	//0 is FIRE
	//1 is ICE
	//2 is EARTH
	//3 is WIND
	//4 is WATER
	//5 is THUNDER
	//6 is LIGHT
	//7 is DARK
	public void setDamageType(int damagetype) {
		this.damagetype = damagetype;
	}
	public void setDamageType(String dt) {
		if(dt.equals("FIRE")) {
			this.damagetype = 0;
		}
		if(dt.equals("ICE")) {
			this.damagetype = 1;
		}
		if(dt.equals("EARTH")) {
			this.damagetype = 2;
		}
		if(dt.equals("WIND")) {
			this.damagetype = 3;
		}
		if(dt.equals("WATER")) {
			this.damagetype = 4;
		}
		if(dt.equals("THUNDER")) {
			this.damagetype = 5;
		}
		if(dt.equals("LIGHT")) {
			this.damagetype = 6;
		}
		if(dt.equals("DARK")) {
			this.damagetype = 7;
		}
	}
	public String getDamageType() {
		if(this.damagetype == 0) {
			return "FIRE";
		}
		if(this.damagetype == 1) {
			return "ICE";
		}
		if(this.damagetype == 2) {
			return "EARTH";
		}
		if(this.damagetype == 3) {
			return "WIND";
		}
		if(this.damagetype == 4) {
			return "WATER";
		}
		if(this.damagetype == 5) {
			return "THUNDER";
		}
		if(this.damagetype == 6) {
			return "LIGHT";
		}
		if(this.damagetype == 7) {
			return "DARK";
		}
		return "BASIC";
		
	}
	/**
	 * Gets upgrade cost per level
	 * @return upgrade cost
	 */
	public int getUpgradeCost() {
		if(level == 1) {
			return (int) (cost * 1.5);
		}
		if(level == 2) {
			return (int) (cost * 2.25);
		}
		if(level == 3) {
			return (int) (cost * 3.25);
		}
		if(level == 4) {
			return (int) (cost * 4.5);
		}
		return 0;
	}
	public void upgrade() {
		level++;
		this.damage *= 1.75;
		this.mana *= 1.5;
		
		this.cost *= 1.6;
	}
	
	//TODO: add spell system

}
