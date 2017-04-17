package Trainer;

import java.util.ArrayList;

import Inventory.ItemCollection;
import Inventory.Item;
import Pokemon.Pokemon;

public class Trainer {
	private ArrayList<Pokemon> pokemonBag;
	private ItemCollection inventoryBag;
	private String playerName;
	private int stepCount;
	
	// constructor
	public Trainer(String name, int step){
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
