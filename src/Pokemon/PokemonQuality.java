/*
 * Author: Mengtao Tang
 * Date: 4/20/2017
 * Course: CSC_335
 * Purpose: This is an enum class that define quality of a pokemon
 * 			It defines the basic capture rate of the pokemon and the
 * 			run away chance for each ture and the max turn before run
 * 
 */

package Pokemon;

public enum PokemonQuality {
	COMMON(90, 15), 
	UNCOMMON(70, 12), 
	RARE(50, 9),
	EPIC(20, 6),
	LEGENDARY(1, 3);
	
	private final double basicCaptureRate;
	private final double runChance;
	private final int maxTurn;
	
	PokemonQuality(int capRate, int maxTurn){
		this.basicCaptureRate = capRate * 0.01;
		this.runChance = runGenerator(capRate);
		this.maxTurn = maxTurn;
	}
	
	public double getCapRate(){
		return this.basicCaptureRate;
	}
	
	public double getRunChance(){
		return this.runChance;
	}
	
	public int getMaxTurn(){
		return this.maxTurn;
	}
	
	// TODO: need an algorithm to generate a run chance
	private double runGenerator(int capRate){
		return (double)(1 - capRate);
	}
}
