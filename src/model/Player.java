package model;

import java.util.ArrayList;

public class Player {
	private ArrayList<Pokemon> pokemonBag;
	private Inventory inventoryBag;
	private String playerName;
	private int stepCount;
	
	// constructor
	public Player(String name, int step){
		pokemonBag = null;
		inventoryBag = null;
		this.playerName = name;
		this.stepCount = 0;
	}
	
	// getter and setter
	public void addPokemon(Pokemon p){
		pokemonBag.add(p);
	}
	
	public void addItem(Item item){
		inventoryBag.add(item);
	}
	
	public int getStep(){
		return stepCount;
	}
	
	public String getName(){
		return playerName;
	}
}
