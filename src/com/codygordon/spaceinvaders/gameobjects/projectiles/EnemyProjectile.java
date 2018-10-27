package com.codygordon.spaceinvaders.gameobjects.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import com.codygordon.spaceinvaders.gameobjects.GameObject;
import com.codygordon.spaceinvaders.gameobjects.barriers.Barrier;
import com.codygordon.spaceinvaders.gameobjects.enemies.Enemy;

public class EnemyProjectile extends Projectile {

	public EnemyProjectile(int speed) {
		super(speed);
	}
	
	@Override
	public void update(Graphics g) {
		g.setColor(Color.RED);
		super.update(g);
	}
	
	@Override
	public void onCollision(GameObject obj) {
		if(obj instanceof Barrier) {
			explode();
		}
	}
}