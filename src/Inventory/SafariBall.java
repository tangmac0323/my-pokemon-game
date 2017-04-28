package Inventory;

import Trainer.Trainer;

public class SafariBall extends Item{
	
	private static final long serialVersionUID = -6527050440324754144L;

	// constructor
	public SafariBall(){
		super("Safari Ball", ItemType.BALL);
	}

	@Override
	public String getInfo() {
		return "This is a regular safari ball";
	}

	@Override
	public void useItem(Object object) {
		this.decrement(1);
	}

}
