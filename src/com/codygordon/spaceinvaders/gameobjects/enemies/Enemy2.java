package com.codygordon.spaceinvaders.gameobjects.enemies;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy2 extends EnemyShooter {

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(location.x, 
				location.y, 
				collider.width, 
				collider.height);
		
		g.setColor(Color.RED);
		g.drawRect(collider.x,
				collider.y,
				collider.width,
				collider.height);
	}
}