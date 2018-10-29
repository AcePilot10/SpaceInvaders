package com.codygordon.spaceinvaders.gameobjects;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class GameObject implements Cloneable {
	
	protected Point location;
	protected Rectangle collider;
	public String name;
	
	public GameObject() {
		location = new Point();
		collider = new Rectangle();
	} 
	
	public void update(Graphics g) { 
		collider.setLocation(location.x, location.y);
		draw(g);
	}
	
	public void draw(Graphics g) { }
	
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
	
	public void setCollider(Rectangle newCollider) {
		this.collider = newCollider;
	}
	
	@Override
	public GameObject clone() {
		try {
			return (GameObject)super.clone();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}