package GameModel;

import java.awt.Point;
import java.util.Observable;

import Map.*;
import Mission.*;
import Trainer.*;

public class GameModel extends Observable{
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
	private static final int BlockPixel = 32;
	
	// game information
	private Mission mission;
	
	public GameModel(){
		initiateMap();
	}
	
	// initiate the map
	private void initiateMap(){
		map_BL = new Map_BottomLeft();
		map_BR = new Map_BottomRight();
		map_TL = new Map_TopLeft();
		map_TR = new Map_TopRight();
	}
	
	public void setTrainer(Trainer trainer){
		this.curTrainer = trainer;
	}
	
	public void setCurMap(Map map){
		this.curMap = map;
	}
	
	public void setMission(Mission mission){
		this.mission = mission;
	}
	
	public Mission getMission(){
		return this.mission;
	}
	
	public int getPixel(){
		return this.BlockPixel;
	}
	
	// set the coordinate of the trainer
	private void setCoords(int x, int y){
		this.xCoords = x;
		this.yCoords = y;
	}
	
	private void changeDir(Direction dir){
		this.curTrainer.setDirection(dir);
	}
	
	private void update(){
		super.setChanged();
		super.notifyObservers();
	}
	
	// move the trainer
	public void moveTrainer(Direction dir){
		// change direction first
		this.changeDir(dir);
		
		// move direction to the east
		if (dir == Direction.EAST){
			// block the path if is an obstacle
			if (curMap.getBlock(xCoords, yCoords + 1).getObstacle() != ObstacleType.NONE){
				// TODO: notify the user that its not passable
				// 		Check if it is an item
			}
			
			// trigger the portal
			else if (curMap.getBlock(xCoords, yCoords + 1).getPassType() == PassableType.PORTAL){
				// TODO: call the change map function
				Point p = new Point();
				p.setLocation(xCoords, yCoords + 1);						
				changeMap(curMap, p);
			}	
			
			// encounter pokemon
			else if (curMap.getBlock(xCoords, yCoords + 1).getPassType() != PassableType.AIR){
				setCoords(xCoords, yCoords + 1);
				update();
				// call the pokemon encounter
				pokemonEncounter();
			}
		}
		// move to west
		else if (dir == Direction.WEST){
			// block the path if is an obstacle
			if (curMap.getBlock(xCoords, yCoords - 1).getObstacle() != ObstacleType.NONE){
				// TODO: notify the user that its not passable
				// 		Check if it is an item
			}
			
			// trigger the portal
			else if (curMap.getBlock(xCoords, yCoords - 1).getPassType() == PassableType.PORTAL){
				// TODO: call the change map function
				Point p = new Point();
				p.setLocation(xCoords, yCoords - 1);						
				changeMap(curMap, p);
			}	
			
			// encounter pokemon
			else if (curMap.getBlock(xCoords, yCoords - 1).getPassType() != PassableType.AIR){
				setCoords(xCoords, yCoords - 1);
				update();
				// call the pokemon encounter
				pokemonEncounter();
			}
		}
		// move to south
		else if (dir == Direction.SOUTH){
			// block the path if is an obstacle
			if (curMap.getBlock(xCoords + 1, yCoords).getObstacle() != ObstacleType.NONE){
				// TODO: notify the user that its not passable
				// 		Check if it is an item
			}
			
			// trigger the portal
			else if (curMap.getBlock(xCoords + 1, yCoords).getPassType() == PassableType.PORTAL){
				// TODO: call the change map function
				Point p = new Point();
				p.setLocation(xCoords + 1, yCoords);						
				changeMap(curMap, p);
			}	
			
			// encounter pokemon
			else if (curMap.getBlock(xCoords + 1, yCoords).getPassType() != PassableType.AIR){
				setCoords(xCoords + 1, yCoords);
				update();
				// call the pokemon encounter
				pokemonEncounter();
			}
		}
		// move to north
		else{
			// block the path if is an obstacle
			if (curMap.getBlock(xCoords - 1, yCoords).getObstacle() != ObstacleType.NONE){
				// TODO: notify the user that its not passable
				// 		Check if it is an item
			}
			
			// trigger the portal
			else if (curMap.getBlock(xCoords - 1, yCoords).getPassType() == PassableType.PORTAL){
				// TODO: call the change map function
				Point p = new Point();
				p.setLocation(xCoords - 1, yCoords);						
				changeMap(curMap, p);
			}	
			
			// encounter pokemon
			else if (curMap.getBlock(xCoords - 1, yCoords).getPassType() != PassableType.AIR){
				setCoords(xCoords - 1, yCoords);
				update();
				// call the pokemon encounter
				pokemonEncounter();
			}
		}
		
		// update 
		update();
	}
	
	public void pokemonEncounter(){
		// TODO: algorithm to encounter pokemon
		// 		Might need to change the view
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
		this.setCoords(newX, newY);
	}
}
