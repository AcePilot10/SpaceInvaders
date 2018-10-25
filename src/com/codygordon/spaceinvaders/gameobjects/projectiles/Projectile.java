package com.codygordon.spaceinvaders.gameobjects.projectiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.GameObject;
import com.codygordon.spaceinvaders.gameobjects.PhysicsObject;
import com.codygordon.spaceinvaders.gameobjects.barriers.Barrier;

public class Projectile extends PhysicsObject {

	public Projectile(int speed) {
		setSpeed(speed);
		setVelocity(new Point(0, 1));
		collider = new Rectangle();
		collider.width = 25;
		collider.height = 50;
	}
	
	@Override
	public void onCollision(GameObject obj) {
		System.out.println("Detected collision with " + obj.getClass().getName());
		if(obj instanceof Barrier) {
			explode();
		}
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
	
	public void explode() {
		System.out.println("Projectile exploding");
		GameContainer.getInstance().getController().destroyGameObject(this);
	}
}