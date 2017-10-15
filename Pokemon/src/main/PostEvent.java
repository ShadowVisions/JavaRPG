package main;

public class PostEvent {
	String type;
	int mapRow;
	int mapCol;
	int gridRow;
	int gridCol;
	
	public PostEvent(String type) {
		this.type = type;
	}
	public void setKey(int mapRow, int mapCol, int gridRow, int gridCol) {
		this.mapRow = mapRow;
		this.mapCol = mapCol;
		this.gridRow = gridRow;
		this.gridCol = gridCol;
	}

}
