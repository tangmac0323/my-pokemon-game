package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import GameModel.Direction;
import GameModel.GameModel;

public class RunPokemon extends JFrame {
	
	private static final long serialVersionUID = 7487437405760007377L;

	private String SAVEFILENAME_GAME = "PokemonGame.ser";
	// declare the main window
	private final static int DefaultHeight = 1200;
	private final static int DefaultWidth = 800;
	private JLabel statusBar;
	private JLabel missionBoard;
	private JTable inventoryTable;
	private JTable pokemonTable;
	
	// declare the view
	private MainGameView mainGamePanel;
	private final static int DefaultGameHeight = 16 * 41;
	private final static int DefaultGameWidth = 16 * 41;
	
	// declare timer detail
	private Timer timer;
	public final static int delayInMillis = 100;
	
	// declare game variable
	private GameModel gameModel;
	private boolean isOver;
	private boolean isWin;
	private boolean isLost;
	
	// main function
	public static void main(String[] args) {
		RunPokemon mainFrame = new RunPokemon();
		mainFrame.setVisible(true);
	}
	
	// constructor
	public RunPokemon(){
		int userPrompt = JOptionPane.showConfirmDialog(null, "Do you want to start with presvious saved data?");
		// if the user choose yes, load the saved file
		if (userPrompt == JOptionPane.YES_OPTION) {
			loadData();
		}
		// if the user choose no, use default
		else if (userPrompt == JOptionPane.NO_OPTION) {
			gameModel = new GameModel();
		}
		// chosen cancel
		else {
			System.exit(0);
		}
		initiatePokemonGame();
		//timer = new Timer(delayInMillis, new MoveListener());
		//timer.start();
	}
	
	private void initiatePokemonGame(){
				setUpMainWindow();
				setUpGameView();		
				addEventListener();
				setUpInfoBoard();
				setUpMissionBoard();
				setUpInventory();
				setUpPokemonTable();
				//System.out.println("GUI set up completed");
	}
	
	public void setUpMainWindow(){
		// define the location of the main window
		this.setTitle("Pokemon Safari Zone - Alpha v0.1");
		this.setSize(DefaultHeight, DefaultWidth);
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setFocusable(true);
		
	}
	
	public void setUpGameView(){
		// set up the main game model
		mainGamePanel = new MainGameView();
		mainGamePanel.setSize(DefaultGameHeight, DefaultGameWidth);
		mainGamePanel.setLocation(25, 25);
		mainGamePanel.setBackground(Color.WHITE);
		Border gameBorder = new LineBorder(Color.BLACK, 2, true);
		mainGamePanel.setBorder(gameBorder);
		this.add(mainGamePanel);
		gameModel.addObserver(mainGamePanel);
		gameModel.update();
	}
	
	public void addEventListener(){
		// add the key listener
		this.addKeyListener(new myKeyListener());
		// add the window listener
		this.addWindowListener(new windowsOnExit());
	}
	
	public void setUpInfoBoard(){
		statusBar = new JLabel("Trainer: " + gameModel.getTrainer().getID(), SwingConstants.LEFT);
		statusBar.setBounds(700, 25, 250, 30);
		statusBar.setFont(new Font("Times New Roman", Font.BOLD, 18));
		getContentPane().add(statusBar);
	}
	
	// show the mission board
	public void setUpMissionBoard(){
		missionBoard = new JLabel("<html>Mission Statistic:<br>" 
								+ "&nbsp;&nbsp;&nbsp;Step Count: " + gameModel.getStepCount() + " / " + gameModel.getMission().getStepCap() + "<br>"
								+ "&nbsp;&nbsp;&nbsp;Total Pokemon Count: " + gameModel.getTrainer().getPokemonCollection().getSize() + " / " + gameModel.getMission().getTotalRequirement() + "</html>",SwingConstants.LEFT);
		missionBoard.setBounds(700, 100, 250, 80);
		missionBoard.setFont(new Font("Times New Roman", Font.BOLD, 18));
		getContentPane().add(missionBoard);
	}
	
	// show the inventory table
	public void setUpInventory(){
		inventoryTable = new JTable(gameModel.getTrainer().getInventory());
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(gameModel.getTrainer().getInventory());
		inventoryTable.setRowSorter(sorter);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		inventoryTable.getColumn("Item Type").setCellRenderer( centerRenderer );		
		inventoryTable.getColumn("Quantity").setCellRenderer( centerRenderer );
		inventoryTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		inventoryTable.getColumnModel().getColumn(1).setPreferredWidth(40);

		JScrollPane pane = new JScrollPane(inventoryTable);
		pane.setBounds(700, 220, 270, 150);
		getContentPane().add(pane);
	}
	
