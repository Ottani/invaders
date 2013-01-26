package com.ottani.invaders.entity;

import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import com.ottani.invaders.app.Constants;

public class Player {
	private float dX;
	private float FRICTION = 0.9f;

	private float shootTimer;
	private Animation animation;
	private Animation explosionAnim;
	private Vector2f pos;
	private float w, h;
	private boolean exploding, alive;
	private float count;

	public Player() throws SlickException {
		w = 32.0f;
		h = 32.0f;
		pos = new Vector2f((Constants.WIDTH - w) / 2.0f, Constants.HEIGHT - h - 20);
		dX = 0.0f;
		shootTimer = 0;
		SpriteSheet ss = new SpriteSheet(new Image("ship.png"), 32, 32);
		animation = new Animation(ss, 250);
		SpriteSheet ssx = new SpriteSheet(new Image("res/explosion.png"), 32, 32);
		explosionAnim =  new Animation(ssx, 125);
		explosionAnim.setLooping(true);
		exploding = false;
		alive = true;
	}

	public void update(Input input, float delta) throws SlickException {
		final float max_speed = 0.5f;
		final float min_speed = -1.0f * max_speed;

		if (!exploding) {
			if (input.isKeyDown(Input.KEY_RIGHT)) {
				dX += 0.1f;
				if (dX > max_speed)
					dX = max_speed;
			}
			if (input.isKeyDown(Input.KEY_LEFT)) {
				dX -= 0.1f;
				if (dX < min_speed)
					dX = min_speed;
			}
		} else {
			count += delta;
			if (count>1800) {
				alive = false;
			}
		}
		
		pos.set(pos.getX()+dX*delta, pos.getY());

		if (pos.getX() < 0.0f) {
			pos.set(0.0f, pos.getY());
			dX = 0.0f;
		} else if (pos.getX()+w > Constants.WIDTH) {
			pos.set(Constants.WIDTH - w, pos.getY());
			dX = 0.0f;
		}
		dX *= FRICTION;
		if (Math.abs(dX) < 0.05f) dX = 0.0f;

		if (shootTimer > 0)
			shootTimer -= delta;
	}

	public void render(Graphics g) throws SlickException {
		g.drawAnimation(animation, pos.x, pos.y);
		if (exploding) g.drawAnimation(explosionAnim, pos.x, pos.y);
	}

	public void shoot(List<PlayerBullet> list) throws SlickException {
		if (!exploding && shootTimer <= 0) {
			list.add(new PlayerBullet(pos.x + w/2.0f, pos.y));
			shootTimer = 300;
		}
	}

	public boolean checkHit(float bx, float by, float bw, float bh) {
		if (exploding) return false;
		if (bx>=pos.x && bx+bw<=pos.x+w && by>=pos.y && by+bh<=pos.y+h) {
			exploding = true;
			count = 0;
			return true;
		}
		return false;
	}

	public boolean checkHit(EnemyBomb e) {
		return checkHit(e.getPx(), e.getPy(), e.getW(), e.getH());
	}
	
	public boolean isAlive() {
		return alive;
	}

}
