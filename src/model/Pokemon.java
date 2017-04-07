package model;

public class Pokemon {
	private PokemonType type;
	private int HP;
	private String name;
	private int randomSeed;
	
	// constructor
	public Pokemon(String name, PokemonType type){
		this.name = name;
		this.type = type;
	}
	
	// setter and getter
	public void setHP(int newHP){
		this.HP = newHP;
	}
	
	public int getHP(){
		return this.HP;
	}
}
