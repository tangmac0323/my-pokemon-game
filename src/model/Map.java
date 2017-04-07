package model;

public class Map {
	private mapBlock[][] map; 
	
	public Map(){
		map = new mapBlock[11][11];
	}
	private class mapBlock{
		private int randomSeed;
		
		public mapBlock(){
			randomSeed = (int) Math.random();
		}
	}

}

