package com.codygordon.spaceinvaders.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Player extends GameObject {

	private int speed;

	public static final int MOVE_LEFT = 1;
	public static final int MOVE_RIGHT = 2;
	
	public Player() {
		location = new Point();
		speed = 5;
	}
	
	@Override
	public void update(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(location.x, location.y, 50, 50);
	}

	public void move(int direction) {
		if(direction == MOVE_LEFT) {
			
			location.x -= speed;
			
		} else if(direction == MOVE_RIGHT) {
			
			location.x += speed;
			
		}
	}
}