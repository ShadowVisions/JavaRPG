package main;
/**
 * Item class for shops and inventory
 * @author Tyler Deaton
 *
 */
public class Item {
	/** Item class type */
	String type;
	/** Item name */
	String name;
	/** Item worth */
	int cost;
	/** HP stat */
	int hp;
	/** Attack stat */
	int atk;
	/** Defense stat */
	int def;
	/** Item upgrade level */
	int level;
	int mana;
	/**
	 * Constructor
	 * @param type item type
	 * @param name item name
	 * @param cost item worth
	 */
	public Item(String type, String name, int cost) {
		this.type = type;
		this.name = name;
		this.cost = cost;
		this.hp = 0;
		this.atk = 0;
		this.def = 0;
		this.mana = 0;
		this.level = 1;
				
	}
	/**
	 * Sets item HP stat
	 * @param hp hp stat
	 */
	public void setHp(int hp) {
		this.hp = hp;
		
	}
	/**
	 * Sets item attack stat
	 * @param atk atk stat
	 */
	public void setAtk(int atk) {
		this.atk = atk;
	}
	/**
	 * Sets item def stat
	 * @param def def stat
	 */
	public void setDef(int def) {
		this.def = def;
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
	/**
	 * Upgrades item
	 */
	public void upgrade() {
		level++;
		if(this.atk > 0) {
			this.atk *= 1.75;
		} else {
			this.atk *= 1.5;
		}
		if(this.def > 0) {
			this.def *= 1.75;
		} else {
			this.def *= 1.5;
		}
		if(this.hp > 0) {
			this.hp *= 1.75;
		} else {
			this.hp *= 1.5;
		}
		
		
		this.cost *= 1.6;
	}

}
