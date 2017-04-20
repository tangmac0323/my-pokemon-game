package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import GameModel.GameModel;
import Map.GroundType;
import Map.ObstacleType;

public class MainGameView extends JPanel implements Observer{

	private static final long serialVersionUID = 7713222421276164624L;

	// declare image sheet
	private BufferedImage groundSheet;
	private BufferedImage treeSheet;
	private BufferedImage trainerSheet;
	
	// declare drawing coords
	private Point upperLeft;
	private GameModel model;
	private static final int VisionRadius = 20;	// define the vision of the trainer on the map
	
	// constructor
	public MainGameView(){
		loadImages();
		repaint();
		
		System.out.println("Initiate the view");
	}
	
	@Override
	public void update(Observable o, Object arg) {
		model = (GameModel) o;
		upperLeft = model.getLocation();
		repaint();
		
	}
	
	// draw the map
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		// check the existence of model
		if (model == null){
			return;
		}
		
		//System.out.println("draw map");
		
		if (true){
			// draw the map
			// define the upper left point of the map
			Point upperLeftMap = findCenter();
			//System.out.println("Current Center: " + upperLeftMap.getX() + " " + upperLeftMap.getY());
			// define the start upper left value
			int startX = upperLeftMap.x - VisionRadius;
			int startY = upperLeftMap.y - VisionRadius;
			//System.out.println("Start from: " + upperLeftMap.x + " " + upperLeftMap.y);
			
			// declare the draw panel coordinates
			int x = 0;
			int y = 0;
			// draw ground
			for (int i = startX; i <= startX + VisionRadius * 2; i++, x++){
				for (int j = startY; j <= startY + VisionRadius * 2; j++, y++){
					//draw ground
					BufferedImage tempGroundImg = drawGround(i, j);
					if (tempGroundImg != null){
						g2.drawImage(tempGroundImg, x*MapBlockSize, y*MapBlockSize, null);
					}
				}
				y = 0;
				
			}	
			
			
			// declare the draw panel coordinates
			x = 0;
			y = 0;
			// draw obstacle
			for (int i = startX; i <= startX + VisionRadius * 2; i++, x++){
				for (int j = startY; j <= startY + VisionRadius * 2; j++, y++){
					// draw rock
					BufferedImage tempRockImg = drawObstacle_Rock(i,j );
					if (tempRockImg != null){
						g2.drawImage(tempRockImg, x*MapBlockSize, y*MapBlockSize, null);
					}
					// draw tree
					BufferedImage tempTreeImg = drawObstacle_Tree(i,j );
					if (tempTreeImg != null){
						g2.drawImage(tempTreeImg, x*MapBlockSize, (y - 1)*MapBlockSize, null);
					}
				}
				y = 0;
			}
			
			x = 0;
			y = 0;
			
			Point trainerOnMap = findTrainerLocation();
			int trainerX = trainerOnMap.x;
			int trainerY = trainerOnMap.y;
			g2.drawImage(drawTrainer(), trainerX * MapBlockSize, trainerY * MapBlockSize - 4, null);
			
		}
	}
	
	private Point findCenter(){
		// check the location of the trainer
		int curX = (int) upperLeft.getX();
		int curY = (int) upperLeft.getY();
		
		// declare the center of the view
		int centerX = curX;
		int centerY = curY;
		
		// check if the vision cover the corner/sides
		// collide left side
		if (curX - VisionRadius < 0){
			centerX = VisionRadius;
		}
		// collide right side
		if (curX + VisionRadius >= model.getCurMap().getSize()){
			centerX = model.getCurMap().getSize() - VisionRadius - 1;
		}
		// collide top
		if (curY - VisionRadius < 0){
			centerY = VisionRadius;
		}
		// collide bottom
		if (curY + VisionRadius >= model.getCurMap().getSize()){
			centerY = model.getCurMap().getSize() - VisionRadius - 1;
		}
		
		Point p = new Point();
		p.setLocation(centerX, centerY);
		return p;
	}
	
	private Point findTrainerLocation(){
		// check the location of the trainer
		int curX = (int) upperLeft.getX();
		int curY = (int) upperLeft.getY();
		
		int onMapX = VisionRadius;
		int onMapY = VisionRadius;
		
		// check if the vision cover the corner/sides
		// collide left side
		if (curX - VisionRadius < 0){
			onMapX = curX;
		}
		// collide right side
		if (curX + VisionRadius >= model.getCurMap().getSize()){
			onMapX = curX + 41 - model.getCurMap().getSize();
		}
		// collide top
		if (curY - VisionRadius < 0){
			onMapY = curY;
		}
		// collide bottom
		if (curY + VisionRadius >= model.getCurMap().getSize()){
			onMapY = curY + 41 - model.getCurMap().getSize();
		}
		
		Point p = new Point();
		p.setLocation(onMapX, onMapY);
		return p;
	}
		
	/*
	 * *************************************** *
	 *  Painting and Animation Creator Below   *
	 * *************************************** *
	 */
	
	/*
	 *  Numbers for sprite sheet
	 */
	// define sprite sheet name
	private static final String gndTextureFileName = "pokemon_ground.gif";
	private static final String treeTextureFileName = "pokemon_tree.gif";
	private static final String trainerTextureFileName = "pokemon_trainer.gif";
	
	// define the size of one block
	public static final int MapBlockSize = 16;
	
	// define the coordinates of ground texture
	private static final int Land_Grass_A_OFFSET_X = 0 * MapBlockSize;
	private static final int Land_Grass_A_OFFSET_Y = 12 * MapBlockSize;
	
	private static final int Land_Sand_A_OFFSET_X = 3 * MapBlockSize;
	private static final int Land_Sand_A_OFFSET_Y = 12 * MapBlockSize;
	
	// define the coordinates of the tree texture
	private static final int Tree_Small_A_OFFSET_X = 0 * MapBlockSize;
	private static final int Tree_Small_A_OFFSET_Y = 15 * MapBlockSize;
	private static final int Tree_Small_A_Height = 32;
	private static final int Tree_Small_A_Width = 16;
	
	// define the coordinates of the rock texture
	private static final int Rock_Small_A_OFFSET_X = 22 * MapBlockSize;
	private static final int Rock_Small_A_OFFSET_Y = 1 * MapBlockSize;
	
	// function to load images sheet
	private void loadImages() {
		// try to open the file of ground textures
		try{
			File gndTextureFile = new File("images" + File.separator + 
					gndTextureFileName);
			groundSheet = ImageIO.read(gndTextureFile);
		}
		catch (IOException e){
			System.out.println("Could not find: " + gndTextureFileName);
		}	
		
		// try to open the file of tree textures
		try{
			File treeTextureFile = new File("images" + File.separator + 
					treeTextureFileName);
			treeSheet = ImageIO.read(treeTextureFile);
		}
		catch (IOException e){
			System.out.println("Could not find: " + treeTextureFileName);
		}	
		
		// try to open the file of the trainer
		try{
			File trainerTextureFile = new File("images" + File.separator + 
					trainerTextureFileName);
			trainerSheet = ImageIO.read(trainerTextureFile);
		}
		catch (IOException e){
			System.out.println("Could not find: " + trainerTextureFileName);
		}
	}
	
	// function to draw the ground according to the model
	private BufferedImage drawGround(int x, int y){
		if (model.getCurMap().getBlock(x, y).getGround() == GroundType.GRASSLAND){
			return groundSheet.getSubimage(Land_Grass_A_OFFSET_X, Land_Grass_A_OFFSET_Y, 
											Tree_Small_A_Height, Tree_Small_A_Width);
		}
		else if (model.getCurMap().getBlock(x, y).getGround() == GroundType.SAND){
			return groundSheet.getSubimage(Land_Sand_A_OFFSET_X, Land_Sand_A_OFFSET_Y, 
					MapBlockSize, MapBlockSize);
		}
		else{
			return null;
		}
	}
	
	// function to draw the rock obstacle according to the model
	private BufferedImage drawObstacle_Rock(int x, int y){
		if (model.getCurMap().getBlock(x, y).getObstacle() == ObstacleType.ROCK){
			return groundSheet.getSubimage(Rock_Small_A_OFFSET_X, Rock_Small_A_OFFSET_Y, 
											MapBlockSize, MapBlockSize);
		}
		else{
			return null;
		}
	}
	
	// function to draw the tree obstacle according to the model
	private BufferedImage drawObstacle_Tree(int x, int y){
		if (model.getCurMap().getBlock(x, y).getObstacle() == ObstacleType.TREE){
			return treeSheet.getSubimage(Tree_Small_A_OFFSET_X, Tree_Small_A_OFFSET_Y, 
										Tree_Small_A_Width, Tree_Small_A_Height);
		}
		else{
			return null;
		}
	}
	
	// define the coordinates of the trainer
	private static final int Trainer_South_1_OFFSET_X = 7;
	private static final int Trainer_South_1_OFFSET_Y = 16;
	private static final int Trainer_Height = 20;
	private static final int Trainer_Width = 16;
	
	// function to draw the tree obstacle according to the model
	private BufferedImage drawTrainer(){
		return trainerSheet.getSubimage(Trainer_South_1_OFFSET_X, Trainer_South_1_OFFSET_Y, 
										Trainer_Width, Trainer_Height);

	}

}
