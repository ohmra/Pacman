package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import model.Perso;

public class InvisiblePacman extends State{
	private int duration;
	private Image up, down, left, right, stand; 
	public InvisiblePacman(int speed) {
		super(new Color(240, 242, 182), speed);
		duration = 5000;
		up = new ImageIcon("images/invisible_up.gif").getImage();
		down = new ImageIcon("images/invisible_down.gif").getImage();
		left = new ImageIcon("images/invisible_left.gif").getImage();
		right = new ImageIcon("images/invisible_right.gif").getImage();
		stand = new ImageIcon("images/invisible_stand.png").getImage();
	}
	@Override
	public void checkDuration(Perso p, int gameSpeed) {
		duration -= gameSpeed;
		Rectangle r = p.getBloc();
		if(r.x % r.width == 0 && r.y % r.height == 0) {
			if(duration <= 0) {
				p.setState(new NormalPacman(getSpeed()));
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
