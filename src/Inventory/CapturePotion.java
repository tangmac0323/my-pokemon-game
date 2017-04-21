package Inventory;

import Trainer.Trainer;

public class CapturePotion extends Item{
	private double alteredChance;

	public CapturePotion(String name, double alterChance, ItemType type) {
		super(name, type);
	}

	@Override
	public void useItem(Trainer trainer) {
		this.decrement(1);
		trainer.incrementBonusCapture(this.alteredChance);
	}

	@Override
	public String getInfo() {
		return "Permanently increase the chance of capture by " + this.alteredChance * 100 + "%";
	}

}
