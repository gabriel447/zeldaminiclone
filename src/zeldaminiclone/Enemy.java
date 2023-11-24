package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends Rectangle{
	
	public int spd = 1;
	public int right = 1, up = 0, down = 0, left = 0;
	
	public int curAnimation = 0;
	
	public int curFrames = 0, targetFrames = 15;
		
	public boolean shoot = false;
	
	public int dir = 1;
	
	public Enemy(int x, int y) {
		super(x, y, 32, 32);
	}
	
	
	public void followPlayer() {
		Player p = Game.player;
		if(x < p.x && World.isFree(x+spd, y)) {
			if(new Random().nextInt(100) < 50)
			x+=spd;
		}else if(x > p.x && World.isFree(x-spd, y)) {
			if(new Random().nextInt(100) < 50)
			x-=spd;
		}
		
		if(y < p.y && World.isFree(x, y+spd)) {
			if(new Random().nextInt(100) < 50)
			y+=spd;
		}else if(y > p.y && World.isFree(x, y-spd)) {
			if(new Random().nextInt(100) < 50)
			y-=spd;
		}
	}
	
	public void tick() {
		boolean moved = true;
		
		followPlayer();
		
		
		if(moved) {
		curFrames++;
		if(curFrames == targetFrames) {
			curFrames = 0;
			curAnimation++;
			if(curAnimation == Spritesheet.enemy_front.length) {
				curAnimation = 0;
			}
		}
		}
	
	}
	
	public void render(Graphics g) {
		g.drawImage(Spritesheet.enemy_front[curAnimation], x, y, 32, 32, null);
	}
	
}
