package Inventory;

import Trainer.Trainer;

public class StepPotion extends Item{
	private int restoreAmount;
	
	public StepPotion(int amount, String name, ItemType type){
		super(name, type);
		this.restoreAmount = amount;
	}
	
	@Override
	public String getInfo() {
		return "restore " + this.restoreAmount + " step count";
	}

	@Override
	public void useItem(Object object) {
		this.decrement(1);
		((Trainer) object).decrementStep(this.restoreAmount);
	}

}
