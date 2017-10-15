package main;

import java.util.ArrayList;

public class Treasure {
	int coins;
	ArrayList<Spell> spells;
	ArrayList<Item> items;
	public Treasure(int coins) {
		this.coins = coins;
		this.spells = new ArrayList<Spell>();
		this.items = new ArrayList<Item>();
	}
	public void addSpell(Spell s) {
		spells.add(s);
	}
	public void addItem(Item i) {
		items.add(i);
	}
	

}
