package com.codygordon.spaceinvaders.input;

import java.awt.event.KeyEvent;

import com.codygordon.spaceinvaders.controllers.GameController;
import com.codygordon.spaceinvaders.game.GameContainer;
import com.codygordon.spaceinvaders.gameobjects.Player;
import com.codygordon.spaceinvaders.interfaces.KeyEventListener;

public class GameKeyListener implements KeyEventListener {

	private GameController controller;
	
	public GameKeyListener() {
		controller = GameContainer.getInstance().getController();
		System.out.println("Key listener initialized...");
	}

	@Override
	public void KeyPressed(KeyEvent e) {
		System.out.print("Key event detected");
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT) {
			controller.getPlayer();
			controller.getPlayer().move(Player.MOVE_LEFT);
		} else if(key == KeyEvent.VK_RIGHT) {
			controller.getPlayer().move(Player.MOVE_RIGHT); 
		}
	}
}