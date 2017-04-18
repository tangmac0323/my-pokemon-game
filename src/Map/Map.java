package Map;

public abstract class Map {
	protected final MapBlock[][] map;	// store the map as grid
	private final int mapSize;
	
	public Map(){
		this.map = new MapBlock[128][128];
		this.mapSize = 128;
		this.mapGenerator();
	}
	
	// getter and setter
	public int getSize(){
		return this.mapSize;
	}
	
	
	// get the information of specific block
	public MapBlock getBlock(int row, int col){
		return map[row][col];
	}
	
	
	// TODO: GENERATE MAP
	public abstract void mapGenerator();

}

