package Inventory;

import Pokemon.Pokemon;

public class HealPotion extends Item{
	private int restoreAmount;	// store the amount of health restored for the pokemon
	
	public HealPotion(int amount, String name, ItemType type){
		super(name, type);
		this.restoreAmount = amount;
	}
	
	@Override
	public String getInfo() {
		return "restore " + this.restoreAmount + " health to the selected pokemon";
	}

	@Override
	public void useItem(Object object) {
		this.decrement(1);
		((Pokemon) object).incrementHP(this.restoreAmount);		
	}

}
