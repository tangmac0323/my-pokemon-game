package Map;

public class Map {
	private final MapBlock[][] map;	// store the map as grid
	private final int mapSize;
	
	public Map(){
		this.map = new MapBlock[129][129];
		this.mapSize = 129;
	}
	
	// getter and setter
	public int getSize(){
		return this.mapSize;
	}
	
	
	// get the information of specific block
	public MapBlock getBlock(int row, int col){
		return map[row][col];
	}
}

