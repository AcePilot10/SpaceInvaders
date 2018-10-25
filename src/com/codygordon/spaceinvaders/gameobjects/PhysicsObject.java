package com.codygordon.spaceinvaders.gameobjects;

import java.awt.Graphics;
import java.awt.Point;

public class PhysicsObject extends GameObject {

	private int speed;
	private Point velocity;
	
	protected void updatePhysics() {
		location.x -= velocity.x * speed;
		location.y -= velocity.y * speed;
	}
	
	@Override
	public void update(Graphics g) {
		updatePhysics();
		super.update(g);
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public Point getVelocity() {
		return this.velocity;
	}
	
	public void setVelocity(Point velocity) {
		this.velocity = velocity;
	}
}