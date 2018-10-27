package com.codygordon.spaceinvaders.gameobjects.projectiles;

import java.awt.Color;
import java.awt.Graphics;

public class EnemyProjectile extends Projectile {

	public EnemyProjectile(int speed) {
		super(speed);
	}
	
	@Override
	public void update(Graphics g) {
		g.setColor(Color.RED);
		super.update(g);
	}
}