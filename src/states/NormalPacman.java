package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import model.Perso;

public class NormalPacman extends State{
	private Image up, down, left, right, stand; 
	public NormalPacman(int speed) {
		super(Color.yellow, speed);
		up = new ImageIcon("images/up.gif").getImage();
		down = new ImageIcon("images/down.gif").getImage();
		left = new ImageIcon("images/left.gif").getImage();
		right = new ImageIcon("images/right.gif").getImage();
		stand = new ImageIcon("images/stand.png").getImage();
	}
	
	@Override
	public void draw(Graphics g, int dx, int dy, int x, int y, int width, int height) {
		if(dx == -1) 
			g.drawImage(left, x, y, width, height, null);
		
		else if (dx == 1) 
			g.drawImage(right, x, y, width, height, null);
		
		else if (dy == 1) 
			g.drawImage(down, x, y, width, height, null);
		
		else if(dy == -1) 
			g.drawImage(up, x, y, width, height, null);
		
		else
			g.drawImage(stand, x, y, width, height, null);
	}
	
	@Override
	public boolean isReseted() {
		return true;
	}
	@Override
	public void checkDuration(Perso p, int gameSpeed) {
	}
}
