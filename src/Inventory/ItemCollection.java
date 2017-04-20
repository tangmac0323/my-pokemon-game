package Inventory;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import Trainer.Trainer;

public class ItemCollection implements TableModel, Serializable{

	private static final long serialVersionUID = 2624045140265552470L;
	
	
	private ArrayList<Item> itemList;
	
	public ItemCollection(){
		this.itemList = new ArrayList<Item>();
		addItem(ItemType.BALL);
		addItem(ItemType.BALL);
		addItem(ItemType.BALL);
		
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
	
	private Item createType(ItemType type){
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
	
	/*
	public void useItem(int i, Trainer trainer){
		itemList.get(i).useItem(trainer);
	}
	*/

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getColumnClass(int col) {
		if (col == 0){
			return String.class;
		}
		
		if (col == 1){
			return Integer.class;
		}
				
		return null;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public String getColumnName(int col) {
		if (col == 0){
			return "Item Type";
		}
		
		if (col == 1){
			return "Quantity";
		}
		
		return null;
	}

	@Override
	public int getRowCount() {
		return itemList.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		if (col == 0){
			return itemList.get(row).getName();
		}
		else{
			return itemList.get(row).getCount();
		}
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
}

