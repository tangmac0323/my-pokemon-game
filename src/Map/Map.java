package Map;

public abstract class Map {
	protected final MapBlock[][] map;	// store the map as grid
	private final int mapSize;
	
	public Map(){
		this.map = new MapBlock[129][129];
		this.mapSize = 129;
		this.mapGenerator();
	}
	
	// getter and setter
	public int getSize(){
		return this.mapSize;
	}
	
	
	// get the information of specific block
	public MapBlock getBlock(int x, int y){
		return map[y][x];
	}
	
	
	// TODO: GENERATE MAP
	public abstract void mapGenerator();
	
	public void printMap(){
		for (int i = 0; i < 129; i ++){
			for (int j = 0; j < 129; j++){
				if (map[j][i].getObstacle() == ObstacleType.ROCK){
					System.out.print("R ");
				}
				else if (map[j][i].getObstacle() == ObstacleType.TREE){
					System.out.print("T ");
				}
				else if (map[j][i].getGround() == GroundType.GRASSLAND){
					System.out.print("G ");
				}
				else if (map[j][i].getGround() == GroundType.SAND){
					System.out.print("S ");
				}
			}
			System.out.println("");
		}
	}

}

