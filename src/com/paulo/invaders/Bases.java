package com.paulo.invaders;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Bases {
	private static final int MAX = 6;
	private float px, py, w, h, sx;
	private Animation[] animations;
	private int[] state;
	
	public Bases() throws SlickException {
		w = 48.0f;
		h = 48.0f;
		sx = ((800-100)-(MAX*w))/MAX-1;
		px = (800-MAX*w-(MAX-1)*sx)/2;
		py = 470;
		
		SpriteSheet ss = new SpriteSheet(new Image("bases.png"), (int)w, (int)h);
		animations = new Animation[MAX];
		state = new int[MAX];
		for( int i=0; i<MAX; ++i) {
			animations[i] = new Animation(ss, 250);
			animations[i].setLooping(false);
			animations[i].stop();
			state[i] = 4;
		}

	}
	
	public void render(Graphics g) {
		for( int i=0; i<MAX; ++i) {
			if (state[i]<=0) continue;
			g.drawAnimation(animations[i], px+((sx+w)*i), py);
		}
	}
	
	public boolean checkHit(float bx, float by, float bw, float bh) {
		for( int i=0; i<MAX; ++i) {
			if (state[i]<=0) continue;
			float tx = px+((sx+w)*i);
			if (bx>=tx && bx+bw<=tx+w && by>=py && by+bh<=py+h) {
				--state[i];
				if(state[i]>0) animations[i].setCurrentFrame(4-state[i]);
				return true;
			}
		}
		return false;
	}

	public boolean checkHit(PlayerBullet e) {
		return checkHit(e.getPx(), e.getPy(), e.getW(), e.getH());
	}
	

}
