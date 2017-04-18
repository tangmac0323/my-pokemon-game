package Map;

import java.awt.Point;

public class Map_BottomLeft extends Map{
	private Point topPortal;
	private Point rightPortal;

	@Override
	public void mapGenerator() {
		// TODO: basic ground type
		this.map[0][1] = new MapBlock(GroundType.GRASSLAND);
		
		
		// TODO: obstacle
		this.map[0][1].setObstacle(ObstacleType.TREE);
		
		
		// TODO: passable
		
	}
	
	public Point getTopPortal(){
		return this.topPortal;
	}
	
	public Point getRightPortal(){
		return this.rightPortal;
	}

}
