/*
 * Author: Mengtao Tang
 * Date: 4/4/2017
 * Course: CSC_335
 * Purpose: This is an enum class that define the basic information
 * 			of all sort of pokemon that will be encountered in the 
 * 			game.
 * 			The defined the data for each pokemon includes:
 * 				name
 * 				index
 * 				quality
 * 				TODO: voice path
 * 
 */

package Pokemon;

public enum Pokedex {
	Caterpie("Caterpie", 10, PokemonQuality.COMMON), 
	Weedle("Weedle", 13, PokemonQuality.COMMON), 
	Pidgey("Pidgey", 16, PokemonQuality.COMMON), 
	Rattata("Rattata", 19, PokemonQuality.COMMON), 
	Spearow("Spearow", 21, PokemonQuality.COMMON),
	Ekans("Ekans", 23, PokemonQuality.COMMON), 
	Pikachu("Pikachu", 25, PokemonQuality.UNCOMMON), 
	Growlithe("Growlithe", 58, PokemonQuality.UNCOMMON), 
	Abra("Abra", 63, PokemonQuality.RARE),
	Dratini("Dratini", 147, PokemonQuality.EPIC),
	Farfetchd("Farfetch'd", 83, PokemonQuality.UNCOMMON), 
	MewTwo("MewTwo", 150, PokemonQuality.LEGENDARY),
	Mew("Mew", 151, PokemonQuality.LEGENDARY);
	
	
	private final String name;		// store the name of the pokemon
	private final int tagIndex;		// store the index of the pokemon in pokedex
	private final PokemonQuality quality;		// store the quality of the pokemon
	
	Pokedex(String name, int tag, PokemonQuality q){
		this.name = name;
		this.tagIndex = tag;
		this.quality = q;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getTagIndex(){
		return this.tagIndex;
	}
	
	public PokemonQuality getQuality(){
		return this.quality;
	}
	
	
}
