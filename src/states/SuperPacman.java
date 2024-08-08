package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import model.Perso;

public class SuperPacman extends State{
	int duration;
	private Image up, down, left, right, stand; 
	public SuperPacman(int speed) {
		super(new Color(255, 128, 0), speed);
		duration = 5000;
		up = new ImageIcon("images/up.gif").getImage();
		down = new ImageIcon("images/down.gif").getImage();
		left = new ImageIcon("images/left.gif").getImage();
		right = new ImageIcon("images/right.gif").getImage();
		stand = new ImageIcon("images/stand.png").getImage();
	}
	public void intersect(Perso p) {
		p.reset();
	}
	@Override
	public void checkDuration(Perso p, int gameSpeed) {
		duration -= gameSpeed;
		Rectangle r = p.getBloc();
		if(r.x % r.width == 0 && r.y % r.height == 0) {
			if(duration <= 0) {
				p.setState(new NormalPacman(getSpeed()/2));
			}
		}
	}
	
	@Override
	public void draw(Graphics g, int dx, int dy, int x, int y, int width, int height) {
		if(dx == -1) 
			g.drawImage(left, x, y, width, height, null);
		
		else if (dx == 1) 
			g.drawImage(right, x, y, width, height, null);
		
		else if (dy == 1) 
			g.drawImage(down, x, y, width, height, null);
		
		else if (dy == -1) 
			g.drawImage(up, x, y, width, height, null);
		
		else
			g.drawImage(stand, x, y, width, height, null);
	}
}
