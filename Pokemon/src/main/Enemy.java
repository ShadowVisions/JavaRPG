package main;

public class Enemy {
	String name;
	int damage;
	int defense;
	int hp;
	int[] resist;
	int xp;
	int level;
	public Enemy(String name, int damage, int defense, int hp, int level) {
		this.name = name;
		this.damage = damage;
		this.defense = defense;
		this.hp = hp;
		this.resist = new int[8];
		this.level = 1;
		this.xp = 15;
	}
	public void setResist(int[] resist) {
		this.resist = resist;
	}
	
	public void setXP(int xp) {
		this.xp = xp;
	}
	public int getDamage(int damage, int damageType) {
		int re = resist[damageType];
		return damage * ((100 - re) / 100);
	}

}
