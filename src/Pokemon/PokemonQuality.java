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
	COMMON(90, 15, 50), 
	UNCOMMON(70, 12, 25), 
	RARE(50, 9, 15),
	EPIC(20, 6, 9),
	LEGENDARY(1, 3, 1);
	
	private final double basicCaptureRate;
	private final double runChance;
	private final double basicEncounterRate;
	private final int maxTurn;
	
	private PokemonQuality(int capRate, int maxTurn, double encounterRate){
		this.basicCaptureRate = capRate * 0.01;
		this.runChance = runGenerator(capRate);
		this.basicEncounterRate = encounterRate * 0.01;
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
	
	public double getEncounterRate(){
		return this.basicEncounterRate;
	}
	
	// TODO: need an algorithm to generate a run chance
	private double runGenerator(int capRate){
		return (double)(1 - capRate);
	}
}
