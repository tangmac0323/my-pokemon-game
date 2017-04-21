/*
 * Author: Mengtao Tang
 * Date: 4/20/2017
 * Course: CSC_335
 * Purpose: This is an class define the information of each
 * 			map block in the map
 * 
 */

package Map;

import java.io.Serializable;

public class MapBlock implements Serializable{
	

	private static final long serialVersionUID = -4085092152859549598L;
	
	private final GroundType ground;
	private ObstacleType obstacle;
	private PassableType passType;
	//private final boolean passable;
	
	// constructor
	public MapBlock(GroundType groundType){
		this.ground = groundType;
		this.obstacle = ObstacleType.NONE;
		this.passType = PassableType.AIR;
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
	
	public void setObstacle(ObstacleType ob){
		this.obstacle = ob;
	}
	
	
	/*
	public void setPassable(PassableType ps){
		this.passType = ps;
	}
	
		
	public boolean canMeetPokemon(){
		if (this.passType != PassableType.AIR && this.obstacle == ObstacleType.NONE){
			return true;
		}
		else{
			return false;
		}
	}
	*/
	
	// get passable status
	public boolean isPassable(){
		if (this.obstacle == ObstacleType.NONE){
			return true;
		}
		else{
			return false;
		}
	}
}
