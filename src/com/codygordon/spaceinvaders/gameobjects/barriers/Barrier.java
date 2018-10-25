package com.codygordon.spaceinvaders.gameobjects.barriers;

import java.awt.Color;
import java.awt.Graphics;

import com.codygordon.spaceinvaders.gameobjects.GameObject;

public class Barrier extends GameObject {

	public static final int WIDTH = 200;
	public static final int HEIGHT = 65;
	
	public Barrier() {
		super();
		collider.width = WIDTH;
		collider.height = HEIGHT;
	}
	
	@Override
	public void update(Graphics g) {
		super.update(g);	
		g.setColor(Color.CYAN);
		g.fillRect(location.x,
				location.y, 
				(int)collider.getWidth(),
				(int)collider.getHeight());
	}
}