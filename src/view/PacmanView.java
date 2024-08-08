package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.Timer;

import controller.Map;

/**
 * The display and configurations of the game
 *
 */

public class PacmanView extends JComponent{
	private int gameSpeed = 15;
	private Timer timer;
	private int key_x, key_y;
	private Map map;
	
	public PacmanView() {
		super();
		initVariables();
		keyListener();
		addTimer();
	}
	
	/*
	 * Timer to make the game move, refresh display according to the gamespeed
	 * Faster when gamespeed approaches 0
	 */
	public void addTimer() {
		timer = new Timer(gameSpeed, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
		timer.start();
	}
	
	public void initVariables() {
		map = new Map("map/map.png");
		key_x = 0;
		key_y = 0;
	}
	
	/*
	 * Keyboard listener
	 */
	public void keyListener() {
		addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_UP) {
					key_x = 0;
					key_y = -1;
				}
				else if(key == KeyEvent.VK_DOWN) {
					key_x = 0;
					key_y = 1;
				}
				else if(key == KeyEvent.VK_LEFT) {
					key_x = -1;
					key_y = 0;
				}
				else if(key == KeyEvent.VK_RIGHT) {
					key_x = 1;
					key_y = 0;
				}
			}
		});
		setFocusable(true);
	}
	
	/**
	 * 
	 * @return The widht of the map
	 */
	public int mapWidth() {
		return map.getWidth();
	}
	
	/**
	 * 
	 * @return The height of the map
	 */
	public int mapHeight() {
		return map.getHeight();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//draw the background of the game
		g.setColor(Color.black);
		g.fillRect(0, 0, map.getWidth(), map.getHeight());
		
		//draw the background of the informations of the game
		g.setColor(new Color(64, 38, 61));
		g.fillRect(0, map.getHeight(), map.getWidth(), 30);
		
		endGame(map.move(key_x, key_y, gameSpeed));
		map.draw(g);
	}
	
	/*
	 * end the game
	 */
	public void endGame(boolean win) {
		if(map.getPacman().getLives()<=0 || win)
			timer.stop();
	}
	
	/*
	 * restart the game/create a new game
	 */
	public void restart() {
		timer.start();
		initVariables();
	}
	
	public void setGameSpeed(int speed) {
		this.gameSpeed = speed;
		timer.setDelay(gameSpeed);
	}
}
