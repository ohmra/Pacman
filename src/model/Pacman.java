package model;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.List;
import states.InvisiblePacman;
import states.NormalPacman;
import states.SuperPacman;
/**
 * This class represents Pacman
 *
 */

public class Pacman extends Perso {
	private int lives;
	private int score;
	
	
	/**
	 * Constructor
	 * @param init_x
	 * @param init_y
	 * @param width Width of pacman
	 * @param height Height of pacman
	 * @param speed Initial speed of pacman
	 */
	public Pacman(int init_x, int init_y, int width, int height, int speed) {
		super(init_x, init_y, speed, width, height);
		setState(new NormalPacman(speed));
		lives = 3;
		score = 0;
	}
	
	/**
	 * @return The current score
	 */
	public int getScore() {	return this.score; }	
	
	/**
	 * @return The remaining lives
	 */
	public int getLives() {	return this.lives; }
	
	/**
	 * This function reset the pacman to its inital posistion, and cost one life
	 * (called when it is eaten by a ghost)
	 */
	@Override
	public void reset() {
		super.reset();
		lives--;
	}
	
	/**
	 * Make the pacman move
	 * @param key_x Horizontal direction
	 * @param key_y Vertical direction
	 * @param cases List of walls
	 * @param width Width of the game
	 * @param height Height of the game
	 * @param pacGums list of all the pacgums
	 * @return the pacgum eaten by pacman, otherwise null
	 */
	public PacGum move(int key_x, int key_y, Bloc[][] cases, int width, int height, List<PacGum> pacGums) {
		super.move(key_x, key_y, cases, width, height);					//we move
		Rectangle r = getBloc();
		for(PacGum g : pacGums){											//check if there is a pacgum
        	if(r.intersects(g.getBloc())) {								  //Update the score and change pacman/ghost state if according to the gum eaten
        		int ancienScore = this.score % 5000;
        		Color c = g.getColor();
        		if(c.equals(Color.gray))
        			this.score += 100;
        		else if(c.equals(Color.orange)) {
        			this.score += 500;
        			setState(new SuperPacman(getInitialSpeed()*2));
        			this.setColor(new Color(255, 128, 0));
        		}
        		else if(c.equals(Color.green))
            			this.score += 1000;
        		else {
        			this.score += 300;
        			setState(new InvisiblePacman(getInitialSpeed()));
        			this.setColor(new Color(240, 242, 182));
        		}
        		if(ancienScore > this.score % 5000)
        			this.lives++;
        		return g;
        	}
        }
		return null;
	}
}
