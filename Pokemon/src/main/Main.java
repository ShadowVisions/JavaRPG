package main;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Main class behind the RPG
 * @author Tyler Deaton
 *
 */
public class Main {
	/** Grid of tiled maps */
	Map[][] grid;
	/** Current row of the grid */
	int currentRow;
	/** Current column of the grid */
	int currentColumn;
	/** Scanner for user input */
	Scanner s;
	/** Number of coins */
	int coins;
	/** Amount of XP */
	int xp;
	/** Name of player */
	String name;
	/** Level of player */
	int level;
	
	
	//inventory and shop items
	/** Player inventory */
	ArrayList<Item> inventory;
	/** Level 1 shop */
	ArrayList<Item> shop1;
	/** Level 2 shop */
	ArrayList<Item> shop2;
	/** Level 3 shop */
	ArrayList<Item> shop3;
	
	/** Spellbook of the player */
	ArrayList<Spell> spellbook;
	/** Unlearned spells */
	ArrayList<Spell> unlearned;
	
	//combat modifiers
	/** Player attack stat */
	int pAtk;
	/** Player hp stat */
	int pHp;
	/** Player def stat */
	int pDefense;
	/** Player mana */
	int pMana;
	
	
	String command;
	GUI g;
	int shopSelected;
	String battleInput;
	boolean itemSell;
	boolean itemUpgrade;
	String encounterMode;
	

