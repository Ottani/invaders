package com.ottani.invaders.entity;

import java.util.List;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.ottani.invaders.app.Constants;

public class EnemyPack {
	
	private float px, py;
	private float dx, dy;
	private float sx, sy;
	private float distDown;
	private float maxX, minX, maxY, minY;
	private float w, h;
	private float vel;
	private Enemy[][] enemies;
	private int qty;
	private int cols;
	private int rows;
	private boolean goingDown;
	private Random rnd = new Random(System.currentTimeMillis());
	
	public EnemyPack() throws SlickException {
		px = 0;
		py = 20;
		dx = 0.05f;
		dy = 0.05f;
		vel = 1.0f;
		sx = 64;
		sy = 40;
		cols = 7;
		rows = 5;
		w =  32;
		h = 24;
		goingDown = false;
		enemies = new Enemy[cols][rows];
		qty = cols*rows;
		SpriteSheet ss = new SpriteSheet(new Image("enemy01.png"),(int)w, (int)h);
		for (int i = 0; i < cols; ++i) {
			for (int j = 0; j < rows; ++j) {
				enemies[i][j] = new Enemy(i*sx+px, j*sy+py, w, h, ss);
			}
		}
		calculateBox();
	}
	
	public void update(float delta, List<EnemyBomb> list) throws SlickException {
		
		if (!goingDown) {
			if (moveBoxX(delta)) {
				dx *= -1.0f;
				distDown = sy*2/3;
				goingDown = true;
			}
		} else {
			moveBoxY(delta);
			if (distDown<=0) {
				distDown = 0;
				goingDown = false;
			}
		}
		
		boolean shouldCalc = false;
		for (int i = 0; i < cols; ++i) {
			for (int j = 0; j < rows; ++j) {
				Enemy e = enemies[i][j];
				if (e.isFinishedExplosion()) {
					shouldCalc = true;
				} else {
					if (e.isAlive()) {
						e.setPos(i*sx+px, j*sy+py);
						if (rnd.nextDouble()<0.003d) {
							e.shoot(list);
						}
					}
				}
			}
		}
		
		if(shouldCalc) calculateBox();
	}
	
	public void render(Graphics g) throws SlickException {
		for (int i = 0; i < cols; ++i) {
			for (int j = 0; j < rows; ++j) {
				enemies[i][j].render(g);
			}
		}
	}
	
	private boolean moveBoxX(float delta) {
		boolean hitWall = false;
		float diff = (dx*vel*delta);
		if (minX+diff<0) {
			diff = 0-minX;
			hitWall = true;
		} else if (maxX+diff>Constants.WIDTH) {
			diff = Constants.WIDTH-maxX;
			hitWall = true;
		}
		px += diff;
		minX += diff;
		maxX += diff;
		
		return hitWall;
	}
	
	private void moveBoxY(float delta) {
		float diff = (dy*vel*delta);
		if (distDown-diff<0) {
			diff = distDown;
		}
		py += diff;
		minY += diff;
		maxY += diff;
		distDown -= diff;
	}
	
	private void calculateBox() {
		minX = (cols+1)*sx+px;
		minY = (rows+1)*sy+py;
		maxX = px-sx;
		maxY = py-sy;
		qty = 0;
		
		for (int i = 0; i < cols; ++i) {
			for (int j = 0; j < rows; ++j) {
				if (enemies[i][j].isAlive()) {
					float x = i*sx+px;
					if (x<minX) minX = x;
					if (x+w>maxX) maxX = x+w;
				
					float y = j*sy+py;
					if (y<minY) minY = y;
					if (y+h>maxY) maxY = y+h;
					
					++qty;
				}
			}
		}
	}
	
	public boolean checkHit(float bx, float by, float bw, float bh) {
		if (bx>=minX && bx+bw<=maxX && by>=minY && by+bh<=maxY) {
			for (int i = 0; i < cols; ++i) {
				for (int j = 0; j < rows; ++j) {
					if (enemies[i][j].checkHit(bx, by, bw, bh) ) {
						vel += 0.08f;
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean checkHit(Entity e) {
		return checkHit(e.getPx(), e.getPy(), e.getW(), e.getH());
	}
	
	public int getQty() {
		return qty;
	}

}
