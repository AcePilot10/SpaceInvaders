package com.codygordon.spaceinvaders.gameobjects.projectiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.GameObject;
import com.codygordon.spaceinvaders.gameobjects.PhysicsObject;
import com.codygordon.spaceinvaders.gameobjects.ambience.Explosion;
import com.codygordon.spaceinvaders.gameobjects.barriers.Barrier;
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy;

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
		if(obj instanceof Barrier) {
			explode();
		} else if(obj instanceof Enemy) {
			Enemy enemy = (Enemy)obj;
			enemy.die();
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
		Explosion explosion = new Explosion(750, 1, 5, 5);
		Point location = getLocation();
		location.x += collider.width / 2;
		explosion.setLocation(location);
		GameContainer.getInstance().getController().initGameObject(explosion);
		explosion.start();
				
		GameContainer.getInstance().getController().destroyGameObject(this);
	}
}