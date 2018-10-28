package com.codygordon.spaceinvaders.gameobjects.barriers;

import java.awt.Color;
import java.awt.Graphics;

import com.codygordon.spaceinvaders.gameobjects.GameObject;

public class Barrier extends GameObject {
	
	private Color color;
	
	public void setSize(int width, int height) {
		collider.width = width;
		collider.height = height;
	}
	
	@Override
	public void update(Graphics g) {
		super.update(g);	
		g.setColor(color);
		g.fillRect(location.x,
				location.y, 
				(int)collider.getWidth(),
				(int)collider.getHeight());
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}