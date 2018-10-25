package com.codygordon.spaceinvaders.gameobjects.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.GameObject;

public class Player extends GameObject {

	private int speed;
	private Rectangle playerRect;

	public static final int MOVE_LEFT = 1;
	public static final int MOVE_RIGHT = 2;
	
	public Player() {
		location = new Point();
		playerRect = new Rectangle();
		playerRect.setBounds(0, 0, 50, 50);
		int height = GameContainer.getInstance().getMainFrame().getHeight();
		location.y = height - (int)(playerRect.getHeight() * 2);
		speed = 5;
	}
	
	@Override
	public void update(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(location.x,
				location.y,
				(int)playerRect.getWidth(),
				(int)playerRect.getHeight());
	}

	public void move(int direction) {
		if(direction == MOVE_LEFT) {
			location.x -= speed;
		} else if(direction == MOVE_RIGHT) {
			location.x += speed;
		}
	}
}