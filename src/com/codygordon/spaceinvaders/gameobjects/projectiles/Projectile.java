package com.codygordon.spaceinvaders.gameobjects.projectiles;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.PhysicsObject;
import com.codygordon.spaceinvaders.gameobjects.ambience.Explosion;

public class Projectile extends PhysicsObject {

	public Projectile(int speed) {
		setSpeed(speed);
		setVelocity(new Point(0, 1));
		collider = new Rectangle();
		collider.width = 5;
		collider.height = 15;
	}
	
	@Override
	public void update(Graphics g) {
		super.update(g);
		g.fillRect(location.x,
				location.y,
				collider.width, 
				collider.height);
	}
	
	public void explode() {
		Explosion explosion = new Explosion(750, 1, 5, 5);
		Point location = getLocation();
		location.x += collider.width / 2;
		explosion.setLocation(location);
		GameContainer.getInstance().getController().initGameObject(explosion);
		explosion.start();
		GameContainer.getInstance().getController().destroyGameObject(this);
	}
}