package com.paulo.invaders;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;

public class Enemy {
	private float px, py;
	private float w, h;
	private Animation animation;
	private boolean alive;
	private boolean exploding;
	
	public Enemy(float px, float py, float w, float h, SpriteSheet ss) {
		this.px = px;
		this.py = py;
		this.w = w;
		this.h = h;
		this.alive = true;
		this.exploding = false;
		animation = new Animation(ss, 250);
		animation.setLooping(false);
		animation.stop();
	}

	public void setPos(float px, float py) {
		this.px = px;
		this.py = py;
	}
	
	public boolean isFinishedExplosion() {
		if (exploding && animation.isStopped()) {
			alive = false;
			return true;
		}
		return false;
	}

	public void render(Graphics g) {
		if (!alive) return;
		g.drawAnimation(animation, px, py);
		if (exploding && animation.isStopped()) {
			alive = false;
		}
	}

	public boolean checkHit(float bx, float by, float bw, float bh) {
		if (!alive || exploding) return false;
		if (bx>=px && bx+bw<=px+w && by>=py && by+bh<=py+h) {
			animation.start();
			animation.setCurrentFrame(1);
			exploding = true;
			return true;
		}
		return false;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void shoot() {
		
	}
	
	
}
