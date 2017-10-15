package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class GUI extends JFrame {
    int x = 0;
    int y = 0;
    Main m;
    String mode;
    JLabel jlab;
    JComboBox jcom;
    ArrayList<Item> shop;
    int shopSelected;
    boolean justPurchased;
    String[] battleStats;
    String battleTurn;
    String recentDamage;
    String recentAttack;
    Treasure t;
    String msg;
    String[] encounterIntro;
    String[][] encounterData;
    int ensize;

    public DrawPanel drawPanel = new DrawPanel();

    public GUI(){
    	
        Action rightAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                x +=10;
                m.command = "D";
                drawPanel.repaint();
            }
        };
        Action leftAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	m.command = "A";
                x -=10;
                drawPanel.repaint();
            }
        };
        Action upAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	if(mode.equals("MAP")) {
            		m.command = "W";
            	}
            	if(mode.equals("SHOP") || mode.equals("SPELLC") || mode.equals("INVEN") || mode.equals("SPELLB") || mode.equals("ENCOUNTER")) {
            		if(shopSelected > 0) {
            			shopSelected--;
            		}
            		
            	}
                x -=10;
                drawPanel.repaint();
            }
        };
        Action downAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	if(mode.equals("MAP")) {
            		m.command = "S";
            	}
            	if(mode.equals("SHOP")) {
            		if(shopSelected < shop.size() - 1) {
            			shopSelected++;
            		}
            		
            	}
            	if(mode.equals("SPELLC")) {
            		if(shopSelected < m.spellbook.size() - 1) {
            			shopSelected++;
            		}
            		
            	}
            	if(mode.equals("INVEN")) {
            		if(shopSelected < m.inventory.size() - 1) {
            			shopSelected++;
            		}
            		
            	}
            	if(mode.equals("SPELLB")) {
            		if(shopSelected < m.spellbook.size() - 1) {
            			shopSelected++;
            		}
            		
            	}
            	if(mode.equals("ENCOUNTER")) {
            		if(shopSelected < ensize - 1) {
            			shopSelected++;
            		}
            		
            	}
                x -=10;
                drawPanel.repaint();
            }
        };
        Action invAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	m.command = "I";
                x -=10;
                drawPanel.repaint();
            }
        };
        Action spAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	m.command = "B";
                x -=10;
                drawPanel.repaint();
            }
        };
        Action enAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	if(mode.equals("SHOP") || mode.equals("SPELLC")) {
            		m.shopSelected = shopSelected;
            	}
                x -=10;
                drawPanel.repaint();
            }
        };
        Action atAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	if(mode.equals("BATTLE")) {
            		m.battleInput = "A";
            	}
            	if(mode.equals("ENCOUNTER")) {
            		m.shopSelected = shopSelected;
            		m.encounterMode = "A";
            	}
                x -=10;
                drawPanel.repaint();
            }
        };
        Action flAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	if(mode.equals("BATTLE")) {
            		m.battleInput = "F";
            	}
            	if(mode.equals("ENCOUNTER")) {
            		m.encounterMode = "F";
            		m.shopSelected = -2;
            	}
                x -=10;
                drawPanel.repaint();
            }
        };
        Action speAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	if(mode.equals("BATTLE")) {
            		m.battleInput = "S";
            	}
            	if(mode.equals("INVEN")) {
            		m.itemSell = true;
            		m.shopSelected = shopSelected;
            	}
            	if(mode.equals("ENCOUNTER")) {
            		m.encounterMode = "S";
            		m.shopSelected = shopSelected;
            	}
                x -=10;
                drawPanel.repaint();
            }
        };
        Action upgAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	
            	if(mode.equals("INVEN")) {
            		m.itemUpgrade = true;
            		m.shopSelected = shopSelected;
            	}
            	if(mode.equals("SPELLB")) {
            		m.itemUpgrade = true;
            		m.shopSelected = shopSelected;
            	}
                x -=10;
                drawPanel.repaint();
            }
        };
        Action quAction = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	if(mode.equals("MAP")) {
            		m.command = "Q";
            	}
            	if(mode.equals("SHOP")) {
            		m.shopSelected = -2;
            	}
            	if(mode.equals("INVEN")) {
            		m.shopSelected = -2;
            	}
            	if(mode.equals("SPELLB")) {
            		m.shopSelected = -2;
            	}
                x -=10;
                drawPanel.repaint();
            }
        };

            InputMap inputMap = drawPanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = drawPanel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        actionMap.put("rightAction", rightAction);
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        actionMap.put("leftAction", leftAction);
        inputMap.put(KeyStroke.getKeyStroke("UP"), "upAction");
        actionMap.put("upAction", upAction);
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        actionMap.put("downAction", downAction);
        inputMap.put(KeyStroke.getKeyStroke("I"), "invAction");
        actionMap.put("invAction", invAction);
        inputMap.put(KeyStroke.getKeyStroke("B"), "spAction");
        actionMap.put("spAction", spAction);
        inputMap.put(KeyStroke.getKeyStroke("Q"), "quAction");
        actionMap.put("quAction", quAction);
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "enAction");
        actionMap.put("enAction", enAction);
        inputMap.put(KeyStroke.getKeyStroke("A"), "atAction");
        actionMap.put("atAction", atAction);
        inputMap.put(KeyStroke.getKeyStroke("F"), "flAction");
        actionMap.put("flAction", flAction);
        inputMap.put(KeyStroke.getKeyStroke("S"), "speAction");
        actionMap.put("speAction", speAction);
        inputMap.put(KeyStroke.getKeyStroke("U"), "upgAction");
        actionMap.put("upgAction", upgAction);
        
        setLayout(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        b.gridx = 0;
        b.gridy = 0;
        b.gridwidth = 800;
        b.gridheight = 500;
        add(drawPanel, b);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 800;
        c.gridy = 0;
        c.gridwidth = 250;
        c.gridheight = 450;
        jlab = new JLabel();
        Dimension di = new Dimension(300, 450);
        jlab.setPreferredSize(di);
        //add(jlab, c);
        GridBagConstraints d = new GridBagConstraints();
        d.gridx = 800;
        d.gridy = 450;
        d.gridwidth = 250;
        d.gridy = 50;
        jcom = new JComboBox();
        Dimension di2 = new Dimension(300, 50);
        jcom.setPreferredSize(di2);
        //add(jcom, d);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void setMain(Main m) {
    	this.m = m;
    }

    public class DrawPanel extends JPanel {


        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(mode.equals("MAP")) {
            	drawMap(g, m.grid[m.currentRow][m.currentColumn]);
                drawPlayer(g, m.grid[m.currentRow][m.currentColumn]);
                g.drawLine(500, 0, 500, 500);
            }
            if(mode.equals("SHOP")) {
            	g.drawString("You have reached the shop. Your funds: " + m.coins, 50, 50);
            	if(shop != null) {
            		g.drawString("Item Name", 50, 100);
            		g.drawString("Item Type", 200, 100);
            		g.drawString("Item Cost", 300, 100);
            		g.drawString("Use up and down arrows to select item and enter to choose, and Q to leave shop!" , 50, 450);
            		g.drawLine(0, 105, 800, 105);
            		g.setColor(Color.WHITE);
            		g.fillRect(0, 125 + (50 * shopSelected), 800, 50);
            		g.setColor(Color.BLACK);
            		
            		for(int i = 0; i < shop.size(); i++) {
            			g.drawString(shop.get(i).name, 50, (50 * i) + 150);
            			g.drawString(shop.get(i).type, 200, (50 * i) + 150);
            			g.drawString("" + shop.get(i).cost, 300, (50 * i) + 150);
            		}
            	}
            }
            if(mode.equals("SPELLC")) {
            	g.drawString("Choose a spell. Your mana: " + m.pMana, 50, 50);
            	if(m.spellbook != null) {
            		g.drawString("Spell Name", 50, 100);
            		g.drawString("Spell Type", 200, 100);
            		g.drawString("Spell Power", 400, 100);
            		g.drawString("Spell Mana", 500, 100);
            		g.drawString("Use up and down arrows to select item and enter to choose, and Q to leave shop!" , 50, 450);
            		g.drawLine(0, 105, 800, 105);
            		g.setColor(Color.WHITE);
            		g.fillRect(0, 125 + (50 * shopSelected), 800, 50);
            		g.setColor(Color.BLACK);
            		
            		for(int i = 0; i < m.spellbook.size(); i++) {
            			g.drawString(m.spellbook.get(i).name, 50, (50 * i) + 150);
            			g.drawString(m.spellbook.get(i).getDamageType(), 200, (50 * i) + 150);
            			g.drawString("" + m.spellbook.get(i).cost, 400, (50 * i) + 150);
            			g.drawString("" + m.spellbook.get(i).mana, 500, (50 * i) + 150);
            		}
            	}
            }
            if(mode.equals("INVEN")) {
            	g.drawString("Current funds: " + m.coins, 50, 30);
            	g.drawString("Current inventory: ", 50, 60);
            	if(m.inventory != null) {
            		g.drawString("Item Name", 50, 100);
            		g.drawString("Item Type", 200, 100);
            		g.drawString("Item Cost", 300, 100);
            		g.drawString("Use up and down arrows to select item and S to sell, U to upgrade, and Q to exit!" , 50, 450);
            		g.drawLine(0, 105, 800, 105);
            		g.setColor(Color.WHITE);
            		g.fillRect(0, 125 + (50 * shopSelected), 500, 50);
            		g.setColor(Color.BLACK);
            		if(m.inventory.size() > 0) {
            			Item it = m.inventory.get(shopSelected);
                		g.drawString("Selected Item Stats: " + it.name, 550, 50);
                		g.drawString("Item Level: " + it.level, 550, 100);
                		g.drawString("Item Attack: " + it.atk, 550, 150);
                		g.drawString("Item Defense: " + it.def, 550, 200);
                		g.drawString("Item Health: " + it.hp, 550, 250);
                		g.drawString("Item Mana: " + it.mana, 550, 300);
                		g.drawString("Item sell worth: " + ((int) (it.cost * .7)), 550, 350);
                		int x = it.getUpgradeCost();
                		if(x == 0) {
                			g.drawString("Item at max upgrades!", 550, 400);
                		} else {
                			g.drawString("Item upgrades for " + x + ".", 550, 400);
                		}
            		}
            		
            		
            		
            		
            		for(int i = 0; i < m.inventory.size(); i++) {
            			g.drawString(m.inventory.get(i).name, 50, (50 * i) + 150);
            			g.drawString(m.inventory.get(i).type, 200, (50 * i) + 150);
            			g.drawString("" + m.inventory.get(i).cost, 300, (50 * i) + 150);
            		}
            	}
            }
            if(mode.equals("SPELLB")) {
            	g.drawString("Current funds: " + m.coins, 50, 30);
            	g.drawString("Current spellbook: ", 50, 60);
            	if(m.spellbook != null) {
            		g.drawString("Spell Name", 50, 100);
            		g.drawString("Spell Level", 200, 100);
            		g.drawString("Spell Type", 300, 100);
            		g.drawString("Spell Power", 400, 100);
            		g.drawString("Spell Mana", 500, 100);
            		g.drawString("Spell Upgrade Cost", 600, 100);
            		g.drawString("Use up and down arrows to select item, U to upgrade, and Q to exit!" , 50, 450);
            		g.drawLine(0, 105, 800, 105);
            		g.setColor(Color.WHITE);
            		g.fillRect(0, 125 + (50 * shopSelected), 800, 50);
            		g.setColor(Color.BLACK);
            		
            	
            		for(int i = 0; i < m.spellbook.size(); i++) {
            			g.drawString(m.spellbook.get(i).name, 50, (50 * i) + 150);
            			g.drawString("" + m.spellbook.get(i).level, 200, (50 * i) + 150);
            			g.drawString(m.spellbook.get(i).getDamageType(), 300, (50 * i) + 150);
            			g.drawString("" + m.spellbook.get(i).damage, 400, (50 * i) + 150);
            			g.drawString("" + m.spellbook.get(i).cost, 500, (50 * i) + 150);
            			if(m.spellbook.get(i).getUpgradeCost() > 0) {
            				g.drawString("" + m.spellbook.get(i).getUpgradeCost(), 600, (50 * i) + 150);
            			} else {
            				g.drawString("MAX", 600, (50 * i) + 150);
            			}
            		}
            	}
            }
            if(mode.equals("BATTLE")) {
            	drawBattle(g);
            }
            if(mode.equals("ENCOUNTER")) {
            	drawEncounter(g);
            }
            if(mode.equals("TREASURE") && t != null) {
            	g.drawString("You have found treasure!", 50, 50);
            	if(t.coins > 0) {
            		g.drawString("You have found " + t.coins + " coins!", 50, 100);
            	}
            	for(int i = 0; i < t.items.size(); i++) {
        			Item a = t.items.get(i);
        			//inventory.add(a);
        			g.drawString("You have found a new item: " + a.name + "!", 50 + (50 * i), 150);
        		}
            	for(int i = 0; i < t.spells.size(); i++) {
        			Spell a = t.spells.get(i);
        			//spellbook.add(a);
        			g.drawString("You have learned a new spell: " + a.name + "!", 50 + (50 * i), 250);
        		}
            }
            if(mode.equals("MSG")) {
            	g.drawString(msg, 50, 50);
            }
            
            
           
            //g.setColor(Color.GREEN);
            //g.fillRect(x, y, 50, 50);
        }

        public Dimension getPreferredSize() {
            return new Dimension(800, 500);
        }
    }
    public void drawMap(Graphics g, Map ma) {
    	 g.setColor(Color.GRAY);
         g.fillRect(0, 0, getWidth(), getHeight());
         for(int i = 0; i < 10; i++) {
        	 for(int j = 0; j < 10; j++) {
        		 Color c = ma.map[j][i].tileColor();
        		 g.setColor(c);
        		 g.fillRect(50 * i, 50 * j, 50, 50);
        	 }
         }
         //g.setFont(new Font(Font.SERIF, 1, 12));
         g.setColor(Color.BLACK);
         g.drawString("Name: " + m.name, 550, 60);
         g.drawString("Level: " + m.level, 550, 110);
         g.drawString("Coins: " + m.coins, 550, 160);
         g.drawString("Attack: " + m.pAtk, 550, 210);
         g.drawString("Defense: " + m.pDefense, 550, 260);
         g.drawString("Health: " + m.pHp, 550, 310);
         g.drawString("Area Name: " + m.grid[m.currentRow][m.currentColumn].name(), 550, 360);
         g.drawString("Area Level: " + m.grid[m.currentRow][m.currentColumn].level, 550, 410);
    }
    public void drawPlayer(Graphics g, Map m) {
    	g.setColor(Color.BLACK);
    	int w = m.playerHeight;
    	int h = m.playerWidth;
    	g.fillRect((50 * h) + 10, (50 * w) + 10, 30, 30);
    }
    public void drawBattle(Graphics g) {
    	g.drawString(battleStats[7], 50, 50);
    	g.drawString(battleStats[0], 50, 100);
    	g.drawString(battleStats[1], 50, 150);
    	g.drawString(battleStats[2], 50, 200);
    	g.drawString(battleStats[3], 50, 250);
    	g.drawString(battleStats[4], 200, 100);
    	g.drawString(battleStats[5], 200, 150);
    	g.drawString(battleStats[6], 200, 200);
    	if(battleTurn.equals("PLAYER")) {
    		g.drawString("It is your turn to act! Press A to attack, S to choose a spell, or F to flee!", 50, 400);
    	}
    	if(recentDamage != null) {
    		g.drawString(recentDamage, 50, 350);
    	}
    	if(recentAttack != null) {
    		g.drawString(recentAttack, 50, 300);
    	}
    }
    public void drawEncounter(Graphics g) {
    	g.setColor(Color.WHITE);
		g.fillRect(0, 125 + (50 * shopSelected), 800, 50);
		g.setColor(Color.BLACK);
    	for(int i = 0; i < encounterIntro.length; i++) {
    		g.drawString(encounterIntro[i], 50, 50 + (50 * i));
    	}
    	for(int i = 0; i < encounterData.length; i++) {
    		for(int j = 0; j < 3; j++) {
    			g.drawString(encounterData[i][j], 50 + (100 * j), 150 + (50 * i));
    		}
    		if(i == 0) {
    			g.drawString(encounterData[i][3], 50, 300);
    		}
    	}
    	
    	if(battleTurn.equals("PLAYER")) {
    		g.drawString("It is your turn to act! Use up and down to select a target and press A to attack, S to choose a spell, or F to flee!", 50, 400);
    	}
    	if(recentDamage != null) {
    		g.drawString(recentDamage, 50, 350);
    	}
    	if(recentAttack != null) {
    		g.drawString(recentAttack, 50, 300);
    	}
    }

    
}