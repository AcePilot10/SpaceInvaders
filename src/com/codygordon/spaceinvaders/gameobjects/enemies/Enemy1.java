package com.codygordon.spaceinvaders.gameobjects.enemies;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy1 extends EnemyShooter {

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(location.x, 
				location.y, 
				collider.width, 
				collider.height);
	}
}