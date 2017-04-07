package model;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> items;
	
	// constructor
	public Inventory(){
		items = null;
	}
	
	// getter and setter
	public void add(Item item){
		items.add(item);
	}
	
	public Item getItem(String name){
		return null;
	}
}
