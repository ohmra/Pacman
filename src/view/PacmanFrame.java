package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * The window of the game
 *
 */

public class PacmanFrame{
	
	private JFrame frame;
	private PacmanView pacmanView;
	private JMenuItem newGame;	
	private JMenuItem quit;
	private JMenu menu;
	private JMenuBar menuBar;
	private JMenu gameSpeedMenu;
//	private JMenuItem fast;
	private JMenuItem normal, slow;
	private ActionListener al;
	
	public PacmanFrame() {
		frame = new JFrame("Pacman");		
		pacmanView = new PacmanView();
		initMenu();
		initActionListener();
		frame.setContentPane(pacmanView);
		frame.setSize(pacmanView.mapWidth()+14, pacmanView.mapHeight()+24+65);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void initMenu() {
		newGame = new JMenuItem("New Game");
		quit = new JMenuItem("Quit");
		menu = new JMenu("Menu");
		
		gameSpeedMenu = new JMenu("Game Speed");
//		fast = new JMenuItem("Fast");
		slow = new JMenuItem("Slow");
		normal = new JMenuItem("Normal");
		
		gameSpeedMenu.add(slow);
		gameSpeedMenu.add(normal);
//		gameSpeedMenu.add(fast);
		
		menu.add(newGame);
		menu.add(gameSpeedMenu);
		menu.add(quit);

		menuBar = new JMenuBar();
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
	}
	
	public void initActionListener() {
		al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(e.getActionCommand()) {
				case "New Game":
					pacmanView.restart();
					break;
				case "Slow":
					pacmanView.setGameSpeed(25);
					break;
				case "Normal":
					pacmanView.setGameSpeed(15);
					break;
//				case "Fast":
//					pacmanView.setGameSpeed(50);
//					break;
				case "Quit":
					System.exit(0);
				}
			}
		};
		newGame.addActionListener(al);
		quit.addActionListener(al);
//		fast.addActionListener(al);
		slow.addActionListener(al);
		normal.addActionListener(al);
		
	}
}
