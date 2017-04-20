package GameModel;

import java.awt.Point;
import java.io.Serializable;
import java.util.Observable;

import Map.*;
import Mission.*;
import Trainer.*;

public class GameModel extends Observable implements Serializable{

	private static final long serialVersionUID = -7756945750821100840L;
	
	// trainer information
	private Trainer curTrainer;
	private int xCoords;
	private int yCoords;
	
	// map information
	private Map_BottomLeft map_BL;
	private Map_BottomRight map_BR;
	private Map_TopLeft map_TL;
	private Map_TopRight map_TR;
	private Map curMap;
	private static final int BlockPixel = 16;
	
	// game information
	private Mission mission;
	private boolean isEnd = true;
	private boolean isWin = false;
	
	public GameModel(){
		initiateMap();
		curTrainer = new Trainer("tmt");
		setLocation(65, 65);
		setCurMap(map_BL);
	}
		
	public void setTrainer(Trainer trainer){
		this.curTrainer = trainer;
	}
	
	public void setCurMap(Map map){
		this.curMap = map;
	}
	
	public void chooseMap(int i){
		if (i == 0){
			curMap = map_BL;
		}
		else if (i == 1){
			curMap = map_BR;
		}
		else if (i == 2){
			curMap = map_TL;
		}
		else{
			curMap = map_TR;
		}
	}
	
	public void setMission(Mission mission){
		this.mission = mission;
	}
	
	public Mission getMission(){
		return this.mission;
	}
	
	/*
	public int getPixel(){
		return this.BlockPixel;
	}
	*/
		
	// get the location of the trainer
	public Point getLocation(){
		Point p = new Point();
		p.setLocation(xCoords, yCoords);
		return p;
	}
	
	// get the current map of the game
	public Map getCurMap(){
		return curMap;
	}
	
	// initiate the map
	private void initiateMap(){
		map_BL = new Map_BottomLeft();
		map_BR = new Map_BottomRight();
		map_TL = new Map_TopLeft();
		map_TR = new Map_TopRight();
	}	
	
	// set the coordinate of the trainer
	private void setLocation(int x, int y){
		this.xCoords = x;
		this.yCoords = y;
	}
	
	private void changeDir(Direction dir){
		this.curTrainer.setDirection(dir);
	}
	
	public void update(){
		super.setChanged();
		super.notifyObservers();
	}
	
	// move the trainer
	public void moveTrainer(Direction dir){
		// change direction first
		this.changeDir(dir);
				
		int nextX = 0;
		int nextY = 0;
		
		// move direction to the east
		if (dir == Direction.EAST){
			// set the next coords
			nextX = xCoords;
			nextY = yCoords + 1;
		}
		// move to west
		else if (dir == Direction.WEST){
			// set the next coords
			nextX = xCoords;
			nextY = yCoords - 1;
		}
		// move to south
		else if (dir == Direction.SOUTH){
			// set the next coords
			nextX = xCoords + 1;
			nextY = yCoords;
		}
		// move to north
		else{
			// set the next coords
			nextX = xCoords - 1;
			nextY = yCoords;
		}
		
		// block the path if is an obstacle
		if (curMap.getBlock(nextX, nextY).getObstacle() != ObstacleType.NONE){
			// TODO: notify the user that its not passable
			// 		Check if it is an item
		}
		
		// trigger the portal
		else if (curMap.getBlock(nextX, nextY).getPassType() == PassableType.PORTAL){
			// count step
			curTrainer.incrementStep(1);
			// TODO: call the change map function
			Point p = new Point();
			p.setLocation(nextX, nextY);						
			changeMap(curMap, p);
		}	
		
		// encounter pokemon
		else if (curMap.getBlock(nextX, nextY).getPassType() != PassableType.AIR){
			// count step
			curTrainer.incrementStep(1);
			
			setLocation(nextX, nextY);
			update();
			// call the pokemon encounter
			pokemonEncounter();
		}		
		
		// update 
		//checkGameOver();
		//checkWin();
		update();
	}
	
	public void pokemonEncounter(){
		// TODO: algorithm to encounter pokemon
		// 		Might need to change the view
		
		return;
	}
	
	public void changeMap(Map map, Point portal){
		int newX = 0;
		int newY = 0;
		// on map bottom left
		if (curMap.getClass() == Map_BottomLeft.class){
			// check portal
			if (portal.equals(map_BL.getRightPortal())){
				curMap = map_BR;
				newX = (int)map_BR.getLeftPortal().getX();
				newY = (int)map_BR.getLeftPortal().getY() + 1;
			}
			else{
				curMap = map_TL;
				newX = (int)map_TL.getBottomPortal().getX() - 1;
				newY = (int)map_TL.getBottomPortal().getY();
			}
		}
		// on map bottom right
		else if (curMap.getClass() == Map_BottomRight.class){
			// check portal
			if (portal.equals(map_BR.getLeftPortal())){
				curMap = map_BL;
				newX = (int)map_BL.getRightPortal().getX();
				newY = (int)map_BL.getRightPortal().getY() - 1;
			}
			else{
				curMap = map_TR;
				newX = (int)map_BR.getLeftPortal().getX() - 1;
				newY = (int)map_BR.getLeftPortal().getY();
			}
		}
		
		// on map top right
		else if (curMap.getClass() == Map_TopRight.class){
			// check portal
			if (portal.equals(map_TR.getLeftPortal())){
				curMap = map_TL;
				newX = (int)map_TL.getRightPortal().getX();
				newY = (int)map_TL.getRightPortal().getY() - 1;
			}
			else{
				curMap = map_BR;
				newX = (int)map_BR.getTopPortal().getX() + 1;
				newY = (int)map_BR.getTopPortal().getY();
			}
		}
		
		// on map top left
		else{
			// check portal
			if (portal.equals(map_TL.getRightPortal())){
				curMap = map_TR;
				newX = (int)map_TR.getLeftPortal().getX();
				newY = (int)map_TR.getLeftPortal().getY() + 1;
			}
			else{
				curMap = map_BL;
				newX = (int)map_BL.getTopPortal().getX() + 1;
				newY = (int)map_BL.getTopPortal().getY();
			}
		}
		
		// reset the coordinates and notify the observer
		this.setLocation(newX, newY);
	}
	
	public void checkGameOver(){
		this.isEnd = mission.checkMissionFailed(curTrainer);
	}
	
	public void checkWin(){
		this.isWin = mission.checkMissionComplete(curTrainer);
	}
	
	public boolean isGameOver(){
		return this.isEnd;
	}
	
	public boolean isWin(){
		return this.isWin;
	}
}