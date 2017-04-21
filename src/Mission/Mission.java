package Mission;

public abstract class Mission {
	private final int stepCap;
	private final MissionType type;
	
	
	// constructor
	public Mission(MissionType mission){
		this.stepCap = 500;
		this.type = mission;
	}
	
	public int getStepCap(){
		return this.stepCap;
	}
	
	public MissionType getMissionType(){
		return this.type;
	}
}
