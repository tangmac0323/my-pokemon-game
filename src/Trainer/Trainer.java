package Trainer;

import java.time.LocalDateTime;
import java.util.ArrayList;

import GameModel.Direction;
import Inventory.ItemCollection;
import Inventory.ItemType;
import Pokemon.Pokemon;
import Pokemon.PokemonCollection;
import Inventory.Item;

public class Trainer {
	
	// loggin information
	//private final String userName;
	//private final String passWord;
	private final String id;
	private final LocalDateTime birthDate;
	
	// character information
	private Direction faceDir;
	private PokemonCollection pokemonCollection;
	private ItemCollection inventory;
	
	// in-game information
	private int stepCount;
	private int rowCoords;
	private int colCoords;
	private double bonusCapture;
	private double bonusRun;
	
	public Trainer(String id){
		this.id = id;
		this.birthDate = LocalDateTime.now();
		
		this.faceDir = Direction.SOUTH;
		this.pokemonCollection = new PokemonCollection();
		this.inventory = new ItemCollection();
		
		this.bonusCapture = 0;
		this.bonusRun = 0;
	}
	
	public void incrementStep(int num){
		this.stepCount += num;
	}
	
	public void decrementStep(int num){
		this.stepCount -= num;
	}
	
	public void setDirection(Direction dir){
		this.faceDir = dir;
	}
	
	public void addItem(ItemType item){
		this.inventory.addItem(item);
	}
	
	public void catchPokemon(Pokemon newPokemon){
		this.pokemonCollection.addPokemon(newPokemon);
	}
	
	public void incrementBonusCapture(double num){
		this.bonusCapture += num;
	}
	
	public void decrementBonusCapture(double num){
		this.bonusCapture -= num;
	}
	
	
	public void incrementBonusRun(double num){
		this.bonusRun += num;
	}
	
	public void decrementBonusRun(double num){
		this.bonusRun -= num;
	}
	
	public int getStepCount(){
		return this.stepCount;
	}
	
	public int getRow(){
		return this.rowCoords;
	}
	
	public int getCol(){
		return this.colCoords;
	}
	
	public double getBonusCapture(){
		return this.bonusCapture;
	}
	
	public double getBonusRun(){
		return this.bonusRun;
	}
}
