package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import model.Perso;



public class AlteredGhost extends State{
	private int duration;
	private Image altered, returning; 
	public AlteredGhost(int speed) {
		super(Color.blue, speed);
		duration = 5000;
		altered = new ImageIcon("images/ghost_weak.gif").getImage();
		returning = new ImageIcon("images/ghost_returning_normal.gif").getImage();
	}
	
	@Override
	public boolean isReseted() {
		return true;
	}
	
	@Override
	public void draw(Graphics g, int dx, int dy, int x, int y, int width, int height) {
		if(duration <= 1500)
			g.drawImage(returning, x, y, width, height, null);
		else
			g.drawImage(altered, x, y, width, height, null);
	}
	
	@Override
	public void checkDuration(Perso p, int gameSpeed) {
		duration -= gameSpeed;
		Rectangle r = p.getBloc();
		if(r.x % r.width == 0 && r.y % r.height == 0) {
			if(duration <= 0) {
				p.setState(new NormalGhost(getSpeed()*2));
			}
		}
	}
		
}
