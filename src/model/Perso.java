package model;

import java.awt.Graphics;
import java.awt.Rectangle;

import states.State;

/**
 *	This class represents a character
 */

public abstract class Perso extends Bloc{
	private int dx;
	private int dy;
	private final int INITIAL_X;
	private final int INITIAL_Y;
	private final int INITIAL_SPEED;
	private State state;
	
	/**
	 * Constructor of a character
	 * @param init_x The initial x position
	 * @param init_y The inital y position
	 * @param speed The initial speed
	 * @param width The width
	 * @param height The height
	 * @param c The color
	 */
	public Perso(int init_x, int init_y, int speed, int width, int height) {
		super(init_x, init_y, width, height);
		INITIAL_X = init_x;
		INITIAL_Y = init_y;
		INITIAL_SPEED = speed;
		dx = 0;
		dy = 0;
	}
	
	/**
	 * Get the state of the character
	 * @return Current state
	 */
	public State getState() {
		return this.state;
	}
	
	/**
	 * Change the state of the character
	 * @param state The new state
	 */
	public void setState(State state) {
		this.state = state;
	}
	
	/**
	 * Get the initial speed
	 * @return The initial speed
	 */
	public int getInitialSpeed() {
		return INITIAL_SPEED;
	}
	
	/**
	 * Make the character to its initial position
	 */
	public void reset() {
		Rectangle r = getBloc();
		r.setLocation(INITIAL_X, INITIAL_Y);
		dx = 0;
		dy = 0;
	}
	
	/**
	 * Draw the character according to its position and color
	 * @param g
	 * @param width Width of the game
	 * @param height Height of the game
	 */
	@Override
	public void draw(Graphics g) {
		//on récupère le rectangle du personnnage
		Rectangle r = getBloc();
		state.draw(g, dx, dy, r.x, r.y, r.width, r.height);
	}
	
	/*
	 * Check if the current position is valid
	 * (when we encounter a wall)
	 * @return true if the position is valid, false otherwise
	 */
	private boolean canMove(int x, int y, int width, int height, Bloc[][] cases) {
    	Rectangle rect = new Rectangle(x, y, width, height);
    	for(int i = 0; i<cases.length; i++)
	    	for(Bloc c : cases[i]) {
	    		if(c != null && rect.intersects(c.getBloc()))
	    			return false;
	    	}
    	return true;
    }
	
	/**
	 * Make the character teleport to the other side of the platform
	 * @param r the character
	 * @param width Width of the game
	 * @param height Height of the game
	 */
	private void wrapAround(Rectangle r, int width, int height) {
		if(dx != 0 || dy != 0) {					//only if we are moving
			int x = r.x;
			int y = r.y;
			if(dx == 1 && r.x >= width-state.getSpeed())
				x = 0;
			else if(dx == -1 && r.x <= 0)
				x = width;
			else if(dy == 1 && r.y >= height-state.getSpeed())
				y = 0;
			else if(dy == -1 && r.y <= 0)
				y = height;
			r.setLocation(x, y);
		}
	}
	
	
	/**
	 * Make the character move
	 * @param key_x Horizontal direction : -1 = to the left , 1 = to the right
	 * @param key_y Vertical direction : -1 = up , 1 = down
	 * @param cases List of the walls
	 * @param width Width of the game
	 * @param height Height of the game
	 */
	public void move(int key_x, int key_y, Bloc[][] cases, int width, int height) {
		Rectangle r = getBloc();
		if(r.x % r.width == 0 && r.y % r.height == 0) {
			if(canMove(r.x + state.getSpeed() * key_x, r.y + state.getSpeed() * key_y, r.width, r.height, cases)) {	//Check if we can move to the desired direction make by the use
				dx = key_x;																							//if we can, change the value according to it
				dy = key_y;																							
			}
			else if(!(canMove(r.x + state.getSpeed() * dx, r.y + state.getSpeed() * dy, r.width, r.height, cases))) {	//Check if we can move with the current directions
				dx = 0;																									
				dy = 0;																									//If we cannot, stop
			}
		}
		wrapAround(r, width, height);
		r.setLocation(r.x + state.getSpeed() * dx, r.y + state.getSpeed() * dy);
	}
}
