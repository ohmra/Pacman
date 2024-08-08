package states;

import java.awt.Color;
import java.awt.Graphics;

import model.Perso;

/**
 * This class represents a State of a character
 *
 */

public abstract class State {
	private Color color;
	private int speed;
	public State(Color color, int speed) {
		this.color = color;
		this.speed = speed;
	}
	
	/**
	 * Get the color
	 * @return The color
	 */
	public Color getColor() { return this.color; }
	
	/**
	 * Get the current speed
	 * @return The speed
	 */
	public int getSpeed() {	return this.speed; }
	
	/**
	 * Check if the character is reseted to its initial state (the return value depends on the character state)
	 * @return false by default
	 */
	public boolean isReseted() { return false; }
	
	/**
	 * Draw the character
	 * @param g
	 * @param dx Horizontal direction
	 * @param dy Vertical Direction
	 * @param x Position x
	 * @param y Position y
	 * @param width Width
	 * @param height Height
	 */
	public abstract void draw(Graphics g, int dx, int dy, int x, int y, int width, int height);
	
	/**
	 * Check the duration of the state
	 * @param p The character
	 * @param gameSpeed The game speed
	 */
	public abstract void checkDuration(Perso p, int gameSpeed);
}
