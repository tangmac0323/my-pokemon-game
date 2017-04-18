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
	private final PassableType passType;
	//private final boolean passable;
	
	// constructor
	public MapBlock(GroundType groundType, ObstacleType obstacleType, PassableType passType){
		this.ground = groundType;
		this.obstacle = obstacleType;
		this.passType = passType;
	}
	
	// getter
	public GroundType getGround(){
		return this.ground;
	}
	
	public ObstacleType getObstacle(){
		return this.obstacle;
	}
	
	public PassableType getPassType(){
		return this.passType;
	}
	
	
	// get passable status
	public boolean isPassable(){
		if (this.obstacle == ObstacleType.NONE){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean canMeetPokemon(){
		if (this.passType != PassableType.AIR){
			return true;
		}
		else{
			return false;
		}
	}
	
}
