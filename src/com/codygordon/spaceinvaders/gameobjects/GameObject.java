package com.codygordon.spaceinvaders.gameobjects;

import java.awt.Graphics;
import java.awt.Point;

public abstract class GameObject {
	
	protected Point location;
	
	public void update(Graphics g) {
		
	}
	
	public Point getLocation() {
		return this.location;
	}
}