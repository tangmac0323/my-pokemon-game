package Inventory;

import java.util.ArrayList;

public class ItemCollection {
	private ArrayList<Item> itemList;
	
	public ItemCollection(){
		this.itemList = new ArrayList<Item>();
		
	}
	
	public void addItem(ItemType type){
		// increase the count for the item
		for (Item tempItem : itemList) {
			if (tempItem.getType() == type){
				tempItem.increment(1);
				return;
			}
		}
		
		// create a new item and add into the list
		this.itemList.add(this.createType(type));
		return;
		
	}
	
	public Item createType(ItemType type){
		if (type == ItemType.BALL){
			return new SafariBall();
		}
		
		if (type == ItemType.CAPTURE_POTION_LARGE){
			return new CapturePotion_Large();
		}
		
		if (type == ItemType.CAPTURE_POTION_MEDIUM){
			return new CapturePotion_Medium();
		}
		
		if (type == ItemType.CAPTURE_POTION_SMALL){
			return new CapturePotion_Small();
		}
		
		if (type == ItemType.STEP_POTION_LARGE){
			return new StepPotion_Large();
		}
		
		if (type == ItemType.STEP_POTION_MEDIUM){
			return new StepPotion_Medium();
		}
		
		if (type == ItemType.STEP_POTION_SMALL){
			return new StepPotion_Small();
		}
		
		return null;
		
	}
}

