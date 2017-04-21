package Inventory;

import Trainer.Trainer;

public class SafariBall extends Item{
	
	
	// constructor
	public SafariBall(){
		super("Safari Ball", ItemType.BALL);
	}

	@Override
	public String getInfo() {
		return "This is a regular safari ball";
	}

	@Override
	public void useItem(Trainer trainer) {
		this.decrement(1);
	}

}
