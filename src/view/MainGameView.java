package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import GameModel.Direction;
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
	private Point finalLocation;
	private Point curLocation;
	private Point trainerOnMap;
	private Point centerOnMap;
	private GameModel model;
	private static final double PixelPerFrame = 2;
	private static final int VisionRadius = 20;	// define the vision of the trainer on the map
	
	// constructor
	public MainGameView(){
		loadImages();
		repaint();
		//curLocation = model.getLocation();
		//System.out.println("Initiate the view");
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
			centerOnMap = findCenter();
			//System.out.println("Current Center: " + upperLeftMap.getX() + " " + upperLeftMap.getY());
			// define the start upper left value
			int startX = centerOnMap.x - VisionRadius;
			int startY = centerOnMap.y - VisionRadius;
			//System.out.println("Start from: " + upperLeftMap.x + " " + upperLeftMap.y);
			
			// declare the draw panel coordinates
			int x = 0;
			int y = 0;
			// draw ground
			for (int i = startX; i <= startX + VisionRadius * 2; i++, x++){
				for (int j = startY; j <= startY + VisionRadius * 2; j++, y++){
					//draw ground
					BufferedImage tempGroundImg = drawGround(i, j, model.getDir());
					if (tempGroundImg != null){
						g2.drawImage(tempGroundImg, x*MapBlockSize, y*MapBlockSize, null);
					}
				}
				y = 0;
				
			}	
			
			// draw the trainer
			g2.drawImage(drawTrainer(), trainerOnMap.x, trainerOnMap.y, null);	
			//System.out.println("Current Location: " + trainerOnMap);
			
			// declare the draw panel coordinates
			x = 0;
			y = 0;
			// draw obstacle
			for (int i = startX; i <= startX + VisionRadius * 2; i++, x++){
				for (int j = startY; j <= startY + VisionRadius * 2; j++, y++){
					// draw rock
					BufferedImage tempRockImg = drawObstacle_Rock(i, j, model.getDir());
					if (tempRockImg != null){
						g2.drawImage(tempRockImg, x*MapBlockSize, y*MapBlockSize, null);
					}
					// draw tree
					BufferedImage tempTreeImg = drawObstacle_Tree(i, j, model.getDir());
					if (tempTreeImg != null){
						g2.drawImage(tempTreeImg, x*MapBlockSize, (y - 1)*MapBlockSize, null);
					}
				}
				y = 0;
			}
			
			x = 0;
			y = 0;
			
			// re-draw the trainer if there is an obstacle upon it
			if (model.getCurMap().getBlock(finalLocation.x, finalLocation.y - 1).getObstacle() != ObstacleType.NONE){
				g2.drawImage(drawTrainer(), trainerOnMap.x, trainerOnMap.y, null);	
				// TODO: Trying to solve the issue that some trees are blocked
				/*
				// redraw the tree
				if (model.getCurMap().getBlock(finalLocation.x, finalLocation.y + 1).getObstacle() == ObstacleType.TREE){
					BufferedImage tempTreeImg = treeSheet.getSubimage(Tree_Small_A_OFFSET_X, Tree_Small_A_OFFSET_Y, 
							Tree_Small_A_Width, Tree_Small_A_Height);
					g2.drawImage(tempTreeImg, finalLocation.x*MapBlockSize, (finalLocation.y + 1)*MapBlockSize, null);
				}
				*/
			}
			else if (model.getCurMap().getBlock(curLocation.x, curLocation.y - 1).getObstacle() != ObstacleType.NONE){
				g2.drawImage(drawTrainer(), trainerOnMap.x, trainerOnMap.y, null);	
				// TODO: Trying to solve the issue that some trees are blocked
				/*
				// redraw the tree
				if (model.getCurMap().getBlock(curLocation.x, curLocation.y + 1).getObstacle() == ObstacleType.TREE){
					BufferedImage tempTreeImg = treeSheet.getSubimage(Tree_Small_A_OFFSET_X, Tree_Small_A_OFFSET_Y, 
							Tree_Small_A_Width, Tree_Small_A_Height);
					g2.drawImage(tempTreeImg, finalLocation.x*MapBlockSize, (curLocation.y + 1)*MapBlockSize, null);
				}
				*/
			}
		}
	}
	
	private Point findCenter(){
		// check the location of the trainer
		int curX = (int) curLocation.getX();
		int curY = (int) curLocation.getY();
		
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
		int curX = (int) curLocation.getX();
		int curY = (int) curLocation.getY();
		
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
	
	/////////////////////// Timer ///////////////////////////////////
	private Timer timer;
	private int counter;
	
	private void startTimer() {
		timer = new Timer(100, new TimerListener());
		timer.start();
	}
	
	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (counter < 4 ){
				counter++;
				// update current location 
				// Check direction
				if (model.getDir() == Direction.EAST){
					trainerOnMap.setLocation(trainerOnMap.x + 4, trainerOnMap.y);
				}
				else if (model.getDir() == Direction.WEST){
					trainerOnMap.setLocation(trainerOnMap.x - 4, trainerOnMap.y);
				}
				else if (model.getDir() == Direction.SOUTH){
					trainerOnMap.setLocation(trainerOnMap.x, trainerOnMap.y + 4);
				}
				else{
					trainerOnMap.setLocation(trainerOnMap.x, trainerOnMap.y - 4);
				}	
				repaint();
			}
			else{
				timer.stop();
				curLocation.setLocation(finalLocation);
			}
		}

		
	}
	
	private void drawTrainerWithAnimation() {
		if (this.timer == null ){
			this.counter = 0; // initialize counter
			startTimer();
			return;
		}
		if (this.timer.isRunning()) {
			this.timer.stop();
			//curLocation.setLocation(finalLocation);
		}
		this.counter = 0; // initialize counter
		startTimer();


	}
	
	@Override
	public void update(Observable o, Object arg) {
		model = (GameModel) o;
		//curLocation = model.getLocation();
		curLocation = model.getPrevLocation();
		finalLocation = model.getLocation();
		trainerOnMap = findTrainerLocation();
		centerOnMap = findCenter();
		trainerOnMap.setLocation(trainerOnMap.x * MapBlockSize, trainerOnMap.y * MapBlockSize - 4);
		
		// calculate the increment
		//startTimer();
		//System.out.println("Current Location: " + curLocation + " Final Location" + finalLocation);
		if (curLocation.equals(finalLocation)){
			repaint();
		}
		else{
			drawTrainerWithAnimation();
		}
		
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
	private BufferedImage drawGround(int x, int y, Direction dir){
		if (dir == Direction.EAST){
			
		}
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
	private BufferedImage drawObstacle_Rock(int x, int y, Direction dir){
		if (model.getCurMap().getBlock(x, y).getObstacle() == ObstacleType.ROCK){
			return groundSheet.getSubimage(Rock_Small_A_OFFSET_X, Rock_Small_A_OFFSET_Y, 
											MapBlockSize, MapBlockSize);
		}
		else{
			return null;
		}
	}
	
	// function to draw the tree obstacle according to the model
	private BufferedImage drawObstacle_Tree(int x, int y, Direction dir){
		if (model.getCurMap().getBlock(x, y).getObstacle() == ObstacleType.TREE){
			return treeSheet.getSubimage(Tree_Small_A_OFFSET_X, Tree_Small_A_OFFSET_Y, 
										Tree_Small_A_Width, Tree_Small_A_Height);
		}
		else{
			return null;
		}
	}
	

	// define the coordinates of the trainer
	private static final int Trainer_Height = 20;
	private static final int Trainer_Width = 16;
	
	private static final int Trainer_South_1_OFFSET_X = 7;
	private static final int Trainer_South_2_OFFSET_X = 25;
	private static final int Trainer_South_3_OFFSET_X = 42;
	private static final int Trainer_South_1_OFFSET_Y = 16;
	
	private static final int Trainer_North_1_OFFSET_X = 114;
	private static final int Trainer_North_2_OFFSET_X = 133;
	private static final int Trainer_North_3_OFFSET_X = 151;
	private static final int Trainer_North_1_OFFSET_Y = 16;

	private static final int Trainer_West_1_OFFSET_X = 60;
	private static final int Trainer_West_2_OFFSET_X = 78;
	private static final int Trainer_West_3_OFFSET_X = 95;
	private static final int Trainer_West_1_OFFSET_Y = 16;

	private static final int Trainer_East_1_OFFSET_X = 169;
	private static final int Trainer_East_2_OFFSET_X = 187;
	private static final int Trainer_East_3_OFFSET_X = 204;
	private static final int Trainer_East_1_OFFSET_Y = 16;

	// function to draw the tree obstacle according to the model
	private BufferedImage drawTrainer(){
		// check the direction of the trainer
		// walking towards south
		if (model.getDir() == Direction.SOUTH){
			if (counter%3 == 0){
				return trainerSheet.getSubimage(Trainer_South_1_OFFSET_X, Trainer_South_1_OFFSET_Y, 
						Trainer_Width, Trainer_Height);
			}
			else if (counter%3 == 1){
				return trainerSheet.getSubimage(Trainer_South_2_OFFSET_X, Trainer_South_1_OFFSET_Y, 
						Trainer_Width, Trainer_Height);
			}
			else{
				return trainerSheet.getSubimage(Trainer_South_3_OFFSET_X, Trainer_South_1_OFFSET_Y, 
						Trainer_Width, Trainer_Height);
			}
		}
		// walking towards north
		else if (model.getDir() == Direction.NORTH){
			if (counter%3 == 0){
				return trainerSheet.getSubimage(Trainer_North_1_OFFSET_X, Trainer_North_1_OFFSET_Y, 
						Trainer_Width, Trainer_Height);
			}
			else if (counter%3 == 1){
				return trainerSheet.getSubimage(Trainer_North_2_OFFSET_X, Trainer_North_1_OFFSET_Y, 
						Trainer_Width, Trainer_Height);
			}
			else{
				return trainerSheet.getSubimage(Trainer_North_3_OFFSET_X, Trainer_North_1_OFFSET_Y, 
						Trainer_Width, Trainer_Height);
			}

		}
		// walking towards west
		else if (model.getDir() == Direction.WEST){
			if (counter%3 == 0){
				return trainerSheet.getSubimage(Trainer_West_1_OFFSET_X, Trainer_West_1_OFFSET_Y, 
						Trainer_Width, Trainer_Height);
			}
			else if (counter%3 == 1){
				return trainerSheet.getSubimage(Trainer_West_2_OFFSET_X, Trainer_West_1_OFFSET_Y, 
						Trainer_Width, Trainer_Height);
			}
			else{
				return trainerSheet.getSubimage(Trainer_West_3_OFFSET_X, Trainer_West_1_OFFSET_Y, 
						Trainer_Width, Trainer_Height);
			}
		}
		// walking towards east
		else{
			if (counter%3 == 0){
				return trainerSheet.getSubimage(Trainer_East_1_OFFSET_X, Trainer_East_1_OFFSET_Y, 
						Trainer_Width, Trainer_Height);
			}
			else if (counter%3 == 1){
				return trainerSheet.getSubimage(Trainer_East_2_OFFSET_X, Trainer_East_1_OFFSET_Y, 
						Trainer_Width, Trainer_Height);
			}
			else{
				return trainerSheet.getSubimage(Trainer_East_3_OFFSET_X, Trainer_East_1_OFFSET_Y, 
						Trainer_Width, Trainer_Height);
			}
		}
	}

}
