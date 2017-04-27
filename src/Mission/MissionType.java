package Mission;

public enum MissionType {
	FINDLENGEND(Difficulty.HELL), 
	STANDARDLADDER(Difficulty.CASUAL), 
	TWENTYPOKEMON(Difficulty.EASY), 
	THIRTYPOKEMON(Difficulty.NORMAL), 
	FIFTYPOKEMON(Difficulty.HARD), 
	FIVERARE(Difficulty.VERYHARD),
	TEST(Difficulty.CASUAL);
	
	private final Difficulty difficulty;
	
	private MissionType(Difficulty difficulty){
		this.difficulty = difficulty;
	}
}
