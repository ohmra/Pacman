package model;

import states.AlteredGhost;
import states.NormalGhost;

/**
 * This class represents a ghost
 *
 */
public class Ghost extends Perso{
	/**
	 * Constructor
	 * @param init_x
	 * @param init_y
	 * @param width
	 * @param height
	 * @param speed
	 */
	public Ghost(int init_x, int init_y, int width, int height, int speed) {
		super(init_x, init_y, speed, width, height);
		setState(new NormalGhost(speed));
	}
	
	/**
	 * Change the state of the ghost
	 */
	public void altered() {
		setState(new AlteredGhost(getInitialSpeed()/2));
	}
	
	/**
	 * Make the ghost move
	 * @param cases The list walls in the game
	 * @param width The width of the game
	 * @param height The height of the game
	 */
	public void move(Bloc[][] cases, int width, int height) {
		int key_ghost_dx=0;
		int key_ghost_dy=0;
		int random = (int) (Math.random() * 4);		//choose random directions
		switch(random) {
			case 0:
				key_ghost_dx = 0;
				key_ghost_dy = 1;
				break;
			case 1:
				key_ghost_dx = 0;
				key_ghost_dy = -1;
				break;
			case 2:
				key_ghost_dx = 1;
				key_ghost_dy = 0;
				break;
			case 3:
				key_ghost_dx = -1;
				key_ghost_dy = 0;
				break;
		}
		super.move(key_ghost_dx, key_ghost_dy, cases, width, height);
	}
}