	/**
	 * Constructor, but also main procedure loop for turns
	 */
	public Main() {
		this.command = "new";
		this.s = new Scanner(System.in);
		this.inventory = new ArrayList<Item>();
		System.out.print("Type your name: ");
		this.name = s.nextLine();
		intro();
		populateGrid();
		populateShops();
		populateSpells();
		
		this.coins = 0;
		this.level = 1;
		
		recalculateStats();
		g = new GUI();
		g.setMain(this);
		/**
		EventQueue.invokeLater(new Runnable(){
            public void run(){
                new GUI();
            }
        });
        */
		
		while(true) {
			//g.mode = "MAP";
			//g.drawPanel.repaint();
			if(!command.equals("")) {
				
				//System.out.println(this.displayMap());
				//System.out.print("Abilities: Enter W, A, S, D to move,\nB to see spellbook, I to see inventory\nM to see world map, or Q to quit: ");
			}
			
			//String command = s.nextLine().toUpperCase();
			
			//switch between GUI and text mode
			if(command.equals("Q")) {
				System.out.println("Goodbye!");
				System.exit(0);
			}
			if(command.equals("I")) {
				displayInventory();
			}
			if(command.equals("B")) {
				displaySpells();
			}
			if(command.equals("M")) {
				System.out.println(printWorldMap());
				System.out.print("Enter anything to continue: ");
				s.nextLine();
			}
			String direction = grid[currentRow][currentColumn].movePlayer(command);
			g.mode = "MAP";
			g.drawPanel.repaint();
			if(!(direction.equals(""))) {
				int x = grid[currentRow][currentColumn].playerHeight;
				int y = grid[currentRow][currentColumn].playerWidth;
				if(direction.equals("NORTH")) {
					
					currentColumn++;
					grid[currentRow][currentColumn].setPlayer(grid[currentRow][currentColumn].height - 1, y);
					grid[currentRow][currentColumn].found = true;
				}
				if(direction.equals("SOUTH")) {
					currentColumn--;
					grid[currentRow][currentColumn].setPlayer(0, y);
					grid[currentRow][currentColumn].found = true;
				}
				if(direction.equals("EAST")) {
					currentRow++;
					grid[currentRow][currentColumn].setPlayer(x, 0);
					grid[currentRow][currentColumn].found = true;
				}
				if(direction.equals("WEST")) {
					currentRow--;
					grid[currentRow][currentColumn].setPlayer(x, grid[currentRow][currentColumn].width - 1);
					grid[currentRow][currentColumn].found = true;
				}
				
			}
			Event e = null;
			if(!command.equals("")) {
				e = grid[currentRow][currentColumn].checkEvent();
			}
			
			command = "";
			if(e != null) {
				
				
				if(e.name.equals("Grass Attack")) {
					int x = doBattle(e.enemy.get(0));
					if(x == 1 && e.p != null) {
						if(e.p.type.equals("KEY")) {
							(grid[e.p.gridRow][e.p.gridCol]).map[e.p.mapCol][e.p.mapRow].unlock();
						}
					}
				}
				if(e.name.equals("Shop")) {
					shopMenu(e.message);
				}
				if(e.name.equals("Character Text")) {
					displayMessage(e.message);
				}
				if(e.name.equals("Battle")) {
					int x = doEncounter(e.enemy);
					
					if(x == 1 && e.p != null) {
						if(e.p.type.equals("KEY")) {
							(grid[e.p.gridRow][e.p.gridCol]).map[e.p.mapCol][e.p.mapRow].unlock();
						}
					}
					
				}
				if(e.name.equals("Treasure")) {
					processTreasure(e.t);
				}
				
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	

	/**
	 * Main, I guess
	 * @param args not used
	 */
	public static void main(String[] args) {
		new Main();
		// run the program

	}
	/**
	 * Displays the current visible map as well as other data,
	 * and returns a string to print.
	 * @return string version of map + player details
	 */
	public String displayMap() {
		String ret = "";
		ret += "\n\n\n\n\n\n\n\n\n";
		ret += "-------------------------------------------------------\n";
		ret += "              Map              |         Stats\n";
		for(int i = 0; i < grid[currentRow][currentColumn].height(); i++) {
			for(int j = 0; j < grid[currentRow][currentColumn].width(); j++) {
				String x = grid[currentRow][currentColumn].tileData(i, j);
				if(x == null) {
					ret += "[" + "/" + "]";
				} else {
					ret += "[" + grid[currentRow][currentColumn].tileData(i, j) + "]";
				}
				
			}
			ret += " | ";
			if(i == 2) {
				ret += " Name: " + name;
			}
			if(i == 3) {
				ret += " Level: " + level;
			}
			if(i == 4) {
				ret += " Coins: " + coins;
			}
			
			if(i == 8) {
				ret += " Area Name: " + grid[currentRow][currentColumn].name();
			}
			if(i == 9) {
				ret += " Area Level: " + grid[currentRow][currentColumn].getLevel();
			}
			if(i == 5) {
				ret += " Attack: " + pAtk;
			}
			if(i == 6) {
				ret += " Defense: " + pDefense;
			}
			if(i == 7) {
				ret += " Health: " + pHp;
			}
			ret += "\n";
			
		}
		ret += "-------------------------------------------------------\n";
		return ret;
	}
	/**
	 * Initiates a battle sequence.
	 * @param name Name of enemy
	 * @param l level of enemy
	 * @param e any additional enemy info
	 */
	public int doBattle(Enemy e) {
		recalculateStats();
		int l = e.level;
		String name = e.name;
		//System.out.println("You encountered a level " + l + " " + name + "!");
		
		
		int enemyHp = 50 + (l * 25) + e.hp;
		int enemyAtk = 10 + (l * 5) + e.damage;
		int enemyDef = 5 + (l * 2) + e.defense;
		
		int rewardCoins = 10 + (l * 5);
		int rewardXp = 10 + (l * 5);
		while(true) {
			String[] battleStats = new String[8];
			battleStats[0] = "Your HP: " + pHp;
			battleStats[1] = "Your attack: " + pAtk;
			battleStats[2] = "Your defense: " + pDefense;
			battleStats[3] = "Your mana: " + pMana;
			battleStats[4] = name + "'s HP: " + enemyHp;
			battleStats[5] = name + "'s attack: " + enemyAtk;
			battleStats[6] = name + "'s defense: " + enemyDef;
			battleStats[7] = "You encountered a level " + l + " " + name + "!";
			/**
			System.out.println("Your HP: " + pHp);
			System.out.println("Your attack: " + pAtk);
			System.out.println("Your defense: " + pDefense);
			System.out.println("Your mana: " + pMana);
			System.out.println(name + "'s HP: " + enemyHp);
			System.out.println(name + "'s attack: " + enemyAtk);
			System.out.println(name + "'s defense: " + enemyDef);
			*/
			g.battleStats = battleStats;
			g.mode = "BATTLE";
			//String ret = "Your HP: " + pHp;
			//ret += "\nYour attack: " + pAtk;
			//g.jlab.setText(ret);
			
			if(pHp > 0) {
				battleInput = "";
				g.battleTurn = "PLAYER";
				g.drawPanel.repaint();
				//String current = "";
				while(battleInput.equals("")) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				/**
				while(!(current.equals("A") || current.equals("F") || current.equals("S"))) {
					System.out.println("It is your turn to act!");
					System.out.print("A to attack, S to cast spell, or F to flee: ");
					current = s.nextLine().toUpperCase();
				}
				*/
				String current = battleInput;
				if(current.equals("F")) {
					System.out.println("You have fled the battle, and received no rewards!");
					System.out.print("Enter anything to continue: ");
					//s.nextLine();
					recalculateStats();
					return 0;
				}
				if(current.equals("A")) {
					System.out.println("You attacked " + name +
							" for " + calculateDmg(pAtk, enemyDef) + " damage!");
					enemyHp -= calculateDmg(pAtk, enemyDef);
					g.recentAttack = "You attacked " + name +
							" for " + calculateDmg(pAtk, enemyDef) + " damage!";
					
				}
				if(current.equals("S")) {
					
					/**
					System.out.println("Current spellbook: ");
					System.out.println("---------------------------------------");
					
					System.out.println("Spell #| Name | Damage Type | Power | Mana");
					for(int i = 1; i < spellbook.size() + 1; i++) {
						System.out.println(i + "      | " + spellbook.get(i - 1).name
								+ "  | " + spellbook.get(i - 1).getDamageType() + " | "
								+ spellbook.get(i - 1).damage + " | " + spellbook.get(i - 1).mana);
					}
					System.out.println("---------------------------------------");
					System.out.println("Your mana: " + pMana);
					System.out.print("\nEnter a number to choose the spell: ");
					*/
					g.mode = "SPELLC";
					g.drawPanel.repaint();
					shopSelected = -1;
					g.shopSelected = 0;
					while(shopSelected == -1) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
					}
					int n = shopSelected + 1;
					/**
					while((n < 1) || (n > spellbook.size() + 1)) {
						try {
							n = Integer.parseInt(s.nextLine());
						} catch (NumberFormatException ex) {
							System.out.println("Must enter a number!");
						}
					}
					*/
					Spell s = spellbook.get(n - 1);
					if(pMana - s.mana < 0) {
						System.out.println("Not enough mana!");
					} else {
						pMana -= s.mana;
						
						enemyHp -= e.getDamage(calculateDmg(s.damage, enemyDef), s.damagetype);
						
						System.out.println("You attacked " + name +
								" for " + e.getDamage(calculateDmg(s.damage, enemyDef), s.damagetype)
								+ " damage!");
						g.recentAttack = "You attacked " + name +
								" for " + e.getDamage(calculateDmg(s.damage, enemyDef), s.damagetype)
								+ " damage!";
					}
					
					
				}
			} else {
				System.out.println("You have been defeated in battle, and received no rewards!");
				System.out.print("Enter anything to continue: ");
				//s.nextLine();
				recalculateStats();
				return 0;
			}
			if(enemyHp > 0) { //enemy alive
				System.out.println(name + " attacked you for " + calculateDmg(enemyAtk, pDefense) + " damage!");
				pHp -= calculateDmg(enemyAtk, pDefense);
				g.recentDamage = name + " attacked you for " + calculateDmg(enemyAtk, pDefense) + " damage!";
			} else { //victory condition -- enemy dead
				System.out.println("You have emerged victorious in battle,"
						+ " and received " + rewardCoins + " coins and "
								+ rewardXp + " xp!");
				
				coins += rewardCoins;
				xp += rewardXp;
				recalculateStats();
				System.out.print("Enter anything to continue: ");
				//s.nextLine();
				grid[currentRow][currentColumn].cleanTile();
				return 1;
			}
			
			
		}
	}
	/**
	 * Initiates the shop menu sequence
	 * @param l shop level to pick from
	 */
	public void shopMenu(String msg) {
		shopSelected = -1;
		ArrayList<Item> current = null;
		if(msg.equals("Shop 1")) {
			current = shop1;
		} else if(msg.equals("Shop 2")) {
			current = shop2;
		} else if(msg.equals("Shop 3")) {
			current = shop3;
		}
		g.shop = current;
		g.mode = "SHOP";
		g.shopSelected = 0;
		
		while(true) {
			g.drawPanel.repaint();
			
			while(shopSelected == -1) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(shopSelected == -2) {
				return;
			}
			
			System.out.println("You have reached the shop!");
			if(current.size() == 0) {
				System.out.println("Out of stock!");
			} else {
				System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				System.out.println("---------------------------------------");
				System.out.println("Item #| Cost | Type | Name");
				for(int i = 1; i < current.size() + 1; i++) {
					System.out.println(i + "     | " + current.get(i - 1).cost
							+ "  | " + current.get(i - 1).type + " | "
							+ current.get(i - 1).name);
				}
				System.out.println("---------------------------------------");
				System.out.println("Your funds: " + coins);
			}
			/**
			System.out.print("\nEnter the number to purchase or 0 to quit: ");
			int n = 0;
			try {
				n = Integer.parseInt(s.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Must enter a number!");
			}
			*/
			int n = shopSelected + 1; //for GUI
			if(n > 0 && n < current.size() + 1) {
				if(current.get(n - 1).cost > this.coins) {
					System.out.println("Insufficient funds!");
					g.shopSelected = 0;
					shopSelected = -1;
				} else {
					coins -= current.get(n - 1).cost;
					inventory.add(current.get(n - 1));
					current.remove(n - 1);
					recalculateStats();
					System.out.println("Item purchased!");
					shopSelected = -1;
					
				}
			}
			if(n == 0) {
				return;
			}
			
		}
	}
	/**
	 * Populates the initial grid with map objects.
	 */
	public void populateGrid() {
		this.grid = new Map[5][5];
		currentRow = 0;
		currentColumn = 0;
		grid[0][0] = new Map(10, 10, 1, "South Woods");
		grid[0][0].playerName = name;
		grid[0][0].found = true;
		grid[0][1] = new Map(10, 10, 1, "North Woods");
		grid[0][1].playerName = name;
		grid[1][1] = new Map(10, 10, 1, "Bridge to Townshire");
		grid[1][1].playerName = name;
		grid[1][0] = new Map(10, 10, 1, "Woodland Bank");
		grid[1][0].playerName = name;
		for(int w = 0; w <= 4; w++) {
			grid[4][w] = new Map(10, 10, 1, "temp");
		}
		for(int w = 0; w <= 4; w++) {
			grid[3][w] = new Map(10, 10, 1, "temp");
		}
		for(int w = 2; w <= 4; w++) {
			grid[2][w] = new Map(10, 10, 1, "temp");
		}
		grid[1][2] = new Map(10, 10, 1, "temp");
		grid[1][3] = new Map(10, 10, 1, "temp");
		grid[1][4] = new Map(10, 10, 1, "temp");
		grid[0][2] = new Map(10, 10, 1, "temp");
		grid[0][3] = new Map(10, 10, 1, "temp");
		grid[0][4] = new Map(10, 10, 1, "temp");
		grid[2][0] = new Map(10, 10, 1, "South Fields");
		grid[2][1] = new Map(10, 10, 1, "Townshire");
		
		
		
		//TODO: more maps
	}
	/**
	 * Populates the shop with items
	 */
	public void populateShops() {
		this.shop1 = new ArrayList<Item>();
		this.shop2 = new ArrayList<Item>();
		this.shop3 = new ArrayList<Item>();
		Item startSword = new Item("Sword", "Blunt Knife", 10);
		startSword.setAtk(3);
		Item startShirt = new Item("Armor", "Leather Jacket", 10);
		startShirt.setDef(2);
		Item startHp = new Item("Gem", "Ruby Crystal", 10);
		startHp.setHp(15);
		
		inventory.add(startSword);
		inventory.add(startShirt);
		inventory.add(startHp);
		
		Item infb = new Item("Sword", "Infinity Blade", 30);
		infb.setAtk(10);
		Item sb = new Item("Armor", "Sturdy Belt", 20);
		sb.setHp(35);
		sb.setAtk(-2);
		Item hp = new Item("Gem", "Aegis Shield", 25);
		hp.setDef(5);
		hp.setHp(10);
		
		shop1.add(infb);
		shop1.add(sb);
		shop1.add(hp);
		//TODO: more items
		
	}
	/**
	 * Populates the spellbooks with spells
	 */
	public void populateSpells() {
		this.spellbook = new ArrayList<Spell>();
		Spell fireblast = new Spell("Fireblast", 30, 20, 50);
		spellbook.add(fireblast);
		
		this.unlearned = new ArrayList<Spell>();
	}
	/**
	 * Recalculates player stats after upgrades or battles.
	 */
	public void recalculateStats() {
		findLevel();
		
		int baseHp = 75 + (level * 30);
		int baseAtk = 15 + (level * 7);
		int baseDefense = 5 + (level * 3);
		int baseMana = 40 + (level * 10);
		int statHp = 0;
		int statAtk = 0;
		int statDefense = 0;
		int statMana = 0;
		for(int i = 0; i < inventory.size(); i++) {
			statHp += inventory.get(i).hp;
			statAtk += inventory.get(i).atk;
			statDefense += inventory.get(i).def;
			statMana += inventory.get(i).mana;
		}
		pHp = baseHp + statHp;
		pAtk = baseAtk + statAtk;
		pDefense = baseDefense + statDefense;
		pMana = baseMana + statMana;
	}
	/**
	 * Finds current player level from xp
	 */
	public void findLevel() {
		if(xp < 100) {
			level = 1;
		} else if(xp >= 100 && xp < 250) {
			level = 2;
		} else if(xp >= 250 && xp < 450) {
			level = 3;
		} else {
			level = fibo(xp);
		}
		
	}
	/**
	 * Using fibonacci sequence, finds level from xp
	 * @param xp current player xp
	 * @return current player level
	 */
	public static int fibo(int xp) {
		int a = 250;
		int b = 450;
		int c = a + b;
		int l = 4;
		while(c <= xp) {
			int temp = c;
			c += b;
			b = temp;
			l++;
		}
		return l;
	}
	/**
	 * Initiates the inventory menu sequence.
	 */
	public void displayInventory() {
		
		while(true) {
			shopSelected = -1;
			g.mode = "INVEN";
			g.shopSelected = 0;
			itemSell = false;
			itemUpgrade = false;
			g.drawPanel.repaint();
			while(shopSelected == -1) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(shopSelected == -2) {
				return;
			}
			/**
			System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Current inventory: ");
			System.out.println("---------------------------------------");
			
			System.out.println("Item #| Worth | Type | Name");
			for(int i = 1; i < inventory.size() + 1; i++) {
				System.out.println(i + "     | " + inventory.get(i - 1).cost
						+ "  | " + inventory.get(i - 1).type + " | "
						+ inventory.get(i - 1).name);
			}
			System.out.println("---------------------------------------");
			System.out.println("Your funds: " + coins);
			System.out.print("\nEnter a number to see the item's stats"
					+ " or 0 to exit: ");
					*/
			int n = shopSelected + 1;
			/**
			try {
				n = Integer.parseInt(s.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Must enter a number!");
			}
			*/
			if(n > 0 && n < inventory.size() + 1) {
				Item i = inventory.get(n - 1);
				/**
				System.out.println("Stats for " + i.name + ": ");
				System.out.println("Level: " + i.level);
				System.out.println("Attack: +" + i.atk);
				System.out.println("Defense: +" + i.def);
				System.out.println("Health: +" + i.hp);
				
				System.out.println("Sells for: " + ((int) (i.cost * .7)));
				*/
				int u = i.getUpgradeCost();
				if(u == 0) {
					System.out.println("Item at max upgrades!");
				} else {
					System.out.println("Upgrades for: " + u);
				}
				
				System.out.print("Enter S to sell, U to upgrade or anything to continue: ");
				//String in = s.nextLine().toUpperCase();
				String in = "";
				if(itemSell) {
					in = "S";
				}
				if(itemUpgrade) {
					in = "U";
				}
				if(in.equals("S")) {
					System.out.println("Item sold!");
					coins += i.cost * .7;
					inventory.remove(n - 1);
					recalculateStats();
					
				}
				if(in.equals("U")) {
					if(coins >= u) {
						System.out.println("Item upgraded!");
						coins -= u;
						i.upgrade();
						recalculateStats();
					} else {
						System.out.println("Insufficient funds!");
					}	
					
				}
			}
			if(n == 0) {
				return;
			}
		}
		
		
	}
	/**
	 * Displays given message to user.
	 * @param msg message to display
	 */
	public void displayMessage(String msg) {
		//System.out.println("\n" + msg);
		g.mode = "MSG";
		g.msg = msg;
		g.drawPanel.repaint();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.print("Enter anything to continue: ");
		//s.nextLine();
	}
	/**
	 * Initiates the intro sequence for the game
	 */
	public void intro() {
		System.out.println("Welcome, " + name + ".");
		System.out.println("On your journey you will encounter several different tiles.\nHere is a brief overview of them:");
		System.out.println("[ ] | Blank tile you can walk on.");
		System.out.println("[/] | Occupied tile you can't walk on.");
		System.out.println("[W] | Water or river tile you can't walk on.");
		System.out.println("[Y] | Grass tile you can walk in, but may find enemies.");
		System.out.println("[^] | Map tile to take you to the next area.");
		System.out.println("[P] | Player tile you are currently on.");
		System.out.println("[C] | NPC tile you can walk on and interact with.");
		System.out.println("[$] | Shop tile you can buy items on.");
		System.out.println("[B] | Battle tile for fighting enemies.");
		System.out.println("[T] | Treasure tile you can find stuff on.");
		System.out.print("Enter anything to continue: ");
		s.nextLine();
		
	}
	/**
	 * Initiates the spell menu sequence.
	 */
	public void displaySpells() {
		while(true) {
			shopSelected = -1;
			g.mode = "SPELLB";
			g.shopSelected = 0;
			itemSell = false;
			itemUpgrade = false;
			g.drawPanel.repaint();
			while(shopSelected == -1) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(shopSelected == -2) {
				return;
			}
			/**
			System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("Current spellbook: ");
			System.out.println("---------------------------------------");
			
			System.out.println("Spell #| Name | Damage Type | Power | Mana");
			for(int i = 1; i < spellbook.size() + 1; i++) {
				System.out.println(i + "      | " + spellbook.get(i - 1).name
						+ "  | " + spellbook.get(i - 1).getDamageType() + " | "
						+ spellbook.get(i - 1).damage + " | " + spellbook.get(i - 1).mana);
			}
			System.out.println("---------------------------------------");
			System.out.println("Your funds: " + coins);
			System.out.print("\nEnter a number to see the spell's stats"
					+ " or 0 to exit: ");
					*/
			int n = shopSelected + 1;
			//int n = 0;
			/**
			try {
				n = Integer.parseInt(s.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Must enter a number!");
			}
			*/
			if(n > 0 && n < spellbook.size() + 1) {
				Spell i = spellbook.get(n - 1);
				System.out.println("Stats for " + i.name + ": ");
				System.out.println("Level: " + i.level);
				System.out.println("Damage: " + i.damage);
				System.out.println("Damage Type: " + i.damagetype);
				System.out.println("Mana Cost: " + i.mana);
				
				System.out.println("Sells for: " + ((int) (i.cost * .7)));
				int u = i.getUpgradeCost();
				if(u == 0) {
					System.out.println("Item at max upgrades!");
				} else {
					System.out.println("Upgrades for: " + u);
				}
				
				System.out.print("Enter S to sell, U to upgrade or anything to continue: ");
				//String in = s.nextLine().toUpperCase();
				String in = "U";
				if(in.equals("S")) {
					System.out.println("Spell sold!");
					coins += i.cost * .7;
					inventory.remove(n - 1);
					recalculateStats();
					
				}
				if(in.equals("U")) {
					if(coins >= u) {
						System.out.println("Spell upgraded!");
						coins -= u;
						i.upgrade();
						recalculateStats();
					} else {
						System.out.println("Insufficient funds!");
					}	
					
				}
			}
			if(n == 0) {
				return;
			}
		}
		
		
	}
	/**
	 * Prints the world map
	 * @return world map
	 */
	public String printWorldMap() {
		String ret = "";
		for(int h = 4; h >= 0; h--) {
			for(int row = 0; row < 10; row++) {
				for(int w = 0; w <= 4; w++) {
					ret += grid[w][h].printRow(row);
				}
				ret += "\n";
			}
			
		}
		return ret;
	}
	/**
	 * Calculates damage to a person
	 * @param atk attack modifer
	 * @param def defense modifier
	 * @return damage dealt
	 */
	public int calculateDmg(int atk, int def) {
		int x = (int) ((atk / def) * atk * .5);
		return x;
	}
	/**
	 * Initiates battle sequence for more than one enemy
	 * @param e sequence of enemies
	 */
	public int doEncounter(ArrayList<Enemy> e) {
		recalculateStats();
		int rewardCoins = 0;
		int rewardXp = 0;
		String[] encounterIntro = new String[e.size()];
		for(int i = 0; i < e.size(); i++) {
			int l = e.get(i).level;
			String name = e.get(i).name;
			System.out.println("You encountered a level " + l + " " + name + "!");
			rewardCoins += 10 + (l * 5);
			rewardXp += 10 + (l * 5);
			encounterIntro[i] = "You encountered a level " + l + " " + name + "!";
		}
		
		
		this.encounterMode = "";
		g.encounterIntro = encounterIntro;
		
		
		while(true) {
			
			/**
			System.out.println("Your HP: " + pHp);
			System.out.println("Your attack: " + pAtk);
			System.out.println("Your defense: " + pDefense);
			System.out.println("Your mana: " + pMana);
			*/
			String[][] encounterData = new String[e.size() + 1][4];
			g.ensize = e.size();
			encounterData[0][0] = "Your HP: " + pHp;
			encounterData[0][1] = "Your attack: " + pAtk;
			encounterData[0][2] = "Your defense: " + pDefense;
			encounterData[0][3] = "Your mana: " + pMana;
			
			for(int i = 0; i < e.size(); i++) {
				
				String name = e.get(i).name;
				int l = e.get(i).level;
				int enemyHp = 50 + (l * 25) + e.get(i).hp;
				int enemyAtk = 10 + (l * 5) + e.get(i).damage;
				int enemyDef = 5 + (l * 2) + e.get(i).defense;
				/**
				System.out.println(name + "'s HP: " + enemyHp);
				System.out.println(name + "'s attack: " + enemyAtk);
				System.out.println(name + "'s defense: " + enemyDef);
				*/
				encounterData[i + 1][0] = name + "'s HP: " + enemyHp;
				encounterData[i + 1][1] = name + "'s attack: " + enemyAtk;
				encounterData[i + 1][2] = name + "'s defense: " + enemyDef;
			}
			g.encounterData = encounterData;
			
			if(pHp > 0) {
				shopSelected = -1;
				g.mode = "ENCOUNTER";
				g.shopSelected = 0;
				itemSell = false;
				itemUpgrade = false;
				g.drawPanel.repaint();
				while(shopSelected == -1) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				}
				if(shopSelected == -2) {
					recalculateStats();
					return 0;
				}
				String current = encounterMode;
				//String current = "";
				while(!(current.equals("A") || current.equals("F") || current.equals("S"))) {
					System.out.println("It is your turn to act!");
					System.out.print("A to attack, S to cast spell, or F to flee: ");
					current = s.nextLine().toUpperCase();
				}
				if(current.equals("F")) {
					System.out.println("You have fled the battle, and received no rewards!");
					System.out.print("Enter anything to continue: ");
					//s.nextLine();
					recalculateStats();
					return 0;
				}
				if(current.equals("A")) {
					//int x = displayEnemies(e);
					int x = shopSelected;
					System.out.println("You attacked " + e.get(x).name +
							" for " + calculateDmg(pAtk, e.get(x).defense) + " damage!");
					e.get(x).hp -= calculateDmg(pAtk, e.get(x).defense);
					g.recentAttack = "You attacked " + e.get(x).name +
							" for " + calculateDmg(pAtk, e.get(x).defense) + " damage!";
					if(e.get(x).hp <= 0) {
						e.remove(x);
					}
					
				}
				if(current.equals("S")) {
					/**
					System.out.println("Current spellbook: ");
					System.out.println("---------------------------------------");
					
					System.out.println("Spell #| Name | Damage Type | Power | Mana");
					for(int i = 1; i < spellbook.size() + 1; i++) {
						System.out.println(i + "      | " + spellbook.get(i - 1).name
								+ "  | " + spellbook.get(i - 1).getDamageType() + " | "
								+ spellbook.get(i - 1).damage + " | " + spellbook.get(i - 1).mana);
					}
					System.out.println("---------------------------------------");
					System.out.println("Your mana: " + pMana);
					System.out.print("\nEnter a number to choose the spell: ");
					*/
					g.mode = "SPELLC";
					g.drawPanel.repaint();
					shopSelected = -1;
					g.shopSelected = 0;
					while(shopSelected == -1) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
					}
					//int n = 0;
					int n = shopSelected + 1;
					while((n < 1) || (n > spellbook.size() + 1)) {
						try {
							n = Integer.parseInt(s.nextLine());
						} catch (NumberFormatException ex) {
							System.out.println("Must enter a number!");
						}
					}
					
					Spell s = spellbook.get(n - 1);
					if(pMana - s.mana < 0) {
						System.out.println("Not enough mana!");
					} else {
						pMana -= s.mana;
						
						//int x = displayEnemies(e);
						int x = shopSelected;
						System.out.println("You attacked " + e.get(x).name +
								" for " + calculateDmg(pAtk, e.get(x).defense) + " damage!");
						e.get(x).hp -= calculateDmg(pAtk, e.get(x).defense);
						if(e.get(x).hp <= 0) {
							e.remove(x);
						}
						g.recentAttack = "You attacked " + e.get(x).name +
								" for " + calculateDmg(pAtk, e.get(x).defense) + " damage!";
					}
					
					
				}
			} else {
				System.out.println("You have been defeated in battle, and received no rewards!");
				System.out.print("Enter anything to continue: ");
				s.nextLine();
				recalculateStats();
				return 0;
			}
			if(anyAlive(e)) { //enemy alive
				for(int i = 0; i < e.size(); i++) {
					System.out.println(e.get(i).name + " attacked you for " + calculateDmg(e.get(i).damage, pDefense) + " damage!");
					pHp -= calculateDmg(e.get(i).damage, pDefense);
				}
				//TODO: Enemy abilities
				
			} else { //victory condition -- enemy dead
				System.out.println("You have emerged victorious in battle,"
						+ " and received " + rewardCoins + " coins and "
								+ rewardXp + " xp!");
				
				coins += rewardCoins;
				xp += rewardXp;
				recalculateStats();
				System.out.print("Enter anything to continue: ");
				s.nextLine();
				grid[currentRow][currentColumn].cleanTile();
				return 1;
			}
			
			
		}
	}
	/**
	 * Displays currently alive enemies
	 * @param e list of enemies
	 * @return selected enemy for attack
	 */
	public int displayEnemies(ArrayList<Enemy> e) {
		System.out.println("Alive enemies: ");
		System.out.println("---------------------------------------");
		
		System.out.println("Enemy #| Name | Hp | Attack | Defense");
		
		for(int i = 0; i < e.size(); i++) {
			if(e.get(i).hp > 0) {
				System.out.println((i + 1) + "      | " + e.get(i).name
						+ "  | " + e.get(i).hp + " | "
						+ e.get(i).damage + " | " + e.get(i - 1).defense);
				
			}
		}
		System.out.print("Which target would you like to attack: ");
		int n = 0;
		while((n < 1) || (n > e.size() + 1)) {
			try {
				n = Integer.parseInt(s.nextLine());
			} catch (NumberFormatException ex) {
				System.out.println("Must enter a number!");
			}
		}
		return n - 1;
	}
	/**
	 * Determines if any enemies are alive
	 * @param e list of enemies
	 * @return whether or not any enemies are alive
	 */
	public static boolean anyAlive(ArrayList<Enemy> e) {
		for(int i = 0; i < e.size(); i++) {
			if(e.get(i).hp > 0) {
				return true;
			}
		}
		return false;
	}
	public void processTreasure(Treasure t) {
		g.t = t;
		g.mode = "TREASURE";
		g.drawPanel.repaint();
		System.out.println("You have found treasure!");
		if(t.coins > 0) {
			System.out.println("You have found " + t.coins + " coins!");
			coins += t.coins;
		}
		
		for(int i = 0; i < t.items.size(); i++) {
			Item a = t.items.get(i);
			inventory.add(a);
			System.out.println("You have found a new item: " + a.name + "!");
		}
		for(int i = 0; i < t.spells.size(); i++) {
			Spell a = t.spells.get(i);
			spellbook.add(a);
			System.out.println("You have learned a new spell: " + a.name + "!");
		}
		grid[currentRow][currentColumn].cleanTile();
		System.out.print("Enter anything to continue: ");
		//s.nextLine();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
