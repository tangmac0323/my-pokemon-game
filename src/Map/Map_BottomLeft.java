package Map;

import java.awt.Point;

public class Map_BottomLeft extends Map{
	private Point topPortal;
	private Point rightPortal;

	@Override
	public void mapGenerator() {
		// TODO: basic ground type
		for (int i = 0; i < this.getSize(); i ++){
			for (int j = 0; j < this.getSize(); j ++){
				if (Math.random() > 0.5){
					this.map[i][j] = new MapBlock(GroundType.GRASSLAND);
				}
				else{
					this.map[i][j] = new MapBlock(GroundType.SAND);
				}
			}
		}
		
		// TODO: obstacle
		for (int i = 10; i < this.getSize() - 10; i ++){
			for (int j = 10; j < this.getSize() - 10; j ++){
				if (Math.random() > 0.95){
					this.map[i][j].setObstacle(ObstacleType.ROCK);
				}
				else if (Math.random() < 0.1){
					this.map[i][j].setObstacle(ObstacleType.TREE);
				}
				else{
					// TO Nothing
				}
			}
		}		
		
		// TODO: passable
		
	}
	
	public Point getTopPortal(){
		return this.topPortal;
	}
	
	public Point getRightPortal(){
		return this.rightPortal;
	}

}
