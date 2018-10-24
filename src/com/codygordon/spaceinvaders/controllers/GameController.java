package com.codygordon.spaceinvaders.controllers;

import com.codygordon.spaceinvaders.gameobjects.Player;
import com.codygordon.spaceinvaders.ui.screens.GameScreen;

public class GameController {

	private Player player;

	private GameScreen view;
		
	public GameController(GameScreen screen) {
		this.view = screen;
		player = new Player();
		view.getObjectsToDraw().add(player);
	}
	
	public Player getPlayer() {
		return this.player;
	}
}