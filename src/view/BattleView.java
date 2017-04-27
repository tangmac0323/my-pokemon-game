package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import Inventory.ItemType;
import Map.GroundType;
import Pokemon.Pokemon;

public class BattleView extends JPanel implements Observer{
	
	private static final long serialVersionUID = 784618488834701873L;
	
	// declare image sheet
	private BufferedImage pokemonSheet;
	private BufferedImage battleField;
	private BufferedImage effectSheet;
	private BufferedImage menuSheet;
	private BufferedImage barSheet;
	private BufferedImage trainerSheet;
	
	// game model
	private GameModel model;
	private int curTurn;
	
	// combat pokemon information
	private Pokemon curPokemon;	// current pokemon
	private double curRunRate;	// current run rate of the pokemon
	private double curCapRate;	// current capture rate
	private int curCapTurn;		// current maximum running turns
	
	// declare variables for animation
	private boolean openingEnd = false;	// flag to check if the openning animation has been played
	private boolean battleEnd = true;
	
	public final static int delayInMillis = 25;
	private static final double PixelPerFrame = 2;
	public final static int framePerSec = 8;
	
	private ItemType usingItem = null;
	
	
	// constructor
	public BattleView(){
		calculateData();
		loadImages();
		repaint();
		this.addMouseListener(new ClickListener());
	}
		
	// draw the map
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		// check the existence of model
		if (model == null){
			return;
		}
		
