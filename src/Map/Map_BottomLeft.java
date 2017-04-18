package Map;

public class Map_BottomLeft extends Map{

	@Override
	public void mapGenerator() {
		// TODO Auto-generated method stub
		this.map[0][1] = new MapBlock(GroundType.GRASSLAND, ObstacleType.TREE, PassableType.SHORTGRASS);
		
	}

}
