package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * This class represents a bloc of pixels
 *
 */

public abstract class Bloc {
	private Rectangle bloc;
	private Color color;
	
	/**
	 * Constructor
	 * @param x The starting x position
	 * @param y The starting y position
	 * @param width Width of the bloc
	 * @param height Height of the bloc
	 */
	public Bloc(int x, int y, int width, int height) {
		bloc = new Rectangle(x, y, width, height);
	}
	
	/**
	 * Change the color of the bloc
	 * @param c The new color
	 */
	public void setColor(Color c) {	this.color = c;	}	
	
	/**
	 * @return The current color
	 */
	public Color getColor() { return this.color; }	
	
	/**
	 * To get the Rectangle instance of the bloc
	 * @return The Rectangle object
	 */
	public Rectangle getBloc() { return this.bloc; }
	
	/**
	 * Draw the bloc (the Rectangle)
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(bloc.x, bloc.y, bloc.width, bloc.height);
	}
	
}
