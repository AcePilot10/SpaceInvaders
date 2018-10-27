package com.codygordon.spaceinvaders.gameobjects.projectiles;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerProjectile extends Projectile {

	public PlayerProjectile(int speed) {
		super(speed);
	}

	@Override
	public void update(Graphics g) {
		g.setColor(Color.BLUE);
		super.update(g);
	}
}