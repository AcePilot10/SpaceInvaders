package com.codygordon.spaceinvaders.gameobjects;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected Point location;
	protected Rectangle collider;
	
	public GameObject() {
		location = new Point();
		collider = new Rectangle();
	}
	
	public void update(Graphics g) { 
		collider.setLocation(location.x, location.y);
	}
	
	public void onCollision(GameObject col) { }
	
	public Point getLocation() {
		return this.location;
	}
	
	public void setLocation(Point location) {
		this.location= location;
	}
	
	public Rectangle getCollider() {
		return this.collider;
	}
}