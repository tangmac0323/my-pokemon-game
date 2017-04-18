package Map;

public class Map_BottomLeft extends Map{

	@Override
	public void mapGenerator() {
		// TODO: basic ground type
		this.map[0][1] = new MapBlock(GroundType.GRASSLAND);
		
		
		// TODO: obstacle
		this.map[0][1].setObstacle(ObstacleType.TREE);
		
		
		// TODO: passable
		
	}

}
