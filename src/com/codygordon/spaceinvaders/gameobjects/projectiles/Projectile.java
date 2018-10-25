package com.codygordon.spaceinvaders.gameobjects.projectiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.codygordon.spaceinvaders.gameobjects.PhysicsObject;

public class Projectile extends PhysicsObject {

	public Projectile(int speed) {
		setSpeed(speed);
		setVelocity(new Point(0, 1));
	}
	
	@Override
	public void update(Graphics g) {
		super.update(g);
		g.setColor(Color.BLUE);
		g.fillRect(location.x,
				location.y,
				25, 
				50);
	}
}