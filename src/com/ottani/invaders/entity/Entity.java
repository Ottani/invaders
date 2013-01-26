package com.ottani.invaders.entity;

public class Entity {
	protected float px, py;
	protected final int w, h;
	
	public Entity(int w, int h) {
		this.w = w;
		this.h = h;
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
