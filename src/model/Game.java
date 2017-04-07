package model;

import java.util.ArrayList;

public class Game {
	private Map[][] worldMap;
	private ArrayList<Player> players;
	
	// constructor
	public Game(){
		worldMap = new Map[5][5];
		players = null;
	}
	
	// add player
	public void addPlayer(Player newPlayer){
		players.add(newPlayer);
	}
}
