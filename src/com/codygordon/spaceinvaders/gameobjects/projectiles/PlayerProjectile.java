package com.codygordon.spaceinvaders.gameobjects.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import com.codygordon.spaceinvaders.gameobjects.GameObject;
import com.codygordon.spaceinvaders.gameobjects.barriers.Barrier;
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy;

public class PlayerProjectile extends Projectile {

	public PlayerProjectile(int speed) {
		super(speed);
	}

	@Override
	public void update(Graphics g) {
		g.setColor(Color.BLUE);
		super.update(g);
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
}