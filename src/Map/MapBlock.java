/*
 * Author: Mengtao Tang
 * Date: 4/20/2017
 * Course: CSC_335
 * Purpose: This is an class define the information of each
 * 			map block in the map
 * 
 */

package Map;

public class MapBlock {
	
	private final GroundType ground;
	private final ObstacleType obstacle;
	
	// constructor
	public MapBlock(GroundType groundType, ObstacleType obstacleType){
		this.ground = groundType;
		this.obstacle = obstacleType;
	}

}
