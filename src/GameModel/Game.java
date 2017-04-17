package GameModel;

import java.util.ArrayList;

import Map.Map;
import Trainer.Trainer;

public class Game {
	private Map[][] worldMap;
	private ArrayList<Trainer> players;
	
	// constructor
	public Game(){
		worldMap = new Map[5][5];
		players = null;
	}
	
	// add player
	public void addPlayer(Trainer newPlayer){
		players.add(newPlayer);
	}
}
