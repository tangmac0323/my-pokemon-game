package Inventory;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ItemCollection implements TableModel, Serializable{
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

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
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

