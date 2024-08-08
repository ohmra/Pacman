package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import model.Perso;

public class NormalGhost extends State{
	private Image up, down, left, right; 
	public NormalGhost(int speed) {
		super(Color.red, speed);
		up = new ImageIcon("images/ghost_up.gif").getImage();
		down = new ImageIcon("images/ghost_down.gif").getImage();
		left = new ImageIcon("images/ghost_left.gif").getImage();
		right = new ImageIcon("images/ghost_right.gif").getImage();
	}
	@Override
	public void checkDuration(Perso p, int gameSpeed) {
		
	}
	
	@Override
	public void draw(Graphics g, int dx, int dy, int x, int y, int width, int height) {
		if(dx == -1) 
			g.drawImage(left, x, y, width, height, null);
		
		else if (dx == 1) 
			g.drawImage(right, x, y, width, height, null);
		
		else if (dy == 1) 
			g.drawImage(down, x, y, width, height, null);
		
		else
			g.drawImage(up, x, y, width, height, null);
		
	}
}
