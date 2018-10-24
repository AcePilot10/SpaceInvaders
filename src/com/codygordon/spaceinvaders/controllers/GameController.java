package com.codygordon.spaceinvaders.controllers;

import com.codygordon.spaceinvaders.gameobjects.Player;
import com.codygordon.spaceinvaders.input.FrameKeyListener;
import com.codygordon.spaceinvaders.input.GameKeyListener;
import com.codygordon.spaceinvaders.ui.screens.GameScreen;

public class GameController {

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
	
	public Player getPlayer() {
		return this.player;
	}
}