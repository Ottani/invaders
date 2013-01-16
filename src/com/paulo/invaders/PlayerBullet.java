package com.paulo.invaders;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class PlayerBullet {
	private float px, py, dY;
	private static final int w=5;
	private static final int h=15;
	private static SpriteSheet missileImg;
	private Animation animation;
	private boolean shouldRemove = false;
	
	public PlayerBullet(float px, float py) throws SlickException {
		if (missileImg==null) {
			missileImg = new SpriteSheet(new Image("res/missile.png"), w, h);
		}
		Random r = new Random((long) (px*py));
		animation = new Animation(missileImg, r.nextInt(10)+5);
		
		this.px = px-w/2;
		this.py = py-h;
		this.dY = -4;
	}
	
	public void update(float delta) {
		if (shouldRemove) return;
		py += dY;
		if (py<=0) this.shouldRemove=true;
	}
	
	public void render(Graphics g) throws SlickException {
		g.drawAnimation(animation, px, py);
	}

	public boolean isShouldRemove() {
		return shouldRemove;
	}

	public float getPx() {
		return px;
	}

	public float getPy() {
		return py;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}
	
	
	
}
