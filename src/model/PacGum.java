package model;

import java.awt.Color;

/**
 * This class represents a PacGum
 */
public class PacGum extends Bloc{
	
	/**
	 * Constructor
	 * @param x The x position
	 * @param y The y position
	 * @param color Color of the pacgum
	 */
	public PacGum(int x, int y, Color color)	{
		super(x+10, y+10, 8, 8);
		setColor(color);
	}
}
