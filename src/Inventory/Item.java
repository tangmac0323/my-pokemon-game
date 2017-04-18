/*
 * Author: Mengtao Tang
 * Date: 4/20/2017
 * Course: CSC_335
 * Purpose: This is an abstract class define the information of an item
 * 
 */
package Inventory;

import Trainer.Trainer;

public abstract class Item {
	private int count;	// store the quantity of the item
	private String name;		// store the name of the item
	private final ItemType type;
	
	// constructor
	public Item(String name, ItemType type){
		this.count = 1;
		this.name = name;
		this.type = type;
	}
	
	public abstract void useItem(Trainer trainer);
		
	public abstract String getInfo();
	
	public void increment(int num){
		this.count += num;
	}
	
	public void decrement(int num){
		this.count -= num;
	}
	
	public int getCount(){
		return this.count;
	}
	
	public String getName(){
		return this.name;
	}
	
	public ItemType getType(){
		return this.type;
	}

}
