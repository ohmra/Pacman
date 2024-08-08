package model;
import java.awt.Color;

/**
 * This class represents a wall 
 * Color(33, 0, 127)
 */

public class Wall extends Bloc{
	
	/**
	 * Constructor
	 * @param x La position x
	 * @param y La position y
	 * @param width Width of the wall
	 * @param height Height of the wall
	 */
	public Wall(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setColor(new Color(33, 0, 127));
	}
}
