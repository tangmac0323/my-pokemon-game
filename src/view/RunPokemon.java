package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import GameModel.Direction;
import GameModel.GameModel;

public class RunPokemon extends JFrame {
	
	private static final long serialVersionUID = 7487437405760007377L;

	// declare the main window
	private final static int DefaultHeight = 1200;
	private final static int DefaultWidth = 800;
	
	// declare the view
	private MainGameView mainGamePanel;
	private final static int DefaultGameHeight = 16 * 41;
	private final static int DefaultGameWidth = 16 * 41;
	
	// declare timer detail
	private Timer timer;
	public final static int delayInMillis = 100;
	
	// declare game variable
	private GameModel gameModel;
	
	
	// main function
	public static void main(String[] args) {
		RunPokemon mainFrame = new RunPokemon();
		mainFrame.setVisible(true);
	}
	
	// constructor
	public RunPokemon(){
		// define the location of the main window
		this.setTitle("Pokemon Safari Zone - Alpha v0.1");
		this.setSize(DefaultHeight, DefaultWidth);
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setFocusable(true);
		
		// add the window listener
		this.addWindowListener(new windowsOnExit());
		
		// set up the main game model
		mainGamePanel = new MainGameView();
		mainGamePanel.setSize(DefaultGameHeight, DefaultGameWidth);
		mainGamePanel.setLocation(25, 25);
		mainGamePanel.setBackground(Color.WHITE);
		Border gameBorder = new LineBorder(Color.BLACK, 2, true);
		mainGamePanel.setBorder(gameBorder);
		this.add(mainGamePanel);
		gameModel = new GameModel();
		gameModel.addObserver(mainGamePanel);
		
		System.out.println("GUI set up completed");
		this.addKeyListener(new myKeyListener());
		
		timer = new Timer(delayInMillis, new MoveListener());
		timer.start();
	}
	
	// move listener
	private class MoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			gameModel.update();
		}

	}
	
	// key board listener
	private class myKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent key) {	
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
			//mainGamePanel.requestFocus();
			System.out.println("New location: " + gameModel.getLocation());
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
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
			// TODO Auto-generated method stub
			
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
	
}
