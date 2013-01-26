package com.ottani.invaders.entity;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.ottani.invaders.app.Constants;

public class EnemyBomb extends Entity {
	private float dY;
	private static SpriteSheet bombImg;
	private Animation animation;
	private boolean shouldRemove = false;
	
	public EnemyBomb(float px, float py) throws SlickException {
		super(8, 8);
		if (bombImg==null) {
			bombImg = new SpriteSheet(new Image("res/bomb.png"), w, h);
		}
		Random r = new Random((long) (px*py));
		animation = new Animation(bombImg, r.nextInt(10)+5);
		
		this.px = px-w/2;
		this.py = py;
		this.dY = 5;
	}
	
	public void update(float delta) {
		if (shouldRemove) return;
		py += dY;
		if (py>=(Constants.HEIGHT-h)) this.shouldRemove=true;
	}
	
	public void render(Graphics g) throws SlickException {
		g.drawAnimation(animation, px, py);
	}

	public boolean isShouldRemove() {
		return shouldRemove;
	}

}
