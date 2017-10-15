package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
/**
 * Tile class
 * @author Tyler Deaton
 *
 */
public class Tile {
	/** Chance out of 100 for grass encounter */
	public static final int grassChance = 15; 
	/** Current data of tile */
	String tileData;
	/** Normal data of tile */
	String normalData;
	/** Random generator */
	Random rand;
	/** Message */
	String message;
	/** Level of tile */
	int level;
	/** Battle enemies */
	ArrayList<Enemy> battle;
	/** Chest treasure */
	Treasure t;
	
	/**
	 * Default constructor
	 */
	public Tile() {
		tileData = null;
		level = 1;
		rand = new Random();
		message = "";
	}
	/**
	 * Gets tile data
	 * @return tile data
	 */
	public String tileData() {
		return tileData;
	}
	/**
	 * Gets normal data
	 * @return normal tile data
	 */
	public String normData() {
		return normalData;
	}
	/**
	 * Sets tile and normal data
	 * @param s data to set
	 */
	public void setTileData(String s) {
		tileData = s;
		normalData = s;
	}
	/**
	 * Determines if player can move to this tile
	 * @return
	 */
	public boolean validMove() {
		if(tileData != null && !tileData.equals("W")) {
			return true;
		}
		return false;
	}
	/**
	 * Sets temp data for tile
	 * @param s temp data
	 */
	public void setTempData(String s) {
		tileData = s;
	}
	/**
	 * Removes temp data from tile
	 */
	public void restore() {
		tileData = normalData;
	}
	/**
	 * Checks tile event
	 * @return tile event if any
	 */
	public Event checkEvent() {
		if(normalData.equals(" ")) {
			return null;
		}
		if(normalData.equals("C")) {
			
			Event e = new Event("Character Text", message);
			
			return e;
		}
		if(normalData.equals("Y")) {
			int n = rand.nextInt(100) + 1;
			if(n > 0 && n < grassChance) {
				if(n > 0 && n < (grassChance / 3)) {
					//name, damage, defense, hp, level
					Enemy en = new Enemy("Turtle", 1, 9, 30, level);
					Event e = new Event("Grass Attack", en);
					
					return e;
				} else if (n < (grassChance * .66)) {
					Enemy en = new Enemy("Tiger", 5, 8, 10, level);
					en.setXP(20);
					Event e = new Event("Grass Attack", en);
					
					return e;
				} else {
					Enemy en = new Enemy("Snake", 10, 3, 5, level);
					Event e = new Event("Grass Attack", en);
					
					return e;
					//TODO: more interesting grass monsters
				}
				
			} else {
				return null;
			}
			
		}
		if(normalData.equals("$")) {
			return new Event("Shop", "Shop 1");
		}
		if(normalData.equals("B")) {
			return new Event("Battle", battle);
		}
		if(normalData.equals("T")) {
			return new Event("Treasure", t);
		}
		return null;
	}
	/**
	 * Sets level of tile
	 * @param l level
	 */
	public void setLevel(int l) {
		level = l;
	}
	/**
	 * Cleans tile if player is on it
	 */
	public void cleanTile() {
		if(!normalData.equals("Y")) {
			normalData = " ";
			tileData = "P";
		}
		if(normalData.equals("T")) {
			normalData = " ";
			tileData = "P";
		}
		
	}
	/**
	 * Sets tile message
	 * @param s string to set as msg
	 */
	public void setMessage(String s) {
		this.message = s;
	}
	/**
	 * Sets battle for B tiles
	 * @param e battle enemies
	 */
	public void setBattle(ArrayList<Enemy> e) {
		this.battle = e;
	}
	/**
	 * Sets treasure for T tiles
	 * @param t treasure
	 */
	public void setTreasure(Treasure t) {
		this.t = t;
	}
	public void unlock() {
		if(this.normalData.equals("G")) {
			this.normalData = " ";
			this.tileData = " ";
		}
	}
	public Color tileColor() {
		if(normalData == null) {
			return Color.GRAY;
		}
		if(normalData.equals(" ")) {
			return Color.WHITE;
		}
		if(normalData.equals("^")) {
			return Color.WHITE;
		}
		if(normalData.equals("v")) {
			return Color.WHITE;
		}
		if(normalData.equals(">")) {
			return Color.WHITE;
		}
		if(normalData.equals("<")) {
			return Color.WHITE;
		}
		if(normalData.equals("Y")) {
			return Color.GREEN;
		}
		if(normalData.equals("W")) {
			return Color.CYAN;
		}
		if(normalData.equals("_")) {
			return Color.LIGHT_GRAY;
		}
		if(normalData.equals("$")) {
			return Color.RED;
		}
		if(normalData.equals("T")) {
			return Color.YELLOW;
		}
		if(normalData.equals("C")) {
			return Color.ORANGE;
		}
		return Color.GRAY;
	}

}
