package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import model.Wall;
import model.Ghost;
import model.PacGum;
import model.Pacman;

public class Map {
	private int width;
	private int height;
	
	private Wall[][] walls;
	private List<PacGum> pacGums;
	private List<Ghost> ghosts;
	private Pacman pacman;
	private static final int BLOC_SIZE = 24;
	private static final int INITIAL_SPEED = BLOC_SIZE / 8;
	private BufferedImage map;
	
	public Map(String path)
	{
		ghosts = new ArrayList<>();
		pacGums = new ArrayList<>();
		createMap(path);
		int[] pixels = new int[width*height];
		map.getRGB(0, 0, width, height, pixels, 0, width);
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++) {
				int val = pixels[j + (i * width)];
				switch(val) {
					case 0xff000000:
						walls[i][j] = new Wall(j*BLOC_SIZE, i*BLOC_SIZE, BLOC_SIZE, BLOC_SIZE);
						break;
					case 0xffffff00:
						pacman = new Pacman(j*BLOC_SIZE, i*BLOC_SIZE, BLOC_SIZE, BLOC_SIZE, INITIAL_SPEED);
						break;
					case 0xffff0000:
						this.ghosts.add(new Ghost(j * BLOC_SIZE, i * BLOC_SIZE, BLOC_SIZE, BLOC_SIZE, INITIAL_SPEED));
						break;
					default:
						createGum(i, j, val);
						break;
				}
			}
	}
	
	public void createGum(int i, int j, int val) {
		Color c;
		if(val == 0xff00ff00) 
			c = Color.green;
		else if(val == 0xffff00ff)
			c = new Color(255, 0, 255);
		else if(val == 0xffff5a00)
			c = Color.orange;
		else
			c = Color.gray;
		this.pacGums.add(new PacGum(j * BLOC_SIZE, i * BLOC_SIZE, c));
	}
	
	public void createMap(String path) {
		try {
			map = ImageIO.read(new File(path));
		}catch(IOException e){
			System.err.println("Path not found:" + e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
		this.width = map.getWidth();
		this.height = map.getHeight();
		walls = new Wall[height][width];
		int[] pixels = new int[this.width * this.height];
		map.getRGB(0, 0, width, height, pixels, 0, width);
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++)
				if(pixels[j + (i*width)] == 0xff000000)
					walls[i][j] = new Wall(j*BLOC_SIZE, i*BLOC_SIZE, BLOC_SIZE, BLOC_SIZE);
	}
	
	public void moveGhost(Ghost f) {			
		f.move(walls, width*BLOC_SIZE, height*BLOC_SIZE);
		
		if(f.getBloc().intersects(pacman.getBloc())) {
    		if(pacman.getState().isReseted())
        		pacman.reset();
    		
    		else if(f.getState().isReseted())
    			f.reset();
    	}
	}
	
	public boolean move(int key_x, int key_y, int gameSpeed)
	{
		pacman.getState().checkDuration(pacman, gameSpeed);
		PacGum g = pacman.move(key_x, key_y, walls, width*BLOC_SIZE, height*BLOC_SIZE, pacGums);
		if(g != null) {
			if(g.getColor() == Color.green)
				createMap("map/map2.png");
			else if(g.getColor() == Color.orange) {
				for(Ghost f : ghosts)
					f.altered();
			}
			pacGums.remove(g);
		}
		
		for(Ghost f : ghosts) {
			f.getState().checkDuration(f, gameSpeed);
			moveGhost(f);
		}
		
		if(pacGums.size() <= 0)
			return true;
		return false;
	}
	
	public void draw(Graphics g){
		for(int i = 0; i < height; i++)
			for(Wall c : walls[i])
				if(c != null)
					c.draw(g);
		
		for(PacGum gom : pacGums) 
			gom.draw(g);
		
		for(Ghost f : ghosts)
			f.draw(g);
			
		pacman.draw(g);
		
		g.setFont(new Font("", Font.BOLD, 28));
		g.setColor(Color.BLACK);
		g.drawString("Vies : " + this.pacman.getLives() + "         Score : " + this.pacman.getScore(), 20, (height+1) * BLOC_SIZE);
	}
	
	public int getWidth() {	return this.width*BLOC_SIZE; }
	public Pacman getPacman() {	return this.pacman;	}	
	public int getHeight() { return this.height*BLOC_SIZE; }
}
