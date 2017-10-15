package main;
/**
 * Tiled map class
 * @author Tyler Deaton
 *
 */
public class Map {
	/** Grid of tiles to form map */
	Tile[][] map;
	/** Height of map */
	int height;
	/** Width of map */
	int width;
	/** Player width location */
	int playerWidth;
	/** Player height location */
	int playerHeight;
	/** Name of map */
	String name;
	/** Name of player */
	String playerName;
	/** Level of map */
	int level;
	/** If player has visited map */
	boolean found;
	/**
	 * Default constructor
	 */
	public Map() {
		height = 10;
		width = 10;
		map = new Tile[height][width];
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				map[i][j] = new Tile();
				map[i][j].setLevel(1);
			}
		}
		name = "Default";
		populateMap("Default");
		this.level = 1;
		this.found = false;
		
	}
	/**
	 * Main constructor
	 * @param h height of map
	 * @param w width of map
	 * @param level tile level
	 * @param seed name of map
	 */
	public Map(int h, int w, int level, String seed) {
		height = h;
		width = w;
		map = new Tile[height][width];
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				map[i][j] = new Tile();
				map[i][j].setLevel(level);
			}
		}
		this.level = level;
		populateMap(seed);
		name = seed;
		this.found = false;
	}
	/**
	 * Returns height
	 * @return height
	 */
	public int height() {
		return height;
	}
	/**
	 * Returns width
	 * @return width
	 */
	public int width() {
		return width;
	}
	/**
	 * Delegates tile data call to given index
	 * @param i height
	 * @param j width
	 * @return tile data at index
	 */
	public String tileData(int i, int j) {
		return map[i][j].tileData();
	}
	/**
	 * Populates map from given seed
	 * @param seed map name preset
	 */
	public void populateMap(String seed) {
		if(seed.equals("South Woods")) { //first tile
			
			map[0][3].setTileData("^");
			map[0][2].setTileData("^");
			map[1][2].setTileData(" ");
			map[1][3].setTileData(" ");
			map[2][2].setTileData(" ");
			map[2][3].setTileData(" ");
			map[2][4].setTileData(" ");
			map[2][5].setTileData(" ");
			map[3][2].setTileData(" ");
			map[3][3].setTileData(" ");
			map[3][4].setTileData(" ");
			map[3][5].setTileData(" ");
			map[2][6].setTileData(" ");
			map[2][7].setTileData(" ");
			map[3][6].setTileData(" ");
			map[3][7].setTileData(" ");
			map[4][6].setTileData(" ");
			map[4][7].setTileData(" ");
			map[5][6].setTileData(" ");
			map[5][7].setTileData(" ");
			map[6][6].setTileData(" ");
			map[6][7].setTileData(" ");
			map[7][6].setTileData(" ");
			map[7][7].setTileData(" ");
			map[8][6].setTileData(" ");
			map[8][7].setTileData(" ");
			map[4][5].setTileData(" ");
			map[5][5].setTileData(" ");
			map[6][5].setTileData(" ");
			map[7][5].setTileData(" ");
			map[8][5].setTileData(" ");
			map[4][3].setTileData("Y");
			map[4][4].setTileData("Y");
			map[5][3].setTileData("Y");
			map[5][4].setTileData("Y");
			map[4][2].setTileData("Y");
			map[5][2].setTileData("Y");
			map[4][8].setTileData("C");
			String sally = "Sally says:\nYou seem new around here! Don't stray out too far in the wild\nwithout visiting the shop for some equipment first!";
			map[4][8].setMessage(sally);
			map[7][4].setTileData("C");
			String frank = "Frank says:\nBe careful wandering in that grass field over there.\nMonsters could give ya the jump!";
			map[7][4].setMessage(frank);
			
			//player
			
			setPlayer(7, 6);
		}
		if(seed.equals("North Woods")) {
			
			map[9][3].setTileData("v");
			map[9][2].setTileData("v");
			
			map[8][3].setTileData(" ");
			map[8][2].setTileData(" ");
			map[8][1].setTileData(" ");
			map[7][3].setTileData(" ");
			map[7][2].setTileData(" ");
			map[7][1].setTileData(" ");
			map[6][3].setTileData("Y");
			map[6][2].setTileData("Y");
			map[6][1].setTileData("Y");
			map[8][4].setTileData(" ");
			map[7][4].setTileData(" ");
			map[7][5].setTileData(" ");
			map[7][6].setTileData(" ");
			map[7][7].setTileData(" ");
			map[8][5].setTileData(" ");
			map[8][6].setTileData(" ");
			map[8][7].setTileData(" ");
			map[7][8].setTileData(" ");
			map[8][8].setTileData(" ");
			map[7][9].setTileData(">");
			map[8][9].setTileData(">");

			map[6][5].setTileData("$");
			map[5][3].setTileData("Y");
			map[5][2].setTileData("Y");
			map[5][1].setTileData("Y");
			map[4][2].setTileData("Y");
			map[4][3].setTileData("Y");
			map[3][3].setTileData("Y");
			map[4][4].setTileData(" ");
			map[4][5].setTileData(" ");
			map[3][4].setTileData(" ");
			map[3][5].setTileData(" ");
			map[3][6].setTileData(" ");
			map[4][6].setTileData(" ");
			map[2][6].setTileData(" ");
			map[3][7].setTileData(" ");
			map[4][7].setTileData(" ");
			map[2][7].setTileData(" ");
			map[3][8].setTileData(" ");
			map[4][8].setTileData(" ");
			
			map[4][9].setTileData(">");
			map[3][9].setTileData(">");
			
			
			
			//setPlayer(9, 3);
			
		}
		if(seed.equals("Bridge to Townshire")) {
			map[4][0].setTileData("<");
			map[3][0].setTileData("<");
			
			map[4][1].setTileData(" ");
			map[3][1].setTileData(" ");
			map[2][1].setTileData(" ");
			map[4][2].setTileData("Y");
			map[3][2].setTileData("Y");
			map[2][2].setTileData("Y");
			map[4][3].setTileData(" ");
			map[3][3].setTileData(" ");
			map[2][3].setTileData(" ");
			map[4][4].setTileData(" ");
			map[3][4].setTileData(" ");
			map[2][4].setTileData(" ");
			map[7][0].setTileData("<");
			map[8][0].setTileData("<");
			map[7][1].setTileData(" ");
			map[8][1].setTileData(" ");
			map[7][2].setTileData(" ");
			map[8][2].setTileData(" ");
			map[7][3].setTileData(" ");
			map[8][3].setTileData(" ");
			map[6][2].setTileData("Y");
			map[6][1].setTileData("Y");
			map[6][3].setTileData("Y");
			map[7][4].setTileData(" ");
			map[8][4].setTileData(" ");
			map[9][3].setTileData("v");
			map[9][4].setTileData("v");
			map[3][9].setTileData(">");
			map[2][9].setTileData(">");
			for(int i = 0; i <= 9; i++) {
				map[i][5].setTileData("W");
				map[i][6].setTileData("W");
				map[i][7].setTileData("W");
				
				
			}
			for(int i = 2; i <= 4; i++) {
				map[i][5].setTileData("_");
				map[i][6].setTileData("_");
				map[i][7].setTileData("_");
				
				
			}
			map[4][8].setTileData(" ");
			map[3][8].setTileData(" ");
			map[2][8].setTileData(" ");
			//setPlayer(4, 0);
		}
		if(seed.equals("Woodland Bank")) {
			map[0][3].setTileData("^");
			map[0][4].setTileData("^");
			map[1][3].setTileData(" ");
			map[1][4].setTileData(" ");
			map[0][5].setTileData("W");
			map[0][6].setTileData("W");
			map[0][7].setTileData("W");
			map[1][5].setTileData("W");
			map[1][6].setTileData("W");
			map[1][7].setTileData("W");
			map[2][6].setTileData("W");
			map[2][7].setTileData("W");
			map[2][8].setTileData("W");
			map[3][6].setTileData("W");
			map[3][7].setTileData("W");
			map[3][8].setTileData("W");
			map[4][9].setTileData("W");
			map[4][7].setTileData("W");
			map[4][8].setTileData("W");
			map[5][9].setTileData("W");
			map[6][9].setTileData("W");
			map[5][8].setTileData("W");
			map[2][3].setTileData(" ");
			map[2][4].setTileData(" ");
			map[3][3].setTileData(" ");
			map[3][4].setTileData(" ");
			map[3][2].setTileData(" ");
			map[2][2].setTileData(" ");
			map[4][2].setTileData(" ");
			map[4][3].setTileData(" ");
			map[5][1].setTileData("Y");
			map[5][2].setTileData("Y");
			map[5][3].setTileData("Y");
			map[5][4].setTileData("Y");
			map[6][1].setTileData("Y");
			map[6][2].setTileData("Y");
			map[6][3].setTileData("Y");
			map[6][4].setTileData("Y");
			map[7][5].setTileData("Y");
			map[7][2].setTileData("Y");
			map[7][3].setTileData("Y");
			map[7][4].setTileData("Y");
			map[7][6].setTileData(" ");
			map[8][5].setTileData(" ");
			map[8][6].setTileData(" ");
			map[7][7].setTileData(" ");
			map[8][7].setTileData(" ");
			map[7][8].setTileData(" ");
			map[8][8].setTileData(" ");
			map[7][9].setTileData(">");
			map[8][9].setTileData(">");
		}
		if(seed.equals("South Fields")) {
			map[7][0].setTileData("<");
			map[8][0].setTileData("<");
			map[6][0].setTileData("W");
			map[5][0].setTileData("W");
			map[6][1].setTileData("W");
			map[5][1].setTileData("W");
			map[6][2].setTileData("W");
			map[5][2].setTileData("W");
			map[6][3].setTileData("W");
			
			map[7][1].setTileData(" ");
			map[8][1].setTileData(" ");
			map[7][2].setTileData(" ");
			map[8][2].setTileData(" ");
			map[7][3].setTileData(" ");
			map[8][3].setTileData(" ");
			map[5][3].setTileData(" ");
			map[7][4].setTileData(" ");
			map[8][4].setTileData(" ");
			map[5][4].setTileData(" ");
			map[6][4].setTileData(" ");
			map[7][5].setTileData("Y");
			map[8][5].setTileData("Y");
			map[5][5].setTileData("Y");
			map[6][5].setTileData("Y");
			map[4][5].setTileData("Y");
			map[3][5].setTileData("Y");
			map[4][4].setTileData("Y");
			map[3][4].setTileData("Y");
			map[4][3].setTileData("Y");
			map[3][3].setTileData("Y");
			map[4][2].setTileData("Y");
			map[3][2].setTileData("Y");
			map[2][3].setTileData("Y");
			map[1][3].setTileData("Y");
			map[2][2].setTileData("Y");
			map[1][2].setTileData("Y");
			map[2][1].setTileData("Y");
			map[3][1].setTileData("Y");
			
			map[7][6].setTileData(" ");
			map[6][6].setTileData(" ");
			map[5][6].setTileData(" ");
			map[7][7].setTileData(" ");
			map[6][7].setTileData(" ");
			map[5][7].setTileData(" ");
			map[4][7].setTileData(" ");
			map[3][7].setTileData(" ");
			map[2][7].setTileData(" ");
			map[1][7].setTileData(" ");
			map[0][7].setTileData("^");
			map[3][8].setTileData(" ");
			map[2][8].setTileData(" ");
			map[1][8].setTileData(" ");
			map[0][8].setTileData("^");
			map[6][8].setTileData(" ");
			map[5][8].setTileData(" ");
			map[4][8].setTileData(" ");
			
			map[1][1].setTileData("T");
			Treasure t11 = new Treasure(50);
			map[1][1].setTreasure(t11);
		}
		if(seed.equals("Townshire")) {
			map[9][8].setTileData("v");
			map[9][7].setTileData("v");
			map[8][8].setTileData(" ");
			map[8][7].setTileData(" ");
			map[2][0].setTileData("<");
			map[3][0].setTileData("<");
			map[2][1].setTileData(" ");
			map[3][1].setTileData(" ");
			map[1][1].setTileData(" ");
			map[4][1].setTileData(" ");
			map[2][2].setTileData(" ");
			map[3][2].setTileData(" ");
			map[1][2].setTileData(" ");
			map[4][2].setTileData(" ");
			map[5][1].setTileData(" ");
			map[6][1].setTileData(" ");
			map[7][1].setTileData(" ");
			map[8][1].setTileData(" ");
			map[5][2].setTileData(" ");
			map[6][2].setTileData(" ");
			map[7][2].setTileData(" ");
			map[8][2].setTileData(" ");
			map[7][3].setTileData(" ");
			map[8][3].setTileData(" ");
			map[7][4].setTileData(" ");
			map[8][4].setTileData(" ");
			map[7][5].setTileData(" ");
			map[8][5].setTileData(" ");
			map[7][6].setTileData(" ");
			map[8][6].setTileData(" ");
			map[7][7].setTileData(" ");
			map[8][7].setTileData(" ");
			map[7][8].setTileData(" ");
			map[8][8].setTileData(" ");
		}
		
		
		
		
	}
	/**
	 * Sets initial player starting location
	 * @param i height
	 * @param j width
	 */
	public void setPlayer(int i, int j) {
		playerWidth = j;
		playerHeight = i;
		map[i][j].setTempData("P");
		
	}
	/**
	 * Moves the player in direction, shifting maps if necessary
	 * @param direction wasd direction
	 * @return direction string if map shift is needed
	 */
	public String movePlayer(String direction) {
		if(direction.equals("W")) {
			if(map[playerHeight][playerWidth].normData().equals("^")) {
				//move maps
				map[playerHeight][playerWidth].restore();
				return "NORTH";
			}
			else if(checkMove(playerHeight - 1, playerWidth)) {
				
				map[playerHeight][playerWidth].restore();
				playerHeight--;
				map[playerHeight][playerWidth].setTempData("P");
				return "";
			}
			
		}
		else if(direction.equals("A")) {
			if(map[playerHeight][playerWidth].normData().equals("<")) {
				//move maps
				map[playerHeight][playerWidth].restore();
				return "WEST";
			}
			else if(checkMove(playerHeight, playerWidth - 1)) {
				map[playerHeight][playerWidth].restore();
				playerWidth--;
				map[playerHeight][playerWidth].setTempData("P");
				return "";
			}
		}
		else if(direction.equals("D")) {
			if(map[playerHeight][playerWidth].normData().equals(">")) {
				//move maps
				map[playerHeight][playerWidth].restore();
				return "EAST";
			}
			else if(checkMove(playerHeight, playerWidth + 1)) {
				map[playerHeight][playerWidth].restore();
				playerWidth++;
				map[playerHeight][playerWidth].setTempData("P");
				return "";
			}
		}
		else if(direction.equals("S")) {
			if(map[playerHeight][playerWidth].normData().equals("v")) {
				//move maps
				map[playerHeight][playerWidth].restore();
				return "SOUTH";
			}
			else if(checkMove(playerHeight + 1, playerWidth)) {
				map[playerHeight][playerWidth].restore();
				playerHeight++;
				map[playerHeight][playerWidth].setTempData("P");
				return "";
			}
		}
		return "";
	}
	/**
	 * Checks to see if a move is valid
	 * @param height height
	 * @param width width
	 * @return if move is valid
	 */
	public boolean checkMove(int height, int width) {
		if(height > 9 || height < 0 || width < 0 || width > 9) {
			return false;
		}
		if(map[height][width].validMove()) {
			return true;
		}
		return false;
	}
	/**
	 * Delegates tile check event at player location
	 * @return tile event at player
	 */
	public Event checkEvent() {
		return map[playerHeight][playerWidth].checkEvent();
	}
	/**
	 * Returns map name
	 * @return map name
	 */
	public String name() {
		return name;
	}
	/**
	 * Delegates cleaning to current player tile
	 */
	public void cleanTile() {
		map[playerHeight][playerWidth].cleanTile();
	}
	public int getLevel() {
		return level;
	}
	public String printRow(int row) {
		String ret = "";
		for(int i = 0; i < height; i++) {
			String td = map[row][i].tileData();
			if(td == null) {
				td = "/";
			}
			if(td.equals("^") || td.equals("v") || td.equals("<") || td.equals(">")) {
				td = " ";
			}
			if(found == false) {
				td = "?";
			}
			ret += "[" + td + "]";
		}
		return ret;
	}

}
