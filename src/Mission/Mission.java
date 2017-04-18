package Mission;

public class Mission {
	private int stepCap;
	private final int initBall;
	private final int rareRequirement;
	private final int totalRequirement;
	private final int legendRequirement;
	private final MissionType type;
	
	
	// constructor
	public Mission(MissionType mission){
		this.type = mission;
		// record when 500 step down
		if (mission == MissionType.STANDARDLADDER){
			this.stepCap = 500;
			this.initBall = 30;
			this.rareRequirement = 0;
			this.totalRequirement = 0;
			this.legendRequirement = 0;
		}
		else if (mission == MissionType.TWENTYPOKEMON){
			this.stepCap = 500;
			this.initBall = 30;
			this.rareRequirement = 0;
			this.totalRequirement = 20;
			this.legendRequirement = 0;
		}
		else if (mission == MissionType.THIRTYPOKEMON){
			this.stepCap = 500;
			this.initBall = 30;
			this.rareRequirement = 0;
			this.totalRequirement = 30;
			this.legendRequirement = 0;
		}
		else if (mission == MissionType.FIFTYPOKEMON){
			this.stepCap = 500;
			this.initBall = 30;
			this.rareRequirement = 0;
			this.totalRequirement = 50;
			this.legendRequirement = 0;
		}
		else if (mission == MissionType.FIVERARE){
			this.stepCap = 500;
			this.initBall = 30;
			this.rareRequirement = 5;
			this.totalRequirement = 0;
			this.legendRequirement = 0;
		}
		else if (mission == MissionType.FINDLENGEND){
			this.stepCap = 500;
			this.initBall = 30;
			this.rareRequirement = 0;
			this.totalRequirement = 0;
			this.legendRequirement = 1;
		}
		else{
			this.stepCap = 500;
			this.initBall = 30;
			this.rareRequirement = 0;
			this.totalRequirement = 0;
			this.legendRequirement = 0;
		}
	}
	
	public int getStepCap(){
		return this.stepCap;
	}
	
	public MissionType getMissionType(){
		return this.type;
	}
	
	public int initBall(){
		return this.initBall;
	}
	
	public int getRareRequirement(){
		return this.rareRequirement;
	}
	
	public int getTotalRequirement(){
		return this.totalRequirement;
	}
	
	public int getLegendRequirement(){
		return this.legendRequirement;
	}
	
	public void incrementStepCap(int num){
		this.stepCap += num;
	}
	
	public void decrementStepCap(int num){
		this.stepCap -= num;
	}
}