		// draw the back ground
		g2.drawImage(drawBattleField(GroundType.GRASSLAND), 0, 0, null);
		g2.drawImage(drawBattleMenu(), 0, BattleField_Height, null);
	}
		
	private void calculateData(){
		
	}
	
	public void setCurPokemon(Pokemon pokemon){
		this.curPokemon = pokemon;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (battleEnd == false){
			model = (GameModel) o;
			calculateData();
			
			// play use item animation
			if(arg != null){
				usingItem = (ItemType) arg;
			}
			
			// play opening animation
			if (!openingEnd){
				openingEnd = true;
				playOpeningAnimation();
			}
			//repaint();
		}
	}
	
	public void startBattle(){
		battleEnd = false;
	}
	
	private void endBattle(){
		battleEnd = true;
	}
	
	private void playOpeningAnimation(){
		// TODO: draw sequence of opening animation
	}
	
	// click on run set the visibility of the view and raise the end flag
	private class ClickListener implements MouseListener {
		private final static int RunButtonHeight = 30;
		private final static int RunButtonWidth = 45;
		private final static int RunButtonHeight_OFFSET = 10;
		private final static int RunButtonWidth_OFFSET = 15;

		@Override
		public void mouseClicked(MouseEvent e) {
			if (!battleEnd){
				int x = e.getX();
				int y = e.getY();
				if (x >= 480 - RunButtonWidth - RunButtonWidth_OFFSET && x < 480 - RunButtonWidth_OFFSET 
						&& y >= 320 - RunButtonHeight - RunButtonHeight_OFFSET && y < 320 - RunButtonHeight_OFFSET){
					System.out.println("Click on: " + x + ", " + y);
					battleEnd = true;
					setVisible(false);
				}
			}			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

	/*
	 * *************************************** *
	 *  Painting and Animation Creator Below   *
	 * *************************************** *
	 */
	
	// define sprite sheet name
	private static final String pokemonTextureFileName = "pokemon_pokemons.png";
	private static final String trainerTextureFileName = "pokemon_trainer.png";
	private static final String effectTextureFileName = "pokemon_effect.png";
	private static final String battleMenuFileName = "pokemon_battle_menu.png";
	private static final String hpBarFileName = "pokemon_hpbar.png";
	private static final String battleFieldFileName = "pokemon_battle_field.png";
	
	private void loadImages(){
		loadPokemonTexture();
		loadTrainerTexture();
		loadEffectTexture();
		loadMenuTexture();
		loadHpBarTexture();
		loadBattleField();
	}
	
	// function to load images sheet
	private void loadPokemonTexture() {
		// try to open the file of pokemon textures
		try{
			File pokemonTextureFile = new File("images" + File.separator + 
					"Texture" + File.separator + 
					pokemonTextureFileName);
			pokemonSheet = ImageIO.read(pokemonTextureFile);
		}
		catch (IOException e){
			System.out.println("Could not find: " + pokemonTextureFileName);
		}	
	}
	
	private void loadTrainerTexture() {
		// try to open the file of trainer texture
		try{
			File trainerTextureFile = new File("images" + File.separator + 
					"Texture" + File.separator + 
					trainerTextureFileName);
			trainerSheet = ImageIO.read(trainerTextureFile);
		}
		catch (IOException e){
			System.out.println("Could not find: " + trainerTextureFileName);
		}	
	}
	
	
	private void loadEffectTexture() {
		// try to open the file of effect texture
		try{
			File effectTextureFile = new File("images" + File.separator + 
					"Texture" + File.separator + 
					effectTextureFileName);
			effectSheet = ImageIO.read(effectTextureFile);
		}
		catch (IOException e){
			System.out.println("Could not find: " + effectTextureFileName);
		}	
	}
	
	private void loadBattleField() {
		// try to open the file of the background
		try{
			File battleFieldTextureFile = new File("images" + File.separator + 
					"Texture" + File.separator + 
					battleFieldFileName);
			battleField = ImageIO.read(battleFieldTextureFile);
		}
		catch (IOException e){
			System.out.println("Could not find: " + battleFieldFileName);
		}	
	}
	
	private void loadMenuTexture() {
		// try to open the file of the menu texture
		try{
			File menuTextureFile = new File("images" + File.separator + 
					"Texture" + File.separator + 
					battleMenuFileName);
			menuSheet = ImageIO.read(menuTextureFile);
		}
		catch (IOException e){
			System.out.println("Could not find: " + battleMenuFileName);
		}	
	}
	
	private void loadHpBarTexture() {
		// try to open the file of the hp bar texture
		try{
			File barTextureFile = new File("images" + File.separator + 
					"Texture" + File.separator + 
					hpBarFileName);
			barSheet = ImageIO.read(barTextureFile);
		}
		catch (IOException e){
			System.out.println("Could not find: " + hpBarFileName);
		}	
	}
	
	
	////////////////////// Draw Battle Field /////////////////////////////
	private final static int BattleField_Height = 224;
	private final static int BattleField_Width = 480;
	
	private final static int BattleField_Grass_OFFSET_X = 497;
	private final static int BattleField_Grass_OFFSET_Y = 12;
	
	private final static int BattleField_Sand_OFFSET_X = 11;
	private final static int BattleField_Sand_OFFSET_Y = 242;
	
	private final static int BattleField_Magic_OFFSET_X = 11;
	private final static int BattleField_Magic_OFFSET_Y = 242;
	
	private BufferedImage drawBattleField(GroundType gndType){
		if (gndType == GroundType.GRASSLAND){
			return battleField.getSubimage(BattleField_Grass_OFFSET_X, BattleField_Grass_OFFSET_Y, 
					BattleField_Width, BattleField_Height);
		}
		else if (gndType == GroundType.SAND){
			return battleField.getSubimage(BattleField_Sand_OFFSET_X, BattleField_Sand_OFFSET_Y, 
					BattleField_Width, BattleField_Height);
		}
		else{
			return battleField.getSubimage(BattleField_Magic_OFFSET_X, BattleField_Magic_OFFSET_Y, 
					BattleField_Width, BattleField_Height);
		}
	}
	
	
	
	
	////////////////////// Draw Battle Menu /////////////////////////////
	private final static int BattleMenu_Height = 96;
	private final static int BattleMenu_Width = 480;
	
	private final static int BattleMenu_OFFSET_X = 594;
	private final static int BattleMenu_OFFSET_Y = 112;
	
	private BufferedImage drawBattleMenu(){
		return menuSheet.getSubimage(BattleMenu_OFFSET_X, BattleMenu_OFFSET_Y, 
				BattleMenu_Width, BattleMenu_Height);

	}
	
	
	/********************* Timer **********************/
	private Timer timer;
	private int counter;
	
	private void startTimer() {
		timer = new Timer(delayInMillis, new TimerListener());
		timer.start();
	}
	
	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	

}
