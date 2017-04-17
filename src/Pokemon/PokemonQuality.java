package Pokemon;

public enum PokemonQuality {
	COMMON(90), 
	UNCOMMON(70), 
	RARE(50),
	EPIC(20),
	LEGENDARY(1);
	
	private final int captureRate;
	
	PokemonQuality(int capRate){
		this.captureRate = capRate;
	}
	
	public int getCapRate(){
		return this.captureRate;
	}
}
