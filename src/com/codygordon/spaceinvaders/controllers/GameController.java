package com.codygordon.spaceinvaders.controllers;

import java.awt.Point;

import com.codygordon.spaceinvaders.gameobjects.player.Player;
import com.codygordon.spaceinvaders.gameobjects.projectiles.Projectile;
import com.codygordon.spaceinvaders.input.FrameKeyListener;
import com.codygordon.spaceinvaders.input.GameKeyListener;
import com.codygordon.spaceinvaders.ui.screens.GameScreen;

public class GameController {

	public static final int PROJECTILE_SPEED = 10;
	
	private Player player;
	private GameScreen view;
	private GameKeyListener listener;
		
	public GameController(GameScreen screen) {
		this.view = screen;
		player = new Player();
		view.getObjectsToDraw().add(player);
		listener = new GameKeyListener();
		FrameKeyListener.getInstance().addListener(listener);
	}
	
	public void shoot() {
		int spawnX = (int)player.getLocation().getX() + (25 / 2);
		int spawnY = (int)player.getLocation().getY() - (50 / 2) - 5;
		Projectile projectile = new Projectile(PROJECTILE_SPEED);
		projectile.setLocation(new Point(spawnX, spawnY));
		view.getObjectsToDraw().add(projectile);
	}
	
	public Player getPlayer() {
		return this.player;
	}
}