	// show the inventory table
	public void setUpPokemonTable(){
		pokemonTable = new JTable(gameModel.getTrainer().getPokemonCollection());
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(gameModel.getTrainer().getPokemonCollection());
		pokemonTable.setRowSorter(sorter);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		pokemonTable.getColumn("Pokedex Index").setCellRenderer( centerRenderer );		
		pokemonTable.getColumn("Captured Time").setCellRenderer( centerRenderer );
		pokemonTable.getColumn("Nickname").setCellRenderer( centerRenderer );
		pokemonTable.getColumn("Type").setCellRenderer( centerRenderer );
		pokemonTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		pokemonTable.getColumnModel().getColumn(1).setPreferredWidth(40);
		pokemonTable.getColumnModel().getColumn(2).setPreferredWidth(40);
		
		JScrollPane pane = new JScrollPane(pokemonTable);
		pane.setBounds(700, 410, 405, 150);
		getContentPane().add(pane);
	}
	
	// add use item button
	public void setUpItemButton(){
		
	}
	
	
	// saving the pokemon game data
	public void saveData(){
		try {
			FileOutputStream saveData_Pokemon = new FileOutputStream(SAVEFILENAME_GAME);
			ObjectOutputStream outFile_Pokemon = new ObjectOutputStream(saveData_Pokemon);
			outFile_Pokemon.writeObject(gameModel);
			outFile_Pokemon.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// loading saved JukeBox object from a file
	public void loadData() {
		try {
			FileInputStream prevData = new FileInputStream(SAVEFILENAME_GAME);
			ObjectInputStream inFile = new ObjectInputStream(prevData);
			
			gameModel = (GameModel) inFile.readObject();
			inFile.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	// move listener
	private class MoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

		}

	}

	// key board listener
		private class myKeyListener implements KeyListener {

			@Override
			public void keyPressed(KeyEvent key) {	

			}

			@Override
			public void keyReleased(KeyEvent key) {
				if (!isOver){
					if (key.getKeyCode() == KeyEvent.VK_UP) {
						gameModel.moveTrainer(Direction.NORTH);
					}
					if (key.getKeyCode() == KeyEvent.VK_DOWN) {
						gameModel.moveTrainer(Direction.SOUTH);
					}
					if (key.getKeyCode() == KeyEvent.VK_LEFT) {
						gameModel.moveTrainer(Direction.WEST);
					}
					if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
						gameModel.moveTrainer(Direction.EAST);
					}
					// update infoboard
					missionBoard.setText("<html>Mission Statistic:<br>" 
								+ "&nbsp;&nbsp;&nbsp;Step Count: " + gameModel.getStepCount() + " / " + gameModel.getMission().getStepCap() + "<br>"
								+ "&nbsp;&nbsp;&nbsp;Total Pokemon Count: " + gameModel.getTrainer().getPokemonCollection().getSize() + " / " + gameModel.getMission().getTotalRequirement() + "</html>");
					//mainGamePanel.requestFocus();
					//System.out.println("New location: " + gameModel.getLocation());	
					
					//System.out.println("Hehe");
					// check win/lost
					checkGameResult();
				}

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		}
	
		
	// set up for the window on exit
	public class windowsOnExit implements WindowListener {

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			if (!isOver){
				int userPrompt = JOptionPane.showConfirmDialog(null, "Do you want to save the data?");
				// if the user choose yes, then save the data
				if (userPrompt == JOptionPane.YES_OPTION) {
					saveData();
					System.exit(0);
				}
				// if the user choose no, exit the program
				else {
					System.exit(0);
				}
			}
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void checkGameResult(){
		if (gameModel.isWin()){
			missionBoard.setText("<html>YOU WIN<br>&nbsp;&nbsp;&nbsp;THE GAME IS OVER</html>");
			isWin = true;
			isOver = true;
		}
		
		if (gameModel.isLost()){
			missionBoard.setText("<html>YOU LOST<br>&nbsp;&nbsp;&nbsp;THE GAME IS OVER</html>");
			isLost = true;
			isOver = true;
		}
	}
	
}
